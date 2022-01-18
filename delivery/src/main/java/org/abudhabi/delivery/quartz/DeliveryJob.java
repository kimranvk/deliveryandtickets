package org.abudhabi.delivery.quartz;

import org.abudhabi.delivery.service.DeliveryService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryJob implements Job {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryJob.class);

	@Autowired
	private DeliveryService deliveryService;
    
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("starting quartz job: DeliveryJob");
	    Long repeatInterval = null;
	    Trigger trigger = jobExecutionContext.getTrigger();
	    if (trigger instanceof SimpleTrigger)
	    {
	        repeatInterval = ((SimpleTrigger)trigger).getRepeatInterval();
	    }
		deliveryService.startInterval(repeatInterval/1000);
		logger.info("Deligated to service layer: DeliveryJob");
	}

}
