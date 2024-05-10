package com.capstone.service;

import com.capstone.domain.Cart;
import com.capstone.domain.CartItem;
import com.capstone.domain.Item.Item;
import com.capstone.domain.Member;
import com.capstone.dto.MemberSessionDto;
import com.capstone.repository.CartRepository;
import com.capstone.repository.ItemRepository;
import com.capstone.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addCart(Long itemId, HttpSession session) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));
        Cart cart = getCurrentCart(session);

        CartItem cartItem = CartItem.createCartItem(cart, item);
        cart.addCartItem(cartItem);

        cartRepository.save(cart);
    }

    private Cart getCurrentCart(HttpSession session) {
        MemberSessionDto getSession = (MemberSessionDto) session.getAttribute("SESSION_KEY");
        Member member = memberRepository.findById(getSession.getId()).orElseThrow(() -> new IllegalArgumentException("No Cart"));
        return member.getCart();
    }


}
