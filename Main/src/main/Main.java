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
        FileReader fs = new FileReader("PL.txt");
	BufferedReader buff = new BufferedReader(fs); 
        Input data = new Input(buff); //send to scanner
        ArrayList name = data.getName();
        ArrayList left = data.getLeft();
        ArrayList<ArrayList> right = data.getRight();
        
        First first = new First(name, left, right);
        
        System.out.print("terminal :- ");
        first.findterminal(left,name);
        System.out.print("non-terminal :- ");
        first.findnonterminal(right,name);
        first.findingFirst(name,left,right) ;
        
    }
    
}
