import java.io.*;
import java.util.*;
import java.text.DateFormat;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  

class Datetimes {
    //to sort an arraylist with dates and times  
ArrayList<String> sort(ArrayList<String>dateStrList) {
      Collections.sort(dateStrList, new Comparator<String>() {  
DateFormat f = new SimpleDateFormat("MM/dd/yyyy '@'hh:mm a");  
@Override  
public int compare(String o1, String o2) {  
try {  
  return f.parse(o1).compareTo(f.parse(o2));  
    } catch (ParseException e) {  
      throw new IllegalArgumentException(e);  
    }  
}  
});   
  
return dateStrList;}
//end of function
  
    //gets each of the dates and adds them to the arraylist
    ArrayList<String> getdates(HashMap <String,String> hmap) {
      ArrayList<String>dateStrList=new ArrayList<String>();
      Iterator<String> itr= hmap.keySet().iterator();
      while (itr.hasNext()) {
          dateStrList.add(itr.next());
        }
      //to sort and retrieve the list
      dateStrList=sort(dateStrList);
      return dateStrList;   
    }      
}
