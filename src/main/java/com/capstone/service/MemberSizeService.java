package com.capstone.service;

import com.capstone.domain.Member;
import com.capstone.domain.MemberSize;
import com.capstone.repository.MemberRepository;
import com.capstone.repository.MemberSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberSizeService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberSizeRepository memberSizeRepository;

    public MemberSize saveMemberSize(Long memberId, double sleeveLength, double shoulderWidth, double chestCrossSection,
                                     double topLength, double bottomLength, double waistWidth) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));

        MemberSize memberSize = MemberSize.builder()
                .member(member)
                .sleeveLength(sleeveLength)
                .shoulderWidth(shoulderWidth)
                .chestCrossSection(chestCrossSection)
                .topLength(topLength)
                .bottomLength(bottomLength)
                .waistWidth(waistWidth)
                .build();

        return memberSizeRepository.save(memberSize);
    }
}
