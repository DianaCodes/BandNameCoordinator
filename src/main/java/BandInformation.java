
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diana Arita
 */

public class BandInformation {
    private String bandName;
    private float setTime;
    
    //Load the file containing the band data
    private File bandContentFile = new File("C:\\Users\\SDEMO2\\Documents\\NetBeansProjects\\BandCoordinator\\src\\main\\java\\bandinfo.txt");

    //List for the names
    private List<String> bandNames = new ArrayList<String>();

    //List for the set times
    private List<Float> setTimes = new ArrayList<Float>();
    
    //create BandInformation hash map to store keys and values
    //to be used by search methods
    private HashMap<String, Float> map = new HashMap<String, Float>();
    
    public BandInformation(){
        //add file contents to the appropriate list
        try {
            Scanner sc = new Scanner(new FileInputStream(bandContentFile));
            
            //use delimeter to filter by pipe
            sc.useDelimiter(Pattern.compile("[|\n]"));
            
            while(sc.hasNext()) {
                setName(bandNames,sc.next());
                setTime(setTimes,sc.nextFloat());
            }
            sc.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("\nProgram terminated Safely...");
        }
        
        //add to hash map
        for(int i=0;i<bandNames.size();i++) {
            map.put(bandNames.get(i),setTimes.get(i));
        }
    }
    
    //getters and setters
    public void setName(List<String> list, String bandName) {
        list.add(bandName);
    }
    
    public String getName(String bandName) {
        for(Map.Entry<String, Float> entry : map.entrySet()) {
            if(Objects.equals(bandName, entry.getKey())) {
                return entry.getKey();
            }
        }
        
        return null;
    }
    
    public void setTime(List<Float> list, float setTime) {
        list.add(setTime);
    }
    
    public Float getSetTime(float setTime) {
        for(Map.Entry<String, Float> entry : map.entrySet()) {
            if(Objects.equals(setTime, entry.getValue())) {
                return entry.getValue();
            }
        }
        
        return null;
    }
    
    public List<String> getNameList(){
        return bandNames;
    }
    
    public List<Float> getSetList(){
        return setTimes;
    }
    
    public HashMap<String,Float> getHashMap(){
        return map;
    }
    
    //method to sort band names with selection sort
    public static List<String> bandNamesSort(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if(list.get(i).compareTo(list.get(j))>0) {
                    String tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        
        return list;
    }
    
    //method to sort set times using selection sort
    public static List<Float> sortSetTimes(List<Float> list) {
        for (int i = 0; i < list.size(); i++) {

            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(i) > list.get(j)) {

                    float tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);

                }

            }

        }
        
        return list;
    }
}
