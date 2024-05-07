package com.capstone.controller;

import com.capstone.dto.ItemDetailDto;
import com.capstone.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemDetailController {

    private final ItemService itemService;

    @GetMapping("/itemDetail/{id}")
    public ItemDetailDto itemDetail(@PathVariable long id){
        return itemService.getItemDetails(id);
    }
}
