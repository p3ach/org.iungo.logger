package org.iungo.logger.test;

import org.iungo.email.api.Mailer;
import org.iungo.logger.api.EmailLogger;
import org.iungo.logger.api.Logger;
import org.iungo.logger.api.SimpleLogger;

public class Test {

	public static void main(String[] args) {
		final SimpleLogger simpleLogger = new SimpleLogger(Test.class.getName());
		simpleLogger.error("hello world");
		simpleLogger.warn("hello world");
		simpleLogger.info("hello world");

		final EmailLogger emailLogger = new EmailLogger(Test.class.getName());
		emailLogger.setEmailLevel(Logger.ERROR);
		emailLogger.setMailer(new Mailer(Mailer.GMAIL_SMTP_SSL, "iungo.logger@gmail.com", "Fred1972!", "iungo.logger@gmail.com"));
		emailLogger.addToRecipient("dandh988@gmail.com");
//		emailLogger.info("hello world");
//		emailLogger.error("bugger");
	}

}
