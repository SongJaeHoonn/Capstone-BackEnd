package com.capstone;

import com.capstone.domain.Gender;
import com.capstone.domain.Item.Bottom;
import com.capstone.domain.Item.Item;
import com.capstone.domain.Item.Top;
import com.capstone.domain.Member;
import com.capstone.domain.Review;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.initDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        public void initDB(){
            // Create sample Member
            Member member = new Member();
            member.setEmail("testMember@google.com");
            member.setPassword("mypassword");
            member.setUsername("홍길동");
            member.setAge(25);
            member.setGender(Gender.MALE);
            em.persist(member);

            // Create sample Bottom
            Bottom bottom = new Bottom();
            bottom.setCompany("BottomCompany");
            bottom.setItemName("이쁜청바지");
            bottom.setPrice(65000);
            bottom.setSiteUrl("https://bottomurl.com");
            bottom.setItemGender(Gender.MALE);
            bottom.setImage("bottom_image_url");
            bottom.setLength(100.0);
            bottom.setWaistCircum(80.0);
            bottom.setHipCircum(100.0);
            bottom.setThighCircum(60.0);
            bottom.setInseam(75.0);
            bottom.setLegOpeningCircum(40.0);
            em.persist(bottom);

            // Create sample Top
            Top top = new Top();
            top.setCompany("TopCompany");
            top.setItemName("이쁜바람막이");
            top.setPrice(127000);
            top.setSiteUrl("https://topurl.com");
            top.setItemGender(Gender.FEMALE);
            top.setImage("top_image_url");
            top.setLength(70.0);
            top.setShoulderWidth(40.0);
            top.setChestWidth(90.0);
            top.setSleeveLength(60.0);
            em.persist(top);

            // Create sample Reviews

            Review review1 = new Review();
            review1.setTitle("미친듯한 청바지");
            review1.setContent("청바지 너무 이뻐요 ㅠㅠ");
            review1.setMember(member);
            review1.setStar(5);
            review1.setItem(bottom);
            em.persist(review1);
            Review review2 = new Review();
            review2.setTitle("아쉬운 바람막이");
            review2.setContent("실물이 왜이래요");
            review2.setMember(member);
            review2.setStar(2);
            review2.setItem(top);
            em.persist(review2);

            List<Review> itemReviewBottom = new ArrayList<>();
            itemReviewBottom.add(review1);
            List<Review> itemReviewTop = new ArrayList<>();
            itemReviewTop.add(review2);
            bottom.setItemReview(itemReviewBottom);
            top.setItemReview(itemReviewTop);

        }
    }
}
