package com.example.demo.controller;

import com.example.demo.dto.HealthStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.sql.Connection;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/public")
public class SystemController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/health")
    public ResponseEntity<HealthStatusDTO> checkHealth() {
        String dbStatus = "Disconnected 🔴";
        String systemStatus = "DOWN 🔻";
        
        // 1. Teste Real de Conexão com o Banco (MariaDB)
        // Tenta validar a conexão com timeout de 2 segundos
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(2)) { 
                dbStatus = "Connected 🟢";
                systemStatus = "UP 🚀";
            }
        } catch (Exception e) {
            dbStatus = "Error: " + e.getMessage();
            System.err.println("Health Check DB Error: " + e.getMessage());
        }

        // 2. Leitura de Métricas do Hardware/OS (A "Ostentação" do Self-Hosted)
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        
        // Conversão de Bytes para MB para ficar legível
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        int cores = Runtime.getRuntime().availableProcessors();
        
        // System Load Average: Média de carga da CPU (Exclusivo de Linux/Unix)
        double systemLoad = osBean.getSystemLoadAverage();

        // 3. Identificar Ambiente (Diferencia Local vs Nuvem vs Proxmox)
        // Se tiver a variável RENDER, é nuvem. Senão, assume que é seu Proxmox.
        String envName = System.getenv("RENDER") != null ? "Render Cloud" : "Proxmox LXC (Self-Hosted)";

        HealthStatusDTO health = new HealthStatusDTO(
            systemStatus,
            dbStatus,
            envName, 
            osBean.getArch(),             
            osBean.getName() + " " + osBean.getVersion(), 
            systemLoad,
            freeMemory,
            totalMemory,
            cores,
            LocalDateTime.now()
        );

        // Retorna HTTP 200 se estiver UP, ou 503 se estiver DOWN
        if (systemStatus.contains("UP")) {
            return ResponseEntity.ok(health);
        } else {
            return ResponseEntity.status(503).body(health);
        }
    }
}