package org.andvicoso.shopand.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.andvicoso.shopand.model.manager.JobManager;

@WebListener
public class JobsListener implements javax.servlet.ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		new JobManager().scheduleSendOffers();
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}