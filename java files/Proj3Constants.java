/*****************************************************************************
 @author Sharma Chakravarthy
 LANGUAGE   : Java version 6
 OS         : Windows 7 (Ultimate)
 PLATFORM   : PC
 Compiler   : javac
 ASSIGNED   : Spring 2013
 
 CONCEPTS   : classes and methods
 PURPOSE    : defines the "Constants" interface
******************************************************************************/

/* These defaults should be used in your projects; let me know if something you need is missing */

/**
 * interface containing constants used by other classes in the package
 * @author samir
 */
public interface Proj3Constants
 { 
  String  DEFAULT_ENTERPRISE_NAME       = "MavPark Systems Ltd.";
  int     DEFAULT_EMPLOYEE_NUMBER       = 0;
  int     D_USERID                      = 0;
  int     D_ZIPCODE                     = 89000;
  String  D_USERTYPE                 = "VIS";
  String  D_USERCLASS                   = "UNKN";
  
  String  EMPTY_STRING                   = "";
  
  String  DEFAULT_FIRST_NAME             = "John";
  String  DEFAULT_LAST_NAME              = "Doe";
  String  GENDER                         = "MALE";
  double  SALARY_PARAM                   = 20.00;
  double  MAX_SALARY                     = 100000.00;
  int     MAX_PARKING_LOTS               = 10; 
  int     MAX_EMPLOYEES			 = 15;
   
  double  ZEROD                          = 0.000;

  int     THREEI                         = 3;
  int     FOURI                          = 4;
  int     FIVEI                          = 5;
  int     SIXI                           = 6;
  int	  SEVENI	                 = 7;
  int	  EIGHTI			 = 8;
  int	  NINEI				 = 9;
  int     TENI                           = 10;
  
  double     OVERTIMERATE                = 12.00;
  double     ONCALLRATE                  = 10.00;
  double     COMMISSIONRATE              = 0.20;
  String    LINE = "-------------------------------------------------------------------------------------------------";
  String    ASTERIK = "*************************************************************************************************";
  
  int    D_LOTNO                         = 11;
  int    D_TOTSPACES                     = 5;
  int    D_REZSPACES                     = 0;
  double D_LOTCOST                       = 20.00;
}
