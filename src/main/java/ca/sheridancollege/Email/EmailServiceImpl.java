package ca.sheridancollege.Email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailServiceImpl  {
  
    @Autowired
    public JavaMailSender emailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendMailWithInline(String name, int charge1, int charge2, int charge3, String to, String subject)
			throws MessagingException {

		// Prepare the evaluation context
		final Context ctx = new Context();
		ctx.setVariable("name", name);
		ctx.setVariable("charge1", charge1);
		ctx.setVariable("charge2", charge2);
		ctx.setVariable("charge3", charge3);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
		final MimeMessageHelper message = 
				new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
		message.setSubject(subject);
		message.setFrom("account@gmail.com");
		message.setTo(to);

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.templateEngine
				.process("emailTemplate.html", ctx);
		message.setText(htmlContent, true); // true = isHtml

		// Send mail
		this.emailSender.send(mimeMessage);
	}

}
