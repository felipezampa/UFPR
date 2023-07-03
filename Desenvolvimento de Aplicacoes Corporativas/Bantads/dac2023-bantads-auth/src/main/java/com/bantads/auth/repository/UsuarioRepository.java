package com.bantads.auth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bantads.auth.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Query("{email:'?0'}")

	Usuario findByEmail(String email);

	Boolean existsByEmail(String email);

	Optional<Usuario> findById(String id);
}
