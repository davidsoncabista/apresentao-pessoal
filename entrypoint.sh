#!/bin/bash

# 1. Inicia o túnel do Cloudflare em segundo plano (&)
# Ele vai escutar na porta 3306 de DENTRO do container
echo "Iniciando túnel Cloudflare..."
cloudflared access tcp --hostname db.davidson.dev.br --url 127.0.0.1:3306 --service-token-id "$CF_ACCESS_CLIENT_ID" --service-token-secret "$CF_ACCESS_CLIENT_SECRET" &

# 2. Espera 5 segundos para garantir que o túnel subiu
sleep 5

# 3. Inicia a aplicação Java
echo "Iniciando aplicação Java..."
java -jar app.jar