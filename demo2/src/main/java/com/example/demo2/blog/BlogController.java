package com.example.demo2.blog;

import com.example.demo2.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/getcurrentuser")
    public ResponseEntity<String> currentUsername(Principal principal){

        return ResponseEntity.ok().body(principal.getName());
    }

    @PostMapping("/save")
    public ResponseEntity<Blog> saveBlog(@RequestBody BlogRequest blogRequest,Principal principal){
        Blog blog=blogService.addBlog(blogRequest,principal);
        return  ResponseEntity.ok().body(blog);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs(){
        List<Blog> blogs=blogService.getAllBlogs();
        return ResponseEntity.ok().body(blogs);
    }


    @DeleteMapping  ("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable(name="id") Long id, Principal principal){
        return ResponseEntity.ok().body(blogService.deletePost(id,principal));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable(name="id") Long id,@RequestBody BlogRequest blogRequest,Principal principal){
        Blog blog=blogService.updateBlog(id,blogRequest,principal);
        if(blog==null) return ResponseEntity.internalServerError().body(null);
        return ResponseEntity.ok().body(blog);
    }

//    @PutMapping("/update/{id}")
//    public String  updateBlog(@PathVariable(name="id") Long id,Principal principal){
//        return blogService.deletePost(id,principal);
//    }

}
