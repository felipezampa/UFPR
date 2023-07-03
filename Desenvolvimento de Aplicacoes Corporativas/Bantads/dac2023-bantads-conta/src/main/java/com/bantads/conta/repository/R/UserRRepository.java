package com.bantads.conta.repository.R;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bantads.conta.model.R.UserR;

public interface UserRRepository extends JpaRepository<UserR, Long> {
	public UserR findBycpf(String cpf);
	public Optional<UserR> findById(String id);
}
