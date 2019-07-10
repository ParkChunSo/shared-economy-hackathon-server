package com.hackathon.sharedeconomy.repository.custom;

import com.hackathon.sharedeconomy.model.dtos.ForSaleDto;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-23.
 */

public interface ForSaleRepositoryCustom {

    List<ForSaleDto.Response> getForSaleResponseDtos(ForSaleDto.Request requestDto);
}
