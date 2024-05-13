package com.capstone.repository.queryrepository;

import com.capstone.domain.Gender;
import com.capstone.domain.Item.Item;
import com.capstone.dto.ItemDetailDto;
import com.capstone.dto.ItemResponseDto;
import com.capstone.dto.ItemReviewDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

    public ItemDetailDto findItemDetails(Long itemId) {
        return em.createQuery(
                        "select new com.capstone.dto.ItemDetailDto(i.company, i.image, i.itemName, i.price, i.siteUrl)" +
                                " from Item i" +
                                " where i.id = :itemId", ItemDetailDto.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    public List<ItemReviewDto> findItemReview(Long itemId) {
        return em.createQuery(
                        "select new com.capstone.dto.ItemReviewDto(m.username ,r.title, r.content, r.star)" +
                                " from Review r" +
                                " join r.member m" +
                                " where r.item.id = :itemId", ItemReviewDto.class)
                .setParameter("itemId", itemId)
                .getResultList();

    }



    public List<ItemResponseDto> findTopCategory(){
        return em.createQuery(
                "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                        " from Item i" +
                        " where TYPE(i) = Top", ItemResponseDto.class)
                .getResultList();
    }

    public List<ItemResponseDto> findBottomCategory(){
        return em.createQuery(
                "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                        " from Item i" +
                        " where TYPE(i) = Bottom", ItemResponseDto.class)
                .getResultList();
    }

    public List<ItemResponseDto> findManCategory(){
        return em.createQuery(
                        "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                                " from Item i" +
                                " where i.itemGender = :gender", ItemResponseDto.class)
                .setParameter("gender", Gender.MALE)
                .getResultList();
    }

    public List<ItemResponseDto> findWomenCategory(){
        return em.createQuery(
                        "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                                " from Item i" +
                                " where i.itemGender = :gender", ItemResponseDto.class)
                .setParameter("gender", Gender.FEMALE)
                .getResultList();
    }

    public List<ItemResponseDto> findSearchedItems(String content) {
        return em.createQuery(
                        "SELECT NEW com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                                " FROM Item i" +
                                " WHERE i.itemName LIKE :content OR i.company LIKE :content", ItemResponseDto.class)
                .setParameter("content", '%'+content+'%')
                .getResultList();
    }
}
