package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dtos.ForSaleDto;
import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.model.entity.Image;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.SaleType;
import com.hackathon.sharedeconomy.repository.ForSaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class ForSaleService {

    private ForSaleRepository forSaleRepository;
    private SignService signService;
    private ImageService imageService;

    public ForSaleService(ForSaleRepository forSaleRepository, SignService signService, ImageService imageService) {
        this.forSaleRepository = forSaleRepository;
        this.signService = signService;
        this.imageService = imageService;
    }

    /*
     * 한 사람당 여러개의 매물 올릴 수 있을 때 사용
     */
    public ForSale findByNameAndUserId(String name, String forSaleUserId) {
        return forSaleRepository.findByNameAndUserId(name, forSaleUserId);
    }

    /*
     * 한 사람당 하나의 매물만 올릴 수 있을 때 사용
     * 현재 판매되고 있는 매물만 찾는다.
     */
    public ForSale findByUserId(String forSaleUserId) {
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

        ForSale forSale = ForSale.builder()
                .name(saveDto.getName())
                .price(saveDto.getPrice())
                .user(user)
                .build();

        for (int i = 0; i < imageList.size(); i++) {
            String writeFileName = user.getId() + "img" + String.valueOf(i);//file 이름 저장 형식 : 중복되지 않기위해 userId + img + 0,1,2..
            String writeFilePath = imageService.convertBase64ToImgFile(imageList.get(i), writeFileName);

            images.add(Image.builder()
                    .path(writeFilePath)
                    .forSale(forSale)
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
    public void updateForSale(ForSaleDto.Save saveDto) {
        ForSale forSale = findByUserId(saveDto.getUserId());
        forSale.updateForSale(saveDto);
        forSaleRepository.save(forSale);
    }

    public List<ForSaleDto.Response> getForSaleResponseDtos(ForSaleDto.Request requestDto) {
        List<ForSaleDto.Response> responseDtos = forSaleRepository.getForSaleResponseDtos(requestDto);
        return convertImgToBase64(responseDtos);
    }

    private List<ForSaleDto.Response> convertImgToBase64(List<ForSaleDto.Response> responseDtos) {

        for (ForSaleDto.Response responseDto : responseDtos) {
            ForSale forSale = responseDto.getForSale();
            List<Image> images = forSale.getImages();

            for (Image image : images) {
                image.updatePath(imageService.convertImgFileToBase64(image.getPath()));
            }
        }

        return responseDtos;
    }

    public void updateSaleType(String userId) {
        ForSale forSale = findByUserId(userId);

        if (forSale.getSaleType() == SaleType.SALE) {
            forSale.updateSaleType();
            forSaleRepository.save(forSale);
        }
    }


}
