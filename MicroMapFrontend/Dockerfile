FROM node:latest as builder
EXPOSE 80
RUN mkdir -p /app
WORKDIR /app
COPY . .
RUN npm install --force
RUN npm run build --prod
FROM nginx:1.25.3-alpine3.18
COPY src/nginx/etc/conf.d/default.conf /etc/nginx/conf/default.conf
COPY --from=builder app/dist usr/share/nginx/html
CMD ["/bin/sh",  "-c",  "exec nginx -g 'daemon off;'"]
