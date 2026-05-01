import { pool } from "./db/pool.js";

/** Crea la tabla de pedidos si no existe (equivalente a ddl-auto en desarrollo). */
export async function initDb() {
  await pool.query(`
    CREATE TABLE IF NOT EXISTS purchase_orders (
      id BIGSERIAL PRIMARY KEY,
      product_id BIGINT NOT NULL,
      quantity INTEGER NOT NULL CHECK (quantity > 0),
      created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
    )
  `);
}
