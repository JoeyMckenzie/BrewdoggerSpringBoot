package com.brewdogger.beer.entity;

import com.brewdogger.beer.model.BeerStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BEERS")
public class Beer {

    @Id
    @SequenceGenerator(name = "BEER_ID_GENERATOR", sequenceName = "BEER_SEQUENCE", allocationSize = 1, initialValue = 100)
    @GeneratedValue(generator = "BEER_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "beer_name")
    private String name;

    @Column(name = "abv")
    private BigDecimal abv;

    @Column(name = "ibu")
    private Integer ibu;

    @Column(name = "beer_style")
    @Enumerated(EnumType.STRING)
    private BeerStyle beerStyle;

    @JoinColumn(name = "brewery_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("beers")
    private Brewery brewery;

    public Beer() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setIbu(Integer ibu) {
        this.ibu = ibu;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }
}
