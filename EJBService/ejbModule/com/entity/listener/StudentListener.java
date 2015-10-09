package com.entity.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.model.Student;

public class StudentListener {
	
	 @PrePersist
	   public void prePersist(Student student){
	      System.out.println("prePersist:" 
	         + "Student to be created with id: "+student.getId());
	   }

	   @PostPersist
	   public void postPersist(Student student){
	      System.out.println("postPersist::"
	         + "Student created with Student id: "+student.getId());
	   }

	   @PreRemove
	   public void preRemove(Student student)
	   {
	      System.out.println("preRemove:"
	         + " About to delete Student: " + student.getId());
	   }

	   @PostRemove
	   public void postRemove(Student student)
	   {
	      System.out.println("postRemove::"
	         + " Deleted Student: " + student.getId());
	   }

	   @PreUpdate
	   public void preUpdate(Student student)
	   {
	      System.out.println("preUpdate::"
	         + " About to update Student: " + student.getId());
	   }

	   @PostUpdate
	   public void postUpdate(Student student)
	   {
	      System.out.println("postUpdate::"
	         + " Updated Student: " + student.getId());
	   }

	   @PostLoad
	   public void postLoad(Student student)
	   {
	      System.out.println("postLoad::"
	         + " Loaded Student: " + student.getId());
	   }

}
