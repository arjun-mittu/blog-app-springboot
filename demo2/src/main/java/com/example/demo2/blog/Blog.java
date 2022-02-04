package com.example.demo2.blog;

import com.example.demo2.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;

    private String body;

//    @ManyToOne (fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name="user_id")
//    @JsonIgnore
    private User user;
}
