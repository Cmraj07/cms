package com.cms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {  // one
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 5000)
    private String description;

    @OneToMany(mappedBy ="post", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

}