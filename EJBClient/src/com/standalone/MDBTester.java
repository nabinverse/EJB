package com.standalone;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.model.Student;



public class MDBTester {
 
   BufferedReader brConsoleReader = null; 
   Properties props;
   InitialContext ctx;
   {
		  final Hashtable jndiProperties = new Hashtable();
	      jndiProperties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	      jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
	      jndiProperties.put("java.naming.provider.url", "localhost");

	      try {
			ctx= new InitialContext(jndiProperties);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
   }
   
   public static void main(String[] args) {
 
	   MDBTester ejbTester = new MDBTester();
 
	   ejbTester.sendMessage();
   }
   
   
   private void sendMessage(){
 
      try {
         Queue queue = (Queue) ctx.lookup("/queue/BookQueue");
       //  ((MQQueue)queue).setTargetClient(JMSC.MQJMS_CLIENT_NONJMS_MQ);
         QueueConnectionFactory factory =
         (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
         QueueConnection connection =  factory.createQueueConnection();
         QueueSession session = 
         connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
         QueueSender sender = session.createSender(queue);
         Student student = new Student();
         student.setFirstName("Nilashree");
         student.setEmail("nabinverse@gmail.com");
         student.setLastName("Mandal");
         ObjectMessage objectm = session.createObjectMessage(student);
         sender.send(objectm);
       
      } catch (Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }finally {
         try {
            if(brConsoleReader !=null){
               brConsoleReader.close();
            }
         } catch (IOException ex) {
            System.out.println(ex.getMessage());
         }
      }
   }
}
   
