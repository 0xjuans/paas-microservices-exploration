import pg from "pg";

/** Pool de conexiones PostgreSQL (base `orders`). */
const { Pool } = pg;
const useSsl = (process.env.DB_SSL ?? "true").toLowerCase() === "true";

export const pool = new Pool({
  host: process.env.DB_HOST ?? "localhost",
  port: Number(process.env.DB_PORT ?? 5433),
  user: process.env.DB_USER ?? "postgres",
  password: process.env.DB_PASSWORD ?? "postgres",
  database: process.env.DB_NAME ?? "orders",
  // Azure PostgreSQL requiere cifrado en tránsito (TLS/SSL).
  ssl: useSsl ? { rejectUnauthorized: false } : false,
});
