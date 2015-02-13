package org.iungo.logger.api;

public interface Logger {

	public static final Integer OFF = 0;
	
	public static final Integer ERROR = 2<<0;

	public static final Integer WARN = 2<<1;

	public static final Integer INFO = 2<<2;

	public static final Integer DEBUG = 2<<3;

	public static final Integer TRACE = 2<<4;
	
	public static final Integer NORMAL = ERROR ^ WARN ^ INFO;
	
	String getName();
	
	Writer getWriter();
	
	void setWriter(Writer writer);
	
	Logger getParent();
	
	void setParent(Logger logger);
	
	Integer getLevel();
	
	void setLevel(Integer level);
	
	Boolean isLevel(Integer level);

	Boolean isError();
	
	Boolean isWarn();
	
	Boolean isInfo();
	
	Boolean isDebug();
	
	Boolean isTrace();
	
	void write(final Integer level, final String text);
	
	void error(final String text);
	
	void warn(final String text);
	
	void info(final String text);
	
	void debug(final String text);
	
	void trace(final String text);
}
