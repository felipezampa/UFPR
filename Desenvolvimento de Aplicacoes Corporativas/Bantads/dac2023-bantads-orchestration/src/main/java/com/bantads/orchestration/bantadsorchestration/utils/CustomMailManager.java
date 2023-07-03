package com.bantads.orchestration.bantadsorchestration.utils;


import java.io.Serializable;

public interface CustomMailManager extends Serializable {
	Boolean sendMail(String mailFrom, String mailTo, String subject, String mailBody);
}
