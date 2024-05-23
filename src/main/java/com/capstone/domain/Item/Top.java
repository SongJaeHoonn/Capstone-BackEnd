package com.capstone.domain.Item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("T")
@Getter
@Setter
public class Top extends Item{
    private double length;          //총장
    private double shoulderWidth;   //어깨너비
    private double chestWidth;      //가슴너비
    private double sleeveLength;    //소매길이
}
