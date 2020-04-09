/**
 * Programmer: 	Sharma Chakravarthy
 * Language:	Java
 * date:        02/01/2013
 * Purpose:		This program uses MavParkTest class to read data from a text file to initialize
 * 				employees, parking lots, users, usage etc.
 * 				
 *              It checks and recovers from some exceptions while reading the input file
 * 
 * USAGE:       You need to initialize your data structures (creation of objects) as the first step. 
 *              once the values are read into local variables, 
 *              it  is YOUR responsibility to add code at proper places to create objects and manage them!
 *
 *              filename is given as a command line argument (e.g, java MavParkTest dataFile-proj3.txt)
 *              for the naming convention used in this file. if you forget to give the data 
 *              file as a command line argument, it will prompt you for that.	
 *          
 *              you can remove or comment out prints once you are sure it is reading the input correctly
 *
 * MAKE SURE:   your program is NOT case sensitive (for vehicle tag, gender, employee type etc.)
 */

import java.io.*;
import java.util.Scanner;

/**
 * driver class for MavPark system
 * @param fileName
 *            as input data filename contaning input items with  as item separators
 * @param   outputFileName as output file name 
 */

public class MavParkTest implements Proj3Constants, DateConstants {

	// introduce your (class and instance) attributes (if needed) for this test class
    // as i have indicated, it is preferable to have a MavPark class for the enterprise
    // and use this ONLY as a driver or test class
    // that way, this class can be removed to make your system a library!!

    private static BufferedReader finput;   //for reading from a file
    private static Scanner cp;              //this is still command prompt
    private static PrintWriter foutput;     //for writing to a file

    //define other variables as needed

	//Note that we are using a DIFFERENT method for reading input file;
	/**
	 * @param iFileName is the input data file name
	 */		

    public static BufferedReader openReadFile(String fileName){
        BufferedReader bf = null;
        try{
            bf = new BufferedReader(new FileReader(fileName));
        }     
        catch(FileNotFoundException FNFE){    
          bf = null;
        }
       finally{
          return bf;
       }
    }

/**
	 * @param oFileName is the input data file name
	 */		

    
    public static PrintWriter openWriteFile(String fileName){
        PrintWriter outputFile = null;
        try{
            outputFile = new PrintWriter(new FileWriter(fileName));
        }     
        catch(IOException IOE){    
          outputFile = null;
        }
       finally{
          return outputFile;
       }
    }

    // add here constructors and methods if you are also using this
    // as the enterprise (MavPark) class

	/**
	 * @param takes
	 *            fineName as command line argument. prompts if not given
	 */
    public static void main(String[] args) {
        
        // declare variables used for input handling
        String enterprisename = DEFAULT_ENTERPRISE_NAME;
        String inputLine = EMPTY_STRING, ifName = EMPTY_STRING, ofName = EMPTY_STRING;

        // determine if input file is provided

	cp = new Scanner(System.in);
        if (args.length < 1) {
                System.out.println("Input Data file name was not supplied");
		System.out.printf("Please type input data file name: ");
		ifName = cp.nextLine();
        } 
        else if (args.length < 2){
            ifName =  args[ZEROI];
            System.out.println("Output file name was not supplied");       
            System.out.printf("Please type output file name: ");
            ofName = cp.nextLine();
        } 
        else {
            ifName = args[ZEROI];
            ofName = args[ONEI];
        }
        // See HOW RECOVERY is done (will cover in a few weeks)
        finput = openReadFile(ifName);
	while (finput == null) {
            System.out.printf("Error: input data FILE %s %s", ifName,
					" does not exist.\nEnter correct INPUT data file name: ");
            ifName = cp.nextLine();
            finput = openReadFile(ifName);
	}
        
        foutput = openWriteFile(ofName);
        
        while (foutput == null){
            System.out.printf("Error: OUTPUT FILE %s %s",  ofName,  
                        " does not exist.\nEnter correct OUTPUT FILE name: ");
            ofName = cp.nextLine();
            foutput = openWriteFile(ofName);
	}  
        System.out.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName);
        
        foutput.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName); //writing to a file!
		/* GET MavPark DETAILS */
		// start reading from data file
		// get name
	try {
            inputLine = finput.readLine();
            foutput.println(inputLine);
            while (inputLine.charAt(BASE_INDEX) == '/'){
		inputLine = finput.readLine();
                foutput.println(inputLine);
            }
            
            String enterpriseName = inputLine;
            //System.out.printf("\n%s %s \n", "Enterprise name is: ",
	    //				enterpriseName);

            // add code as needed to construct/store enterprise name somewhere in an object!
 
                        
                        
 //------------------creating mavSystem object-------------------------------------------------------------------------
            MavPark mavSystem = new MavPark();                                                 
            mavSystem.setEnterpriseName(enterpriseName);//setting enterprise name
            mavSystem.setFileOutput(foutput);//passing fouput so that file writing can be done through mavpark  class                                                //|
 //--------------------------------------------------------------------------------------------------------------------
                        
            // add code here: to initialize your arrays, arraylists,
            // and attributes as needed

            /* GET EMPLOYEE DETAILS */

            // reading details for each employee from the data file
            //System.out.printf("\nReading Employees: \n");
	    
            int numEmployees = 0;
            inputLine = finput.readLine();
            foutput.println(inputLine);
			
            while (inputLine.charAt(BASE_INDEX) == '/'){
                    inputLine = finput.readLine();		
                    foutput.println(inputLine);
            }
	
            while ( (!inputLine.toLowerCase().equals("end"))){
            	
                String[] chopEmp = inputLine.split("!");

                // fill all fields for a single employee from a single line
                String empType = chopEmp[ZEROI].toUpperCase();
                String empFName = chopEmp[ONEI];
                String empLName = chopEmp[TWOI];
                String empBDate = chopEmp[THREEI];
                String empGender = chopEmp[FOURI].toUpperCase();
                String empHireDate = chopEmp[FIVEI];
                double empBaseSalary = Double.parseDouble(chopEmp[SIXI]);
                double value = Double.parseDouble(chopEmp[SEVENI]);
                // add code here: in order to convert a date string to a Date object,
                // invoke the appropriate constructor of the Date class
                Date dob = new Date(empBDate); // dob is a local variable
                Date doh = new Date(empHireDate);//doh is a local variable


                /*
                System.out.printf("(%6s, %10s, %6s, %12s, %10.2f, %4s, %12s)\n",
                                empFName, empLName, empGender, empHireDate,
                                empBaseSalary, empType, dob);
                
                */
                 // add code here: based on the empType appripriate class objects 

                //creating a new employee by calling a method CreateEmployee from MavPark Class
                //------------------------------------------------------------------------------------------------------
                mavSystem.createEmployee(empType,empFName, empLName,empGender, dob, doh, empBaseSalary,value);
                //-------------------------------------------------------------------------------------------------------
                // of the inheritance hierarchy need to be created
                //make sure you set the release date to null (will be used later)
                //add this employee to an array or array list
                
                inputLine = finput.readLine();
                foutput.println(inputLine);
		
                while (inputLine.charAt(BASE_INDEX) == '/'){
                    inputLine = finput.readLine();
                    foutput.println(inputLine);	
                }
                
                numEmployees +=1;
            }
            //System.out.printf("#Employees: %d\n", numEmployees);

            /* GET PARKING LOT DETAILS */

            //System.out.printf("\nReading Parking Lots: \n");
	    int numParkingLots = 0;
            inputLine = finput.readLine();
            foutput.println(inputLine);
            while (inputLine.charAt(BASE_INDEX) == '/'){
                    inputLine = finput.readLine();		
                    foutput.println(inputLine);
            }
            
            while ( (!inputLine.toLowerCase().equals("end"))){
            	String[] chopPLots = inputLine.split("!");

            	// get fields of a parking lot from one line of input
				
                String  lotName = chopPLots[ZEROI];
		int     lotNumber = Integer.parseInt(chopPLots[ONEI]);
                String  lotLocation = chopPLots[TWOI];
		int     totSpaces = Integer.parseInt(chopPLots[THREEI]);
		int     reservedSpaces = Integer.parseInt(chopPLots[FOURI]);
		String  lotType = chopPLots[FIVEI].toUpperCase();
                double  lotCost = Double.parseDouble(chopPLots[SIXI]);
                
                //creating a new parking lot by calling method from Mavpark Class
                mavSystem.createParkingLot(lotName,lotNumber,lotLocation,totSpaces,reservedSpaces,lotType,lotCost);

		/*
                System.out.printf("[%3d, %20s, %10s, %4d, %3d, %s, %10.2f]\n", lotNumber,
					lotName, lotLocation, totSpaces, reservedSpaces,
					lotType, lotCost);
                */
		// add code here to construct a parking lot object
                //add code to add parking lot object to the enterprise object
                inputLine = finput.readLine();
		while (inputLine.charAt(BASE_INDEX) == '/')
                    inputLine = finput.readLine();	
                numParkingLots +=1;
	}
        
        //System.out.printf("#ParkingLots: %d\n", numParkingLots);
	/* GET USER DETAILS */
        // reading details for each user from the data file
        // note that numUsers is computed, not input!
			
        //System.out.printf("\nUsers: \n");
	
        int numUsers =0;
	inputLine = finput.readLine();
        foutput.println(inputLine);
                
        while (inputLine.charAt(BASE_INDEX) == '/'){
            inputLine = finput.readLine();
            foutput.println(inputLine);
        }
        
        while ( (!inputLine.toLowerCase().equals("end"))){
                //System.out.println(inputLine);
		String[] chopUser = inputLine.split("!");

		// fill all fields for a single user from a single line
		int    userId = Integer.parseInt(chopUser[ZEROI]);
                String userVehicleTag = chopUser[ONEI].toUpperCase();
		String userFName = chopUser[TWOI];
		String userLName = chopUser[THREEI];
		String userDOB = chopUser[FOURI];
		String userGender = chopUser[FIVEI];
		String userAddress = chopUser[SIXI];
		int    userZipcode = Integer.parseInt(chopUser[SEVENI]);
		String userType = chopUser[EIGHTI].toUpperCase();
		String userClassification = chopUser[NINEI].toUpperCase();
                
                //creating a new user by calling a method form mavpark class
                mavSystem.createUser(userId,userVehicleTag,userFName,userLName,userDOB,userGender,userAddress,userZipcode,userType,userClassification);

		/*
                System.out.printf("{%6d, %10s, %10s, %10s, %10s, %6s, %20s, %6d, %4s, %5s} \n",
				  userId, userVehicleTag, userFName, userLName, userDOB,
				  userGender, userAddress, userZipcode,userType, userClassification);
                */
		// add code: create a user object and add it to the enterprise

                inputLine = finput.readLine();
                foutput.println(inputLine);
		
                while (inputLine.charAt(BASE_INDEX) == '/'){
                    inputLine = finput.readLine();
                    foutput.println(inputLine);
                }
                numUsers += 1;
	    }
            //System.out.printf("#users: %d\n", numUsers);

            // you will be adding MOST of your code here
            // add code for processing menu and its output
            // DO NOT REMOVE THE CODE but add to it
            //remove print stmts once you have made sure input processing is correct!
            
            //System.out.printf("\nStart Processing Menu: \n\n");
            foutput.println("\nStart Processing Menu: \n\n");
            System.out.printf("[WELCOME TO THE %s PARKING SYSTEM SOFTWARE]\n\n",enterpriseName);
            foutput.printf("[WELCOME TO THE %s PARKING SYSTEM SOFTWARE]\n\n",enterpriseName);
            
            //read each line as before and process according to the menu number
            inputLine = finput.readLine();
            System.out.println(inputLine);
            foutput.println( inputLine);
            while (inputLine.charAt(BASE_INDEX) == '/'){
		inputLine = finput.readLine();
                //System.out.println(inputLine);
                //foutput.println( inputLine );
            }
            
            System.out.println("["+ inputLine + "] ");
            foutput.println("["+ inputLine + "] ");
            
            boolean guiExecute = false;
            
            while ( (!inputLine.toLowerCase().equals("end"))){
                String[] chopMenuLine = inputLine.split("!");
                
                switch (Integer.parseInt(chopMenuLine[ZEROI])){
            
                    case 10: //for processing project 1 commands
                            mavSystem.proj2Menu();
                            break;
                    case 11: //process parking info as in project 2
                            /* GET PARKING INFO */
                            // reading details for each parking from the data file
                            //you can also make a method and call it.

                            // fill all fields for a single parking from a single line
                            int parkingLotNumber = Integer.parseInt(chopMenuLine[ONEI]);
                            
                            String parkingDate = chopMenuLine[TWOI];
                            Date pDate = new Date(parkingDate);
                            
                            String parkingTag = chopMenuLine[THREEI];
                            
                            if ((parkingLotNumber >= 1) && (parkingLotNumber <= 5)){
                                String parkingType = chopMenuLine[FOURI].toUpperCase();
                                
                             //calling menu11R from mavpark class to implement the menu for regular parking lot   
                             //------------------------------------------------------------------------------
                                mavSystem.displayParkingInfo(parkingLotNumber,pDate,parkingTag,parkingType);
                             //------------------------------------------------------------------------------
                                //System.out.printf("{%4d, %10s, %10s, %5s} \n",parkingLotNumber, parkingDate, parkingTag, parkingType);
                                // add code: create a parking info bject as appropriate
                                //create time objects by calling the right constructor and store them 
                            }
                            else if ((parkingLotNumber) >= 6 && (parkingLotNumber <=10)){
                                int meterNumber = Integer.parseInt(chopMenuLine[FOURI]);
                                String startTime = chopMenuLine[FIVEI];
                                String endTime = chopMenuLine[SIXI];
                                double meterAmount = Double.parseDouble(chopMenuLine[SEVENI]);
                             
                             //calling menu11M from mavpark class to implement the menu for metered parking lot 
                             //--------------------------------------------------------------------------------------------------   
                                mavSystem.displayParkingInfo(parkingLotNumber, pDate, parkingTag, meterNumber, startTime, endTime, meterAmount);
                             //--------------------------------------------------------------------------------------------------  
                                /*
                                System.out.printf("{%4d, %10s, %10s, %4d, %10s, %10s, %10.2f} \n",
                                    parkingLotNumber, parkingDate, parkingTag, meterNumber,
                                    startTime, endTime, meterAmount);
                                */
                                // add code: create a parking info object as appropriate
                                //create time objects by calling the right constructor and store them 
                            } 
                            else{ 
                                System.out.println("Incorrect Parking lot number in:" + inputLine);   
                                foutput.println("Incorrect Parking Lot Number In:" + inputLine);
                            }    
                            break;
                
                    case 12:
                            /* GET TICKETING DETAILS */
			    // reading details for each ticket 
                            // you can also  make a method in this class, pass the aray
                            // fill all fields for a single ticket from a single line
                            int    ticketLotNumber = Integer.parseInt(chopMenuLine[ONEI]);
                            String ticketDate = chopMenuLine[TWOI];
                            String ticketTag = chopMenuLine[THREEI].toUpperCase();
                            String ticketTime = chopMenuLine[FOURI];
                            double ticketAmount = Double.parseDouble(chopMenuLine[FIVEI]);
                            if ((ticketLotNumber >= 1) && (ticketLotNumber <= 5)){
                                
                                //processing menu12 form mavpark class
                                //------------------------------------------------------------------------------
                                mavSystem.displayTicketInfo(ticketLotNumber,ticketDate,ticketTag,ticketTime,ticketAmount);
                                //------------------------------------------------------------------------------
                            /*
                            System.out.printf("{%4d, %10s, %10s, %10s, %10.2f} \n",
						ticketLotNumber, ticketDate, ticketTag, ticketTime, ticketAmount);
                            */    
                             // add code: create a ticketing data structures as appropriate
                            }
                            else if ((ticketLotNumber >= 6) && (ticketLotNumber <=10)){
                                int meterNumber = Integer.parseInt(chopMenuLine[SIXI]);	
                                
                                //processing menu12 from mavpark class
                                //-------------------------------------------------------------------------------------------
                                mavSystem.displayTicketInfo(ticketLotNumber,ticketDate,ticketTag,ticketTime,ticketAmount,meterNumber);
                                //--------------------------------------------------------------------------------------------
				/*
                                System.out.printf("{%4d, %10s, %10s, %10s, %10.2f, %5d} \n",
						ticketLotNumber, ticketDate, ticketTag, ticketTime, ticketAmount, meterNumber);
                                */
                            // add code: create a ticketing data structures as appropriate
                            } 
                            else { 
                                System.out.println("Incorrect Parking lot number in: %s: SKIPPED" + inputLine);
                                foutput.println("Incorrect Parking Lot Number in : %s: SKIPPED" + inputLine);
                            }

                            break;
                    case 13: //process hire employee
                        
                            //getting all the employee details from file
                            String empType = chopMenuLine[ONEI].toUpperCase();
                            String firstName = chopMenuLine[TWOI].toUpperCase();
                            String lastName = chopMenuLine[THREEI].toUpperCase();
                            String dateOfBirth = chopMenuLine[FOURI];
                            String gender = chopMenuLine[FIVEI].toUpperCase();
                            String dateOfHire = chopMenuLine[SIXI];
                            double baseSalary = Double.parseDouble(chopMenuLine[SEVENI]);
                            double value = Double.parseDouble(chopMenuLine[EIGHTI]);
                            
                            //processing menu 13
                            mavSystem.hireEmployee(empType, firstName, lastName, dateOfBirth, gender, dateOfHire, baseSalary,value);
                            break;
                    case 14: //process release employee
                            //getting employee id from file
                            int empId14 = Integer.parseInt(chopMenuLine[ONEI]);
                            //processing menu 14
                            mavSystem.removeEmployee(empId14);
                            
                            break;
                    case 15: //compute monthly salary
                            //getting employee id and salaryParameter from file
                            int empId15 = Integer.parseInt(chopMenuLine[ONEI]);
                            int salaryParam = Integer.parseInt(chopMenuLine[TWOI]);
                            
                            
                            //processing menu 15
                            mavSystem.getSalary(empId15,salaryParam);
                            break;
                    case 16: //revenue computation
                            String lotNumber;
                            String meterNumber;
                            
                            if(chopMenuLine.length == 2){ // onely lot number was read from file
                                lotNumber = chopMenuLine[ONEI];
                                mavSystem.getRevenue(lotNumber);//processing menu 16
                                
                            }
                            else if(chopMenuLine.length == 3){ //both lot number and meter number read
                                lotNumber = chopMenuLine[ONEI];
                                meterNumber = chopMenuLine[TWOI];
                                
                                mavSystem.getRevenue(lotNumber,meterNumber); //processing menu 16
                            }
                            break;
                    case 17: //Ticket report
                            String carTag = chopMenuLine[ONEI];
                            
                            mavSystem.ticketList(carTag);//processing menu 17
                            
                            break;
                    case 0: //process Gui
                            mavSystem.displayGui();
                            guiExecute = true;
                            break;
                    default: System.out.printf("unknown command: %s: SKIPPED\n", inputLine);
                            foutput.printf("unknown command: %s: SKIPPED\n", inputLine);
                            break;
                }
                if (guiExecute == true)//if case 0 hs been executed then end further menu processing.
                    break;
                
                inputLine = finput.readLine();
                System.out.printf("\n\n");
                foutput.printf("\n\n");
                System.out.println("["+ inputLine + "]");
                foutput.println("["+ inputLine + "]");
                while (inputLine.charAt(BASE_INDEX) == '/'){
                    inputLine = finput.readLine();
                    //System.out.println(inputLine);
                    //foutput.println(inputLine);
                }
            }
            System.out.printf("Finished processing all commands. bye!"); 
            foutput.printf("Finished processing all commands. bye!");  
            // DO NOT REMOVE or DISTURB the REST OF THE CODE	
        } catch(NullPointerException NPE){
            System.out.println("null pointer exception: " + "\nPlease correct " + NPE.getMessage());
        } 
        catch (NumberFormatException NFE) {
            System.out.println("I/O Error in File: " + ifName + "\ncheck for: "
					+ NFE.getMessage() + " and correct it in: " + inputLine);
        } catch (RuntimeException RE) {
            System.out.println("Invalid Data error in File: " + ifName
					+ "\nPlease correct " + RE.getMessage() + " in the file!" + inputLine);
        }
        catch(IOException IOE){
            System.out.println("input/output Data error in File: " + ifName + "\nPlease correct " + IOE.getMessage() + " in the file!" + inputLine);
        } 
        finally{
            foutput.close();
        }
    }
}
