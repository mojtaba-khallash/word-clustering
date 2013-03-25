package ir.ac.iust.nlp.wordclustering;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mojtaba Khallash
 */
public class ReadFile {

    private static String wordName = "word_paths.txt";
    private static String lemmName = "lemm_paths.txt";

    public static HashMap<String, List<String>> GetList(boolean word, int clusterNumber) {
        String workingDir = System.getProperty("user.dir") + File.separator;

        String name;
        if (word == true)
            name = wordName;
        else
            name = lemmName;

        try {
            String FileName = workingDir + "Source" + File.separator + 
                    clusterNumber + File.separator + name;
            BufferedReader reader =new BufferedReader(new InputStreamReader(
                    new FileInputStream(FileName), "UTF8"));
            
            HashMap<String, List<String>> map = new HashMap<String, List<String>>();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\t");
                String id = split[0];
                List<String> list = map.get(id);
                if (list == null) {
                    list = new LinkedList<String>();
                }
                list.add(split[1]);
                map.put(id, list);
            }
            
            return map;
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot Find Corpus File:");
            System.out.println("  - " + workingDir + name);
            System.exit(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        
        return null;
    }
    
    public static HashMap<String, String> GetList(int prefixLength, boolean word, int clusterNumber) {
        String workingDir = System.getProperty("user.dir") + File.separator;
        
        String name;
        if (word == true)
            name = wordName;
        else
            name = lemmName;

        String FileName = workingDir + "Source" + File.separator + 
                clusterNumber + File.separator + name;
        try {
            BufferedReader reader =new BufferedReader(new InputStreamReader(
                    new FileInputStream(FileName), "UTF8"));
            
            HashMap<String, String> map = new HashMap<String, String>();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\t");
                String strID = split[0];
                if (prefixLength != -1 && strID.length() > prefixLength) {
                    strID = strID.substring(0, prefixLength);
                }
                map.put(split[1], strID);
            }
            
            return map;
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot Find Corpus File:");
            System.out.println("  - " + workingDir + name);
            System.exit(1);
        } catch (Exception ex) {
            System.exit(1);
        }
        
        return null;
    }
}