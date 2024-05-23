package com.capstone.repository.queryrepository;

import com.capstone.domain.Gender;
import com.capstone.domain.Item.Item;
import com.capstone.dto.ItemDetailDto;
import com.capstone.dto.ItemResponseDto;
import com.capstone.dto.ItemReviewDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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



    public Page<ItemResponseDto> findTopCategory(PageRequest pageRequest){
        String queryStr = "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                " from Item i" +
                " where TYPE(i) = Top";
        TypedQuery<ItemResponseDto> query = em.createQuery(queryStr, ItemResponseDto.class);

        String countQueryStr = "select count(i) from Item i" +
                " where TYPE(i) = Top";
        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        long total = countQuery.getSingleResult();

        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        List<ItemResponseDto> items = query.getResultList();

        return new PageImpl<>(items, pageRequest, total);
    }

    public Page<ItemResponseDto> findBottomCategory(PageRequest pageRequest){

        String queryStr = "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                " from Item i" +
                " where TYPE(i) = Bottom";
        TypedQuery<ItemResponseDto> query = em.createQuery(queryStr, ItemResponseDto.class);

        String countQueryStr = "select count(i) from Item i" +
                " where TYPE(i) = Bottom";
        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        long total = countQuery.getSingleResult();

        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        List<ItemResponseDto> items = query.getResultList();

        return new PageImpl<>(items, pageRequest, total);
    }

    public Page<ItemResponseDto> findManCategory(PageRequest pageRequest){
        String queryStr = "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                " from Item i" +
                " where i.itemGender = :gender";
        TypedQuery<ItemResponseDto> query = em.createQuery(queryStr, ItemResponseDto.class);
        query.setParameter("gender", Gender.MALE);

        String countQueryStr = "select count(i) from Item i" +
                " where i.itemGender = :gender";
        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        countQuery.setParameter("gender", Gender.MALE);
        long total = countQuery.getSingleResult();

        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        List<ItemResponseDto> items = query.getResultList();

        return new PageImpl<>(items, pageRequest, total);
    }

    public Page<ItemResponseDto> findWomenCategory(PageRequest pageRequest){

        String queryStr = "select new com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                          " from Item i" +
                          " where i.itemGender = :gender";
        TypedQuery<ItemResponseDto> query = em.createQuery(queryStr, ItemResponseDto.class);
        query.setParameter("gender", Gender.FEMALE);

        String countQueryStr = "select count(i) from Item i" +
                " where i.itemGender = :gender";
        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        countQuery.setParameter("gender", Gender.FEMALE);
        long total = countQuery.getSingleResult();

        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        List<ItemResponseDto> items = query.getResultList();

        return new PageImpl<>(items, pageRequest, total);
    }

    public Page<ItemResponseDto> findSearchedItems(String content, PageRequest pageRequest) {
        String queryStr = "SELECT NEW com.capstone.dto.ItemResponseDto(i.id, i.image, i.itemName, i.price, i.company)" +
                " FROM Item i" +
                " WHERE i.itemName LIKE :content OR i.company LIKE :content";

        TypedQuery<ItemResponseDto> query = em.createQuery(queryStr, ItemResponseDto.class);
        query.setParameter("content", '%' + content + '%');

        // 총 개수 조회를 위한 쿼리
        String countQueryStr = "SELECT COUNT(i) FROM Item i" +
                " WHERE i.itemName LIKE :content OR i.company LIKE :content";
        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        countQuery.setParameter("content", '%' + content + '%');
        long total = countQuery.getSingleResult();

        // 페이징 매개변수 설정
        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        // 결과 리스트 가져오기
        List<ItemResponseDto> items = query.getResultList();

        // Page 객체 생성 및 반환
        return new PageImpl<>(items, pageRequest, total);
    }
}
