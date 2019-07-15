package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dtos.ForSaleDto;
import com.hackathon.sharedeconomy.model.dtos.goods.GoodsResponse;
import com.hackathon.sharedeconomy.model.dtos.goods.GoodsSaveRequest;
import com.hackathon.sharedeconomy.model.entity.Goods;
import com.hackathon.sharedeconomy.model.dtos.goods.GoodsListResponse;
import com.hackathon.sharedeconomy.model.entity.GoodsImage;
import com.hackathon.sharedeconomy.model.entity.Image;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.SaleType;
import com.hackathon.sharedeconomy.repository.ForSaleRepository;
import com.hackathon.sharedeconomy.repository.GoodsImageRepository;
import com.hackathon.sharedeconomy.repository.ImageRepository;
import com.hackathon.sharedeconomy.utill.ImageUtils;
import com.hackathon.sharedeconomy.utill.JwtUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class GoodsService {

    private static final String UPLOAD_PATH = "/usr/local/tomcat-8.0.53/webapps/imgfile/";
    private ForSaleRepository forSaleRepository;
    private SignService signService;
    private ImageService imageService;
    private final GoodsImageRepository imageRepository;

    public GoodsService(ForSaleRepository forSaleRepository, SignService signService, ImageService imageService, GoodsImageRepository imageRepository) {
        this.forSaleRepository = forSaleRepository;
        this.signService = signService;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    public List<GoodsListResponse> getGoodsByUserInfo(String token) {
        String userId = JwtUtils.getUserIdByToken(
                JwtUtils.resolveToken(token)
        );
        return null;
//        List<ForSaleDto.Response> responseDtos = forSaleRepository.getForSaleResponseDtos(requestDto);
//        return convertImgToBase64(responseDtos);
    }

    public List<GoodsListResponse> getGoodsByAddress(String city, String gu, String dong) {

        return null;
    }

    public GoodsResponse getGoodsByTitleAndSellerId(String title, String sellerId) {

        return null;
    }
    /*
     * 한 사람당 여러개의 매물 올릴 수 있을 때 사용
     */

    public Goods findByNameAndUserId(String name, String forSaleUserId) {
        return forSaleRepository.findByNameAndUserId(name, forSaleUserId);
    }
    /*
     * 한 사람당 하나의 매물만 올릴 수 있을 때 사용
     * 현재 판매되고 있는 매물만 찾는다.
     */

    public Goods findByUserId(String forSaleUserId) {
        return forSaleRepository.findByUserId(forSaleUserId);
    }

    /*
     * 연관 관계에 의해 imageService의 사진이 저장되면서 forSale정보가 등록된다.
     */
    public void saveForSale(ForSaleDto.Save saveDto) {
        if (!isEmptyForSaleByUserId(saveDto.getUserId())) {
            throw new UserDefineException("해당 유저의 매물이 등록되어 있습니다.");
        }

        User user = signService.findById(saveDto.getUserId());
        List<String> imageList = saveDto.getImagePath();
        List<Image> images = new ArrayList<>();

        Goods goods = Goods.builder()
                .title(saveDto.getName())
                .monthlyRent(saveDto.getPrice())
                .user(user)
                .build();

        for (int i = 0; i < imageList.size(); i++) {
            String writeFileName = user.getId() + "img" + String.valueOf(i);//file 이름 저장 형식 : 중복되지 않기위해 userId + img + 0,1,2..
            String writeFilePath = imageService.convertBase64ToImgFile(imageList.get(i), writeFileName);

            images.add(Image.builder()
                    .path(writeFilePath)
                    .goods(goods)
                    .build());
        }

        imageService.saveAll(images);
    }

    private Boolean isEmptyForSaleByUserId(String forSaleUserId) {
        return ObjectUtils.isEmpty(findByUserId(forSaleUserId));
    }

    /*
     * 이미지 수정 보류
     */
    public void updateGoods(ForSaleDto.Save saveDto) {
        Goods goods = findByUserId(saveDto.getUserId());
        goods.updateForSale(saveDto);
        forSaleRepository.save(goods);
    }

    public void updateSaleType(String userId) {
        Goods goods = findByUserId(userId);

        if (goods.getSaleType() == SaleType.SALE) {
            goods.updateSaleType();
            forSaleRepository.save(goods);
        }
    }

    public void  saveGoods(MultipartFile[] files, GoodsSaveRequest dto) throws Exception {
        List<GoodsImage> images = new ArrayList<>();

        for(MultipartFile file : files){
            GoodsImage goodsImage = ImageUtils.uploadFile(UPLOAD_PATH, file);
            images.add(goodsImage);
        }

        forSaleRepository.save(dto.ToEntity(images));
    }

    public byte[] getImageResource(String fileName) throws IOException {
        GoodsImage goodsImage = imageRepository.findByFileName(fileName);

        byte[] result;

        File file = new File(goodsImage.getPath());

        InputStream in = new FileInputStream(file);

        result = IOUtils.toByteArray(in);

        return result;
    }
}
