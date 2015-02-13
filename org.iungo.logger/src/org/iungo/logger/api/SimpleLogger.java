package org.iungo.logger.api;



public class SimpleLogger extends AbstractLogger {
	
	public SimpleLogger(final String name) {
		super(name);
		setWriter(new Writer() {
			@Override
			public String go(final Integer level, final String text) {
				return String.format("[%d/%d/%d] [%s] [%s]", new Object[]{System.currentTimeMillis(), nextSequence(), level, getName(), text});
			}
		});
	}

	@Override
	public synchronized void write(final Integer level, final String text) {
		if ((level & ERROR) == ERROR) {
			System.err.println(getWriter().go(level, text));
		} else {
			System.out.println(getWriter().go(level, text));
		}
	}
}
