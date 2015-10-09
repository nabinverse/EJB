package com.mdb;
 
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.model.Student;
import com.service.SimpleSessionBeanRemote;
 
@MessageDriven(
   name = "studentmdb",
   activationConfig = {
      @ActivationConfigProperty( propertyName = "destinationType", 
                                 propertyValue = "javax.jms.Queue"),
      @ActivationConfigProperty( propertyName = "destination", 
                                 propertyValue ="/queue/BookQueue")
   }
)
public class StudentMessageDrivenBean implements MessageListener {
 
   @Resource
   private MessageDrivenContext mdctx;  
 
   @EJB(name="simplesbean")
   SimpleSessionBeanRemote sessionbean;
 
   public StudentMessageDrivenBean(){        
   }
 
   public void onMessage(Message message) {
      ObjectMessage objectMessage = null;
      try {
    	  
    	  System.out.println(" Receiver Called");
         objectMessage = (ObjectMessage) message;
         Student student = (Student) objectMessage.getObject(); 
         try {
			sessionbean.addStudent(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
      } catch (JMSException ex) {
         mdctx.setRollbackOnly();
      }       
   }   
}