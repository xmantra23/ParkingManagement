/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * Class for creating Regular Parking Lot objects
 * @author samir
 */
public class RegularLot extends ParkingLot {
    private double yearlyParkingRate;//the yearly parking rate for a regular lot
    
    /**
     * Constructor: Creates a metered Parking lot object from user given arguments.
     * @param lotName is the parking lot name
     * @param lotNumber is the parking lot number
     * @param lotLocation is the parking lot location
     * @param totalSpaces is the total spaces in a parking lot
     * @param reservedSpaces is the total reserved spaces in a parking lot
     * @param yearlyParkingRate  is the yearly parking rate for parking lot
     */
    public RegularLot(String lotName,int lotNumber,String lotLocation,int totalSpaces,int reservedSpaces,double yearlyParkingRate){
        super(lotName,lotNumber,lotLocation,totalSpaces,reservedSpaces);//calling constructor of the super class
        this.yearlyParkingRate = yearlyParkingRate;//setting the yearly parking rate
    }
    
    
    /**
     * implements abstract method computeRevenue from abstract super class ParkingLot
     * @return computed revenue for this parking lot
     */
    @Override
    public double computeRevenue(){
        double amount = (getTotalSpaces() + getReservedSpaces()) * yearlyParkingRate; //computing total revenue of this parking lot
        return amount;
    }
    
    /**
     * 
     * @return parking rate as a double 
     */
    @Override
    public double getParkingRate(){
        return yearlyParkingRate;
    }
    
}//end of class
