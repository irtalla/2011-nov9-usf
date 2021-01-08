package com.revature.aspects;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private Logger log;
	
	@Around("everything()")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		log = Logger.getLogger(pjp.getTarget().getClass());
		log.trace("Method with signature: " + pjp.getSignature());
		log.trace("with arguments: " + Arrays.toString(pjp.getArgs()));
		try {
			obj = pjp.proceed();
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.warn(Arrays.toString(t.getStackTrace()));
			throw t;
		}
		log.trace(pjp.getSignature() + " returned: " + obj);
		
		return obj;
	}
	
	@Pointcut("execution(* com.revature..*(..) )")
	private void everything() {}
}
