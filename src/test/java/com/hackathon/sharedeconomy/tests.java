package com.hackathon.sharedeconomy;

import com.hackathon.sharedeconomy.service.ForSaleService;
import com.hackathon.sharedeconomy.service.SignService;
import com.hackathon.sharedeconomy.service.ShoppingService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class tests {

    @Autowired
    private ForSaleService forSaleService;

    @Autowired
    private SignService signService;

    @Autowired
    private ShoppingService shoppingService;

   /* @Test
    public void saveForSale() {
        String userId = "testid3";
        User user = loginService.findById(userId);
        System.out.println(user.getAddress());

        List<String> imgs = new ArrayList<>();
        imgs.add("img1");
        imgs.add("img2");

        ForSaleSaveDto forSaleSaveDto = ForSaleSaveDto.builder()
                .name("매물2")
                .price(10000L)
                .userId(userId)
                .imagePath(imgs)
                .build();

        forSaleService.saveForSale(forSaleSaveDto);
    }*/



/*@Test
    public void saveShopping() {
        ShoppingSaveDto shoppingSaveDto = ShoppingSaveDto.builder()
                .userId("testid")
                .build();

        shoppingService.saveShopping(shoppingSaveDto);
    }*/


    /*@Test
    public void getForSaleResponse() {
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .userId("testid")
                .build();

        *//*ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .address("중계1동")
                .build();*//*


        forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }*/

    /*@Test
    public void findByNameAndUserId() {
        ForSale forSale = forSaleService.findByNameAndUserId("매물", "testid");
        System.out.println(forSale.getUser().getId());
    }*/


/*@Autowired
    private ImageService imageService;
    private final String WINDOW_PATH = "C:\\testimg\\";


@Test
    public void imageEncodingTest() {
        String strBase64 = imageService.convertImgFileToBase64("test.png");
        System.out.println("=====================");
        System.out.println(strBase64);
    }

    @Test
    public void imageDecodingTest() {
        String strBase64 = imageService.convertImgFileToBase64("test.png");
//        imageService.convertBase64ToImgFile(strBase64);
    }*/


}
