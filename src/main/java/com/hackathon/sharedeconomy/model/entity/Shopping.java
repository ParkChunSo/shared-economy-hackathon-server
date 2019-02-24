package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Getter
@Setter
@Entity
@Table(name = "shopping_tbl")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private ForSale forSale;

    public Shopping() {
    }

    @Builder
    public Shopping(User user, ForSale forSale) {
        this.user = user;
        this.forSale = forSale;
    }
}
