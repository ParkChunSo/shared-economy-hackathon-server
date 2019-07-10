package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dtos.ShoppingDto;
import com.hackathon.sharedeconomy.service.ShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YoungMan on 2019-02-15.
 */

@Api(description = "찜(장바구니) 관련 API")
@RestController
@RequestMapping(value = "/shared/shopping")
public class ShoppingController {

    private ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @ApiOperation(value = "찜한 매물 등록")
    @ApiImplicitParam(name = "shoppingSaveDto", dataType = "ShoppingSaveDto")
    @PostMapping
    public void saveShopping(@RequestBody ShoppingDto.Save saveDto) {
        shoppingService.saveShopping(saveDto);
    }
}
