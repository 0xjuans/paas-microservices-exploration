# PaaS Microservices Exploration

Proyecto de microservicios para exploracion practica de PaaS con free tier en Azure y AWS.

## Arquitectura

- `catalog-service` esta implementado con **Spring Boot** (Java).
- `order-service` esta implementado con **Node.js** (Express).
- Ambos servicios usan **PostgreSQL gestionado**.
- La comunicacion entre servicios es por **HTTP/HTTPS**.
- `order-service` valida por HTTP que el producto exista en `catalog-service` antes de crear un pedido.
- Ambos exponen documentacion **OpenAPI/Swagger**.

## Despliegue en Azure (Container Apps + Azure PostgreSQL)

- Catalog service: [https://catalog-service--0000009.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io](https://catalog-service--0000009.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io)
- Order service: [https://order-service--0000003.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io](https://order-service--0000003.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io)

### Swagger Azure

- Catalog Swagger: [https://catalog-service--0000009.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io/swagger-ui.html](https://catalog-service--0000009.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io/swagger-ui.html)
- Order Swagger: [https://order-service--0000003.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io/swagger-ui.html](https://order-service--0000003.livelyrock-c41fe32c.canadacentral.azurecontainerapps.io/swagger-ui.html)

## Despliegue en AWS (EC2 + ECR + RDS PostgreSQL)

- Catalog service: [http://3.22.223.128:8081](http://3.22.223.128:8081)
- Order service: [http://3.22.223.128:8082](http://3.22.223.128:8082)

### Swagger AWS

- Catalog Swagger: [http://3.22.223.128:8081/swagger-ui/index.html](http://3.22.223.128:8081/swagger-ui/index.html)
- Order Swagger: [http://3.22.223.128:8082/swagger-ui.html](http://3.22.223.128:8082/swagger-ui.html)

## Endpoints principales

### Catalog service

- Estado raiz: `GET /`
- Listar productos: `GET /api/products`
- Obtener producto por id: `GET /api/products/{id}`
- Crear producto: `POST /api/products`

### Order service

- Estado raiz: `GET /`
- Listar pedidos: `GET /api/orders`
- Crear pedido: `POST /api/orders`

## Flujo funcional

1. Crear un producto en `catalog-service`.
2. Crear un pedido en `order-service` enviando `productId` y `quantity`.
3. `order-service` consulta por HTTP a `catalog-service` para validar el producto.
4. Si el producto existe, el pedido se persiste en PostgreSQL.
