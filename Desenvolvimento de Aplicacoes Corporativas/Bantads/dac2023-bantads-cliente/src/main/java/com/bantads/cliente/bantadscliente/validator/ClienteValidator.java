package com.bantads.cliente.bantadscliente.validator;

import com.bantads.cliente.bantadscliente.DTOs.PostClienteDTO;
import com.bantads.cliente.bantadscliente.DTOs.PutClienteDTO;
import com.bantads.cliente.bantadscliente.DTOs.PutEnderecoDTO;

public final class ClienteValidator {
	public static boolean validate(PutClienteDTO putClienteDTO) {
		if (validate(putClienteDTO.getEndereco()) || CommonValidator.validateString(putClienteDTO.getNome())
				|| putClienteDTO.getSalario().doubleValue() <= 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validate(PutEnderecoDTO putEnderecoDTO) {
		if (CommonValidator.validateString(putEnderecoDTO.getBairro())
				|| CommonValidator.validateString(putEnderecoDTO.getCep())
				|| CommonValidator.validateString(putEnderecoDTO.getCidade())
				|| CommonValidator.validateString(putEnderecoDTO.getLogradouro())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateCpf(String cpf) {
		return false;
	}

	public static boolean validate(PostClienteDTO postClienteDTO) {
		if (validate(postClienteDTO.getEndereco()) || CommonValidator.validateString(postClienteDTO.getNome())
				|| postClienteDTO.getSalario().doubleValue() <= 0.0
				|| CommonValidator.validateString(postClienteDTO.getCpf())
				|| CommonValidator.validateString(postClienteDTO.getSenha()) || postClienteDTO.getSenha().length() < 8
				|| !postClienteDTO.getSenha().equals(postClienteDTO.getConfirmarSenha())) {
			return true;
		} else {
			return false;
		}
	}
}
