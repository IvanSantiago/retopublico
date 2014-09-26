package mx.gob.sct.utic.qoscell.sigtic.services;

import mx.gob.sct.utic.qoscell.util.Mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sistemas Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class Mail_Service {
	private Mailer Mailer;

	/**
	 * 
	 */
	@Transactional(readOnly = true)
	public boolean sendMail(String from, String to, String subject, String msg) {
		Mailer.sendMail(from, to, subject, msg);	
		return true;
	}

	/**
	 * Spring use - DI
	 * @param Mailer
	 */
	@Autowired
	public void setMailer(Mailer Mailer) {
		this.Mailer = Mailer;
	}
}
