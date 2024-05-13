package com.capstone.service;

import com.capstone.domain.Item.Item;
import com.capstone.dto.ItemDetailDto;
import com.capstone.dto.ItemResponseDto;
import com.capstone.repository.ItemRepository;
import com.capstone.repository.queryrepository.ItemQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemQueryRepository itemQueryRepository;
    private final ItemRepository itemRepository;

    public ItemDetailDto getItemDetails(long itemId) {
        return itemQueryRepository.findItemDetailDtos(itemId);
    }

    public List<ItemResponseDto> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponseDto> result = new ArrayList<>();
        for (Item item : items) {
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setId(item.getId());
        }

        return result;
    }

    public List<ItemResponseDto> searchItem(String content) {
        List<ItemResponseDto> list = new ArrayList<>();
        List<Item> items = itemRepository.findByItemNameContaining(content);
        for (Item item : items) {
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setId(item.getId());
            itemResponseDto.setItemName(item.getItemName());
            itemResponseDto.setCompany(item.getCompany());
            itemResponseDto.setPrice(item.getPrice());
            itemResponseDto.setImage(item.getImage());
            list.add(itemResponseDto);
        }
        return list;
    }
}
