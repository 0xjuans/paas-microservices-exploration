/** Comprueba que el producto exista en catalog-service (Spring Boot). */

const base = () => (process.env.CATALOG_SERVICE_BASE_URL ?? "http://localhost:8081").replace(/\/$/, "");

export class ProductNotInCatalogError extends Error {
  constructor(productId) {
    super(`Producto no encontrado en catálogo: ${productId}`);
    this.name = "ProductNotInCatalogError";
  }
}

export class CatalogUnavailableError extends Error {
  constructor(message) {
    super(message);
    this.name = "CatalogUnavailableError";
  }
}

export async function assertProductExists(productId) {
  const url = `${base()}/api/products/${productId}`;
  let res;
  try {
    res = await fetch(url, { method: "GET" });
  } catch (e) {
    throw new CatalogUnavailableError(`No se pudo contactar al catalog: ${e.message}`);
  }
  if (res.status === 404) {
    throw new ProductNotInCatalogError(productId);
  }
  if (!res.ok) {
    throw new CatalogUnavailableError(`Catalog respondió ${res.status}`);
  }
}
