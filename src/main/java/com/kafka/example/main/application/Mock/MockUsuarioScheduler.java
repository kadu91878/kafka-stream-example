package com.kafka.example.main.application.Mock;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kafka.example.main.application.entities.Usuario;

@Component
public class MockUsuarioScheduler {

    @Autowired
    private KafkaTemplate<String, Usuario> kafkaTemplate;

    @Scheduled(fixedRate = 30000)
    public void sendMockUsuarios() {
        Usuario validUser = new Usuario();
        validUser.setUsuarioNome("Valid");
        validUser.setUsuarioSobrenome("User");
        validUser.setEmail("valid.user@example.com");
        validUser.setSenha(UUID.randomUUID().toString());

        Usuario invalidUser = new Usuario();
        invalidUser.setUsuarioNome("Invalid");
        invalidUser.setUsuarioSobrenome("User");
        invalidUser.setEmail("invaliduserexample.com");
        invalidUser.setSenha("");

        kafkaTemplate.send("usuario-topic", validUser);
        kafkaTemplate.send("usuario-topic", invalidUser);
    }
}

