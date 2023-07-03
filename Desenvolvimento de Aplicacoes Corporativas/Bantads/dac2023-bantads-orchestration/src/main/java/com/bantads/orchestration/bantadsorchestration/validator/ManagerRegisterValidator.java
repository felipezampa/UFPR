package com.bantads.orchestration.bantadsorchestration.validator;

import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.UserDTO;

public final class ManagerRegisterValidator {
    public static boolean validate(ManagerDTO managerDTO) {
        if (validateManager(managerDTO)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateManager(ManagerDTO managerDTO) {
        if (CommonValidator.validateString(managerDTO.getNome()) ||
                CommonValidator.validateString(managerDTO.getCpf()) ||
                managerDTO.getCpf().length() != 11) {
            return false;
        } else {
            return true;
        }
    }
}
