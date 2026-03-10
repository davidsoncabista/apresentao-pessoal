package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        StringBuilder errorDetails = new StringBuilder();
        errorDetails.append("Erro ").append(status != null ? status : "desconhecido").append("\n");
        if (message != null) {
            errorDetails.append("Mensagem: ").append(message).append("\n");
        }
        if (exception != null) {
            errorDetails.append("Exceção: ").append(exception.toString()).append("\n");
        }
        return errorDetails.toString();
    }
}