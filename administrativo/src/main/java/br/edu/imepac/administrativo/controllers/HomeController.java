package br.edu.imepac.administrativo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Bem-vindo à API da Clínica Médica");
        response.put("status", "API REST protegida com Keycloak");
        response.put("autenticacao", "Esta API requer autenticação via Keycloak");
        response.put("keycloak_url", "http://localhost:8180");
        response.put("endpoints", Map.of(
            "especialidades", "/api/especialidades",
            "medicos", "/api/medicos"
        ));
        response.put("como_usar", Map.of(
            "1", "Acesse o Keycloak em http://localhost:8180 para obter um token",
            "2", "Use o token no header Authorization: Bearer <token>",
            "3", "Faça requisições para os endpoints da API"
        ));
        return ResponseEntity.ok(response);
    }
}

