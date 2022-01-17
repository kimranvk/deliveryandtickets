package org.abudhabi.tickets.aop;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class TicketsServiceAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(value = "execution(* org.abudhabi.tickets.service.*.*(..))", 
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		if(logger.isDebugEnabled())
			logger.debug("{} returned with value {}", joinPoint, result);
	}
	
	@Before(value = "execution(* org.abudhabi.tickets.service.*.*(..))")
	public void Before(JoinPoint joinPoint) {
		if(logger.isDebugEnabled())
			logger.debug("Before execution of {} with args {}", joinPoint, Arrays.toString(joinPoint.getArgs()));		
	}
	
	@After(value = "execution(* org.abudhabi.tickets.service.*.*(..))")
	public void after(JoinPoint joinPoint) {
		if(logger.isDebugEnabled())
			logger.debug("After execution of {}", joinPoint);
	}	
}