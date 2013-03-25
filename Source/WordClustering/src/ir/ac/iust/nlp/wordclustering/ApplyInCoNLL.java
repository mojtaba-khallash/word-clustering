package ir.ac.iust.nlp.wordclustering;

import java.io.*;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Mojtaba Khallash
 */
public class ApplyInCoNLL {
    
    public static PrintStream out = System.out;

    public static void Start(String input, String output, int clusterCount, int prefixLength, boolean word) throws Exception {
        
        out.println();
        out.println("Started: " + new Date(System.currentTimeMillis()));
        
        int numFeats = 0;
        
        HashMap<String, String> map = ReadFile.GetList(prefixLength, word, clusterCount);
        
        File outFile = new File(output);
        if (outFile.exists())
            outFile.delete();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(input), "UTF8"));
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(output, true), "UTF-8"));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().length() != 0) {
                String[] split = line.split("\t");
                int id = Integer.parseInt(split[0]);
                String lexeme = split[1];
                String lemma = split[2];
                String cpos = split[3];
                String fpos = split[4];
                String feat = split[5];
                int head = Integer.parseInt(split[6]);
                String dependency = split[7];

                String val;
                if (word == true) {
                    val = lexeme;
                }
                else {
                    val = lemma;
                }
                
                String SVC = getWordClusterID(map, val);
                if (SVC.length() != 0) {
                    numFeats++;
                    if (feat.equals("_")) {
                        feat = SVC;
                    } else {
                        feat += "|" + SVC;
                    }
                }

                StringBuilder os = new StringBuilder();
                os.append(id).append("\t").append(lexeme).append("\t").append(lemma).append("\t").append(cpos).append("\t").append(fpos).append("\t").append(feat).append("\t").append(head).append("\t").append(dependency).append("\t_\t_");
                writer.write(os.toString() + "\n");
            } else {
                writer.write("\n");
            }
        }
        
        writer.close();
        reader.close();
        
        out.println("Number of added feats: " + numFeats);
        out.println("Finished: " + new Date(System.currentTimeMillis()));
    }

    private static String getWordClusterID(HashMap<String, String> map, String word) {
        word = word.replaceAll("ي", "ی");
        String res = map.get(word);
        if (res == null)
            return "";
        else
            return "wcID=" + res;
    }
}