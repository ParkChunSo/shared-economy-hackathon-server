package com.hackathon.sharedeconomy.model.enums;

/**
 * Created by YoungMan on 2019-02-14.
 */

public enum SaleType {
    WAIT("대기중"),
    SALE("판매중"),
    PROCESSING("처리중"),
    COMPLETE("판매완료")
    ;

    private String saleExplain;

    SaleType(String saleExplain) {
        this.saleExplain = saleExplain;
    }

    private String getSaleExplain() {
        return saleExplain;
    }

    public static SaleType convertSaleType(String role) {
        if (role.matches(WAIT.getSaleExplain())){
            return WAIT;
        }
        else if (role.matches(SALE.getSaleExplain())) {
            return SALE;
        }
        else if(role.matches(PROCESSING.getSaleExplain())) {
            return PROCESSING;
        }
        else if(role.matches(COMPLETE.getSaleExplain())){
            return COMPLETE;
        }
        return null;
    }
}
