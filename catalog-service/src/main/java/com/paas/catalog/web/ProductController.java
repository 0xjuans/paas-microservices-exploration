package com.paas.catalog.web;

import com.paas.catalog.domain.Product;
import com.paas.catalog.service.ProductService;
import com.paas.catalog.service.ProductService.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** API REST del catálogo (/api/products). Traduce HTTP al servicio de dominio. */
@Tag(name = "Productos", description = "Catálogo persistido en PostgreSQL")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Listar productos")
    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @Operation(summary = "Obtener producto por id")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "Crear producto")
    @ApiResponse(responseCode = "201", description = "Producto creado")
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product body) {
        Product saved = productService.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Void> notFound(ProductNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
