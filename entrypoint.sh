#!/bin/bash

# Garante que o cloudflared pegue o ID, não importa o nome da variável no Render
export TUNNEL_SERVICE_TOKEN_ID="${CF_ACCESS_CLIENT_ID:-$CF_CLIENT_ID}"
export TUNNEL_SERVICE_TOKEN_SECRET="${CF_ACCESS_CLIENT_SECRET:-$CF_CLIENT_SECRET}"

echo "Iniciando túnel Cloudflare para db.davidson.dev.br..."
# Roda em background (&) apontando para o localhost interno
cloudflared access tcp --hostname db.davidson.dev.br --url 127.0.0.1:3306 &

echo "Aguardando 5 segundos para o túnel estabilizar..."
sleep 5

echo "Iniciando aplicação Java..."
java -jar app.jar