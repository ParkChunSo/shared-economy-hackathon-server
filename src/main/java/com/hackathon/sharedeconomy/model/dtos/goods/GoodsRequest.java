package com.hackathon.sharedeconomy.model.dtos.goods;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodsRequest {
    private String title;
    private String seller;
}
