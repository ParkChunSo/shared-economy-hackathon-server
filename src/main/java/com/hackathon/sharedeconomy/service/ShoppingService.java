package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.model.dtos.ShoppingDto;
import com.hackathon.sharedeconomy.model.entity.Shopping;
import com.hackathon.sharedeconomy.repository.ShoppingRepository;
import org.springframework.stereotype.Service;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class ShoppingService {

    private ShoppingRepository shoppingRepository;
    private SignService signService;
    private GoodsService goodsService;

    public ShoppingService(ShoppingRepository shoppingRepository, SignService signService, GoodsService goodsService) {
        this.shoppingRepository = shoppingRepository;
        this.signService = signService;
        this.goodsService = goodsService;
    }

    public void saveShopping(ShoppingDto.Save saveDto) {
        Shopping shopping = Shopping.builder()
                .goods(goodsService.findByUserId(saveDto.getForSaleUserId()))
                .user(signService.findById(saveDto.getUserId()))
                .build();

        shoppingRepository.save(shopping);
    }
}
