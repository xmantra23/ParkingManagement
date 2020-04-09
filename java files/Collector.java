/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Class: Creates a Collector object, extends person Super Class and implements Employee Interface
 * @author samir
 */
public class Collector extends Person implements Employee{
    private Date hireDate;//employee hire date
    private Date releaseDate;//employee release date
    private double monthlySalary;//employee monthly base salary
    private double commission;//employee commission rate
    
    
    /**
     * Constructor: Creates an employee object of type Collector with user provided arguments for attributes
     * @param firstName is the Employee first name
     * @param lastName is the employee last name
     * @param gender is the employee gender
     * @param dateOfBirth is the employee date of birth
     * @param hireDate is the employee hire date
     * @param monthlySalary is the employee monthly salary
     * @param commission is the commission rate for this employee
     */
    public Collector(String firstName,String lastName,String gender,Date dateOfBirth,Date hireDate,double monthlySalary,double commission){
        super(firstName,lastName,gender,dateOfBirth);
        this.hireDate = hireDate;
        this.releaseDate = null;//initializing release date
        this.monthlySalary = monthlySalary;
        this.commission = commission;
    }
    
    /**
     * implements the getEmpType method from employee interface
     * @return the employee type as a String
     */
    @Override
    public String getEmpType(){
        return ("COLLECTOR");//returnin employee type as string
    }
    
    
    /**
     * Overrides the toString method from Person Super Class
     * @return details for Collector Employee in one line formatted
     */
    @Override
    public String toString(){
        return String.format("%s %-15s %-12.2f %-12s\n",super.toString(),hireDate,monthlySalary,"COLLECTOR");
    }
    
    /**
     * implements the computeSalary method from Employee interface
     * @param amountRaised is the total amount in dollars raised by this employee
     * @return the computed salary for employee of type collector
     */
    @Override
    public double computeSalary(int amountRaised){
        double amount = monthlySalary + (amountRaised * commission);//computing salary for commissioned employee
        return amount;
    }
    
    
    /**
     * implements the getBaseSalary method from Employee interface
     * 
     * @return the base salary of employee 
     */
    @Override
    public double getBaseSalary(){
        return monthlySalary;
    }
    
    
    
    @Override
     /**
      * @return the employee hire date
     */
    public Date getDOH(){
        return hireDate;
    }
    
    
    /**
     * 
     * @return the number of days the employee has worked 
     */
    @Override
    public int getCareerLength(){
        Date today = new Date();
        int days = hireDate.daysBetween(today);
        return days;
    }
    
    
}//end of class
