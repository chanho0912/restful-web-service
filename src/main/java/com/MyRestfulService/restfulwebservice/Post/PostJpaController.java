package com.MyRestfulService.restfulwebservice.Post;

import com.MyRestfulService.restfulwebservice.Account.Account;
import com.MyRestfulService.restfulwebservice.Account.AccountRepository;
import com.MyRestfulService.restfulwebservice.Account.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostJpaController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/account/{id}/posts")
    public List<Post> retrieveAllPostsByAccount(@PathVariable int id) {
        Optional<Account> user = accountRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(String.format("ID[%s]is not found", id));
        }
        return user.get().getPosts();
    }

    @PostMapping("/account/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<Account> user = accountRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(String.format("ID[%s]is not found", id));
        }
        post.setAccount(user.get());
        Post newPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
