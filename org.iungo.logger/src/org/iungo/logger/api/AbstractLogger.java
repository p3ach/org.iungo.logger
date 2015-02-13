package org.iungo.logger.api;

/**
 * Abstract Logger implementation.
 * 
 * This will add the Logger to Loggers.
 * 
 * @author Dick Murray.
 *
 */
public abstract class AbstractLogger implements Logger {

	protected final String name;
	
	protected volatile Writer writer = null;
	
	protected volatile Integer level = NORMAL;
	
	protected volatile Logger parent = null;
	
	protected volatile Integer sequence = 0;
	
	public AbstractLogger(final String name) {
		super();
		this.name = name;
		Loggers.getInstance().add(this);
	}
	
	protected synchronized Integer nextSequence() {
		return ++sequence;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Writer getWriter() {
		return writer;
	}

	@Override
	public synchronized void setWriter(final Writer writer) {
		this.writer = writer;
	}

	@Override
	public Logger getParent() {
		return parent;
	}
	
	@Override
	public synchronized void setParent(final Logger parent) {
		this.parent = parent;
	}
	
	@Override
	public Integer getLevel() {
		return level;
	}

	public String getLevelAsString() {
		return (isError() ? "[Error]" : "") + (isWarn() ? "[Warn]" : "") + (isInfo() ? "[Info]" : "") + (isDebug() ? "[Debug]" : "") + (isTrace() ? "[Trace]" : ""); 
	}
	
	@Override
	public synchronized void setLevel(final Integer level) {
		write(INFO, String.format("Changed logger level for [%s] from [%d] to [%d].", name, this.level, level));
		this.level = level;
	}
	
	@Override
	public Boolean isLevel(final Integer level) {
		return ((getLevel() & level) == level);
	}
	
	@Override
	public Boolean isError() {
		return isLevel(ERROR);
	}
	
	@Override
	public Boolean isWarn() {
		return isLevel(WARN);
	}
	
	@Override
	public Boolean isInfo() {
		return isLevel(INFO);
	}
	
	@Override
	public Boolean isDebug() {
		return isLevel(DEBUG);
	}
	
	@Override
	public Boolean isTrace() {
		return isLevel(TRACE);
	}

	/**
	 * By default we do not write anything but if parent is defined we call parent.write(Integer, String).
	 */
	@Override
	public synchronized void write(final Integer level, final String text) {
		final Logger parent = getParent();
		if (parent != null) {
			parent.write(level, text);
		}
	}
	
	@Override
	public void error(final String text) {
		if (isError()) {
			write(ERROR, text);
		}
	}

	@Override
	public void warn(final String text) {
		if (isWarn()) {
			write(WARN, text);
		}
	}

	@Override
	public void info(final String text) {
		if (isInfo()) {
			write(INFO, text);
		}
	}

	@Override
	public void debug(final String text) {
		if (isDebug()) {
			write(DEBUG, text);
		}
	}

	@Override
	public void trace(final String text) {
		if (isTrace()) {
			write(TRACE, text);
		}
	}

	@Override
	public String toString() {
		return String.format("%s name=[%s] level=[%d:%s] parent=[%s]", this.getClass().getName(), getName(), getLevel(), getLevelAsString(), getParent());
	}
}
