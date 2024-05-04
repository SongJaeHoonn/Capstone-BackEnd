package com.capstone.domain.Item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Bottom extends Item{
    private double length;          //총장
    private double waistCircum;     //허리단면
    private double hipCircum;       //엉덩이단면
    private double thighCircum;     //허벅지단면
    private double inseam;          //밑위
    private double legOpeningCircum;//밑단단면
}
