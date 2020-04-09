/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Class: Useful for creating parking objects that hold information about a particular parking event
 * @author samir
 */
public class ParkingInfo {
    
    private int lotNumber;//the parking lot number
    private Date activityDate;//the parking activity date
    private String carTag;// the car tag number
    private String parkingStatus;// enter or exit
    private int meterNumber;//meter number
    private double meterAmount;//meter amount
    private Time enterTime;//enter time for a meter
    private Time exitTime;//exit time for a meter
    
    /**
     * Constructor: Creates a ParkingInfo object that holds parking information for a regular parking lot
     *              arguments are to be provided by the user
     * @param lotNumber is the parking lot Number
     * @param activityDate is the parking activity date
     * @param carTag is the car tag number
     * @param parkingStatus is the parking status("ENTER/EXIT")
     */
    //constructor for regular parking info
    public ParkingInfo(int lotNumber,Date activityDate,String carTag,String parkingStatus){
        this.lotNumber = lotNumber;
        this.activityDate = activityDate;
        this.carTag = carTag.toUpperCase();
        this.parkingStatus = parkingStatus.toUpperCase();
    }
    
    /**
     * Constructor: Creates a ParkingInfo object that holds parking information for a metered parking lot
     *              arguments are to be passed to by the user
     * @param lotNumber is the parking lot Number
     * @param activityDate is the parking activity date
     * @param carTag is the car tag number
     * @param meterNumber is the meter number where the car was parked
     * @param enterTime is the time when the car entered in the meter
     * @param exitTime is the time when the car exited from the meter
     * @param meterAmount is the amount charged by the meter per hour
     */
    
    //constructor for metered parking info
    public ParkingInfo(int lotNumber,Date activityDate,String carTag,int meterNumber,Time enterTime,Time exitTime,double meterAmount){
        this.lotNumber = lotNumber;
        this.activityDate = activityDate;
        this.carTag = carTag.toUpperCase();
        this.meterNumber = meterNumber;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
        this.meterAmount = meterAmount;
    }
    
    /**
     * 
     * @return the car tag number
     */
    public String getCarTag(){
        return carTag.toUpperCase(); // car tag number in upper case
    }
    
    /**
     * 
     * @return the meter number where the car was parked 
     */
    public int getMeterNo(){
        return meterNumber;//meter number
    }
    
    /**
     * 
     * @return the lot number where the car was parked
     */
    public int getLotNo(){
        return lotNumber;//the parking lot number
    }
    
    /**
     * 
     * @return the parking rate of the meter 
     */
    public double getMeterRate(){
        return meterAmount;//the meter parking rate
    }
    
    /**
     * 
     * @return total time interval for which the car was parked 
     */
    public double getTotalTime(){
         double totalTime = 0;
         Time intervalTime = exitTime.subtractTime(enterTime);
         //computing the total parking time of a car
         totalTime = (intervalTime.getHour() + (intervalTime.getMinute()/60.00)  + (intervalTime.getSecond()/3600.00));
         
         return totalTime;//total parking time of a car in a meter
     }
     
    /**
     * 
     * @return car enter time 
     */
     public Time getEnterTime(){
         return enterTime;//vehicle enter time in a meter
     }
     
     /**
      * 
      * @return car exit time 
      */
     public Time getExitTime(){
         return exitTime;//vehicle exit time in a meter
     }
     
     /**
      * 
      * @return parking activity date 
      */
     public Date getParkingDate(){
         return activityDate;
     }
     
     /**
      * 
      * @return parking status (ENTER OR EXIT) 
      */
     public String getParkingStatus(){
         return parkingStatus.toUpperCase();
     }
    
    
    /**
     * Overrides the toString from the object class
     * @return  parking information for a vehicle in one line formatted
     */
    @Override
    public String toString(){
        if(lotNumber <= 5)//regular lot, different formatting
            return String.format("%-7d %-15S %-15S %-7S %-10s %-12s %-12s",lotNumber,activityDate,carTag,parkingStatus,"NONE","NONE","NONE");
        else//metered lot
            return String.format("%-7d %-15S %-15S %-7S %-10d %-12s %-12s",lotNumber,activityDate,carTag,"NONE",meterNumber,enterTime,exitTime);
    }
    //for testing
    public static void main(String[] args){
        Time a1 = new Time("11:00");
        Time a2 = new Time("12:00");
        
        Time intervalTime = a2.subtractTime(a1);
        System.out.println(intervalTime);
        
        double totalTime = (intervalTime.getHour() + (intervalTime.getMinute()/60.00)  + (intervalTime.getSecond()/3600.00));
        
        System.out.println(totalTime);
    }
}//end of class
        
        
    
    
    
