package org.iungo.logger.api;

public class SystemOutLogger extends PrintStreamLogger {
	
	public static final SystemOutLogger DEFAULT_SYSTEM_OUT_LOGGER;
	static {
		DEFAULT_SYSTEM_OUT_LOGGER = new SystemOutLogger(SystemOutLogger.class.getName());
		DEFAULT_SYSTEM_OUT_LOGGER.setWriter(Sim);
	}

	public SystemOutLogger(final String name) {
		super(name);
		setPrintStream(System.out);
	}
}
