#!/bin/bash

echo "--- INICIANDO SETUP ---"

# Se o Render não definir a PORT, usa 8080 por padrão by davidson
SERVER_PORT=${PORT:-8080}

echo "Iniciando túnel Cloudflare..."
nohup cloudflared access tcp --hostname 192.168.0.40:3306 --url 127.0.0.1:3306 \
    --service-token-id "$CF_ACCESS_CLIENT_ID" \
    --service-token-secret "$CF_ACCESS_CLIENT_SECRET" > cloudflared.log 2>&1 &
echo "Aguardando 5 segundos..."
sleep 5

echo "Iniciando aplicação Java na porta $SERVER_PORT..."

# -Dserver.port=$SERVER_PORT : Usa a porta que o Render quer
# -Dserver.address=0.0.0.0   : OBRIGATÓRIO para o Render conseguir acessar (não pode ser localhost)
exec java -Xmx350m -Dserver.port=$SERVER_PORT -Dserver.address=0.0.0.0 -jar app.jar