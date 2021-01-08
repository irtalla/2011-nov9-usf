package com.revature.aspects;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.revature.beans.Flower;

@Component
@Aspect
public class GeneralAspect {
//	@Before("aspectHook()")
//	public void beforeEverything(JoinPoint jp) {
//		System.out.println("This is " + jp.getSignature().toString());
//		System.out.println("This happens before all the methods.");
//	}
//	
//	@After("aspectHook()")
//	public void afterEverything(JoinPoint jp) {
//		System.out.println("This happens after all the methods.");
//	}
//	
//	@AfterReturning(pointcut="aspectHook()", returning="obj")
//	public void afterReturning(JoinPoint jp, Object obj) {
//		System.out.println("Returning: " + obj);
//		System.out.println("This happens after returning.");
//	}
//	
//	@AfterThrowing(pointcut="aspectHook()", throwing="e")
//	public void afterThrowing(JoinPoint jp, Throwable e) {
//		System.out.println("Throwing: " + Arrays.toString(e.getStackTrace()));
//		System.out.println("This happens after throwing");
//	}
	
//	@Around("aspectHook()")
//	public Object aroundEverything(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println("Around........");
//		Object obj = null;
//		try {
//			obj = pjp.proceed();
//			System.out.println("Finished the method. Returned: " + obj);
//		} catch (Throwable t) {
//			System.out.println("Threw exception: " + Arrays.toString(t.getStackTrace()));
//			throw t;
//		}
//		List<Flower> flowerList = (List) obj;
//		for (Flower f : flowerList) {
//			f.setName("Andrew");
//		}
//		return flowerList;
//	}
	
	@Pointcut("execution(public java.util.List com.revature..*(..) )")
	private void aspectHook() {} // a hook is an empty method used as a target for an annotation
}
