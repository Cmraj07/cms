package com.cms.controller;

import com.cms.entity.Post;
import com.cms.repository.CommentRepository;
import com.cms.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public String createPost(
            @RequestBody Post post
    ) {
        postRepository.save(post);
        return "Post created successfully!";
      }
      @DeleteMapping
    public void deletePost(){
          postRepository.deleteById(1L);
      }

}
