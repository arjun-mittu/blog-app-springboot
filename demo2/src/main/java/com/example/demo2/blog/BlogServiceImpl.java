package com.example.demo2.blog;

import com.example.demo2.domain.Role;
import com.example.demo2.domain.User;
import com.example.demo2.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service @RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs=new ArrayList<>();
        for (Blog blog : blogRepo.findAll()) {
            blogs.add(blog);
        }
        return blogs;
    }

    @Override
    public Blog addBlog(BlogRequest blogRequest, Principal principal) {
        User user=userRepo.findByUsername(principal.getName());
        //User user=userRepo.findByUsername("arj");
        Blog blog=new Blog();
        blog.setBody(blogRequest.getBody());
        blog.setTitle(blogRequest.getTitle());
        blog.setUser(user);
        Blog newblog=blogRepo.save(blog);
        return newblog;

    }

    @Override
    public String deletePost(Long id, Principal principal) {
        User user=userRepo.findByUsername(principal.getName());
        Blog blog=blogRepo.findById(id).get();
        ArrayList<Role> user_roles=new ArrayList<>(user.getRoles());
        ArrayList<String> only_roles=new ArrayList<>();

        for(int i=0;i<user_roles.size();i++){
            only_roles.add(user_roles.get(i).getName());
        }
//        return only_roles;

        if(blog.getUser().getId().equals(user.getId()) || only_roles.contains("ROLE_ADMIN") || only_roles.contains("ROLE_SUPER_ADMIN") ){
            blogRepo.deleteById(id);
            return "successfully deleted";
        }
        return "Error";


    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepo.getById(id);
    }

    @Override
    public Blog updateBlog(Long id, BlogRequest blogRequest, Principal principal) {
        User currentUser=userRepo.findByUsername(principal.getName());
        Blog blog=blogRepo.findById(id).get();
        if(blog.getUser().getId().equals(currentUser.getId())){
            blog.setTitle(blogRequest.getTitle());
            blog.setBody(blogRequest.getBody());
            return blogRepo.save(blog);
        }
        return null;
    }



}
