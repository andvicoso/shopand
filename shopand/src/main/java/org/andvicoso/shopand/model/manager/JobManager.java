package org.andvicoso.shopand.model.manager;

import org.andvicoso.shopand.model.job.SendOffersJob;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobManager {

	public void scheduleSendOffers() {
		String group = "offers";
		Class<? extends Job> c = SendOffersJob.class;
		ScheduleBuilder<? extends Trigger> sb = SimpleScheduleBuilder
				.simpleSchedule().withIntervalInSeconds(10).repeatForever();

		//run(c, sb, group);
	}

	private void run(Class<? extends Job> c,
			ScheduleBuilder<? extends Trigger> sb, String group) {
		Trigger trigger = createTrigger(c, sb, group);

		JobDetail job = createJob(c, group);

		schedule(trigger, job);
	}

	private Trigger createTrigger(Class<? extends Job> jobClass,
			ScheduleBuilder<? extends Trigger> scheduleBuilder, String group) {
		String triggerName = jobClass.getSimpleName() + "Trigger";
		return TriggerBuilder.newTrigger().withIdentity(triggerName, group)
				.withSchedule(scheduleBuilder).build();
	}

	private JobDetail createJob(Class<? extends Job> jobClass, String group) {
		String jobName = jobClass.getSimpleName() + "Trigger";
		return JobBuilder.newJob(jobClass).withIdentity(jobName, group).build();
	}

	private void schedule(Trigger trigger, JobDetail job) {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched;
		try {
			sched = sf.getScheduler();
			sched.scheduleJob(job, trigger);
			sched.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
