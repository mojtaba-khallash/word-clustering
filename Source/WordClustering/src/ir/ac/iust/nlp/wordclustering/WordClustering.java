package ir.ac.iust.nlp.wordclustering;

/*
* Copyright (C) 2013 Iran University of Science and Technology
*
* This file is part of "Word Clustering" Project, as available 
* from http://nlp.iust.ac.ir This file is free software;
* you can redistribute it and/or modify it under the terms of the GNU General 
* Public License (GPL) as published by the Free Software Foundation, in 
* version 2 as it comes in the "COPYING" file of the VirtualBox OSE 
* distribution. VirtualBox OSE is distributed in the hope that it will be 
* useful, but WITHOUT ANY WARRANTY of any kind.
*
* You may elect to license modified versions of this file under the terms 
* and conditions of either the GPL.
*/

import java.io.File;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Mojtaba Khallash
 */
public class WordClustering {

    // -v 0 -i <input-file> -o <output-file> -c <number-of-cluster(50|100)> -m <mode> -p <prefix-length>
    public static void main(String[] args) {
        boolean visisble = true;
        String input = "";
        String output = "";
        int clusterCount = 50;
        int prefixLength = -1;
        boolean word = true;
        
        showIntroduction();
        
        boolean exception = false;
        try {
            for (int i = 0; i< args.length; i++) {
                if (args[i].equals("-v")) {
                    i++;
                    String val = args[i];
                    if (!(val.equals("0") || val.equals("1"))) {
                        throw new Exception();
                    }
                    visisble = val.equals("1");
                }
                else if (args[i].equals("-i")) {
                    i++;
                    input = args[i];
                }
                else if (args[i].equals("-o")) {
                    i++;
                    output = args[i];
                }
                else if (args[i].equals("-c")) {
                    i++;
                    clusterCount = Integer.parseInt(args[i]);
                }
                else if (args[i].equals("-p")) {
                    i++;
                    prefixLength = Integer.parseInt(args[i]);
                }
                else if (args[i].equals("-m")) {
                    i++;
                    word = ("word".equals(args[i]));
                }
            }
            
            if (visisble == false && 
                (input.length() == 0 ||
                 output.length() == 0)) {
                throw new Exception();
            }
        } catch (Exception e) {
            exception = true;
            visisble = false;
        }
        finally {
            if (visisble == false) {
                if (exception == true) {
                    showHelp();
                    System.exit(1);
                }
                else {
                    try {
                        File in = new File(System.getProperty("user.dir") + File.separator + input);
                        if (!in.exists()) {
                            System.out.println("\t- Input file \"" + input + "\" not found.");
                            throw new Exception();
                        }
                        System.out.println("\tUsing " + (word == true ? "Word" : "Lemm"));
                        System.out.println("\tCluster Count: " + clusterCount);
                        System.out.println("\tPrefix Length: " + prefixLength);
                        ApplyInCoNLL.Start(input, output, clusterCount, prefixLength, word);
                        System.exit(0);
                    } catch(Exception ex) {
                        System.exit(1);
                    }
                }
            }
            else {
                showHelp();
            }
        }

        WClustering application = new WClustering();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(application);
            application.pack();
        } catch (Exception e) {
        }
        application.setVisible(true);
    }
    
    private static void showIntroduction() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                           WordClustering 1.0");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                            Mojtaba Khallash");
        System.out.println();
        System.out.println("             Iran University of Science and Technology (IUST)");
        System.out.println("                                 Iran");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();        
    }
    
    private static void showHelp() {
        System.out.println("Required Arguments:");
        System.out.println("        -v <visibility (0|1)>");
        System.out.println("        -i <input file>");
        System.out.println("        -o <output file>");
        System.out.println("Optional Arguments:");
        System.out.println("        -c <number-of-cluster(50|100) [default: 50]>");
        System.out.println("        -m <mode (word|lemm) [default word])>");
        System.out.println("        -p <prefix length (default -1 means full bit length)>");
    }
}