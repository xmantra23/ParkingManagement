/*****************************************************************************
 @author Sharma Chakravarthy
 LANGUAGE   : Java version 6
 OS         : Windows win 7 Ultimate
 PLATFORM   : PC
 Compiler   : javac
 
 CONCEPTS   : classes and methods
 PURPOSE    : defines an interface
******************************************************************************/



/**
 * an interface implemented by Monitor,Collector and Maintainer , subclasses of Person
 * @author samir
 */

public interface Employee {
        /**
         * computes the salary of employee
         * @param salaryParameter
         * @return computed salary for an employee
         */
	public double computeSalary(int salaryParameter); //this cannot be static
        
        /**
         * 
         * @return employee first name
         */
        public String getFirstName();
        
        /**
         * 
         * @return employee last name 
         */
        public String getLastName();
        
        /**
         * 
         * @return employee type
         */
        public String getEmpType();
        
        /**
         * 
         * @return employee gender
         */
        public String getGender();
        
        /**
         * employee identification number
         * @return employee id
         */
        public int getId();
        
        /**
         * 
         * @return employee date of birth  
         */
        public Date getDOB();
        
        /**
         * 
         * @return employee base salary 
         */
        public double getBaseSalary();
        
        /**
         * 
         * @return employee hire date 
         */
        public Date getDOH();
        
        /**
         * 
         * @return total number of employed days 
         */
        public int getCareerLength();
        
        
}
