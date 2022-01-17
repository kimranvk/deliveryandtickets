package org.abudhabi.delivery.quartz;

import org.abudhabi.delivery.service.DeliveryService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
		deliveryService.startInterval(-1);
		logger.info("Deligated to service layer: DeliveryJob");
	}

}
