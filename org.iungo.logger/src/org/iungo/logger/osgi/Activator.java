package org.iungo.logger.osgi;

import org.iungo.common.api.AbstractBundleActivator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractBundleActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@Override
	protected void defineServices() {
	}

}
