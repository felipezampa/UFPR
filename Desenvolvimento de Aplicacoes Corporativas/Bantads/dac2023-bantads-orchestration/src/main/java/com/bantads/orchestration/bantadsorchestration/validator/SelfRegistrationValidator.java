package com.bantads.orchestration.bantadsorchestration.validator;

import com.bantads.orchestration.bantadsorchestration.DTOs.AddressDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.UserDTO;

public final class SelfRegistrationValidator {
	public static boolean validate(CustomerDTO customerDTO) {
		if (validateCliente(customerDTO) && validateEndereco(customerDTO.getEndereco())
				&& validateUsuario(customerDTO.getUsuario())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateCliente(CustomerDTO customerDTO) {
		if (CommonValidator.validateString(customerDTO.getNome())
				|| CommonValidator.validateString(customerDTO.getCpf()) ||
				customerDTO.getSalario().doubleValue() <= 0.0 || 
				customerDTO.getEndereco() == null) {
			return false;
		} else {
			return true;
		}

	}


    public static boolean validateClienteUpdate(CustomerUpdateDTO customerDTO) {
		if (CommonValidator.validateString(customerDTO.getNome())
				|| CommonValidator.validateString(customerDTO.getCpf()) ||
				customerDTO.getSalario().doubleValue() <= 0.0 || 
				customerDTO.getEndereco() == null) {
			return false;
		} else {
			return true;
		}

	}


	public static boolean validateEndereco(AddressDTO enderecoDTO) {
		if (CommonValidator.validateString(enderecoDTO.getCep())
				|| CommonValidator.validateString(enderecoDTO.getBairro())
				|| CommonValidator.validateString(enderecoDTO.getCidade())
				|| enderecoDTO.getEstado() == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean validateUsuario(UserDTO usuarioDTO) {
		if (CommonValidator.validateString(usuarioDTO.getEmail())) {
			return false;
		} else {
			return true;
		}
	}
}
