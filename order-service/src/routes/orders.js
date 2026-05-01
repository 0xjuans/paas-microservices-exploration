import { Router } from "express";
import { pool } from "../db/pool.js";
import {
  assertProductExists,
  CatalogUnavailableError,
  ProductNotInCatalogError,
} from "../catalogClient.js";

/** Rutas REST /api/orders: listar y crear (validación HTTP al catálogo). */
export const ordersRouter = Router();

function mapRow(row) {
  return {
    id: String(row.id),
    productId: String(row.product_id),
    quantity: row.quantity,
    createdAt: row.created_at.toISOString(),
  };
}

ordersRouter.get("/", async (_req, res, next) => {
  try {
    const { rows } = await pool.query(
      "SELECT id, product_id, quantity, created_at FROM purchase_orders ORDER BY id"
    );
    res.json(rows.map(mapRow));
  } catch (e) {
    next(e);
  }
});

ordersRouter.post("/", async (req, res, next) => {
  const productId = req.body?.productId;
  const quantity = req.body?.quantity;

  if (
    productId === undefined ||
    quantity === undefined ||
    !Number.isInteger(Number(productId)) ||
    Number(productId) < 1 ||
    !Number.isInteger(Number(quantity)) ||
    Number(quantity) < 1
  ) {
    res.status(400).json({ error: "productId y quantity deben ser enteros positivos" });
    return;
  }

  const pid = Number(productId);
  const qty = Number(quantity);

  try {
    await assertProductExists(pid);
    const insert = await pool.query(
      `INSERT INTO purchase_orders (product_id, quantity) VALUES ($1, $2)
       RETURNING id, product_id, quantity, created_at`,
      [pid, qty]
    );
    res.status(201).json(mapRow(insert.rows[0]));
  } catch (e) {
    if (e instanceof ProductNotInCatalogError) {
      res.status(400).send();
      return;
    }
    if (e instanceof CatalogUnavailableError) {
      res.status(502).json({ error: e.message });
      return;
    }
    next(e);
  }
});
