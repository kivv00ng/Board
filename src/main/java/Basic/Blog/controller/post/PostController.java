package Basic.Blog.controller.post;


import Basic.Blog.domain.Post;
import Basic.Blog.repository.PostRepository;
import Basic.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/add")
    public String addPostForm(Model model){
        model.addAttribute("postForm", new PostForm());
        return "posts/addPostForm";
    }

    @PostMapping("/posts/add")
    public String addPost(@ModelAttribute PostForm postForm, BindingResult bindingResult){
        //필드 오류
        if (!StringUtils.hasText(postForm.getTitle())){
            bindingResult.addError(new FieldError("postForm","title", postForm.getTitle(), false, null,null, "제목을 입력하세요."));
        }
        if (!StringUtils.hasText(postForm.getContent())){
            bindingResult.addError(new FieldError("postForm","content", postForm.getContent(), false, null, null,"내용을 입력하세요."));
        }

        if(bindingResult.hasErrors()){
            return "posts/addPostForm";
        }

        //성공 로직
        Post post = Post.CreatePost(postForm.getTitle(), postForm.getContent());

        postService.save(post);

        return "redirect:/";
    }

    @GetMapping("/posts/{postId}")
    public String post(@PathVariable Long postId, Model model){
        Post post = postService.findOne(postId);
        model.addAttribute("post",post);
        return "posts/post";
    }
    @GetMapping("/posts/{postId}/edit")
    public String postUpdateForm(@PathVariable Long postId, Model model){
        Post post = postService.findOne(postId);

        PostForm postForm = new PostForm();

        postForm.setId(post.getId());
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent());

        model.addAttribute("postForm", postForm);

        return "posts/postEditForm";
    }

    @PostMapping("/posts/{postId}/edit")
    public String postUpdate(@PathVariable Long postId, @ModelAttribute("postForm") PostForm postForm){

        postService.updatePost(postId, postForm.getTitle(), postForm.getContent() );
        return "redirect:/posts/{postId}";
    }

}
