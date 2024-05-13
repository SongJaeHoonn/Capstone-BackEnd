package com.capstone.controller;

import com.capstone.domain.Item.Item;
import com.capstone.dto.ItemResponseDto;
import com.capstone.repository.ItemRepository;
import com.capstone.repository.queryrepository.ItemQueryRepository;
import com.capstone.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final ItemQueryRepository itemQueryRepository;
    private final ItemService itemService;

    @GetMapping("/man")
    public List<ItemResponseDto> findManCategory(){
        return itemQueryRepository.findManCategory();
    }

    @GetMapping("/women")
    public List<ItemResponseDto> findWomenCategory(){
        return itemQueryRepository.findWomenCategory();
    }

    @GetMapping("/top")
    public List<ItemResponseDto> findTopCategory(){
        return itemQueryRepository.findTopCategory();
    }

    @GetMapping("/bottom")
    public List<ItemResponseDto> findBottomCategory(){
        return itemQueryRepository.findBottomCategory();
    }

    @GetMapping("/search")
    public List<ItemResponseDto> search(@RequestParam("content") String content) {
        return itemQueryRepository.findSearchedItems(content);
    }
}
