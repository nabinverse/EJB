package com.service.intercepter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BusinessInterceptor {
   @AroundInvoke
   public Object methodInterceptor(InvocationContext ctx) throws Exception
   {
      System.out.println("*** Intercepting call to service method: " 
      + ctx.getMethod().getName());
      return ctx.proceed();
   }
   @PostConstruct
	public Object initialize(InvocationContext ctx)throws Exception{
		System.out.println("Initialize");
		 return ctx.proceed();
	}
	
	@PreDestroy()
	public Object destroy(InvocationContext ctx)throws Exception{
		System.out.println("Destroy");
		return ctx.proceed();
	}
}