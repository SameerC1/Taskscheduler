import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
class Editor extends Datetimes { // Prints each event

  void PrintDates(HashMap<String, String> Hmap, ArrayList<String> dateStrList) { // Prints out everything with a for loop
    for (int i = 0, j = dateStrList.size(); i < j; i++) {
      System.out.println(dateStrList.get(i) + ": " + Hmap.get(dateStrList.get(i)));
    }
  }

  // Serializes the hashmap to be used again
  void serialize(HashMap<String, String> Hmap) {
    try {
      FileOutputStream fos = new FileOutputStream("WriteDates.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(Hmap);
      oos.close();
      fos.close();
    } catch (Exception e) {
      System.out.println("Error serializing object");
      System.exit(0);
    }
  }

 public static HashMap<String, String> deserialize() {
    HashMap<String, String> Hmap = null;
    File file=new File("WriteDates.ser");
   //returns an empty hashmap if the file is empty 
   if(file.length() == 0) {
      HashMap<String,String> hmap=new HashMap<>();
      return hmap;
    } try {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Hmap = (HashMap<String, String>) ois.readObject();
        ois.close();
        fis.close();
    } catch (Exception e) {
        System.out.println("Error deserializing object");
        System.exit(0);
    }
    return Hmap;
}

  // To add an element into the map
  HashMap<String, String> addDate(HashMap<String, String> Hmap) {
    // Set the date and time format
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy '@'hh:mma");

    // Create a Scanner object for user input
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter a date and time
    System.out.println("Enter a date and time (e.g. 01/01/2021 @09:00 AM):");
    String inputString = input.nextLine();
    // checks if the date entered was valid
    boolean isdate = IsValid(inputString);
    // redoes the function if invalid,
    // goes on to get the description if the date is valid
    if (isdate) {
      return addDesc(Hmap, inputString);
    } else {
      return addDate(Hmap);
    }
  }

  // prompt the user to enter the description for the event
  private HashMap<String, String> addDesc(HashMap<String, String> Hmap, String date) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the description of the event.");
    String inputStr = input.nextLine();
    // adds the date, and description into the hashmap
    Hmap.put(date, inputStr);
    return Hmap;
  }

  HashMap<String, String> delete(HashMap<String, String> Hmap, ArrayList<String> dateStrList) {
    Scanner input = new Scanner(System.in);
    // Prints out each date
    System.out.println("Type the date of the event you want to delete.");
    PrintDates(Hmap, dateStrList);
    String removal = input.nextLine();
    if (dateStrList.contains(removal)) {
      // Get the iterator over the HashMap
      Iterator<HashMap.Entry<String, String>> itr = Hmap.entrySet().iterator();
      // to iterate over the hashmap to
      // find the entry to be deleted
      while (itr.hasNext()) {
        // finds the entry to delete
        HashMap.Entry<String, String> entry = itr.next();
        if (removal.equals(entry.getKey())) {
          itr.remove();
        }
      }

      return Hmap;
    }
    // redoes the delete entry function if date is not found
    else {
      System.out.println("Invalid date.");
      return delete(Hmap, dateStrList);
    }
  }

   // To check if a date and time is valid and an actual date
  private boolean IsValid(String inputString) {
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy '@'hh:mm a");
    try {
      // Try to parse the string using the specified format
      LocalDateTime dateTime = LocalDateTime.parse(inputString, dateTimeFormat);
      // If the parse method doesn't throw an exception, the string is in a valid date
      // and time format
      return true;
    } catch (DateTimeParseException e) {
      // If the parse method throws an exception, the string is not in a valid date
      // and time format
      System.out.println("The string is not in a valid date and time format.");
      return false;
    }
  }

}
