/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * class creates a Gui object for MavPark system
 * @author samir
 */
public class Gui extends JFrame {
    
    /**
     * Constructor: creates a Gui object with tabbed windows and other gui components for user interaction  
     */
    public Gui(){
        
        super("Shrestha Parking Management");//invoking the superclass constructor to create a label for the JFrame.
        setSize(800,700);//setting the window size
        setDefaultCloseOperation(EXIT_ON_CLOSE);//for closing the window
        
        setLayout(new GridLayout());//setting a grid layout for the frame.
        
        JTabbedPane tabs = new JTabbedPane();
        
        //creating three tabs for employees, user and parking lot.
        tabs.add("EMPLOYEES",employeePanel(MavPark.getEmployees()));
        tabs.add("USER",userPanel(MavPark.getUsers()));
        tabs.add("PARKING LOT",parkingLotPanel(MavPark.getParkingLots()));
        getContentPane().add(tabs);
      
        setVisible(true);//making the windows visible
    }
    
    
    /**
     * creates a panel that contains components to get information about parking lots
     * @param lots is the Arraylist variable that contains all the parking lot objects
     * @return returns a JPanel containing Gui components for parking lots
     */
    
    //a method for creating a panel for parking lot.
    private Component parkingLotPanel(ArrayList<ParkingLot> lots){
        
        
        JPanel mainPanel = new JPanel();//the main panel for holding all other panels
        
        Border line;
        line = BorderFactory.createLineBorder(Color.RED);//creating a red border line
        
        //creating different panels.
        JPanel namePanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel firstInfoPanel = new JPanel();
        JPanel secondInfoPanel = new JPanel();
        
        
        GridLayout layout = new GridLayout(2,2);//creating a new layout for the mainPanel
        layout.setHgap(4);//setting the gaps
        layout.setVgap(4);
        
        mainPanel.setLayout(layout);//setting the layout for the mainPanel
        
        namePanel.setLayout(new BorderLayout());
        searchPanel.setLayout(new GridLayout(10,0));//creating 10 rows and 0 columns
        firstInfoPanel.setLayout(new BorderLayout());
        secondInfoPanel.setLayout(new BorderLayout());
        
        int i = 0;
        String[] lotName = new String[lots.size()];//creating a string array to hold lot names
        
        
        //storing parking lot names in the string array
        for(ParkingLot e: lots){
            lotName[i++] = e.getLotName();
        }
        
        final JList  lotList = new JList(lotName);//creating a Jlist with all the parking lots names
        
        namePanel.add(new JLabel("SELECT A PARKING LOT"),BorderLayout.NORTH);
        namePanel.add(lotList,BorderLayout.CENTER);
        namePanel.add(new JScrollPane(lotList));
        lotList.setVisibleRowCount(6);
        lotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lotList.setBorder(line);
        
        final JTextArea firstInfoArea = new JTextArea();
        firstInfoArea.setBackground(Color.LIGHT_GRAY);
        firstInfoArea.setEditable(false);
        firstInfoArea.setBorder(line);
        firstInfoPanel.add(new JLabel("INFORMATION: "),BorderLayout.NORTH);
        firstInfoPanel.add(firstInfoArea);
        
        
        final JCheckBox highestMeterLot = new JCheckBox("DISPLAY METERED LOT WITH HIGHEST REVENUE");
        final JCheckBox lowestMeterLot = new JCheckBox("DISPLAY METERED LOT WITH LOWEST REVENUE");
        final JTextField textField = new JTextField();
        final JButton enterButton = new JButton("PRESS BUTTON");
        
        
        final JTextField ticketField = new JTextField();
        final JButton searchButton = new JButton("PRESS BUTTON");
        
        //adding all the components to the parkinglot panel
        searchPanel.add(new JLabel("ENTER LOT NUMBER BELOW TO CALCULATE REVENUE"));
        searchPanel.add(textField);
        searchPanel.add(enterButton);
        searchPanel.add(new JLabel(""));
        searchPanel.add(highestMeterLot);
        searchPanel.add(lowestMeterLot);
        searchPanel.setBorder(line);
        
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel ("ENTER DATE (mm-dd-yyyy)TO VIEW OCCUPANCY DETAILS"));
        searchPanel.add(ticketField);
        searchPanel.add(searchButton);
        
        
        final JTextArea secondInfoArea = new JTextArea();
        secondInfoArea.setBackground(Color.LIGHT_GRAY);
        secondInfoArea.setEditable(false);
        secondInfoArea.setBorder(line);
        secondInfoPanel.add(new JLabel("INFORMATION: "),BorderLayout.NORTH);
        secondInfoPanel.add(secondInfoArea);
        
        secondInfoPanel.add(new JScrollPane(secondInfoArea));
        
        mainPanel.add(namePanel);
        mainPanel.add(firstInfoPanel);
        mainPanel.add(searchPanel);
        mainPanel.add(secondInfoPanel);
        
        //creating an event listener handler for check boxes
        CheckBoxHandler handler = new CheckBoxHandler(highestMeterLot,lowestMeterLot,secondInfoArea);
        
        //registering the check boxes
        highestMeterLot.addItemListener(handler);
        lowestMeterLot.addItemListener(handler);
        
        //handling the event for searchButton
        searchButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String parkingDate = ticketField.getText();//getting the parking Date that user entered in the text field
                        String info;//for holding the formatted string
                        secondInfoArea.setText(null);
                        
                        if(!parkingDate.equals("*")){ //if user hasn't entered * as the date then need to calculate for a specific date
                            try{ //code for handling the exception. The code below may generate exception
                              Date pDate = new Date(parkingDate);//creating a date object from parkingDate string
                              for(ParkingLot lots: MavPark.getParkingLots()){//going through all the parking lots
                                    int count = 0;//declaring a counter
                                    if(lots.getLotNumber() < 6){   //only consider for regular lots
                                        for(ParkingInfo pInfo: lots.getRecords()){  //going through all the parking records
                                            if((pDate.compareTo(pInfo.getParkingDate()) == 0)){ // dates matched
                                                if(pInfo.getParkingStatus().equals("ENTER"))//if status is enter
                                                    count = count + 1;//increment count
                                                else if(pInfo.getParkingStatus().equals("EXIT"))//if status is exit
                                                    count = count - 1;//decrement count
                                            }//end if
                                        }//end for
                                        
                                        //creating a formatted string
                                        info = String.format(" LOT NAME: %s\n LOT NO: %d\n PARKING DATE: %s\n TOTAL SPACE: %d\n TOTAL OCCUPANCY: %d\n\n",
                                                lots.getLotName(),lots.getLotNumber(),pDate,lots.getTotalSpaces(),count);
                                        //displaying the formatted string in the gui
                                        secondInfoArea.append(info);
                                        secondInfoArea.append("\n--------------------------------------\n");
                                    }//end if
                              }//end for
                            }//end try
                            catch(NumberFormatException nfe){  // catching the exception from code in the try block
                                 secondInfoArea.setText("INVALID DATE!!\n\nENTER DATE IN (MM-DD-YYYY) FORMAT");
                            }
                        }//end if
                        else{
                           //calculate occupancy details for all the dates
                            secondInfoArea.setText("FOR ALL DATES:\n\n");
                            for(ParkingLot lots: MavPark.getParkingLots()){
                                int count = 0;
                                if(lots.getLotNumber() < 6){
                                    for(ParkingInfo pInfo: lots.getRecords()){
                                            if(pInfo.getParkingStatus().equals("ENTER"))
                                                count = count + 1;
                                            else if(pInfo.getParkingStatus().equals("EXIT"))
                                                count = count - 1;
                                    }//end for
                                    info = String.format(" LOT NAME: %s\n LOT NO: %d\n TOTAL SPACE: %d\n TOTAL OCCUPANCY: %d\n\n",
                                            lots.getLotName(),lots.getLotNumber(),lots.getTotalSpaces(),count);
                                    secondInfoArea.append(info);
                                    secondInfoArea.append("\n--------------------------------------\n");
                                }//end if
                            }//end for
                        
                        }//end else
                     }//end of method
                }//end for anonymous class
        );//end of registering
        
        
        //event handling for enterButton with an anonymous inner class
        enterButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        try{
                            boolean lotFound = false;// a condition checker variable
                            int lotNo = Integer.parseInt(textField.getText());//getting the lot number from the textField
                            for(ParkingLot lots: MavPark.getParkingLots()){//looping through all the parking lots
                                if(lotNo == lots.getLotNumber()){ // checking the lot number for a match
                                    lotFound = true;//parking lot number matched
                                    double revenue = lots.computeRevenue();//compute the revenue
                                    String revenueInfo = String.format("Revenue From Lot Number: %d\n\n  $%.2f",lotNo,revenue);
                                    //adding the formatted string to the JTextArea.
                                    secondInfoArea.setText(revenueInfo);

                                }//end if
                            }//end for
                            if(lotFound == false){//the parking lot number didn't match with any
                                secondInfoArea.setText("No match found for the given lot number\n\ncheck lot number\n");
                            }
                        }//end try
                        catch(NumberFormatException nfe){
                            secondInfoArea.setText("\n\nInvalid Lot Number!!!\n");
                        }
                    
                    
                    }//end method
                
                
                }//end anonymous class
        );
        
        
        //event handler for lotList the Jlist contaiining all the parking lot names with an anonymous inner class
        lotList.addListSelectionListener(
                new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e){
                        int index = 0;//a counter for finding which parking lot was clicked
                        if(!e.getValueIsAdjusting()){ // checking if the user has selected an item from the list
                            for(ParkingLot lots: MavPark.getParkingLots()){ //looping through all the parking lots
                                if(index == lotList.getSelectedIndex()){ // finding out which index was selected
                                    //getting all the parking lot details
                                    int lotNumber = lots.getLotNumber();
                                    String lotName = lots.getLotName();
                                    String location = lots.getAddress();
                                    int totalCapacity = lots.getTotalSpaces();
                                    int reservedSpaces = lots.getReservedSpaces();
                                    double parkingRate = lots.getParkingRate();
                                    double revenue = lots.computeRevenue();
                                    String lotInfo;//for storing formatted string
                                    //checking if it is a regular or a metered lot
                                    if(lots instanceof RegularLot)
                                        lotInfo = String.format(" LOT NO:  %d\n\n LOT NAME:  %s\n\n LOCATION:  %s\n\n"
                                                + " TOTAL CAPACITY:  %d\n\n RESERVED SPACE:  %d\n\n  YEARLY RATE:  $%.2f\n\n"
                                                + " REVENUE:  $%.2f",
                                                lotNumber,lotName,location,totalCapacity,reservedSpaces,parkingRate,revenue);
                                    else
                                        lotInfo = String.format(" LOT NO:  %d\n\n LOT NAME:  %s\n\n LOCATION:  %s\n\n"
                                                + " TOTAL METERS:  %d\n\n METER RATE:  $%.2f\n\n REVENUE:  $%.2f",
                                                lotNumber,lotName,location,totalCapacity,parkingRate,revenue);
                                        
                                    
                                    //adding the formatted string to the JTextArea named as firstInfoArea
                                    firstInfoArea.setText(lotInfo);
                                }//end inner if
                                index++;//incrementing the counter
                            }//end for
                        }//end if
                    }//end of method
                }//end of anonymous class
        );//end of registering
        return mainPanel;//returning the panel containing all the components with their functionality to the place where called
    }//end of parkinglot panel
                        
                
    /**
     * creates a panel that contains gui components related to employees
     * @param employees is the ArrayList variable that contains all the employee objects
     * @return returns a JPanel containing Gui components for employees
     */            
                
    //a method for creating a panel containing all components for employee
    private Component employeePanel(ArrayList<Employee> employees){
        
        Border line;
        line = BorderFactory.createLineBorder(Color.RED);//creating a red border line
        
        
        //creating Jpanels for adding Gui components.
        JPanel panel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel firstInfoPanel = new JPanel();
        JPanel secondInfoPanel = new JPanel();
        
        //creating a new layout
        GridLayout layout = new GridLayout(2,2);
        layout.setHgap(2);//setting the horizontal gap
        layout.setVgap(2);//setting the vertical gap
        
        
        //setting the layouts for all the panel
        panel.setLayout(layout);
        namePanel.setLayout(new BorderLayout());
        searchPanel.setLayout(new FlowLayout());
        firstInfoPanel.setLayout(new BorderLayout());
        secondInfoPanel.setLayout(new BorderLayout());
        
        JLabel nameLabel = new JLabel("SELECT AN EMPLOYEE");
        
        //creating an ImageIcon for adding to the interface
        ImageIcon pic = new ImageIcon(getClass().getResource("flag.png"));
          //panel1.add(new JLabel(pic));
        
        int i = 0;
        
        //storing all the employees name in a string array.
        String[] empName = new String[employees.size()];
        for(Employee e: employees){
            empName[i++] = e.getFirstName() + " "+ e.getLastName();
        }
        
        
        final JCheckBox longestEmployee = new JCheckBox("DISPLAY OLDEST EMPLOYEE");//creating a checkbox
        
        final JList empList = new JList(empName);//creating a JList with employee names
        final JTextArea infoArea = new JTextArea();//creating a text area for outputing result
        final JTextArea secondInfoArea = new JTextArea();//creating a text area for outputing result
        
        
        //setting background color and borders
        infoArea.setBorder(line);
        infoArea.setBackground(Color.lightGray);
        infoArea.setEditable(false);
        secondInfoArea.setBackground(Color.LIGHT_GRAY);
        secondInfoArea.setBorder(line);
        secondInfoArea.setEditable(false);
        
        //adding components to respective JPanel
        firstInfoPanel.add(new JLabel("INFORMATION: "),BorderLayout.NORTH);
        firstInfoPanel.add(infoArea,BorderLayout.CENTER);
        
        secondInfoPanel.add(new JLabel("INFORMATION: "),BorderLayout.NORTH);
        secondInfoPanel.add(secondInfoArea,BorderLayout.CENTER);
        
        
        empList.setVisibleRowCount(4);//setting visible rows number as 4
        empList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//single selection mode
        empList.setBorder(line);//adding border line
        
        namePanel.add(nameLabel,BorderLayout.NORTH);
        namePanel.add(empList,BorderLayout.CENTER);
        namePanel.add(new JScrollPane(empList));
        panel.add(namePanel);
        panel.add(firstInfoPanel);
        
        searchPanel.setBorder(line);
        searchPanel.add(longestEmployee);
        searchPanel.add(new JLabel(pic));//adding picture as a label
        panel.add(searchPanel);
        panel.add(secondInfoPanel);
        
        
        //implementing the event handler for longestEmployee checkbox with an anonymous inner class
        longestEmployee.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent e){
                        int max = 0;
                        int empId = 0;//to store id of the longest employed employee
                        //finding the employee with longest duration of work days
                        for(Employee emp: MavPark.getEmployees()){
                            if(emp.getCareerLength() > max){
                                max = emp.getCareerLength();
                                empId = emp.getId();
                            }
                        }
                        //printing the employee details for the employee whose id matches with the empId(id of longest duration employee found above)
                        if(longestEmployee.isSelected()){
                            for(Employee emp: MavPark.getEmployees()){
                                if(empId == emp.getId())
                                    secondInfoArea.setText(String.format(" LONGEST EMPLOYED EMPLOYEE:\n\n FIRST NAME: %s\n LAST NAME: %s\n BIRTHDATE: %s\n TOTAL DAYS HIRED: %d",
                                    emp.getFirstName(),emp.getLastName(),emp.getDOB(),max));
                            }
                        }
                        if (!longestEmployee.isSelected())//if checkbox is unselected
                            secondInfoArea.setText(null);//clear the information area
                    }//end of method
                
                }//end of anonymous class 
         );//end registering
        
        
        //event handler for handling empList the Jlist containing employee names
        empList.addListSelectionListener(
           new ListSelectionListener(){
               @Override
               public void valueChanged(ListSelectionEvent e){
                   int index = 0;
                   
                   if (!e.getValueIsAdjusting()){ // checking if the user has selected an employee name from list
                           for(Employee emps: MavPark.getEmployees()){
                              if(empList.getSelectedIndex() == index){//finding which employee was selected
                                  //getting the details for the selected employee
                                  int id = emps.getId();
                                  String first = emps.getFirstName();
                                  String last = emps.getLastName();
                                  Date dob = emps.getDOB();
                                  Date doh = emps.getDOH();
                                  String gender = emps.getGender();
                                  String type = emps.getEmpType();
                                  double salary = emps.getBaseSalary();
                                  //creating a formatted string with all the info
                                  String empInfo = String.format(" EMP ID:   %d\n\n FIRST NAME:   %s\n\n LAST NAME:   %s\n\n BIRTHDATE:   %s\n\n HIREDATE   %s\n\n GENDER:   %s\n\n TYPE:   %s\n\n BASE SALARY:   $%.2f\n\n"
                                                                  ,id,first,last,dob,doh,gender,type,salary);
                                  //adding the formatted info to the information display text area.
                                  infoArea.setText(empInfo);
                              }//end if
                              index++;//incrementing the index counter
                           }//end for
                   }//end if
               }//end method
           }//end of anonymous class
        );//end of registering
        return panel;//returning the panel with all the employee gui components
   }//end of employee panel
    
  
    /**
     * creates a panel that contains gui components related to user
     * @param users is the ArrayList variable that contains all the user objects
     * @return returns a JPanel containing Gui components for users
     */
    //a method for creating a panel containing all components for user
    private Component userPanel(ArrayList<User> users){
        
        Border line;
        line = BorderFactory.createLineBorder(Color.RED);//creating a red border line
        
        //creating various panels for holding components.
        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel firstInfoPanel = new JPanel();
        final JPanel secondInfoPanel = new JPanel();
        
        //creating a new layout for the panel
        GridLayout layout = new GridLayout(2,2);
        layout.setHgap(4);
        layout.setVgap(4);
        
        //setting the layouts
        mainPanel.setLayout(layout);
        namePanel.setLayout(new BorderLayout());
        searchPanel.setLayout(new GridLayout(10,0));
        firstInfoPanel.setLayout(new BorderLayout());
        secondInfoPanel.setLayout(new BorderLayout());
        
        searchPanel.setBorder(line);
        
        //getting all user names and storing in a string array
        int i = 0;
        String[] userName = new String[users.size()];
        
        for(User e: users){
            userName[i++] = e.getFirstName() + " "+ e.getLastName();
        }
        
        final JList  userList = new JList(userName);//creating a JList with all the user names
        
        
        //adding all the gui components and labels to their respective containers
        namePanel.add(new JLabel("SELECT A USER"),BorderLayout.NORTH);
        namePanel.add(userList,BorderLayout.CENTER);
        namePanel.add(new JScrollPane(userList));
        userList.setVisibleRowCount(6);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setBorder(line);
        
        final JTextArea firstInfoArea = new JTextArea();
        firstInfoArea.setBackground(Color.LIGHT_GRAY);
        firstInfoArea.setEditable(false);
        firstInfoPanel.add(new JLabel("INFORMATION: "),BorderLayout.NORTH);
        firstInfoPanel.add(firstInfoArea);
        firstInfoArea.setBorder(line);
        
        
        final JTextField textField = new JTextField();
        final JButton enterButton = new JButton("PRESS BUTTON");
        searchPanel.add(new JLabel(""));
        
        searchPanel.add(new JLabel("ENTER A CAR TAG BELOW FOR TICKET INFORMATION"));
        searchPanel.add(textField);
        searchPanel.add(new JLabel(""));
        searchPanel.add(enterButton);
        
        final JTextArea secondInfoArea = new JTextArea();
        secondInfoArea.setBackground(Color.LIGHT_GRAY);
        secondInfoArea.setEditable(false);
        secondInfoArea.setBorder(line);
        
        secondInfoPanel.add(new JLabel("INFORMATION: "),BorderLayout.NORTH);
        secondInfoPanel.add(secondInfoArea);
        secondInfoPanel.add(new JScrollPane(secondInfoArea));
        
        
        //adding all the panels to the mainPanel
        mainPanel.add(namePanel);
        mainPanel.add(firstInfoPanel);
        mainPanel.add(searchPanel);
        mainPanel.add(secondInfoPanel);
        
        
        //registering the event for enterButton . anonymous inner class is used
        enterButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        boolean tagFound = false;//for condition checking
                        String carTag = textField.getText().toUpperCase();//getting the entered car tag
                        //checking through all the user with car tags
                        for(User user: MavPark.getUsers()){
                            if(user.getCarTag().equals(carTag)){
                                  tagFound = true;//car tag match found
                                  //getting all the user details
                                  int id = user.getId();
                                  String first = user.getFirstName();
                                  String last = user.getLastName();
                                  Date dob = user.getDOB();
                                  String gender = user.getGender();
                                  String userType = user.getUserType();
                                  String classification = user.getUserClass();
                                  String userAddress = user.getUserAddress();
                                  
                                  //creating a formatted string and storing in userInfo
                                  String userInfo = String.format("USER ID:   %d\n FIRST NAME:   %s\n LAST NAME:   %s\n BIRTHDATE   "
                                          + "%s\n GENDER:   %s\n VEHICLE TAG NO:   %s\n ADDRESS:   %s\n TYPE:   %s\n CLASSIFICATION:   %s\n"
                                          ,id,first,last,dob,gender,textField.getText().toUpperCase(),userAddress,userType,classification);
                                  
                                  secondInfoArea.setText(userInfo);//addding formateed string to the user panel
                                  
                                  secondInfoArea.append("--------------------------------------------------------------------------------------------\n");
                                  secondInfoArea.append(" TICKET INFO FOR THIS USER:");
                                  secondInfoArea.append("\n--------------------------------------------------------------------------------------------\n");
                                  int count = 0;
                                  
                                  //going through all the ticket records of this user
                                  for(TicketInfo ticket: user.getRecords()){
                                      count++;
                                      int ticketId = ticket.getTicketId();
                                      int lotNumber = ticket.getLotNumber();
                                      Date ticketDate = ticket.getIssueDate();
                                      Time ticketTime = ticket.getIssueTime();
                                      double ticketAmount = ticket.getAmount();
                                      
                                      String ticketInfo = String.format(" TICKET ID: %d\n LOT NUMBER: %d\n DATE OF ISSUE: %s\n ISSUE TIME: %s\n AMOUNT: %.2f\n"
                                                                        ,ticketId,lotNumber,ticketDate,ticketTime,ticketAmount);
                                      secondInfoArea.append(ticketInfo);//appending the ticket information to the infomarion window
                                      secondInfoArea.append("\n--------------------------------------------------------------------------------------------\n");
                                      secondInfoArea.append("--------------------------------------------------------------------------------------------\n");
                                  }
                                  if (count == 0)//user has no tickets received
                                      secondInfoArea.append("THIS USER HAS NOT RECEIVED ANY TICKETS");
                                  else
                                      secondInfoArea.append("\nTOTAL TICKETS RECEIVED: "+count+"\n");
                                
                            }//end if
                        }//end for
                        if(tagFound == false){ // no match found the car tag
                            secondInfoArea.setText("\n\nINVALID VEHCILE TAG OR TAG DOESN'T EXIST\n\n\n");
                            secondInfoArea.append("PLEASE CHECK THAT YOU ENTERED CORRECT TAG");
                        }
                            
                    }//end method
                
                }//end anonymous class
                
        );
        
        
        //registering event handler for userList the Jlist containing all user names
        userList.addListSelectionListener(
                new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e){
                       int index = 0;
                       if (!e.getValueIsAdjusting()){//checking if user has selected a user
                          
                           //going through all the users and checking which user was selected 
                           for(User user: MavPark.getUsers()){
                              if(index == userList.getSelectedIndex()){
                                  
                                  //storing all the details of the selected user
                                  int id = user.getId();
                                  String first = user.getFirstName();
                                  String last = user.getLastName();
                                  Date dob = user.getDOB();
                                  String gender = user.getGender();
                                  String carTag = user.getCarTag();
                                  String userType = user.getUserType();
                                  String classification = user.getUserClass();
                                  String userAddress = user.getUserAddress();
                                  
                                  //creating a formatted string with all the user information
                                  String userInfo = String.format("USER ID:   %d\n\n FIRST NAME:   %s\n\n LAST NAME:   %s\n\n BIRTHDATE   "
                                          + "%s\n\n GENDER:   %s\n\n VEHICLE TAG NO:   %s\n\n ADDRESS:   %s\n\n TYPE:   %s\n\n CLASSIFICATION:   %s\n\n"
                                          ,id,first,last,dob,gender,carTag,userAddress,userType,classification);
                                  
                                  firstInfoArea.setText(userInfo);//settting the information area with the formatted text
                              }//endif
                              index++;//incrementing the index counter for users
                          }//end for
                       }//end if
                    }//end method
                }//end anonymous class
                
        );
        
        return mainPanel;//returning the panel containing all the components for user tab
        
    }
    
    
    /**
     * a private inner class used by  parkingLotPanel to handle events related to JCheckBox
     */
    //inner class for handling check boxes
    private class CheckBoxHandler implements ItemListener{
        
        //class fields
        private JCheckBox highBox;
        private JCheckBox lowBox;
        private JTextArea infoArea;
        
        public CheckBoxHandler(JCheckBox highBox,JCheckBox lowBox,JTextArea infoArea){
            //setting the fields
            this.highBox = highBox;
            this.lowBox = lowBox;
            this.infoArea = infoArea;
        
        }
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            int lotMinNo = 0;
            int lotMaxNo = 0;
            double min = 100000.0000;
            double max = 0;
            String lotName;
            String location;
            int totalCapacity;
            double parkingRate;
            String lotInfo;
            if(highBox.isSelected() && lowBox.isSelected()){ // both checkboxes are selected
                //computing the lot with the minimum revenue for metered lots
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.computeRevenue() < min && lots.getLotNumber() > 5){
                        min = lots.computeRevenue();
                        lotMinNo = lots.getLotNumber();
                    }//end if
                }//end for
                
                //computing the lot with the maximum revenue for metered lots
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.computeRevenue() > max && lots.getLotNumber() > 5){
                        max = lots.computeRevenue();
                        lotMaxNo = lots.getLotNumber();
                    }//end if
                }//end for
                
                
                infoArea.setText("THE METERED LOT WITH THE LOWEST REVENUE\n\n");
                //getting the information about the metered lot with the minimum revenue and displaying output
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.getLotNumber() == lotMinNo){
                        lotName = lots.getLotName();
                        location = lots.getAddress();
                        totalCapacity = lots.getTotalSpaces();
                        parkingRate = lots.getParkingRate();
                        lotInfo = String.format(" LOT NO:  %d\n LOT NAME:  %s\n LOCATION:  %s\n"
                                                + " TOTAL METERS:  %d\n METER RATE:  $%.2f\n REVENUE:  $%.2f",
                                                lotMinNo,lotName,location,totalCapacity,parkingRate,min);
                        infoArea.append(lotInfo);
                        }
                }
                
                infoArea.append("\n\nTHE METERED LOT WITH THE HIGHEST REVENUE\n\n");
                //getting the information about the metered lot with the maximum revenue and displaying output
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.getLotNumber() == lotMaxNo){
                        lotName = lots.getLotName();
                        location = lots.getAddress();
                        totalCapacity = lots.getTotalSpaces();
                        parkingRate = lots.getParkingRate();
                        lotInfo = String.format(" LOT NO:  %d\n LOT NAME:  %s\n LOCATION:  %s\n"
                                                + " TOTAL METERS:  %d\n METER RATE:  $%.2f\n REVENUE:  $%.2f",
                                                lotMaxNo,lotName,location,totalCapacity,parkingRate,max);
                        infoArea.append(lotInfo);
                        }
                }//end for
            }//end if
            else if(lowBox.isSelected()){//only lowBox was selected
                
                //finding the lot number for the metered lot with the lowest revenue
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.computeRevenue() < min && lots.getLotNumber() > 5){
                        min = lots.computeRevenue();
                        lotMinNo = lots.getLotNumber();
                    }//end if
                }//end for
                
                //displaying information about the metered lot with the lowest revenue by using the lot number found above
                infoArea.setText("THE METERED LOT WITH THE LOWEST REVENUE\n\n");
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.getLotNumber() == lotMinNo){
                        lotName = lots.getLotName();
                        location = lots.getAddress();
                        totalCapacity = lots.getTotalSpaces();
                        parkingRate = lots.getParkingRate();
                        lotInfo = String.format(" LOT NO:  %d\n\n LOT NAME:  %s\n\n LOCATION:  %s\n\n"
                                                + " TOTAL METERS:  %d\n\n METER RATE:  $%.2f\n\n REVENUE:  $%.2f",
                                                lotMinNo,lotName,location,totalCapacity,parkingRate,min);
                        infoArea.append(lotInfo);
                        }
                }
                
            }//end else if
            else if(highBox.isSelected()){ // the highBox was selected
                
                //getting the lot number of the lot with the highest revenue
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.computeRevenue() > max && lots.getLotNumber() > 5){
                        max = lots.computeRevenue();
                        lotMaxNo = lots.getLotNumber();
                    }//end if
                }//end for
                
                //dispalying info about the lot just found above in the text area
                infoArea.setText("THE METERED LOT WITH THE HIGHEST REVENUE\n\n");
                for(ParkingLot lots: MavPark.getParkingLots()){
                    if(lots.getLotNumber() == lotMaxNo){
                        lotName = lots.getLotName();
                        location = lots.getAddress();
                        totalCapacity = lots.getTotalSpaces();
                        parkingRate = lots.getParkingRate();
                        lotInfo = String.format(" LOT NO:  %d\n\n LOT NAME:  %s\n\n LOCATION:  %s\n\n"
                                                + " TOTAL METERS:  %d\n\n METER RATE:  $%.2f\n\n REVENUE:  $%.2f",
                                                lotMaxNo,lotName,location,totalCapacity,parkingRate,max);
                        infoArea.append(lotInfo);
                        }
                }//end for
            }//end else if
            else//if no boxes selected clear the information area
                infoArea.setText(null);
        }//end of method itemStateChanged
     }//end of inner class CheckBoxHandler
}//end of class Gui


