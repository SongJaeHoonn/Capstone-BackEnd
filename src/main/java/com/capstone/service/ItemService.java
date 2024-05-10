package com.capstone.service;

import com.capstone.domain.Item.Item;
import com.capstone.dto.ItemDetailDto;
import com.capstone.repository.ItemRepository;
import com.capstone.repository.queryrepository.ItemQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemQueryRepository itemQueryRepository;

    public ItemDetailDto getItemDetails(long itemId) {
        return itemQueryRepository.findItemDetailDtos(itemId);
    }
}
