package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dtos.ForSaleDto;
import com.hackathon.sharedeconomy.service.ForSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "매물 관련 API")
@RestController
@RequestMapping(value = "/shared/forsale")
public class ForSaleController {

    private ForSaleService forSaleService;

    public ForSaleController(ForSaleService forSaleService) {
        this.forSaleService = forSaleService;
    }

    @ApiOperation(value = "로그인 후 매물 페이지, 로그인 한 사용자 주소에 해당하는 매물 목록 리스트")
    @ApiImplicitParam(name = "id", value = "사용자 id" , dataType = "string", paramType = "path")
    @GetMapping("/id/{id}")
    public List<ForSaleDto.Response> getForSaleById(@PathVariable("id") String userId) {
        ForSaleDto.Request requestDto = ForSaleDto.Request.builder()
                .userId(userId)
                .build();

        return forSaleService.getForSaleResponseDtos(requestDto);
    }


    @ApiOperation(value = "주소 검색시에 나오는 매물 목록 리스트")
    @ApiImplicitParam(name = "address", value = "검색한 주소" , dataType = "string", paramType = "path")
    @GetMapping("/address/{address}")
    public List<ForSaleDto.Response> getForSaleListByRegion(@PathVariable("address") String address) {
        ForSaleDto.Request requestDto = ForSaleDto.Request.builder()
                .address(address)
                .build();

        return forSaleService.getForSaleResponseDtos(requestDto);
    }


    @ApiOperation(value = "매물 정보 등록(저장)")
    @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto")
    @PostMapping
    public void saveForSale(@RequestBody ForSaleDto.Save saveDto) {
        forSaleService.saveForSale(saveDto);
    }


    @ApiOperation(value = "매물 정보 수정(매물가격 또는 이름 수정, 이미지 수정 보류)")
    @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto")
    @PutMapping
    public void updateForSale(@RequestBody ForSaleDto.Save saveDto) {
        forSaleService.updateForSale(saveDto);
    }


    @ApiOperation(value = "해당 유저의 매물 판매완료로 변경")
    @PutMapping("/id/{id}")
    public void changeSaleType(@PathVariable("id") String userId) {
        forSaleService.updateSaleType(userId);
    }




   /* @ApiOperation(value = "매물 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto"),
    })
    @PutMapping
    public void updateForSale(@RequestBody ForSaleSaveDto forSaleSaveDto) {

    }*/
}
