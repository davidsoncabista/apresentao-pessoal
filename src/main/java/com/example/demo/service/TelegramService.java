package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(String name, String email, String message) {
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        // Formata a mensagem bonitinha
        String text = "📬 *Novo Contato do Portfólio*\n\n" +
                      "👤 *Nome:* " + name + "\n" +
                      "📧 *Email:* " + email + "\n" +
                      "📝 *Mensagem:* \n" + message;

        // Monta o JSON para o Telegram
        Map<String, Object> body = new HashMap<>();
        body.put("chat_id", chatId);
        body.put("text", text);
        body.put("parse_mode", "Markdown"); // Para usar negrito

        // Envia
        restTemplate.postForObject(url, body, String.class);
    }
}