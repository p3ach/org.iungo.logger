package org.iungo.logger.api;

import java.lang.ref.WeakReference;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Loggers {

	private static final Loggers instance = new Loggers();

	public static Loggers getInstance() {
		return instance;
	}
	
	private final Set<WeakReference<Logger>> loggers = new ConcurrentSkipListSet<>(new Comparator<WeakReference<Logger>>() {
		@Override
		public int compare(WeakReference<Logger> o1, WeakReference<Logger> o2) {
			/*
			 * If both WeakReference(s) refer to the same Logger or are Null return 0;
			 * It's possible that either or both will return Null via get(). 
			 */
			if (o1.get() == o2.get()) {
				return 0;
			}
			/*
			 * We return 1 (o1 > o2) as it is enough that we add the Logger to the Set and this is the "fastest" way.
			 */
			return 1;
		}
	});
	
	private Loggers() {
	}

	public void add(final Logger logger) {
		loggers.add(new WeakReference<Logger>(logger));
	}

	public Logger get(final String name) {
		final Iterator<WeakReference<Logger>> iterator = loggers.iterator();
		while (iterator.hasNext()) {
			WeakReference<Logger> weakReference = iterator.next();
			Logger logger = weakReference.get();
			if (logger == null) {
				iterator.remove();
			} else {
				if (logger.getName().equals(name)) {
					return logger;
				}
			}
		}
		return null;
	}

	public void setLevel(final String name, final Integer level) {
		final Logger logger = get(name);
		if (logger == null) {
			throw new UnsupportedOperationException(String.format("Failed to get Logger named [%s].", name));
		}
		logger.setLevel(level);
	}
	
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(1024);
		result.append("Loggers :");
		final Iterator<WeakReference<Logger>> iterator = loggers.iterator();
		while (iterator.hasNext()) {
			WeakReference<Logger> weakReference = iterator.next();
			Logger logger = weakReference.get();
			if (logger == null) {
				iterator.remove();
			} else {
				result.append(String.format("\nLogger [%s].", logger));
			}
		}
		return result.toString();
	}
}
