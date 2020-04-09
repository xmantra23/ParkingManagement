/*************************************************************************
 *  Compilation:  javac Date.java <br>
 *  Execution:    java Date <br>
 *************************************************************************/

/**
 * A simple immutable data type Date for use in Project 2 (CSE 1325, Fall 2010)
 * <br>
 * Useful for creating Date objects to hold calendar date
 * 
 * @author Sharma Chakravarhty (Extended Robert Sedgewick and Kevin Wayne code,
 *         Copyright 2007)
 * @author extended by Yuanzhe Cai (10/8/2010)
 * @see java.util.Calendar
 * @since 02/13/2010 Fixed a logical error/bug for addMonths on 2/22/2010 (Cai)
 * @since 08/2012 Fixed a logical error in the constructor with m and y (sharma)
 * @since 08/2012 Fixed a logical error in the methid addMonths (sharma)
 * @since 09/2012  fixed bugs in Date (m, y) and daysBetween (sharma)
 */


import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * class creates a date object
 * @author samir
 */

public class Date implements DateConstants {

	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31,
			30, 31, 30, 31 };
    private static final int LEAP_YEAR = 366;
    private static final int NON_LEAP_YEAR = 365;

	private final int month; // month (between 1 and 12)
	private final int day; // day (between 1 and DAYS[month])
	private final int year; // year

/**
	 * Constructor: default; returns today's date
	 */
    public Date() {
        GregorianCalendar c = new GregorianCalendar();
        month = c.get(Calendar.MONTH)+1;  //our month starts from 1
        day = c.get(Calendar.DAY_OF_MONTH);
        year = c.get(Calendar.YEAR);
    }

	/**
	 * Constructor: Does bounds-checking to ensure object represents a valid
	 * date
	 * 
	 * @param m    represents the month between 1 and 12
	 * @param d    represents the date between 1 and the corresponding number
	 *             from array DAYS
	 * @param y    represents the year
	 * @exception RuntimeException
	 *                if the date is invalid
	 */
	public Date(int m, int d, int y) {
		if (!isValid(m, d, y))
			throw new RuntimeException("1: Invalid date correct input"  + m + ":" + d + ":" + y);
		this.month = m;
		this.day = d;
		this.year = y;
	}

	/**
	 * Constructor: Does bounds-checking to ensure string represents a valid
	 * date
	 * 
	 * @param sDate    represents a date given in format mm-dd-yyyy as a string
	 * @exception RuntimeException if the date is invalid
	 */
	public Date(String sDate) {
		int m, d, y;
		String[] chopDate = sDate.split("-");
		m = Integer.parseInt(chopDate[ZEROI]);
		d = Integer.parseInt(chopDate[ONEI]);
		y = Integer.parseInt(chopDate[TWOI]);
		if (!isValid(m, d, y))
			throw new RuntimeException("2:Invalid date: correct input" + m + ":" + d + ":" + y);
		month = m;
		day = d;
		year = y;
	}

	/**
	 * constructor:    creates a date with a given year; fills in valid last 
     * month and day;
	 * 
	 * @param y    represents a date for a  year supplied as an integer
	 */
	public Date(int y) {

		month = LAST_MONTH;
		day = DAYS[LAST_MONTH];
		year = y;
	}

	/**
	 * create a date with a given month and year; fills in valid day;
	 * 
	 * @param m    represents the month between 1 and 12
	 * @param y    represents the year
	 * @exception  RuntimeException if the date is invalid
	 */
	public Date(int m, int y) {

		if (m < FIRST_MONTH || m > LAST_MONTH) throw new RuntimeException("3:Invalid date; correct input" + m + ":" + y);
        else month = m;
		if (!isLeapYear(y) && m == FEB) day = DAYS[m]-1;
        else day = DAYS[m];
        year = y;
        if (!isValid(month, day, year))
			throw new RuntimeException("4::Invalid date; correct input" + m + ":" + y);
	}

	/**
	 * Is the given date valid?
	 * 
	 * @param month, day, and year
	 * @return false if month exceeds 12 or is less than 1
	 * @return false if day exceeds the corresponding days for a month from
	 *         array DAYS
	 * @return false if the year is not a leap year and has 29 days
	 */
	private static boolean isValid(int m, int d, int y) {
		if (y < 0) return false; //will not handle negative years!
        if (m < FIRST_MONTH || m > LAST_MONTH)
			return false;
		if (d < FIRST_DAY || d > DAYS[m])
			return false;
		if (m == FEB && d == DAYS[FEB] && !isLeapYear(y))
			return false;
		return true;
	}

	/**
	 * is y a leap year?
	 * 
	 * @param y
	 *            represents the year specified
	 * @return true if year divisible by 400
	 * @return false if year divisible by 100 and not by 400
	 */
	private static boolean isLeapYear(int y) {
		if (y % 400 == 0)
			return true;
		if (y % 100 == 0)
			return false;
		return (y % 4 == 0);
	}

	/**
	 * does a given date belong to the given year y ?
	 * 
	 * @param y
	 *            represents the year specified
	 * @return true if date belongs to that Calendar year
	 * @return false if date DOES NOT belongs to that Calendar year
	 */
    //could have just checked for year as if (this.year != y) return false;
	public boolean dateBelongsTo(int y) {
		
        if (this.year != y) return false;
        else return true;
        //if (this.isBefore(new Date(FIRST_MONTH, FIRST_DAY, y)) || 
        //        this.isAfter(new Date(LAST_MONTH, DAYS[LAST_MONTH], y)))
		//	return false;
		//else return true;
	}


/**
	 * is (m, y) a leap month?
	 * 
     * @param m represents the month specified
	 * @param y represents the year specified
	 * @return true if it is a leap month
	 * @return false otherwise
	 */
	private static boolean isLeapMonth(int m, int y) {
		if (isLeapYear(y)) return ((m == 2) ? true : false);
		return false;
	}

	// return the next Date
	/**
	 * adds a day and returns a new Date object
	 * 
	 * @return returns a new Date object adding a day
	 */
	public Date next() {
		if (isValid(month, day + 1, year))
			return new Date(month, day + 1, year);
		else if (isValid(month + 1, 1, year))
			return new Date(month + 1, 1, year);
		else
			return new Date(1, 1, year + 1);
	}

	// is this Date after b?
	/**
	 * compares two Date objects
	 * 
	 * @param b Date object
	 * @return true if this Date is after Date b
	 */
	public boolean isAfter(Date b) {
		return compareTo(b) > 0;
	}

	// is this Date a before b?
	/**
	 * compares two date objects
	 * 
	 * @param b Date object
	 * @return true if this Date is before Date b
	 */
	public boolean isBefore(Date b) {
		return compareTo(b) < 0;
	}


	// comparison function between two dates
	/**
	 * compares two Date objects
	 * 
	 * @param b    Date object
	 * @return     0 if this Date is the same as Date b <br>
	 *             negative integer if this Date is earlier than Date b <br>
	 *             positive integer if this Date is after Date b
	 */
	public int compareTo(Date b) {
		if (year != b.year)
			return year - b.year;
		if (month != b.month)
			return month - b.month;
		return day - b.day;
	}

	
	// advance date by number of months
	/**
	 * advances the date by m months (fixed a bug on 2/22/2010)
	 * 
	 * @param m    represents the  months to advance
	 * @return     new Date object (as the originbal obj is immutable)
	 */
	public Date addMonths(int m) {
        int td;
		int tm = (month + (m % 12)) > 12 ? ((month + (m % 12)) % 12) : month
				+ (m % 12);
		int ty = (month + (m % 12)) > 12 ? year + (m / 12) + 1 : year + (m / 12);
        td = day;
		if (isLeapYear(ty) && (day > DAYS[tm]))  td = DAYS[tm];
        if (!isLeapYear(ty) && tm == FEB && day > DAYS[tm] -1) td = DAYS[tm]-1;
        return (new Date(tm, td, ty));
    }

	// subtracts two dates to return the difference in full months
	/**
	 * @param endDate  represents the second date
	 * @return         int months - the difference between the two dates
	 */
	public int monthsBetween(Date endDate) {
		int monthDiff = 0;
        boolean negative = false;
		Date sDate = this;
        Date eDate = endDate;
        if (eDate.isBefore(sDate)){
            sDate = endDate;
            eDate = this; 
            negative = true; 
        } 
         
        if (sDate.month <= eDate.month) {
			if (sDate.day <= eDate.day) {
				monthDiff = (eDate.month - sDate.month)
						+ (eDate.year - sDate.year) * 12;
			} else {
				monthDiff = (eDate.month - sDate.month - 1)
						+ (eDate.year - sDate.year) * 12;
			}
		} else {
			if (sDate.day <= eDate.day) {
				monthDiff = (12 - sDate.month + eDate.month)
						+ (eDate.year - sDate.year - 1) * 12;
			} else {
				monthDiff = (11 - sDate.month + eDate.month)
						+ (eDate.year - sDate.year - 1) * 12;
			}
		}
		return ((negative) ? -monthDiff : monthDiff);
    }
            
/*

	}
*/
	
    // returns the number of days between two dates
	/**
     * @ param  endDate is the second date
	 * @ return the number of days between two dates
     *          - 1 if start date is after the end date
     *          +ve #days if the start date is before end date
	 * 
	 */
	public int daysBetween(Date endDate) {

		if (this.isAfter(endDate)) return -1;
        int totDays = 0;
        for (int y = this.year; y < endDate.year; y++) 
			totDays += (isLeapYear(y) ? LEAP_YEAR : NON_LEAP_YEAR);
			
		int daysBeforeFirst = 0;
		for (int m = 1; m < this.month; m++)
			daysBeforeFirst +=  (m == 2 && !isLeapMonth(m, this.year)) ? DAYS[m]-1 : DAYS[m];
		daysBeforeFirst += this.day;

		int daysInSecond = 0;
		for (int me = 1; me < endDate.month; me++)
			daysInSecond += (me ==2 && !isLeapMonth(me, endDate.year) ? DAYS[me]-1  : DAYS[me]);
		daysInSecond += endDate.day;	
			
		return totDays - daysBeforeFirst + daysInSecond;
	}

	// return a string representation of this date
	/**
	 * replaces the default toString of Object class
	 */
	public String toString() {
		return "[" + month + "-" + day + "-" + year + "]";
	}


	/**
	 * Code for testing the Date class
	 * 
	 * @param args Array of String arguments
	 */

	public static void main(String[] args) {
        int count;
		Date today = new Date(1, 26, 2011);
		System.out.println(today);
		for (int i = 0; i < 14; i++) {
			today = today.next();
			System.out.println(today);
		}
        Date expiry = new Date(2011);
		System.out.println("testing 2011 as input:" + expiry);
        expiry = new Date(2012);
        System.out.println("testing 2012 as input:" + expiry);
        expiry = new Date(2, 2013);
        System.out.println("testing [2, 2013] as input:" + expiry);
        expiry = new Date(2, 2012);
        System.out.println("testing [2, 2012] as input:" + expiry);
        expiry = new Date(8, 2013);
        System.out.println("testing [8, 2013] as input:" + expiry);
        expiry = new Date(7, 2012);
        System.out.println("testing [7, 2012] as input:" + expiry);
        
                
		Date todayDate = new Date();
		System.out.println("todays date: " + todayDate);
		System.out.println("current month:" + todayDate.month);

        todayDate = new Date(2, 2010);
        System.out.println("todays date: " + todayDate);
		System.out.println("current month: " + todayDate.month);
        System.out.println("current day: " + todayDate.day);

        //testing dateBelongsTo method
        Date lease = new Date("08-01-2010");
		expiry = new Date("12-31-2011");
        Date expiry1 = new Date("1-01-2012");
        System.out.println(lease + " belongs to " + "2012: " + lease.dateBelongsTo(2012));
        System.out.println(lease + " belongs to " + "2010: " + lease.dateBelongsTo(2010));
        System.out.println(lease + " belongs to " + "2000: " + lease.dateBelongsTo(2000));
        System.out.println(expiry +  " belongs to " + "2011: " + expiry.dateBelongsTo(2011));
        System.out.println(expiry + " belongs to " + "2012: " + expiry.dateBelongsTo(2012));
        System.out.println(expiry + " belongs to " + "201: " + expiry.dateBelongsTo(201));
        System.out.println(expiry1 + " belongs to " + "2012: " + expiry1.dateBelongsTo(2012));
        System.out.println(expiry1 + " belongs to " + "2011: " + expiry1.dateBelongsTo(2011));
        System.out.println(expiry1 + " belongs to " + "2056: " + expiry1.dateBelongsTo(2056));

        lease = new Date("08-01-2010");
		expiry = new Date("08-01-2011");
        expiry1 = new Date("08-01-2012");

		// testing daysBetween
        System.out.println("\nTESTING daysBetween method\n------------------------------");
		count = lease.daysBetween(expiry);
		System.out.println("Days between "  + lease + " and " + expiry + "is: " + count);
        count = lease.daysBetween(expiry1);
        System.out.println("Days between "  + lease + " and " + expiry1 + "is: " + count);
		count = lease.daysBetween(new Date("12-31-2016"));
		System.out.println("Days between "  + lease + " and [12-31-2016] " + "is: " + count);
        count = lease.daysBetween(lease);
		System.out.println("Days between "  + lease + " and " + lease + "is: " + count);

        //testin isBefore
        System.out.println("\nTESTING isBefore method\n------------------------------");
        boolean isBefore = today.isBefore(today.next());
		System.out.println(today + " is before " + today.next() + ": " + isBefore);
		isBefore = today.next().isBefore(today);
        System.out.println(today.next() + " is before " + today + ": " + isBefore);
        isBefore = today.isBefore(today);
        System.out.println(today + " is before " + today + ": " + isBefore);
        
        //testing  addMonths
        System.out.println("\nTESTING addMonths method\n------------------------------");
        today = new Date("1-31-2011");
        Date newDate = today.addMonths(1);
        System.out.println("adding 1 months to " + today + " gives: " + newDate);
        today = new Date("1-31-2012");
        newDate = today.addMonths(1);
        System.out.println("adding 1 months to " + today + " gives: " + newDate);
		newDate = today.addMonths(24);
        System.out.println("adding 24 months to " + today + " gives: " + newDate);
        today = new Date("12-15-2012");
        newDate = today.addMonths(2);
        System.out.println("adding 2 months to " + today + " gives: " + newDate);
        today = new Date("2-29-2012");
		newDate = today.addMonths(15);
        System.out.println("adding 15 months to " + today + " gives: " + newDate);
		newDate = today.addMonths(23);
        System.out.println("adding 23 months to " + today + " gives: " + newDate);
        newDate = today.addMonths(0);
        System.out.println("adding 0 months to " + today + " gives: " + newDate);
		newDate = today.addMonths(48);
        System.out.println("adding 48 months to " + today + " gives: " + newDate);

        //testing monthsBetween
        System.out.println("\nTESTING monthsBetween method\n------------------------------");
		int monthDiff = today.monthsBetween(today.next());
        System.out.println("months between " + today + " and " + today.next() + ": " + monthDiff);
        monthDiff = today.monthsBetween(new Date(3, 29, 2012));
        System.out.println("months between " + today + " and " + "[3-29-2012]" + ": " + monthDiff);
        monthDiff = today.next().monthsBetween(today);
        System.out.println("months between " + today.next() + " and " + today + ": " + monthDiff);
        today = new Date(1, 15, 2011);
        Date endDate = new Date("2-15-2011");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        today = new Date(1, 15, 2011);
        endDate = new Date("2-14-2011");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        today = new Date(1, 31, 2011);
        endDate = new Date("3-30-2011");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        today = new Date(1, 15, 2011);
        endDate = new Date("2-15-2011");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        
        today = new Date();
        endDate = new Date("2-28-2014");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        today = new Date("3-31-2012");
        endDate = new Date("1-31-2012");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        today = new Date("3-30-2012");
        endDate = new Date("1-31-2012");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        today = new Date("2-29-2012");
        endDate = new Date("1-31-2012");
		monthDiff = today.monthsBetween(endDate);
        System.out.println("months between " + today + " and " + endDate + ": " + monthDiff);
        
		//following will generate exception
        //System.out.println(lease.dateBelongsTo(-2));
		//today = new Date(13, 13, 2010);
        //expiry = new Date(13, 2012);
		// expiry = new Date("2-29-2009");
        // expiry = new Date("3-32-2009");
		// System.out.println(expiry);
		
		
	}

}

