FROM node:latest as builder
ENV BACKEND=http://localhost:8080
EXPOSE 80
RUN mkdir -p /app
WORKDIR /app
COPY . .

RUN npm install --force
RUN npm run build --prod
FROM nginx:alpine
COPY src/nginx/etc/conf.d/default.conf /etc/nginx/conf/default.conf
COPY --from=builder app/dist usr/share/nginx/html
# When the container starts, replace the env.js with values from environment variables
CMD ["/bin/sh",  "-c",  "envsubst < /usr/share/nginx/html/assets/env.template.js > /usr/share/nginx/html/assets/env.js && exec nginx -g 'daemon off;'"]
