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
    private ForSaleService forSaleService;

    public ShoppingService(ShoppingRepository shoppingRepository, SignService signService, ForSaleService forSaleService) {
        this.shoppingRepository = shoppingRepository;
        this.signService = signService;
        this.forSaleService = forSaleService;
    }

    public void saveShopping(ShoppingDto.Save saveDto) {
        Shopping shopping = Shopping.builder()
                .forSale(forSaleService.findByUserId(saveDto.getForSaleUserId()))
                .user(signService.findById(saveDto.getUserId()))
                .build();

        shoppingRepository.save(shopping);
    }
}
