package com.mohammad;

public class Salary {
	
	   private int empId;
       private int month;
       private int amount;

       public Salary(int empId, int month, int amount) {
           this.empId = empId;
           this.month = month;
           this.amount = amount;
       }

       public int getEmpId() {
           return empId;
       }

       public int getMonth() {
           return month;
       }

       public int getAmount() {
           return amount;
       }
}
