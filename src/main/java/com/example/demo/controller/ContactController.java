package com.example.demo.controller;

import com.example.demo.dto.ContactRequest;
import com.example.demo.service.TelegramService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/contact")
public class ContactController {

    private final TelegramService telegramService;

    public ContactController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @PostMapping
    public ResponseEntity<String> sendContact(@Valid @RequestBody ContactRequest request) {
        try {
            telegramService.sendMessage(request.getName(), request.getEmail(), request.getMessage());
            return ResponseEntity.ok("Mensagem enviada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao enviar mensagem.");
        }
    }
}