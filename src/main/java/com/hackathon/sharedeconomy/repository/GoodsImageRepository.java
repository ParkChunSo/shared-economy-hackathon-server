package com.hackathon.sharedeconomy.repository;

import com.hackathon.sharedeconomy.model.entity.GoodsImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsImageRepository extends JpaRepository<GoodsImage, Long> {
    GoodsImage findByFileName(String fileName);
}
