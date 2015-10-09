package com.servlet;  
  
import java.io.IOException;  
  
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;  
import javax.naming.InitialContext;  
import javax.naming.NamingException;  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletConfig;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  


import com.model.Student;  
import com.service.SimpleSessionBeanRemote;  
  
/** 
 *  
 * @author Sameera Jayasekara 
 *  
 */  
public class ManageStudentServlet extends HttpServlet {  
  
 private static final long serialVersionUID = 1L;  
 private SimpleSessionBeanRemote simpleSBean;  
 Context context =null;
  
 public void init(ServletConfig config) throws ServletException {  
	
  super.init(config);  
  try {  
	  final Hashtable jndiProperties = new Hashtable();
      jndiProperties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
      jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
      jndiProperties.put("java.naming.provider.url", "localhost");

      context= new InitialContext(jndiProperties);

     
      System.out.println("");
  } catch (NamingException e) {  
   e.printStackTrace();  
  }  
  
 }  
  
 protected void doGet(HttpServletRequest request,  
   HttpServletResponse response) throws ServletException, IOException {  
  
 }  
  
 protected void doPost(HttpServletRequest request,  
   HttpServletResponse response) throws ServletException, IOException {  
  
  String message = "";  
  
  String firstName = request.getParameter("fname");  
  String lastName = request.getParameter("lname");  
  String email = request.getParameter("email");  
  
  Student student = new Student();  
  student.setFirstName(firstName);  
  student.setLastName(lastName);  
  student.setEmail(email);  
  try {
	Object obj= context.lookup("simplesbean");
	simpleSBean= (SimpleSessionBeanRemote)obj;
} catch (NamingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  if (simpleSBean.addStudent(student)) {  
   message = "Student Successfuly Added";  
  } else {  
   message = "Student Adding Failed";  
  }  
  
  request.setAttribute("message", message);  
  RequestDispatcher rd = request.getRequestDispatcher("success.html");  
  rd.forward(request, response);  
  
 }  
  
} 