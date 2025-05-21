// src/main/java/com/example/projeto/repository/CarroRepository.java
package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projeto.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    // você pode declarar queries customizadas aqui, se precisar
}
