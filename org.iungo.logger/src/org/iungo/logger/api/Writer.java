package org.iungo.logger.api;

public interface Writer {
	
	static final String TIMESTAMP = "%t";
	
	static final String SEQUENCE = "%s";
	
	static final String NAME = "%n";
	
	String go(final Integer level, final String text);
}
