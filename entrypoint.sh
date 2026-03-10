#!/bin/bash

echo "--- INICIANDO APLICAÇÃO SELF-HOSTED ---"

# Porta padrão para self-hosted
SERVER_PORT=${PORT:-8080}

echo "Iniciando aplicação Java na porta $SERVER_PORT..."

# Configuração para self-hosted (pode ser localhost ou 0.0.0.0 dependendo da configuração)
exec java -Xmx512m -Dserver.port=$SERVER_PORT -Dserver.address=0.0.0.0 -jar app.jar