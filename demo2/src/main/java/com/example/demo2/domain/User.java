package com.example.demo2.domain;

import com.example.demo2.blog.Blog;
import lombok.*;
import org.springframework.core.SpringVersion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private List<Blog> blogs;
}
