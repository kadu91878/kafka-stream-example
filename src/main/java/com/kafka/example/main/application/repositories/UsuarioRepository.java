package com.kafka.example.main.application.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.example.main.application.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

