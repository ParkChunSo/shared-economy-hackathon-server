package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Entity
@Table(name = "image_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Goods goods;

    @Builder
    public Image(String path, Goods goods) {
        this.path = path;
        this.goods = goods;
    }

    public void updatePath(String path) {
        this.path = path;
    }
}
