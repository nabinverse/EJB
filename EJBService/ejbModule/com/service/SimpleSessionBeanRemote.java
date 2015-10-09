package com.service;

import javax.ejb.Remote;

import com.model.Student;

@Remote
public interface SimpleSessionBeanRemote {
	
	public boolean addStudent(Student student) throws Exception;

}
