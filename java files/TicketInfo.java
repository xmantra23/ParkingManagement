/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *Class: can be used to create ticket objects to hold information about tickets received by a user
 * @author samir
 */
public class TicketInfo {
    
    private static int ticketCounter;
    private int ticketId;
    private int lotNumber;//the parking lot number
    private Date ticketDate;// the date ticket was issued
    private String carTag;//the car tag number
    private Time ticketTime;//the ticket issue time
    private double ticketAmount;//the amount issued on the ticket
    private int meterNumber;//the meter number in which the ticket was issued
    
    /**
     * Constructor: Creates a Ticket object with the arguments provided by the user.This constructor to be used for creating ticket info
     *              for regular parking lot
     * @param lotNumber is the parking lot number
     * @param ticketDate is the ticket issue date
     * @param carTag is the car tag to which the ticket was issued
     * @param ticketTime is the time the ticket was issued
     * @param ticketAmount  is the amount of the ticket issued in dollars
     */
    //constructor to be used if the ticketing info is for a regular parking lot
    public TicketInfo(int lotNumber,Date ticketDate,String carTag,Time ticketTime,double ticketAmount){//regular lot
        ticketCounter++;
        ticketId = ticketCounter;
        this.lotNumber = lotNumber;
        this.ticketDate = ticketDate;
        this.carTag = carTag.toUpperCase();//converting to uppercase
        this.ticketTime = ticketTime;
        this.ticketAmount = ticketAmount;
    }
    
    /**
     * Constructor: Creates a Ticket object with the arguments provided by the user. This constructor to be used for creating ticket objects 
     *              for metered parking lot
     * @param lotNumber is the parking lot number
     * @param ticketDate is the ticket issue date
     * @param carTag is the car tag to which the ticket was issued
     * @param ticketTime is the time the ticket was issued
     * @param ticketAmount  is the amount of the ticket issued in dollars
     * @param meterNumber is the meter number at which the ticket was issued
     */
    
    //constructor to be used if the ticketing info is for a metered parking lot
    public TicketInfo(int lotNumber,Date ticketDate,String carTag,Time ticketTime,double ticketAmount,int meterNumber){//metered lot
        ticketCounter++;
        ticketId = ticketCounter;
        this.lotNumber = lotNumber;
        this.ticketDate = ticketDate;
        this.carTag = carTag.toUpperCase();
        this.ticketTime = ticketTime;
        this.ticketAmount = ticketAmount;
        this.meterNumber = meterNumber;
    }
    
    
    /**
     * returns the car tag number
     * @return 
     */
    public String getTag(){
        return carTag.toUpperCase();//returning the car tag  in uppercase
    }
    
    /**
     * 
     * @return ticket id number 
     */
    
    public int getTicketId(){
        return ticketId;
    }
    
    /**
     * 
     * @return lot number where ticket was issued 
     */
    public int getLotNumber(){
        return lotNumber;
    }
    
    
    /**
     * 
     * @return ticket issue date 
     */
    public Date getIssueDate(){
        return ticketDate;
    }
    
    
    /**
     * 
     * @return ticket issue time 
     */
    public Time getIssueTime(){
        return ticketTime;
    }
    
    /**
     * 
     * @return ticket amount 
     */
    public double getAmount(){
        return ticketAmount;
    }
    
    /**
     * Overrides the toString of the object class
     * @return 
     */
    @Override
    public String toString(){
        if(lotNumber <= 5)//its a regular parking lot
            return String.format("%-5d %-15s %-8s %-15s %-15.2f %-8s",lotNumber,ticketDate,carTag,ticketTime,ticketAmount,"NONE");
        else//its a metered parking lot
            return String .format("%-5d %-15s %-8s %-15s %-15.2f %-8d",lotNumber,ticketDate,carTag,ticketTime,ticketAmount,meterNumber);
    }

}//end of class
