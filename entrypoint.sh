#!/bin/bash

echo "--- INICIANDO SETUP ---"

# Debug: Confirma se o Render injetou as variáveis (sem mostrar a senha)
if [ -z "$CF_ACCESS_CLIENT_ID" ]; then
    echo "ERRO CRÍTICO: A variável CF_ACCESS_CLIENT_ID não foi encontrada!"
    echo "Verifique se o nome no Render é exatamente CF_ACCESS_CLIENT_ID"
else
    echo "Token ID detectado: ${CF_ACCESS_CLIENT_ID:0:5}..."
fi

echo "Iniciando túnel Cloudflare para db.davidson.dev.br..."

# AQUI ESTÁ A MUDANÇA: Passamos as variáveis do Render direto para o comando
# O 'nohup' e '&' garantem que ele rode em background sem travar o script
nohup cloudflared access tcp --hostname db.davidson.dev.br --url 127.0.0.1:3306 \
    --service-token-id "$CF_ACCESS_CLIENT_ID" \
    --service-token-secret "$CF_ACCESS_CLIENT_SECRET" > cloudflared.log 2>&1 &

echo "Aguardando 5 segundos para o túnel estabilizar..."
sleep 5

# Mostra as últimas linhas do log do túnel para ver se deu erro
echo "--- LOG DO TÚNEL ---"
cat cloudflared.log
echo "--------------------"

echo "Iniciando aplicação Java..."
# Usamos exec para que o Java assuma o PID 1 (melhor gerenciamento de sinais)
exec java -jar app.jar