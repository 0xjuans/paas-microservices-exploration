package com.paas.catalog.web;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Endpoint raíz con estado básico del servicio. */
@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("service", "catalog-service", "status", "ok");
    }
}
