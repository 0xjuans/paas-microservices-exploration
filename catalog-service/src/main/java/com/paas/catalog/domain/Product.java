package com.paas.catalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/** Entidad JPA: producto persistido en PostgreSQL (tabla products). */
@Schema(description = "Producto del catálogo")
@Entity
@Table(name = "products")
public class Product {

    @Schema(description = "Identificador del producto (Long)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del producto (String no vacío)")
    @NotBlank
    @Column(nullable = false)
    private String name;

    @Schema(description = "Precio unitario del producto en COP (Long, entero positivo)", example = "50000")
    @Positive
    @Column(nullable = false)
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
