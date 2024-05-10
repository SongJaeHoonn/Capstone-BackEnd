package com.capstone.repository.queryrepository;

import com.capstone.dto.ItemDetailDto;
import com.capstone.dto.ItemReviewDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemQueryRepository {

    private final EntityManager em;

    public ItemDetailDto findItemDetailDtos(Long itemId){
        ItemDetailDto result = findItemDetails(itemId);

        List<ItemReviewDto> itemReview = findItemReview(itemId);
        result.setItemReview(itemReview);

        return result;
    }

    private ItemDetailDto findItemDetails(Long itemId) {
        return em.createQuery(
                        "select new com.capstone.dto.ItemDetailDto(i.company, i.image, i.itemName, i.price, i.siteUrl)" +
                                " from Item i" +
                                " where i.id = :itemId", ItemDetailDto.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    private List<ItemReviewDto> findItemReview(Long itemId) {
        return em.createQuery(
                        "select new com.capstone.dto.ItemReviewDto(m.username ,r.title, r.content, r.star)" +
                                " from Review r" +
                                " join r.member m" +
                                " where r.item.id = :itemId", ItemReviewDto.class)
                .setParameter("itemId", itemId)
                .getResultList();

    }
}
