package org.iungo.logger.api;

import java.io.PrintStream;

public class PrintStreamLogger extends AbstractLogger {
	
	protected volatile PrintStream outputStream = null;
	
	public PrintStreamLogger(final String name) {
		super(name);
	}

	public PrintStream getPrintStream() {
		return outputStream;
	}
	
	public synchronized void setPrintStream(final PrintStream printStream) {
		this.outputStream = printStream;
	}

	@Override
	public synchronized void write(final Integer level, final String text) {
		try {
			final PrintStream printStream = getPrintStream(); // Get and null check as it's volatile...
			if (printStream != null) {
				final Writer writer = getWriter(); // Get and null check as it's volatile...
				if (writer != null) {
					printStream.print(writer.go(level, text));
				}
			}
		} catch (final Exception exception) {
			
		}
	}
}
