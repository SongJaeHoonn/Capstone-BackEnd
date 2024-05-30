package com.capstone.controller;

import com.capstone.dto.ItemDetailDto;
import com.capstone.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/members/{memberId}/recommend")
    public List<ItemDetailDto> getRecommendedItem(@PathVariable Long memberId) {
        return recommendService.getRecommendedItem(memberId);
    }
}
