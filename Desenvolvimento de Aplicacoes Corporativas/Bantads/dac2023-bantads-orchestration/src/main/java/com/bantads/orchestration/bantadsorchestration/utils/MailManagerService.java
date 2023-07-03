package com.bantads.orchestration.bantadsorchestration.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailManager")
public class MailManagerService implements CustomMailManager {
	private JavaMailSender mailSender;

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public Boolean sendMail(final String mailFrom, final String mailTo, final String subject, final String mailBody) {
		try {
			mailSender.send(new MimeMessagePreparator() {
				public void prepare(javax.mail.internet.MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
					message.setFrom(mailFrom);
					message.addTo(mailTo);
					message.setSubject(subject);
					message.setText(mailBody, true);
				}
			});
		} catch (MailSendException e) {
			System.out.print(e.toString());
			return false;
		}
		return true;
	}
}
//}
