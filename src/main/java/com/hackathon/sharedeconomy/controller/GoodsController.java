package com.hackathon.sharedeconomy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.sharedeconomy.model.dtos.ForSaleDto;
import com.hackathon.sharedeconomy.model.dtos.goods.GoodsResponse;
import com.hackathon.sharedeconomy.model.dtos.goods.GoodsListResponse;
import com.hackathon.sharedeconomy.model.dtos.goods.GoodsSaveRequest;
import com.hackathon.sharedeconomy.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(description = "매물 관련 API")
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    //TODO: 지도 API에서 매물 위치 정보를 주는 방식의 따라 검색 Api가 달라질 수 있음.

    private GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation(value = "사용자 주소에 해당하는 매물 목록 리스트")
    @GetMapping
    public List<GoodsListResponse> getGoodsByUserInfo(@RequestHeader(name = "Authorization") String token) {
        return goodsService.getGoodsByUserInfo(token);
    }

    @ApiOperation(value = "시, 군, 구를 통한 매물 목록 리스트")
    @ApiImplicitParam(name = "id", value = "사용자 id" , dataType = "string", paramType = "path")
    @GetMapping("/city/{city}/gu/{gu}/dong/{dong}")
    public List<GoodsListResponse> getGoodsByAddress(@PathVariable String city, @PathVariable String gu, @PathVariable String dong){
        return goodsService.getGoodsByAddress(city,gu,dong);
    }


//    @ApiOperation(value = "주소 검색시에 나오는 매물 목록 리스트")
//    @ApiImplicitParam(name = "address", value = "검색한 주소" , dataType = "string", paramType = "path")
//    @GetMapping("/address/{address}")
//    public List<GoodsListResponse> getForSaleListByRegion(@PathVariable("address") String address) {
//        ForSaleDto.Request requestDto = ForSaleDto.Request.builder()
//                .address(address)
//                .build();
//
//        return goodsService.getGoodsByUserInfo(requestDto);
//    }

    @GetMapping("/seller/{seller}/title/{title}")
    public GoodsResponse getGoodsByTitleAndSellerId(@PathVariable(name = "title") String title, @PathVariable(name = "seller") String sellerId){
        return goodsService.getGoodsByTitleAndSellerId(title, sellerId);
    }

    @ApiOperation(value = "매물 저장")
    @PostMapping("/save")
    public ResponseEntity<?> saveGoods(@RequestPart MultipartFile[] files, @RequestParam String json) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        GoodsSaveRequest dto = objectMapper.readValue(json, GoodsSaveRequest.class);
        goodsService.saveGoods(files, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/image/{image}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getGoodsImage(@PathVariable String image) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(goodsService.getImageResource(image));
    }



    @ApiOperation(value = "매물 정보 등록(저장)")
    @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto")
    @PostMapping
    public void saveForSale(@RequestBody ForSaleDto.Save saveDto) {
        goodsService.saveForSale(saveDto);
    }


    @ApiOperation(value = "매물 정보 수정(매물가격 또는 이름 수정, 이미지 수정 보류)")
    @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto")
    @PutMapping
    public void updateForSale(@RequestBody ForSaleDto.Save saveDto) {
        goodsService.updateGoods(saveDto);
    }


    @ApiOperation(value = "해당 유저의 매물 판매완료로 변경")
    @PutMapping("/id/{id}")
    public void changeSaleType(@PathVariable("id") String userId) {
        goodsService.updateSaleType(userId);
    }




   /* @ApiOperation(value = "매물 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto"),
    })
    @PutMapping
    public void updateGoods(@RequestBody ForSaleSaveDto forSaleSaveDto) {

    }*/
}
