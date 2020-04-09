


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * MavPark class to be used by MavParkTest class for testing and running the program
 * @author samir
 */
public class MavPark implements Proj3Constants{
    private String enterpriseName;//stores the enterprise name
    private PrintWriter foutput;//for output to a file
    private static ArrayList<Employee> employee;//for holding employee objects
    private static ArrayList<ParkingLot> parkingLot;//for holding parking lot objects
    private static ArrayList<User> user;//for holding user objects
    
    
    /**
     * CONSTRUCTOR: INITIALIZES ALL THE CLASS ATTRIBUTES FOR CREATING MAVPARK OBJECTS
     */
    
    public MavPark(){
        //initializing all the fields
        employee = new ArrayList<Employee>();
        parkingLot = new ArrayList<ParkingLot>();
        user = new ArrayList<User>();
        enterpriseName = DEFAULT_ENTERPRISE_NAME;
   }
   
   /**
    * used by the MavPark class to write output to a file
    * @param foutput is the output stream
    */
   public void setFileOutput(PrintWriter foutput){   //getting the foutput file writer from mavparktest for file writing
       if(foutput == null){
           System.out.printf("invalid output stream provided to MavPark class. cannot write to file without it\n");
           foutput.printf("invalid output stream provided to MavPark class.cannot write to file without it\n");
       }
       this.foutput = foutput;
   }
   
   /**
    * sets the enterpriseName
    * @param enterpriseName is the name of the enterprise
    */ 
   public void setEnterpriseName(String enterpriseName){
       this.enterpriseName = enterpriseName;
   }
   
   public static ArrayList<Employee> getEmployees(){
   
       return employee;
   }
   
   public static ArrayList<User> getUsers(){
       return user;
   }
   
   public static ArrayList<ParkingLot> getParkingLots(){
       return parkingLot;
   
   } 
   
   /**
    * creates an employee object using polymorphism(monitor/collector/maintainer)
    * @param empType is the type of the employee(must be one of MNTR/MAINT/COLL)
    * @param firstName is the first name of the employee
    * @param lastName is the last name of the employee
    * @param gender is the gender of the employee
    * @param dateOfBirth is the date of birth of the employee
    * @param hireDate is the hire date of the employee
    * @param monthlySalary is the monthly base salary in dollars and cents of the employee
    */
   //method to create an employee
   public void createEmployee(String empType,String firstName,String lastName,String gender,Date dateOfBirth,Date hireDate,
                             double monthlySalary,double value){
      //error checking before creating a new employee
      if(monthlySalary >= 0.0 && value >= 0.0 &&(gender.toUpperCase().equals("MALE") || gender.toUpperCase().equals("FEMALE"))){ 
           if(empType.toUpperCase().equals("MNTR")){       //employee is a monitor
               //polymorphically creating a new monitor object and adding it to an employee reference array list
               employee.add(new Monitor(firstName,lastName,gender,dateOfBirth,hireDate,monthlySalary,value));
           }
           else if(empType.toUpperCase().equals("COLL")){  //employee is a commision employee
               //polymorphically creating a new collector object and adding it to an employee referenced arraylist 
               employee.add(new Collector(firstName,lastName,gender,dateOfBirth,hireDate,monthlySalary,value));
           }
           else if(empType.toUpperCase().equals("MAINT")){ //employee is a maintainer
               //polymorphically craeting a new maintainer object and adding it to an employee referenced arraylist
               employee.add(new Maintainer(firstName,lastName,gender,dateOfBirth,hireDate,monthlySalary,value));
           }
           else{  // employee types donot match the three choices
               System.out.printf("invalid employee type for employee name: %s %s repalced with type MNTR\n",firstName,lastName);
               foutput.printf("invalid employee type for employee name: %s %s repalced with type MNTR\n",firstName,lastName);
               employee.add(new Monitor(firstName,lastName,gender,dateOfBirth,hireDate,monthlySalary,value));
           }
      }//endif
      else{//repalcing with default values
          System.out.printf("invaid data read for employee name: %s %s  replaced with default values\n",firstName,lastName);
          foutput.printf("invaid data read for employee name: %s %s  replaced with default values\n",firstName,lastName);
          employee.add(new Monitor(firstName,lastName,GENDER,dateOfBirth,hireDate,MAX_SALARY,SALARY_PARAM));
      }
   }//end method
   
   /**
    * creates a parking lot object of type Regular or Metered using polymorphism
    * @param lotName is the name of the lot
    * @param lotNumber is the lot number
    * @param lotLocation is the location of the parking lot
    * @param totalSpaces is the total space capacity of the parking lot
    * @param reservedSpaces is the total reserved spaces in the parking lot
    * @param lotType is the type of the lot(Regular/Metered)
    * @param lotCost is the cost of parking at this parking lot
    */
   public void createParkingLot(String lotName,int lotNumber,String lotLocation,int totalSpaces,
                                int reservedSpaces,String lotType,double lotCost){
       //ERROR CHECKING BEFORE CREATING A PARKING LOT
       if(lotNumber > 0 && totalSpaces > 0 && reservedSpaces >= 0 && lotCost > 0.0 &&(totalSpaces > reservedSpaces)){
           if(lotNumber > 0 && lotNumber <= 5){//regular parking lot
               //adding a RegularLot object to parkingLot arraylist
               parkingLot.add(new RegularLot(lotName,lotNumber,lotLocation,totalSpaces,reservedSpaces,lotCost));
           }       
           else if(lotNumber > 5 && lotNumber <= 10){//metered parking lot
               //adding a RegularLot object to parkingLot arraylist
               parkingLot.add(new MeteredLot(lotName,lotNumber,lotLocation,totalSpaces,reservedSpaces,lotCost));
           }
           else{
               System.out.printf("Invalid lot number for lotName %s, replaced with default values \n",lotName);
               foutput.printf("Invalid lot number for lotName %s, replaced with default values \n", lotName);
               parkingLot.add(new RegularLot(lotName,D_LOTNO,lotLocation,totalSpaces,reservedSpaces,lotCost));
           }
       }//endif
       else{//replacing with default values
           System.out.printf("Invalid data read for lotName %s, replaced with default values \n",lotName);
           foutput.printf("Invalid data read for lotName %s, replaced with default values \n",lotName);
           parkingLot.add(new RegularLot(lotName,D_LOTNO,lotLocation,D_TOTSPACES,D_REZSPACES,D_LOTCOST));
       }
   }//end method
   
   /**
    * creates a user object that hold the detail about a user
    * @param userId is the user id
    * @param userVehicleTag is the user vehicle tag number
    * @param userFName is the user first name
    * @param userLName is the user last name
    * @param userDOB is the user date of birth
    * @param userGender is the user gender
    * @param userAddress is the user address
    * @param userZipcode is the user zip code
    * @param userType is the type of user( stud/fac/unkn)
    * @param userClassification  is the user classification
    */
   
   //method creates a new user object
   public void createUser(int userId,String userVehicleTag,String userFName,String userLName,String userDOB,
                          String userGender,String userAddress,int userZipcode,String userType,String userClassification){
       Date dOB = new Date(userDOB);//creating a date object that hold date of birth information
       if(userId > 0 && (userGender.toUpperCase().equals("MALE") || userGender.toUpperCase().equals("FEMALE"))  && userZipcode > 0 
          &&(userType.toUpperCase().equals("STUD")|| userType.toUpperCase().equals("FAC")|| userType.toUpperCase().equals("VIS"))){
           //adding a new user object to the user arraylist
           user.add(new User(userId,userVehicleTag,userFName,userLName,dOB,userGender,
                        userAddress,userZipcode,userType,userClassification));
       }
       else{//replacing with default values
           System.out.printf("invalid data read for user Name: %s %s, replaced with default values\n",userFName,userLName);
           foutput.printf("invalid date read for user Name: %s %s, replaced with default values\n",userFName,userLName);
           user.add(new User(D_USERID,userVehicleTag,userFName,userLName,dOB,GENDER,
                        userAddress,D_ZIPCODE,D_USERTYPE,D_USERCLASS));
       }
   }//end of method
   
   
   /**
    * prints all the employees with their details in one line format 
    */
   public void printEmployees(){
       //printing all the employees in formatted output
       System.out.printf("%-3s %-10s %-10s %-8s %-15s %-15s %-12s %-12s\n","ID","FIRSTNAME","LASTNAME","GENDER","BIRTHDATE","HIREDATE","BASE SALARY","EMP TYPE");
       System.out.println(LINE);
       for(int i = 0; i < employee.size(); i++)
           System.out.println(employee.get(i));
       System.out.println(LINE);
   }
   
   /**
    * prints all the employee with their details in one line format to an output file
    */
   public void printToFileEmp(){
       //output all the employees with their details to a file in formatted manner
       foutput.printf("%-3s %-10s %-10s %-8s %-15s %-15s %-12s %-12s\n","ID","FIRSTNAME","LASTNAME","GENDER","BIRTHDATE","HIREDATE","BASE SALARY","EMP TYPE");
       foutput.println(LINE);
       for(int i = 0; i < employee.size(); i++)
           foutput.println(employee.get(i));
       foutput.println(LINE);
   }
   
   /*******************************************
    *MENU IMPLEMENTATION FUNCTIONS BELOW HERE *                                         *
    *******************************************/ 
   /**
    * displays the menu of project 2
    */
   public void proj2Menu(){
       
       System.out.println("----------------------------------------------------");
       System.out.println("1-->List all employees with details. ");
       System.out.println("2-->List all parking lot with details.");
       System.out.println("3-->List users with details.");
       System.out.println("4-->List the parking lots where this car has parked.");
       System.out.println("5-->Display collection for a meter.");
       System.out.println("6-->Release an employee.");
       System.out.println("7-->List of tickets issued.");
       System.out.println("----------------------------------------------------");
       
       foutput.println("----------------------------------------------------");
       foutput.println("1-->List all employees with details. ");
       foutput.println("2-->List all parking lot with details.");
       foutput.println("3-->List users with details.");
       foutput.println("4-->List the parking lots where this car has parked.");
       foutput.println("5-->Display collection for a meter.");
       foutput.println("6-->Release an employee.");
       foutput.println("7-->List of tickets issued.");
       foutput.println("----------------------------------------------------");
   
   }
   
   /**
    * implements the menu number 11 of the project description
    * accepts parking info for regular parking lot
    * @param lotNumber is the parking lot number
    * @param parkingDate is the parking date
    * @param parkingTag is the vehicle tag number
    * @param parkingType is the parking status type (ENTER/EXIT)
    */
   
   //method implements menu 11 for regular parking information
    public void displayParkingInfo(int lotNumber,Date parkingDate,String parkingTag,String parkingType){
       System.out.println(ASTERIK);
       foutput.println(ASTERIK);
       
       boolean lotFound = false;
       System.out.println("\nPARKING INFORMATION:");
       foutput.println("\nPARKING INFORMATION:");
       System.out.println("-----------------------\n");
       foutput.println("-----------------------\n");
       
       
       ParkingInfo info = new ParkingInfo(lotNumber,parkingDate,parkingTag,parkingType);
       int k = 1;//variable used for keeping record of the position of the parking lot information in the arraylist
       for(int i = 0; i < parkingLot.size(); i++){
           if(parkingLot.get(i).getLotNumber() == lotNumber){  //adding a parking info in the matched parking lot
               k = i;//storing the position in the arraylist
               lotFound = true;
               if(parkingLot.get(i).getFreeSpaces() == 0 && parkingType.toUpperCase().equals("ENTER")){ // checking to see if parking lot is full before entry
                   System.out.println("PARKING LOT FULL!!");
                   foutput.println("PARKING LOT FULL!!");
               }//end if
               else if(parkingLot.get(i).getOccupiedNum() == 0 && parkingType.toUpperCase().equals("EXIT")){ // checking to see if parking lot is empty before exiting
                   System.out.println("PARKING LOT WAS EMPTY. INVALID PARKING INFO.");
                   foutput.println("PARKING LOT WAS EMPTY.INVALID PARKING INFO.");
               }
               else{//valid to add the parking infromation now
                   parkingLot.get(i).addParkingInfo(info);//adding the parking information within a parking lot

                   if(parkingType.toUpperCase().equals("ENTER")){
                       parkingLot.get(i).occupySpace();//incrementing the space counter in parking lot
                   }
                   else if(parkingType.toUpperCase().equals("EXIT")){
                       parkingLot.get(i).freeSpace();//decrementing the space counter in parkinglot
                   }//end if
               }//end else
           }//end if
       }//end for
       
       if(lotFound == false){
           System.out.println("LOT NUMBER DOESN'T MATCH, RECORD NOT SAVED IN DATABASE");
           foutput.println("LOT NUMBER DOESN'T MATCH,RECORD NOT SAVED IN DATABASE");
       }
       
       System.out.printf("%-7s %-15S %-15S %-7S %-10s %-12s %-12s\n","LOT NO","PARKING DATE","VEHICLE TAG","STATUS","METER #","ENTER TIME","EXIT TIME");
       System.out.println(LINE);
       foutput.printf("%-7s %-15S %-15S %-7S %-10s %-12s %-12s\n","LOT NO","PARKING DATE","VEHICLE TAG","STATUS","METER #","ENTER TIME","EXIT TIME");
       foutput.println(LINE);
       
       boolean tagFound = false;
       //checking for all the records associated witha car tag and printing in formatted order
       for(int i = 0; i < parkingLot.size(); i++){
           for(int j = 0; j < parkingLot.get(i).getRecordNum(); j++){
               if(parkingTag.toUpperCase().equals(parkingLot.get(i).getRecords().get(j).getCarTag())){ 
                   tagFound = true; //car tag found
                   System.out.println(parkingLot.get(i).getRecords().get(j));
                   foutput.println(parkingLot.get(i).getRecords().get(j));//printing to a file
               }//end if
           }//end for
       }//end for
       
       if(tagFound == false){
           System.out.println("NO PARKING INFO FOR THIS CAR TAG FOUND");
           foutput.println("NO PARKNIG INFO FOR THIS CAR TAG FOUND");
       }
       //printing information about free and occupied space
       System.out.printf("IN LOT NUMBER %d  FREE SPACE: %d  OCCUPIED SPACE: %d\n",parkingLot.get(k).getLotNumber(),parkingLot.get(k).getFreeSpaces(),parkingLot.get(k).getOccupiedNum());
       foutput.printf("IN LOT NUMBER %d FREE SPACE: %d  OCCUPIED SPACE: %d\n",parkingLot.get(k).getLotNumber(),parkingLot.get(k).getFreeSpaces(),parkingLot.get(k).getOccupiedNum());
       
       System.out.println(LINE);
       foutput.println(LINE);
   }
   
   /**
    * implements the menu number 11 of the project description
    * accepts parking information for metered parking lot
    * @param lotNumber is the parking lot number
    * @param parkingDate is the parking date
    * @param parkingTag is the vehicle tag number
    * @param meterNumber is the meter number for parking
    * @param startTime is the meter start time
    * @param endTime is the meter end time
    * @param meterAmount is the amount charged per hour parking
    */
    
    //method implements menu 11 for metered parking information
    public void displayParkingInfo(int lotNumber,Date parkingDate,String parkingTag,int meterNumber,String startTime,String endTime,double meterAmount){
       System.out.println(ASTERIK);
       foutput.println(ASTERIK);
       //creating time objects to be used 
       Time enterTime = new Time(startTime);
       Time exitTime = new Time(endTime);
       
       System.out.println("\nPARKING INFORMATION:");
       foutput.println("\nPARKING INFORMATION:");
       System.out.println("-----------------------\n");
       foutput.println("-----------------------\n");
       //creating a new parking information
       ParkingInfo info = new ParkingInfo(lotNumber,parkingDate,parkingTag,meterNumber,enterTime,exitTime,meterAmount);
       int k = 1;//for storing the position of the required parkinglot object in the arraylist
       
       boolean lotFound = false;
       boolean meterExists = true;
       for(int i = 0; i < parkingLot.size(); i++){
           //checking if the current lot in a metered lot and performing operation specific to metered lot
           if(parkingLot.get(i).getLotNumber() == lotNumber && parkingLot.get(i) instanceof MeteredLot){ // store information in this parking lot
               k = i;//storing the position to be used later
               lotFound = true;
               MeteredLot meterLot = (MeteredLot)parkingLot.get(i);
               if(meterLot.checkMeter(lotNumber, meterNumber, enterTime, exitTime)){ // checking to see if meter is occupied
                   System.out.println("METER OCCUPIED");
                   foutput.println("METER OCCUPIED");
               }
               else{ // everything is ok. valid to store the parking information now
                   parkingLot.get(i).addParkingInfo(info);
               }
           }
       }
       
       if(lotFound == false){
           System.out.println("COULDN'T FIND LOT NUMBER, INFO NOT RECORDED");
           foutput.println("COULDN'T FIND LOT NUMBER,INFO NOT RECORDED");
       }
       
       System.out.printf("%-7s %-15S %-15S %-7S %-10s %-12s %-12s\n","LOT NO","PARKING DATE","VEHICLE TAG","STATUS","METER #","ENTER TIME","EXIT TIME");
       System.out.println(LINE);
       foutput.printf("%-7s %-15S %-15S %-7S %-10s %-12s %-12s\n","LOT NO","PARKING DATE","VEHICLE TAG","STATUS","METER #","ENTER TIME","EXIT TIME");
       foutput.println(LINE);
       
       boolean tagFound = false;
       //checking all the records for a vehicle tag and printing the record and printing to a file as well
       for(int i = 0; i < parkingLot.size(); i++){
           for(int j = 0; j < parkingLot.get(i).getRecordNum(); j++){
               if(parkingTag.toUpperCase().equals(parkingLot.get(i).getRecords().get(j).getCarTag())){  //checking parking tags in all the parking information
                   tagFound = true;
                   System.out.println(parkingLot.get(i).getRecords().get(j));
                   foutput.println(parkingLot.get(i).getRecords().get(j));
               }//end if
           }//end for
       }//end for
       
       if(tagFound == false){
           System.out.println("couldn't find parking information for this car tag");
           foutput.println("couldn't find parking information for this car tag");
       }
       
       //printing information about free meters and occupied meters
       System.out.printf("IN LOT NUMBER %d  FREE METERS: %d  OCCUPIED METERS: %d\n",parkingLot.get(k).getLotNumber(),parkingLot.get(k).getFreeSpaces(),parkingLot.get(k).getOccupiedNum());
       foutput.printf("IN LOT NUMBER %d FREE METERS: %d  OCCUPIED METERS: %d\n",parkingLot.get(k).getLotNumber(),parkingLot.get(k).getFreeSpaces(),parkingLot.get(k).getOccupiedNum());
       
       System.out.println(LINE);
       foutput.println(LINE);
  }//end menu11M
   
   
    /**
     * implements menu12 for parking ticket issued at a regular parking lot
     * accepts ticket information for a regular parking lot
     * @param ticketLotNumber is the lot number where the ticket was issued
     * @param ticketDate is the date when the ticket was issued
     * @param carTag is the vehicle tag to which the ticket was issued
     * @param ticketTime is the time when the ticket was issued
     * @param ticketAmount is the amount of the ticket issued
     */
    
    //method implements menu 12 for ticket issued at regular parking lot
    public void displayTicketInfo(int ticketLotNumber,String ticketDate,String carTag,String ticketTime,double ticketAmount){
       
       System.out.println(ASTERIK);
       foutput.println(ASTERIK);
       
       //creating appropriate date and time objects
       Date ticketIssueDate = new Date(ticketDate);
       Time ticketIssueTime = new Time(ticketTime);
       
       System.out.println("TICKET INFORMATION FOR CAR TAG: " + carTag);
       System.out.println("-----------------------------------------\n");
       
       foutput.println("TICKET INFORMATION FOR CAR TAG: " + carTag);
       foutput.println("-------------------------------------------\n");
       //creating a new ticket object
       TicketInfo ticketInfo = new TicketInfo(ticketLotNumber,ticketIssueDate,carTag,ticketIssueTime,ticketAmount);
       
       
       for(int i = 0; i < user.size();i++){
           if(user.get(i).getCarTag().equals(carTag.toUpperCase())){ // adding ticket information in the correct user object.every user object has a unique car tag
               user.get(i).addTicket(ticketInfo);
           }// end if
       }//end for
       
       //for formatted output
       System.out.printf("%-5s %-15s %-8s %-15s %-15s %-8s\n","LOT#","TICKET DATE","CAR TAG","TICKET TIME","TICKET AMOUNT","METER#");
       System.out.println(LINE);
       
       foutput.printf("%-5s %-15s %-8s %-15s %-15s %-8s\n","LOT#","TICKET DATE","CAR TAG","TICKET TIME","TICKET AMOUNT","METER#");
       foutput.println(LINE);
       
       boolean tagFound = false;
       
       //going through all the user and their ticket records and printing all associated with a given car tag
       for(int i = 0; i < user.size(); i++){
           for(int j = 0; j < user.get(i).getRecordSize(); j++){
               if(carTag.toUpperCase().equals(user.get(i).getRecords().get(j).getTag())){ // car tag matches then print
                   tagFound = true;
                   System.out.println(user.get(i).getRecords().get(j));
                   foutput.println(user.get(i).getRecords().get(j));
               }//end if
           }//end for
       }//end for
       
       if(tagFound == false){
           System.out.println("couldn't find ticket information for this car tag");
           foutput.println("couldn't find ticket information for this car tag");
       }
       
       System.out.println(LINE);
       foutput.println(LINE);
   }
   
   /**
    * implements menu12 for parking ticket issued at a metered parking lot
    * accepts ticket information for a metered parking lot
    * @param ticketLotNumber is the lot number where the ticket was issued
    * @param ticketDate is the date when the ticket was issued
    * @param carTag is the vehicle tag to which the ticket was issued
    * @param ticketTime is the time when the ticket was issued
    * @param ticketAmount is the amount of the ticket issued
    * @param meterNumber is the meter number where the ticket was issued 
    */ 
   
   //method implements menu 12 for ticket issued at a metered parking lot
   public void displayTicketInfo(int ticketLotNumber,String ticketDate,String ticketTag,String ticketTime,double ticketAmount,int meterNumber){
       
       System.out.println(ASTERIK);
       foutput.println(ASTERIK);
       
       //creating appropriate date and time objects
       Date ticketIssueDate = new Date(ticketDate);
       Time ticketIssueTime = new Time(ticketTime);
       
       boolean userExists = false;//for error checking
       
       System.out.println("TICKET INFORMATION FOR CAR TAG: " + ticketTag);
       System.out.println("--------------------------------------------\n");
       
       foutput.println("TICKET INFORMATION FOR CAR TAG: " + ticketTag);
       foutput.println("----------------------------------------------\n");
       
       //creating a new ticket information
       TicketInfo ticketInfo = new TicketInfo(ticketLotNumber,ticketIssueDate,ticketTag,ticketIssueTime,ticketAmount,meterNumber);
       
       //adding ticket information in the appropriate user object
       for(int i = 0; i < user.size(); i++){
           if(user.get(i).getCarTag().equals(ticketTag.toUpperCase())){ // checking for a matching user tag
               user.get(i).addTicket(ticketInfo);
           }//endif
       }//end for
       
       System.out.printf("%-5s %-15s %-8s %-15s %-15s %-8s\n","LOT#","TICKET DATE","CAR TAG","TICKET TIME","TICKET AMOUNT","METER#");
       System.out.println(LINE);
       
       foutput.printf("%-5s %-15s %-8s %-15s %-15s %-8s\n","LOT#","TICKET DATE","CAR TAG","TICKET TIME","TICKET AMOUNT","METER#");
       foutput.println(LINE);
       
       //going through all the records and printing the one which matches the car tag
       for(int i = 0; i < user.size(); i++){
           for(int j = 0; j < user.get(i).getRecordSize(); j++){
               if(ticketTag.toUpperCase().equals(user.get(i).getRecords().get(j).getTag())){
                   userExists = true;
                   System.out.println(user.get(i).getRecords().get(j));//printing to command prompt
                   foutput.println(user.get(i).getRecords().get(j));//printing to file
               }//end if
           }//end for
       }//end for
       //no match found for the vehicle tag. means no user exist that has a vehicle with that parking tag
       if(userExists == false){
           System.out.println("VEHICLE PARKING TAG DOESN'T EXIST");
           foutput.println("VEHICLE PARKING TAG DOESN'T EXIST");
       }
       
       System.out.println(LINE);
       foutput.println(LINE);
   }//end menu 12R
               
   
   /**
    * implements menu 13 in the project description
    * accepts information for hiring a new employee
    * @param empType is the type of employee
    * @param firstName is the first name of the employee
    * @param lastName is the last name of the employee
    * @param dateOfBirth is the date of birth of the employee
    * @param gender is the gender of the employee
    * @param dateOfHire is the date of Hire of the employee
    * @param baseSalary is the monthly base salary of the employee
    */
   
   //method implements menu13 from the project description
   public void hireEmployee(String empType,String firstName,String lastName,String dateOfBirth,
                      String gender,     String dateOfHire,double baseSalary,double value){
       
       System.out.println(ASTERIK);
       foutput.println(ASTERIK);
       
       //creating appropriate date objects
       Date dob = new Date(dateOfBirth);
       Date doh = new Date(dateOfHire);
       
       //creating a new employee object from the argument provided to the method
       this.createEmployee(empType,firstName,lastName,gender,dob,doh,baseSalary,value);
       
       System.out.println("EMPLOYEE HIRED");
       System.out.println("----------------");
       foutput.println("EMPLOYEE HIRED");
       foutput.println("-----------------");
       
       //going through all the employee and printing the employee just hired
       for(int i = 0; i < employee.size(); i++){
           if(employee.get(i).getFirstName().equals(firstName.toUpperCase()) && employee.get(i).getLastName().equals(lastName.toUpperCase())
           && employee.get(i).getDOB().equals(dob)){ // finding the correct employee to print
               System.out.printf("%-3s %-10s %-10s %-8s %-15s %-15s %-12s %-12s\n","ID","FIRSTNAME","LASTNAME","GENDER","BIRTHDATE","HIREDATE","BASE SALARY","EMP TYPE");
               System.out.println(LINE);
               System.out.println(employee.get(i));
               System.out.println(LINE);
               
               foutput.printf("%-3s %-10s %-10s %-8s %-15s %-15s %-12s %-12s\n","ID","FIRSTNAME","LASTNAME","GENDER","BIRTHDATE","HIREDATE","BASE SALARY","EMP TYPE");
               foutput.println(LINE);
               foutput.println(employee.get(i));
               foutput.println(LINE);
            }
       }
   }
   
   /**
    * implements menu 14 in the project description
    * removes an employee from the list and displays the remaining employees and their details
    * @param empId is the employee identification number
    */
   //method implements menu 14 of the project description
   public void removeEmployee(int empId){
       System.out.println(ASTERIK);
       foutput.println(ASTERIK);
       boolean found = false; // for checking if employee id was found
       
       //searching for the correct employee based on the id to remove
       for(int i = 0; i < employee.size(); i++){
           if(employee.get(i).getId() == empId){
               found = true;
               System.out.println("EMPLOYEE REMOVED , ID: " + empId);
               foutput.println("EMPLOYEE REMOVED , ID: " + empId);
               employee.remove(i);//removing employee from the arraylist
           }
       }
       
       if(found == false){//informing that no employees were removed sincel no id matched with the given id
           System.out.println("employee id was not found, no employee were removed");
           foutput.println("employee id was not found, no employee were removed");
       }
       //displaying remaining employees and their details in command window and file
       System.out.println("REMAINING EMPLOYEES");
       foutput.println("REMAINING EMPLOYEES");
       this.printEmployees();//printing in command window
       this.printToFileEmp();//printing in file
  }//end of method
  
  /**
    * implements menu 15 in the project description
    * computer the salary based on the employee type for an employee and displays salary with other informations
    * @param empId is the employee identification number
    * @param salaryParam is the salary parameter based on the employee type
    */
   
  //method implements menu 15 of the project description
  public void getSalary(int empId,int salaryParam){
      
      System.out.println(ASTERIK);//asterik is defined in the project3 constants
      foutput.println(ASTERIK);
      
      double salary = 0;//for holding the computed salary value
      boolean found = false;
      for(int i =0; i < employee.size(); i++){
          if(employee.get(i).getId() == empId){ //id match, employee found
              found = true;//employee found
              salary = employee.get(i).computeSalary(salaryParam); // polymorphically calling the compute salary method and storing in salary
              
              //formatted output of the employee details with their computed salary
              System.out.printf("%-3s %-10s %-10s %-10s %-8s %-15s\n","ID","EMP TYPE","FIRSTNAME","LASTNAME","GENDER","MONTHLY SALARY");
              System.out.println(LINE);
              System.out.printf("%-3d %-10s %-10s %-10s %-8s $%-15.2f\n\n",empId,employee.get(i).getEmpType(),employee.get(i).getFirstName(),
                                employee.get(i).getLastName(),employee.get(i).getGender(),salary);

              foutput.printf("%-3s %-10s %-10s %-10s %-8s %-15s\n","ID","EMP TYPE","FIRSTNAME","LASTNAME","GENDER","MONTHLY SALARY");
              foutput.println(LINE);
              foutput.printf("%-3d %-10s %-10s %-10s %-8s $%-15.2f\n\n",empId,employee.get(i).getEmpType(),employee.get(i).getFirstName(),
                                employee.get(i).getLastName(),employee.get(i).getGender(),salary);
          }//endif
      }//end for
      
      //no id match for any employee
      if(found == false){
          System.out.println("EMPLOYEE NOT FOUND");
          foutput.println("EMPLOYEE NOT FOUND");
      }
  }//end of menu15
      
  
  /**
   * implements menu 16 of the project description
   * computes revenue based on the number of arguments and argument type and displays it
   * @param args is the lot number and meter number (but can accept only lot number , also can accept * instead of numeric values)
   */
  
  //method implements menu 16 of the project description
  public void getRevenue(String ...args){ //reading variable length arguments
      //storing as a string
      String lotNumber;
      String meterNumber;
      
      //using as an int for computation
      int lotNo;
      int meterNo;
      
      double regularRevenue = 0;//for storing revenue generated from regular parking lot
      double meterRevenue = 0;//for storing revenue generated from metered parking lot
      double totalRevenue = 0;//for storing total revenue
      
      if(args.length == 1){//only single argument was passed
          lotNumber = args[0];//storing the parking lot number as a string
          if(lotNumber.equals("*")){ // revenue from all the parking lots
              System.out.println("REVENUE FROM ALL PARKING LOTS");
              foutput.println("REVENUE FROM ALL PARKING LOTS");
              for(int i = 0; i < parkingLot.size(); i++){
                  if(parkingLot.get(i).getLotNumber() <= 5)//revenues from regular lots
                    regularRevenue = regularRevenue + parkingLot.get(i).computeRevenue();
                  else//revenues from metered lots
                    meterRevenue = meterRevenue + parkingLot.get(i).computeRevenue();
              }//end for
              totalRevenue = regularRevenue + meterRevenue;//calculating total revenue
          }//end if
      }//end if
      else if(args.length == 2){//two arguments were passed to the method
          lotNumber = args[0];//first argument lot number
          meterNumber = args[1];//second argument, meter number
          
          if(lotNumber.equals("*")){//print revenues from all parking lots , ignore second argument
              System.out.println("REVENUE FROM ALL PARKING LOTS");
              foutput.println("REVENUE FROM ALL PARKING LOTS");
              for(int i = 0; i < parkingLot.size(); i++){
                  if(parkingLot.get(i).getLotNumber() <= 5)
                    regularRevenue = regularRevenue + parkingLot.get(i).computeRevenue();
                  else
                    meterRevenue = meterRevenue + parkingLot.get(i).computeRevenue();
              }//end for
              totalRevenue = regularRevenue + meterRevenue;
          }//endif
          else{//use lot number
              lotNo = Integer.parseInt(lotNumber);//converting to int
              if(meterNumber.equals("*")){//if second argument is not an int calculate for all meters of that lotNo if metered, for that lot if regular
                  System.out.println("REVENUE FROM LOT NO " + lotNo);
                  foutput.println("REVENUE FROM LOT NO " + lotNo);
                  for(int i = 0; i < parkingLot.size(); i++){//checking for the parking lot number
                    if(parkingLot.get(i).getLotNumber() == lotNo ){//only compute revenue from that parking lot
                        if(parkingLot.get(i).getLotNumber() <= 5)
                            regularRevenue = regularRevenue + parkingLot.get(i).computeRevenue();
                        else
                            meterRevenue = meterRevenue + parkingLot.get(i).computeRevenue();
                    }//endif
                  }//end for
                  totalRevenue = regularRevenue + meterRevenue;
              }//endif
              else{//use first and second parameter. lot number , meter number respectively
                  meterNo = Integer.parseInt(meterNumber);//converting to int
                  System.out.println("REVENUE FROM PARKING LOT NO " + lotNo + " METER NO: " + meterNo);
                  foutput.println("REVENUE FROM PARKING LOT NO " + lotNo + " METER NO: " + meterNo);
                  for(int i = 0; i < parkingLot.size(); i++){
                            if(parkingLot.get(i).getLotNumber() == lotNo && parkingLot.get(i) instanceof MeteredLot){//checking for the lot number
                                MeteredLot meterLot = (MeteredLot)parkingLot.get(i);
                                totalRevenue = totalRevenue + meterLot.meterRevenue(meterNo);//computing revenue for that meter only
                            }//endif
                  }//end for
             }//end else
          }//end else
      }//end else if
      
      
      if(totalRevenue > 0.0){
        foutput.printf("$%.2f\n",totalRevenue);
        System.out.printf("$%.2f\n",totalRevenue);
      }
      else{//no revenue was computed. either meter doesn't exist or no parking has been done on that meter
        foutput.printf("LOT OR METER DOESN'T EXIST OR METER HAS NOT BEEN USED\n");
        System.out.printf("LOT OR METER DOESN'T EXIST OR METER HAS NOT BEEN USED\n");
      }
  }//end of menu16
  
  
  /**
   * implements menu 17 of the project description
   * displays all the ticket issued to a car 
   * @param carTag is the vehicle tag number
   */
  //method implements menu 17 from the project description
  public void ticketList(String carTag){
      int count = 0;//ticket counter
      
      //for formatted output
      System.out.println(ASTERIK);
      foutput.println(ASTERIK);
  
      System.out.println("TICKET INFORMATION FOR CAR TAG: " + carTag);
      foutput.println("TICKET INFORMATION FOR CAR TAG: " + carTag);
      
       System.out.printf("%-5s %-15s %-8s %-15s %-15s %-8s\n","LOT#","TICKET DATE","CAR TAG","TICKET TIME","TICKET AMOUNT","METER#");
       System.out.println(LINE);
       
       foutput.printf("%-5s %-15s %-8s %-15s %-15s %-8s\n","LOT#","TICKET DATE","CAR TAG","TICKET TIME","TICKET AMOUNT","METER#");
       foutput.println(LINE);
       
       //going through all the ticket records within all the users
       for(int i = 0; i < user.size(); i++){
           for(int j = 0; j < user.get(i).getRecordSize(); j++){
               if(carTag.toUpperCase().equals(user.get(i).getRecords().get(j).getTag())){ // if parking tag matches with the record print it
                   count++;//incrementing  counter
                   System.out.println(user.get(i).getRecords().get(j));
                   foutput.println(user.get(i).getRecords().get(j));
               }//end if
           }//end for
       }//end for
      
      if(count == 0){ // no records exist for this vehicle tag number
          System.out.println("TICKET NOT FOUND");
          foutput.println("TICKET NOT FOUND");
      }
  }//end of menu 17
  
  /**
   * implements Gui for the MavPark system
   */
  
  public void displayGui(){
      Gui mavGui = new Gui();
  }
    
}//end of class  MavPark 
    
    
    