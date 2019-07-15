package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hackathon.sharedeconomy.model.enums.ImageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GoodsImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String path;

    private String uri;

    private ImageType type;

    @Builder
    public GoodsImage(String fileName, String path, String uri, ImageType type) {
        this.fileName = fileName;
        this.path = path;
        this.uri = uri;
        this.type = type;
    }
    //    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonBackReference
//    private Goods goods;
}
