package com.brewdogger.beer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties
public class BeerRequest {

    private String name;
    private BigDecimal abv;
    private Integer ibu;
    private BeerStyle beerStyle;

    public BeerRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAbv() {
        return abv;
    }

    public void setAbv(BigDecimal abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public BeerStyle getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(BeerStyle beerStyle) {
        this.beerStyle = beerStyle;
    }
}
