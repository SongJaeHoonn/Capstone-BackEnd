package com.capstone.domain;

import com.capstone.domain.Item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String username;

    private Gender gender;

    private int age;

    @JsonIgnore
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}