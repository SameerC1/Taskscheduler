//Event scheduler program by Ian, Sameer, and Romeo
//This is the first version of the program.

import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    //instantiates the event planning object and deserializes the hashmap
    Editor datetimes=new Editor();
    ArrayList<String>Alist=null;
    HashMap<String, String> dates = datetimes.deserialize();
    //to clear out the screen,greet the user, and create the scanner object
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Welcome to the event scheduler");
    Scanner input=new Scanner(System.in);

    //main loop for the program
    while (true) {
        //updates the list of dates in order
        if(dates.size() > 0) {
           Alist=datetimes.sort(datetimes.getdates(dates));
          } else {
           Alist=null;      
          }
        //gets user input for choice
        System.out.println("Would you like to\n1. See the events you scheduled\n2. Add an event\n3. Remove an event\n4. Save and leave the app");
        char choice=input.next().charAt(0);

        if(choice == '1') {
          //clears the screen
          System.out.print("\033[H\033[2J");
          System.out.flush();
          //tells the user there is no events if there wasnt any
          if(Alist == null) {
            try{
              System.out.println("You haven't added any events yet.");
              Thread.sleep(3000); // pause for 3 seconds
              System.out.print("\033[H\033[2J");
              System.out.flush();
              continue;
              } catch (Exception e) {
              System.out.println("This isn't supposed to happen.");
              return;
              }
          } //end of condition
          datetimes.PrintDates(dates,Alist);
        }
          
        else if(choice =='2') {
          //clears the screen
          System.out.print("\033[H\033[2J");
          System.out.flush();
          dates=datetimes.addDate(dates);
        }
          
        else if(choice =='3') { 
          //clears the screen
          System.out.print("\033[H\033[2J");
          System.out.flush();
          if(dates.size() > 0) {
            dates=datetimes.delete(dates, Alist);
          } else {
            System.out.println("You haven't added any events.");
          }
        }
          
        else if(choice =='4') { //clears the screen
          System.out.print("\033[H\033[2J");
          System.out.flush();
          datetimes.serialize(dates);
          System.out.println("Goodbye");
          return;
        }
        else {
          //clears the screen and tells user to pick a valid choice
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println("Please select a valid choice.");
        }
      }
   }
}
