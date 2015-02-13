package org.iungo.logger.api;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.mail.MessagingException;

import org.iungo.email.api.Mailer;


public class EmailLogger extends SimpleLogger {

	protected volatile Mailer mailer = null;
	
	protected volatile Integer emailLevel = OFF;
	
	protected final Set<String> to = new ConcurrentSkipListSet<>();
	
	protected final Set<String> cc = new ConcurrentSkipListSet<>();
	
	protected final Set<String> bcc = new ConcurrentSkipListSet<>();
	
	public EmailLogger(final String name) {
		super(name);
	}

	/*
	 * Mailer methods.
	 */
	
	/**
	 * Volatile read of current Mailer.
	 * @return
	 */
	public Mailer getMailer() {
		return mailer;
	}
	
	/**
	 * Synchronized set of Mailer.
	 * @param mailer
	 */
	public synchronized void setMailer(final Mailer mailer) {
		this.mailer = mailer;
	}
	
	/*
	 * Email level methods.
	 */

	public Integer getEmailLevel() {
		return emailLevel;
	}
	
	public synchronized void setEmailLevel(final Integer emailLevel) {
		this.emailLevel = emailLevel;
	}
	
	protected Boolean isEmailEnabled(final Integer level) {
		return ((getEmailLevel() & level) == level);
	}

	/*
	 * Recipient (to, cc, bcc) methods.
	 */

	public synchronized void addToRecipient(final String recipient) {
		to.add(recipient);
	}

	public synchronized void addCcRecipient(final String recipient) {
		cc.add(recipient);
	}

	public synchronized void addBccRecipient(final String recipient) {
		bcc.add(recipient);
	}
	
	@Override
	public synchronized void write(final Integer level, final String text) {
		super.write(level, text);
		if (isEmailEnabled(getLevel())) {
			try {
				final Mailer mailer = getMailer();
				if (mailer != null) {
					mailer.simpleEmail(to, cc, bcc, String.format(""), text).send();
				}
			} catch (final MessagingException messagingException) {
				System.out.println(messagingException.getMessage());
			}
		}
	}

	@Override
	public String toString() {
		return String.format("[%s] Mailer=[%s]", super.toString(), mailer);
	}

}
