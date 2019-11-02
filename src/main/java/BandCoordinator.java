
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Diana Arita
 */
public class BandCoordinator {
    public static void main(String[] args){
        //create instance of BandInformation class
        BandInformation data = new BandInformation();
        
        //Sort the names list
        System.out.println(data.bandNamesSort(data.getNameList()));
        
        //Sort the set times list
        System.out.println(data.sortSetTimes(data.getSetList()));
        
        System.out.println(data.getHashMap());
        
        //Display my name and email address
        System.out.println("Submitted by: Diana Arita - aritad@csp.edu");
        
        OUTER:
        do {
            Scanner scan = new Scanner(System.in);
            
            System.out.println();
            System.out.println("Search by Band Name(1) or Set List(2) or exit(3):");
            String choice = scan.nextLine();
            int choiceInt = Integer.valueOf(choice);
            
            switch (choiceInt) {
                case 1:
                    //search by band name
                    //ask user for name to search by
                    System.out.println("Enter the Band Name you are looking for:");
                    String bandName = scan.nextLine();
                    //searching by band name
                    System.out.println(binarySearchName(data.bandNamesSort(data.getNameList()),bandName, data.getHashMap()));
                    break;
                case 2:
                    //search by set list
                    //ask user for set time to search by
                    System.out.println("Enter the Set time you are looking for:");
                    float setTimeInput = scan.nextFloat();
                    int lowSet = 0;
                    int highSet = data.getSetList().size() -1;
                    //searching by set time
                    System.out.println(binarySearchSet(data.sortSetTimes(data.getSetList()), setTimeInput, lowSet, highSet, data.getHashMap()));
                    break;
                case 3:
                    //exit
                    System.out.println("Bye!");
                    break OUTER;
                default:
                    break;
            }
        } while (true);
    }
    
    //method to search for band name using binary search non-recursive
    private static String binarySearchName(List<String> list, String key, HashMap<String, Float> map) {
        int begin = 0;
        int end = list.size()-1;
        int mid=0;
        key = key.toLowerCase();
        
        boolean found = false;
        
        //key is between begin and end
        while(!found && begin <= end) {
            mid = (begin + end) / 2; //integer division
            
            if((key.compareTo(list.get(mid).toLowerCase()) == 0)) {
                found = true;
            } else if((key.compareTo(list.get(mid).toLowerCase()) < 0)) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        
        if(found) {
            //Using the hash map in the parameter, display the key and value depending on the key
            for(Entry<String, Float> entry : map.entrySet()) {
                if(Objects.equals(list.get(mid), entry.getKey())) {
                    return "Band found is: " + 
                            list.get(mid) + " has a set time of " + 
                            entry.getValue() + " minutes";
                }
            }
        } else {
            return "The band " + key + " was not found";
        }
        
        return null;
    }
    
    //method to search for set time recursively with binary search
    private static String binarySearchSet(List<Float> list, float keyVal,
            int low, int high, HashMap<String, Float> map) {
        int key = (int) keyVal;
        int mid = (low + high) / 2;
        
        //if the element is found, then return it
        if(high < low) {
            //Using the hash map in the parameter, display the key and value depending on the value
            for(Entry<String, Float> entry : map.entrySet()) {
                if(Objects.equals(list.get(low), entry.getValue())) {
                    return "Band with the closest set time is: " + 
                            entry.getKey() + " has a set time of " + 
                            list.get(low) + " minutes";
                }
            }
        }
        
        //recursively compare key and mid
        if(key < list.get(mid)) {
            return binarySearchSet(list, key, low, mid - 1, map);
        } else if(key == list.get(mid)) {
            return mid + "";
        } else {
            return binarySearchSet(list, key, mid + 1, high, map);
        }
    }
}
