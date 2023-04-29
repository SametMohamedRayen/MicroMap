FROM node:latest as builder
ENV ENDPOINT=http://localhost:8080/
RUN mkdir -p /app
WORKDIR /app
COPY . .

RUN npm install
RUN npm run build --prod
FROM nginx:alpine
COPY src/nginx/etc/conf.d/default.conf /etc/nginx/conf/default.conf
COPY --from=builder app/dist usr/share/nginx/html
