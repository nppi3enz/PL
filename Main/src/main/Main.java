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
        
        //data.showProduct();
        
        First step2 = new First(name, left, right);
        
        System.out.print("non-terminal :- ");
        ArrayList nonterminal = step2.findnonterminal(left,name);
        System.out.print("terminal :- ");
        ArrayList terminal = step2.findterminal(right,name);
        ArrayList union = new ArrayList(terminal);
        union.add("$");
        union.addAll(nonterminal);
        System.out.print("Union :- ");
        System.out.println(union.toString());
        
        ArrayList<ArrayList> first = step2.findingFirst(name,left,right);
        step2.findingFollow(name,left,right,first);
       
                
        
    }
    
}
