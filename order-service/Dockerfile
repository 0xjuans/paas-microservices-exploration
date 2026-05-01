# Imagen ligera para PaaS; requiere `npm ci` en build (sin multi-stage por simplicidad del taller).
FROM node:22-alpine
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci --omit=dev
COPY src ./src
EXPOSE 8082
ENV NODE_ENV=production
CMD ["node", "src/server.js"]
