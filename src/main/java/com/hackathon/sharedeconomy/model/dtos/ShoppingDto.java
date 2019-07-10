package com.hackathon.sharedeconomy.model.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-03-05.
 */

public class ShoppingDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Save {
        private String userId;
        private String forSaleUserId;

        @Builder
        public Save(String userId, String forSaleUserId) {
            this.userId = userId;
            this.forSaleUserId = forSaleUserId;
        }
    }
}
