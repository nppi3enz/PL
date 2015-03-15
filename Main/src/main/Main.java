package main;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author apple
 */
public class Main {

    public static void main(String[] args) throws IOException{
        FileReader fs = new FileReader("PL-C-Minus-1.txt");
	BufferedReader buff = new BufferedReader(fs); 
        Input data = new Input(buff); //send to scanner
        data.findTerminal();
        
    }
    
}
