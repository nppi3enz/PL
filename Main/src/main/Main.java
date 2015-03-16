package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 *
 * @author apple
 */
public class Main {
    static Stack stack = new Stack();
    static int[][] SLRTable;
    static ArrayList<String> TnNT;
    static ArrayList<ArrayList<Integer>> right;
    static ArrayList<Integer> left;
    static ArrayList<Integer> nonterminal;
    static ArrayList<Integer> terminal;
    static ArrayList<ArrayList<Integer>> first, follow;
    public static void main(String[] args) throws IOException{
        FileReader fs = new FileReader("PL.txt");
	BufferedReader buff = new BufferedReader(fs); 
        Input data = new Input(buff); 
        
        //STEP1 : scanner
        ArrayList name = data.getName();
        left = data.getLeft();
        right = data.getRight();
        
        //data.showProduct();
        
        First step2 = new First(name, left, right);
        //STEP2 : find non-terminal
        System.out.print("non-terminal :- ");
        nonterminal = step2.findnonterminal(left,name);
        
        //STEP3 : find terminal
        System.out.print("terminal :- ");
        terminal = step2.findterminal(right,name);
        
        //STEP4 : find union
        TnNT = new ArrayList<String>();
        // ArrayList arrtemp = new ArrayList<String>();
        //union.addAll(terminal);
        for(int i=0;i<terminal.size();i++) {
            String tmp = (String)name.get(terminal.get(i));
            //int tmp = terminal.get(i);
            TnNT.add(tmp);
        }
        
        TnNT.add("$");
        //union.addAll(nonterminal);
        for(int i=0;i<nonterminal.size();i++) {
            String tmp = (String)name.get(nonterminal.get(i));
            TnNT.add(tmp);
        }
        
        System.out.print("TnNT :- ");
        System.out.println(TnNT.toString());
        
        //STEP5 : First
        first = step2.findingFirst(name,left,right);
        
        //STEP6 : Follow
        follow = step2.findingFollow(name,left,right,first);
        
}
    public static void show(){ //for show value
        System.out.println(TnNT);
        System.out.println(left);
        System.out.println(right);
        System.out.println(terminal);
        System.out.println(nonterminal);
        System.out.println(first);
        System.out.println(follow);
    }
}
