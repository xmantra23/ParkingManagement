/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Class: Creates a Monitor object, extends person Super Class and implements Employee Interface
 * @author samir
 */
public class Monitor extends Person implements Employee{
    
    private Date hireDate;//employee hire date
    private Date releaseDate;//employee release date
    private double monthlySalary;//employee monthly base salary
    private double overTimeRate;//employee overtime rate per hour
    
    /**
     * CONSTRUCTOR: creates an employee object of type Monitor with user provided argument to initialize its fields
     * @param firstName is the employee first name
     * @param lastName is the employee last name
     * @param gender is the employee gender
     * @param dateOfBirth is the employee date of birth
     * @param hireDate is the employee date of hiring
     * @param monthlySalary is the employee monthly base salary
     * @param overTimeRate is the over time hourly rate for employee of this type
     */
    public Monitor(String firstName,String lastName,String gender,Date dateOfBirth,Date hireDate,double monthlySalary,double overTimeRate){
        super(firstName,lastName,gender,dateOfBirth);
        this.overTimeRate = overTimeRate;
        this.hireDate = hireDate;
        this.monthlySalary = monthlySalary;
        this.releaseDate = null;//initializing to null
    }
    
    /**
     * implements the getEmpType method from Employee interface
     * @return the employee type as a string
     */
    @Override
    public String getEmpType(){
        return("MONITOR");
    }
    
    /**
     * overrides the toString method of the Super Class Person
     * @return details for employee of type Monitor in one line formatted
     */
    @Override
    public String toString(){
        return String.format("%s %-15s %-12.2f %-12s\n",super.toString(),hireDate,monthlySalary,"MONITOR");
    }
    
    /**
     * implements the computeSalary method of the Employee interface for employee type monitor
     * @param overTimeHour is the hourly rate for overtime hours
     * @return the computed salary for employee of type monitor
     */
    @Override
    public double computeSalary(int overTimeHour){
        double amount = monthlySalary + (overTimeHour * overTimeRate);//computing the salary for monitor employee
        return amount;
    }
    
    /**
     * 
     * @return employee base salary 
     */
    @Override
    public double getBaseSalary(){
        return monthlySalary;
    }
    
    
    /**
     * 
     * @return employee hire date 
     */
    @Override
    public Date getDOH(){
        return hireDate;
    }
    
    /**
     * 
     * @return total number of days the employee has worked 
     */
    @Override
    public int getCareerLength(){
        Date today = new Date();
        int days = hireDate.daysBetween(today);
        return days;
    }
    
}//end of class
