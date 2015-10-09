package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.entity.listener.StudentListener;
@Entity
@Table(name="student")
@EntityListeners(value={StudentListener.class})
public class Student implements Serializable{
	
	 private static final long serialVersionUID = 1L;  
	  
	    @Id  
	    @GeneratedValue  
	    @Column(name = "id")  
	    private Integer id;  
	    @Column(name = "first_name", length = 100)  
	    private String firstName;  
	    @Column(name = "last_name", length = 100)  
	    private String lastName;  
	    @Column(name = "email", length = 100)  
	    private String email;  
	  
	    public String getEmail() {  
	        return email;  
	    }  
	  
	    public void setEmail(String email) {  
	        this.email = email;  
	    }  
	  
	    public String getFirstName() {  
	        return firstName;  
	    }  
	  
	    public void setFirstName(String firstName) {  
	        this.firstName = firstName;  
	    }  
	  
	    public Integer getId() {  
	        return id;  
	    }  
	  
	    public void setId(Integer id) {  
	        this.id = id;  
	    }  
	  
	    public String getLastName() {  
	        return lastName;  
	    }  
	  
	    public void setLastName(String lastName) {  
	        this.lastName = lastName;  
	    }  

}
