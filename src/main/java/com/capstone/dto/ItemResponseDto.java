package com.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponseDto {

    private Long id;
    private String image;
    private String itemName;
    private int price;
    private String company;


}
