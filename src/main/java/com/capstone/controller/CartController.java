package com.capstone.controller;

import com.capstone.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    @PostMapping("/addCart/{id}")
    public ResponseEntity<Void> addCart(@PathVariable Long id, HttpServletRequest request){
        cartService.addCart(id, request.getSession(false));
        return ResponseEntity.ok().build();
    }


}
