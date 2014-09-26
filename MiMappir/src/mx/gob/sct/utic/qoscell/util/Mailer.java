package mx.gob.sct.utic.qoscell.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class Mailer {/*
	public Mailer() {
		super();
	}

	public void Send(String fromEmail, String toEmail, String Subject,
			String Body) {
		try {
			Properties props = new Properties();
			//URLName urln = new URLName("10.33.148.30");
			// Authenticate auth = new Authenticate("arlander@sct.gob.mx",
			// "arl660");
			//props.put("mail.smtp.host", "10.33.148.30");
			props.put("mail.smtp.host", "10.33.148.27");
			// props.put("mail.smtp.auth", "true");
			Session s = Session.getInstance(props, null);
			// Session s = Session.getInstance(props, auth);
			// s.setPasswordAuthentication(urln,
			// auth.getPasswordAuthentication());

			MimeMessage message = new MimeMessage(s);
			InternetAddress from = new InternetAddress(fromEmail);
			message.setFrom(from);
			message.addRecipients(Message.RecipientType.TO, toEmail);
			message.setSubject(Subject);
			String mt = "text/html";
			message.setDataHandler(new DataHandler(Body.toString(), mt));
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	private MailSender mailSender;
 
	public void sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}


}
