/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 * Abstract Class: useful for inheriting attributes needed to create a Parking Lot class of specific type
 * @author samir
 */
public abstract class ParkingLot {
    
    private String lotName;//parking lot name
    private int lotNumber;//parking lot number
    private String lotLocation;//parking lot location
    private int totalSpaces;// total parking capacity of the parking lot
    private int reservedSpaces;//total reserved saces in the parking lot
    private ArrayList<ParkingInfo> parkingInfo;//to hold all the parking information for this parking lot
    
    private int counter;//counter used for determinining number of space occupied and free.
    
    /**
     * Constructor: Creates a parking lot object with user provided arguments to set its fields
     * @param lotName is the parking lot name
     * @param lotNumber is the parking lot number
     * @param lotLocation is the parking lot location
     * @param totalSpaces is the total occupancy space of the parking lot
     * @param reservedSpaces is the total reserved spaces in the parking lot
     */
    public ParkingLot(String lotName,int lotNumber,String lotLocation,int totalSpaces,int reservedSpaces){
        //setting all the fields
        this.lotName = lotName;
        this.lotNumber = lotNumber;
        this.lotLocation = lotLocation;
        this.totalSpaces = totalSpaces;
        this.reservedSpaces = reservedSpaces;
        parkingInfo = new ArrayList<ParkingInfo>();
    }
    
    /**
     * adds a parking information object 
     * @param info is the parking information object of type ParkingInfo(object created with ParkingInfo class)
     */
    public void addParkingInfo(ParkingInfo info){
        parkingInfo.add(info);//adding a parking information object tot he parkingInfo arraylist
        
    }
    
    /**
     * 
     * @return parking Lot number 
     */
    public int getLotNumber(){
        return lotNumber;//returnin the lot number
    }
    
    /**
     * 
     * @return the total number of parking information for a parking lot 
     */
    public int getRecordNum(){
        return parkingInfo.size();//returining the total number of parking records for this lot
    }
    
   /**
    * used for counting how many space are occupied in a lot, used in the mavpark class
    */
     public void occupySpace(){
        counter++;//incrementing the space counter to siginify that a parking space has been occupied
    }
    
     /**
      * decrements the counter when a space is freed in a lot, used in the mavpark class
      */
    public void freeSpace(){
        counter--;//decrementing the space counter to signify that a parking space has been freed
    }
    
    /**
     * 
     * @return total number of parking spaces in a parking lot 
     */
    public int getTotalSpaces(){
        return totalSpaces;//returning total number of spaces in a parking lot
    }
    
    /**
     * 
     * @return total number of reserved spaces in a parking lot 
     */
    public int getReservedSpaces(){
        return reservedSpaces;//returning total reserved spaces in a parking lot
    }
    
    /**
     *
     * @return total number of parking spaces available in a parking lot 
     */
    public int getFreeSpaces(){
        return (totalSpaces - reservedSpaces - counter);//returning total available parking spaces in a parking lot
    }
    
    /**
     * 
     * @return total occupied spaces in a parking lot
     */
    public int getOccupiedNum(){
        return counter;//returning total occupied space in a parking lot
    }
    
    /**
     * 
     * @return the parking records for a parking lot as an arrayList 
     */
    public ArrayList<ParkingInfo> getRecords(){
        return parkingInfo;//returining the arraylist containing all the parking records for a parking lot
    }
    
    public String getLotName(){
    
        return lotName;
    }
    
    public String getAddress(){
        return lotLocation;
    }
    
    
   
    /**
     * abstract method implemented in subclasses for revenue computation
     * @return computed revenue for a parking lot
     */
    public abstract double computeRevenue();//abstract method , has implementations in the sub class
    public abstract double getParkingRate();//abstract method , has implemetations in the sub class
    
}//end of class