package com.kafka.example.main.application.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.example.main.application.entities.Usuario;
import com.kafka.example.main.application.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @KafkaListener(topics = "usuario-topic", groupId = "usuario-group")
    public void listen(Usuario usuario) {
        if (isValid(usuario)) {
            usuario.setVerificacao("VALIDACAO_SEM_ERRO");
        } else {
            usuario.setVerificacao("VALIDACAO_COM_ERRO");
        }
        usuarioRepository.save(usuario);
    }

    private boolean isValid(Usuario usuario) {
        return usuario.getEmail().contains("@") && !usuario.getSenha().isEmpty();
    }
}

