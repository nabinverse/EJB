package com.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.model.Student;
import com.service.intercepter.BusinessInterceptor;

/**
 * Session Bean implementation class SimpleSessionBean
 */
@Stateless(mappedName = "simplesbean")
@WebService
@Interceptors({BusinessInterceptor.class})
@TransactionManagement(value=TransactionManagementType.BEAN)
public class SimpleSessionBean implements SimpleSessionBeanRemote {

	@PersistenceContext
	EntityManager entityManager;
	
	@Resource
	UserTransaction transaction;
	
	@Resource
	private SessionContext context;

	   private void createTimer(long duration) {
	      context.getTimerService().createTimer(duration, "Hello World!");
	   }

	   @Timeout
	   public void timeOutHandler(Timer timer){
	      System.out.println("timeoutHandler : " + timer.getInfo());        
	     // timer.cancel();
	   }
	
    /**
     * Default constructor. 
     */
    public SimpleSessionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@WebMethod
	public boolean addStudent(Student student)  throws Exception{
		
		try{
		// TODO Auto-generated method stub
			transaction.begin();
			createTimer(5000);
			entityManager.persist(student);
			System.out.println("Student Added :" +student.getFirstName());
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
		return true;
	}
	
	@PostConstruct
	public void initialize(){
		System.out.println("Initialize");
	}
	
	@PreDestroy()
	public void destroy(){
		System.out.println("Destroy");
	}
	
}
