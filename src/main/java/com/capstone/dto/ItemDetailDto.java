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
    private String siteUrl;
    private List<ItemReviewDto> itemReview;

    public ItemDetailDto(String company, String image, String itemName, int price, String siteUrl) {
        this.company = company;
        this.image = image;
        this.itemName = itemName;
        this.price = price;
        this.siteUrl = siteUrl;
    }

    public ItemDetailDto(String company, String image, String itemName, int price, String siteUrl, List<ItemReviewDto> itemReview) {
        this.company = company;
        this.image = image;
        this.itemName = itemName;
        this.price = price;
        this.siteUrl = siteUrl;
        this.itemReview = itemReview;
    }
}
