/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Class for creating metered Lots 
 * @author samir
 */
public class MeteredLot extends ParkingLot {
    private double meterRate;//parking rate per hour at this parking lot
    private int[] meter;//array for holding information about the occupancy state of a metere
    
    
    /**
     * Constructor: Creates a Metered Lot object holding all the description of a metered parking lot
     * @param lotName is the lot name
     * @param lotNumber is the lot number
     * @param lotLocation is the lot location
     * @param totalSpaces is the total meters in the parking lot
     * @param reservedSpaces is the total reserved meters in the parking lot(is always 0 for metered lots)
     * @param meterRate is the meter parking rate per hour
     */
    public MeteredLot(String lotName,int lotNumber,String lotLocation,int totalSpaces,int reservedSpaces,double meterRate){
        super(lotName,lotNumber,lotLocation,totalSpaces,reservedSpaces);//calling the constructor of the super class
        this.meterRate = meterRate;
        this.meter = new int[(totalSpaces + 1)];//meter number starts from 1 
    }
    
    /**
     * Implements the CheckMeter method in superclass ParkingLot
     * @param lotNo is the parking lot number
     * @param meterNo is the metered number parked at
     * @param enterTime is the parking enter time
     * @param exitTime is the parking exit time
     * @return true if meter occupied, false if meter free
     */
    public boolean checkMeter(int lotNo,int meterNo,Time enterTime,Time exitTime){
        for(int i = 0; i < getRecords().size(); i++){
            //if time overlaps with the time of this parking info, then the meters are occupied
            if(Time.intervalOverlap(enterTime,exitTime,getRecords().get(i).getEnterTime(),getRecords().get(i).getExitTime()))
                meter[getRecords().get(i).getMeterNo()] = 1;//1 means meter occupied
            else//meter not occupied
                 meter[getRecords().get(i).getMeterNo()] = 0;//0 means meter unoccupied
                
        }
        
        for(int i = 0; i < getRecords().size(); i++){
            if(lotNo == getRecords().get(i).getLotNo() && getRecords().get(i).getMeterNo() == meterNo)
                //using the intervalOverlap method from Time class
                if(Time.intervalOverlap(enterTime,exitTime,getRecords().get(i).getEnterTime(),getRecords().get(i).getExitTime()))
                    return true;//intervals overlap so cannot park in this meter of this lot
        }
        return false;//intervals don't overlap
    }
    
    /**
     * implements the abstract method addParkingInfo from superclass ParkingLot
     * @param info parking information object of type ParkingInfo(object must be created with ParkingInfo class)
     */
    @Override
    public void addParkingInfo(ParkingInfo info){
            //meter number are in ascending order. meterNo cannot exceed the total number of meters available
            if(super.getTotalSpaces() < info.getMeterNo()){
                System.out.println("METER DOESN'T EXIST!!");
            }
            else{
                getRecords().add(info);//adding the parking info
                meter[info.getMeterNo()] = 1;//signalling that the meter is now occupied
            }
    }
    
    /**
     * implements the abstract method getOccupiedNum() from superclass ParkingLot
     * @return total occupied meters number
     */
    @Override
    public int getOccupiedNum(){
        int count = 0;
        for(int i = 0; i < meter.length; i++){//checking all the meteres to see which are occupied in a lot
            if(meter[i] == 1){
                count++;
            }
        }
        return count;//returning the total number of meteres occupied
    }
    
    /**
     * implements the abstract method getFreeSpaces() from superClass ParkingLot
     * @return total number of free meters
     */
    @Override
    public int getFreeSpaces(){
        return (super.getTotalSpaces() - getOccupiedNum());//returning total free meters in a parking lot
    }
    
    /**
     * implements the abstract method computeRevenue() from superclass ParkingLot
     * @return computed revenue from all meters in this parking lot
     */
    @Override
    public double computeRevenue(){
        //computing revenue from all the meters in this lot
        double amount = 0;
        for(int i = 0; i < getRecords().size(); i++ ){
            amount = amount + (getRecords().get(i).getTotalTime() * meterRate);
        }
        return amount;
    }
    
    /**
     * implements the abstract method meterRevenue from superClass ParkingLot
     * @param meterNo computed revenue for a meter in this parking lot
     * @return computed revenue for a meter
     */
    public double meterRevenue(int meterNo){
        //computing revenue from only a particular meter in this lot
        double amount = 0;
        for(int i = 0; i < super.getRecords().size(); i++){
            if(super.getRecords().get(i).getMeterNo() == meterNo){
                amount = amount + (getRecords().get(i).getTotalTime() * meterRate);
            }
        }
        return amount;//returnin the total meter revenue
    }
    
    @Override
    /**
     * @return parking rate for metered lot
     */
    public double getParkingRate(){
        return meterRate;
    }
    //for testing
    public static void main(String[] args){
        ParkingInfo info = new ParkingInfo(5,new Date("08-08-2011"),"7aa-100",5,new Time("10:00"),new Time("11:00"),2.00);
        
        MeteredLot lot = new MeteredLot("gaza",2,"erb",5,5,2.00);
        
        lot.addParkingInfo(info);
        
        
    
    }
    
    
}//end of class
