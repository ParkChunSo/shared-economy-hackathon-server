package com.hackathon.sharedeconomy.model.dtos;

import com.hackathon.sharedeconomy.model.entity.Goods;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-03-04.
 */

public class ForSaleDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String userId;
        private String address;

        @Builder
        public Request(String userId, String address) {
            this.userId = userId;
            this.address = address;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Goods goods;
        private String userId;
        private String phoneNumber;
        private String address;

        @Builder
        public Response(Goods goods, String userId, String phoneNumber, String address) {
            this.goods = goods;
            this.userId = userId;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Save {
        private Long price;
        private String name;
        private String userId;
        private List<String> imagePath = new ArrayList<>();

        @Builder
        public Save(Long price, String name, String userId, List<String> imagePath) {
            this.price = price;
            this.name = name;
            this.userId = userId;
            this.imagePath = imagePath;
        }
    }

}
