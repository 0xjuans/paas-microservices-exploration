import express from "express";
import swaggerUi from "swagger-ui-express";
import YAML from "yaml";
import { readFileSync } from "fs";
import { fileURLToPath } from "url";
import { dirname, join } from "path";

import { initDb } from "./initDb.js";
import { ordersRouter } from "./routes/orders.js";

/** Punto de entrada: API de pedidos, Swagger UI y manejo de errores global. */

const __dirname = dirname(fileURLToPath(import.meta.url));
const openapiYaml = readFileSync(join(__dirname, "openapi.yaml"), "utf8");
const openapiDoc = YAML.parse(openapiYaml);

await initDb();

const app = express();
const port = Number(process.env.PORT ?? 8082);

app.use(express.json());

app.get("/health", (_req, res) => {
  res.json({ status: "ok", service: "order-service-node" });
});

app.use("/api/orders", ordersRouter);

app.use("/swagger-ui.html", swaggerUi.serve, swaggerUi.setup(openapiDoc));

app.use((err, _req, res, _next) => {
  console.error(err);
  res.status(500).json({ error: "Error interno" });
});

app.listen(port, () => {
  console.log(`order-service (Node) escuchando en http://localhost:${port}`);
  console.log(`Swagger UI: http://localhost:${port}/swagger-ui.html`);
});
