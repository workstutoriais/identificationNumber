package Java;

import java.text.DateFormat;
import java.time.LocalDate;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
public class IdentificationNumber {

        // This Method receive as parameter an Int and return an Array of Int
        public static long[] convertIDtoArray(long identificationNumber) {

            String temp = Long.toString(identificationNumber);
            long[] id_array = new long[temp.length()];
            for (int i = 0; i < temp.length(); i++) {
                id_array[i] = temp.charAt(i) - '0';
            }

            return id_array;
        }

        //if each digits appears max. 3 times in this id
        public static void countDigitRepeating(long [] arrayDigits){
            int digitCounter = 0;
            long element=0;
            for (int i = 0; i <arrayDigits.length ; i++) {
                int counter =1;
                for (int j = i+1; j <arrayDigits.length ; j++) {
                    if(arrayDigits[i]==arrayDigits[j]){
                        counter++;
                    }
                }
                if(digitCounter<counter){
                    digitCounter=counter;
                    element = arrayDigits[i];
                }
            }
            System.out.println("Element repeating maximum no of times: " + element + ", maximum count: " + digitCounter);
        }



        //if the penultimate number is lower than the previous one and bigger than the next one
        public static boolean validateNumberLowerBigger(long[] arrayDigits){
            boolean result = false;

            int b= arrayDigits.length;

            if((arrayDigits[(b-2)] < arrayDigits[b-3]) &&
                    (arrayDigits[b-2] > arrayDigits[b-1])){
                result=true;
            }
            if(b >9){
                System.out.println("Identification Number has more than 10 digitis:"+b+"\n");
            }
            System.out.println("Identification Number Lenght is:"+b+"\n");
            System.out.println("penultimate number is:"+arrayDigits[(b-2)]+" \n");
            System.out.println("Previous number is:"+arrayDigits[(b-3)]+" \n");
            System.out.println("Next number is:"+arrayDigits[(b-1)]+" \n");

            return result;
        }


        //This method below receive a String of 10 Digits, Convert and return an Array of Int
        public static long[] convert(String stringDigits) {

            long[] arrayDigits = new long[stringDigits.length()];

            for(int i = 0; i < stringDigits.length(); i++) {
                arrayDigits[i] = stringDigits.charAt(i) - '0';
            }

            return arrayDigits;
        }


    //This method return the month code
    public static long monthCode(int month){
        /*(January = 0 February = 3 March = 3 April = 6 May = 1 June = 4 July = 6 August = 2 September = 5 October = 0 November = 3 December = 5*/
        String monthCodes= "033614625035";

        long monthCode = 0;
        long[] arrayMonthCodes = convert(monthCodes);;

        for(int i = 1; i <= 12; i++) {
           // System.out.println(arrayMonthCodes[i]);
            System.out.println(i);
            if(i == month){
                monthCode = arrayMonthCodes[i];
            }

        }

        return monthCode;

    }

    //Calculate the Year CodeThe Year Code
    //To calculate the Year Code, use this formula: (YY + (YY div 4)) mod 7
    //10 x 7 is 70, leaving us with 51, because 121 - 70 = 51.
    //7 x 7 = 49, and 51 - 49 = 2.
    public static int yearCode(int year){
    int yearcode=0;
    int yy=0;
    int calc =0;
    int mod =0;
    yy=Math.floorMod(year, 100);
    calc = yy+(yy/4);
        mod=Math.floorMod(calc, 7);
        yearcode  = mod;

        return yearcode;
    }

    //This method Return an Array with all the FRiday of the Year
    public static int returnNumberFridayOfYear(int y){

        int choseYear = y;

        int size = 47;
        String[] months= new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int countFridays =0;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today ="";
        int count =0;
        LocalDate date2;
        for(int i = 0; i <= 11; i++) {
            Month month = Month.valueOf(months[i].toUpperCase());
                    date2 = Year.of(choseYear).atMonth(month).atDay(1).
                    with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
            int dayNumberMonth = date2.getDayOfMonth();
            Month mi = date2.getMonth();

            while (mi == month) {
                date2 = date2.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                int dayMonthN = date2.getDayOfMonth();
                today = formatter.format(date2.getDayOfMonth());
                int fridayN = date2.getDayOfWeek().getValue();

                mi = date2.getMonth();

                String dd =date2.toString();
                int dia = getDayOfMonth(dd);
                if(dia == 13){
                    count++;
                }

            }



        }

        return count;

    }


    //This method receive the Date as String and return the day of month value
    public static  int getDayOfMonth(String date){
         LocalDate fridayDate = LocalDate.parse(date);
         int day = fridayDate.getDayOfMonth();
        return day;
    }

    public static void main(String[] args) {
            System.out.println("Test Execution Results: \n ");

            String longIDNumber;
            longIDNumber = "12555551174987";
            long[] identificationN = convert(longIDNumber);
            countDigitRepeating(identificationN);
            boolean result = validateNumberLowerBigger(identificationN) ;
            if(result == true){
                System.out.println("\n The penultimate number is lower than the previous one and bigger than the next one");
            }
        int year = 1987;
        System.out.println("\n The year " + year + " has " +  returnNumberFridayOfYear(year) +" Fridays 13th " );

        }

}
