package com.paas.orders.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Mapea la respuesta JSON del catalog al validar que un producto existe. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogProductDto {

    private Long id;
    private String name;
    private Long price;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
