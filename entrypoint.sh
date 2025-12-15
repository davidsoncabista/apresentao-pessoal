#!/bin/bash

echo "--- INICIANDO SETUP ---"

# Debug de Token
if [ -z "$CF_ACCESS_CLIENT_ID" ]; then
    echo "ERRO: CF_ACCESS_CLIENT_ID não encontrado!"
else
    echo "Token ID detectado."
fi

echo "Iniciando túnel Cloudflare..."
# Inicia o túnel em background
nohup cloudflared access tcp --hostname db.davidson.dev.br --url 127.0.0.1:3306 \
    --service-token-id "$CF_ACCESS_CLIENT_ID" \
    --service-token-secret "$CF_ACCESS_CLIENT_SECRET" > cloudflared.log 2>&1 &

echo "Aguardando 10 segundos para garantir conexão do túnel..."
sleep 10  # Aumentei para 10s porque o Render é lento

echo "Iniciando aplicação Java..."

# MUDANÇA IMPORTANTE AQUI:
# 1. -Xmx350m: Limita a memória para o container não travar
# 2. -Dserver.address=0.0.0.0: Obriga a ouvir em todos os IPs (Resolve o erro do Render)
# 3. -Dserver.port=8080: Garanta a porta 8080
exec java -Xmx350m -Dserver.address=0.0.0.0 -Dserver.port=8080 -jar app.jar