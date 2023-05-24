package com.mohammad;

public class Employee {
	
	 private int id;
     private String name;
     private int deptId;

     public Employee(int id, String name, int deptId) {
         this.id = id;
         this.name = name;
         this.deptId = deptId;
     }

     public int getId() {
         return id;
     }

     public String getName() {
         return name;
     }

     public int getDeptId() {
         return deptId;
     }
}
