package com.capstone.controller;

import com.capstone.domain.MemberSize;
import com.capstone.dto.MemberSizeDto;
import com.capstone.service.MemberSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberSizeController {

    @Autowired
    private MemberSizeService memberSizeService;

    @PostMapping("/{memberId}/sizes")
    public MemberSize saveMemberSize(@PathVariable Long memberId, @RequestBody MemberSizeDto memberSizeDto) {
        return memberSizeService.saveMemberSize(
                memberId,
                memberSizeDto.getSleeveLength(),
                memberSizeDto.getShoulderWidth(),
                memberSizeDto.getChestCrossSection(),
                memberSizeDto.getTopLength(),
                memberSizeDto.getBottomLength(),
                memberSizeDto.getWaistWidth()
        );
    }
}
