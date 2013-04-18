package org.andvicoso.shopand.model.job;

import org.andvicoso.shopand.model.dao.UserDaoJPA;
import org.andvicoso.shopand.model.manager.MailManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class SendOffersJob implements Job {

	private MailManager mailManager;
	private UserDaoJPA userDao;

	public SendOffersJob() {
		userDao = new UserDaoJPA();
		mailManager = new MailManager();
	}

	@Override
	public void execute(JobExecutionContext context) {
		mailManager.sendOffersMail(userDao.list());
	}

}