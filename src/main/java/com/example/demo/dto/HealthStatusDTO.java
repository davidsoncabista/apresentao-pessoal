package com.example.demo.dto;

import java.time.LocalDateTime;

// O 'record' é um recurso moderno do Java (v14+) perfeito para DTOs
public record HealthStatusDTO(
    String status,          // Status Geral (UP/DOWN)
    String database,        // Conexão com MariaDB
    String environment,     // Nome do Ambiente (Proxmox/Render)
    String osArch,          // Arquitetura (amd64)
    String osVersion,       // Kernel Linux
    double systemLoad,      // Carga da CPU
    long memoryFree,        // Memória Livre (MB)
    long memoryTotal,       // Memória Total (MB)
    int availableProcessors,// Núcleos de CPU
    LocalDateTime timestamp // Hora da consulta
) {}