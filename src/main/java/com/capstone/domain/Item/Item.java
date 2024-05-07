package com.capstone.domain.Item;

import com.capstone.domain.Gender;
import com.capstone.domain.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String company;
    @Column(name = "item_name")
    private String itemName;
    private int price;
    @Column(name = "site_url")
    private String siteUrl;
    @Column(name = "item_gender")
    private Gender itemGender;
    private String image;
    @OneToMany(mappedBy = "item")
    private List<Review> itemReview;
}
