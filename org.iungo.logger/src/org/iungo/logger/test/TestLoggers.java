package org.iungo.logger.test;

import static org.junit.Assert.*;

import org.iungo.logger.api.Logger;
import org.iungo.logger.api.Loggers;
import org.iungo.logger.api.SimpleLogger;
import org.iungo.logger.api.SystemOutLogger;
import org.junit.Test;

public class TestLoggers {

	@Test
	public void test() throws InterruptedException {
		Logger test = new SimpleLogger(TestLoggers.class.getName());
		
		Logger l1 = new SimpleLogger("L1");
		
		test.info(Loggers.getInstance().toString());
		
		Logger l2 = new SimpleLogger("L2");
		
		test.info(Loggers.getInstance().toString());
		
		l1 = null;
		System.gc();
		
		test.info(Loggers.getInstance().toString());
	}

}
