package com.capstone.dto;

import com.capstone.domain.Review;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ItemDetailDto {

    private String company;
    private String image;
    private String itemName;
    private int price;
    private List<ItemReviewDto> itemReview;

    public ItemDetailDto(String company, String image, String itemName, int price) {
        this.company = company;
        this.image = image;
        this.itemName = itemName;
        this.price = price;
    }

    public ItemDetailDto(String company, String image, String itemName, int price, List<ItemReviewDto> itemReview) {
        this.company = company;
        this.image = image;
        this.itemName = itemName;
        this.price = price;
        this.itemReview = itemReview;
    }
}
