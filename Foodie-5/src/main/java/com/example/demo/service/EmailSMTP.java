package com.example.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.demo.model.MessageEmail;
/**
 * Sirve para enviar los emails del formulario de contacto
 * @author estefgar
 *
 */
@Component
public class EmailSMTP {
	
	@Autowired private JavaMailSender emailSender;

	public void sendSimpleMessage(MessageEmail datos, String subject) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		String finalMsg = new String("<h3>El siguiente usuario se ha puesto en contacto con FoodieCrush:</h3>"
				+"<b>Nombre:</b> "+datos.getFullName()+". <br>"
				+"<b>Mensaje:</b><br><i>"+datos.getMessage()+"</i><br>"
				+"<b>Email de contacto:</b> "+datos.getEmail())+"";
		
		helper = new MimeMessageHelper(message, true);

		helper.setFrom(datos.getEmail());
		helper.setTo("contact.foodiecrush@gmail.com");
		helper.setSubject(subject);
		helper.setText(finalMsg, true);


		emailSender.send(message);
	}
}
