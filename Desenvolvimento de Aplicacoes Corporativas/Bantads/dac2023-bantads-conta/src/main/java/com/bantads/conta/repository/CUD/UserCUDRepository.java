package com.bantads.conta.repository.CUD;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bantads.conta.model.CUD.UserCUD;

public interface UserCUDRepository extends JpaRepository<UserCUD, Long> {
	public Optional<UserCUD> findByCpf(String cpf);
	public Optional<UserCUD> findById(String id);
}
