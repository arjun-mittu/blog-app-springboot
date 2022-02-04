package com.example.demo2.blog;

import com.example.demo2.domain.Role;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public interface BlogService {
    public List<Blog> getAllBlogs();
    public Blog addBlog(BlogRequest blogRequest,Principal principal);
    public String deletePost(Long id, Principal principal);
    public Blog getBlog(Long id);
    public Blog updateBlog(Long id,BlogRequest blogRequest,Principal principal);
}
