/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Collections;

/**
 *
 * @author apple
 */
public class Input extends First{
    ArrayList<String> name = new ArrayList<String>(); 
    ArrayList<Integer> left = new ArrayList<Integer>(); 
    ArrayList<ArrayList> right = new ArrayList<>();
    
	public Input(BufferedReader buff) throws IOException{
		String line=null; 
		
		int count = 0;
		
		while ( (line = buff.readLine()) != null ){
			count++;
			String[] data = line.split(" -> ");
			String[] dataCutNumber = data[0].split(" ");
			String[] dataCutOr = data[1].split(Pattern.quote(" | "));;
			
                        //check replace left
			if(!name.contains(dataCutNumber[1])) {
				name.add(dataCutNumber[1]);
			}
			int idleft = name.indexOf(dataCutNumber[1]);
			
			for(int j=0;j<dataCutOr.length;j++){
				
				String[] eachPR = dataCutOr[j].split(" ");
				//check replace right
				left.add(idleft);
				right.add(new ArrayList<Integer>());
				for(int k=0;k<eachPR.length;k++) {
						if(!name.contains(eachPR[k])) {
							name.add(eachPR[k]);
						}	
						int posright = name.indexOf(eachPR[k]);
						int posleft = left.indexOf(idleft);
						right.get(left.size()-1).add(posright);
						
					}
				}
		}
            name.add("$");
	}
        public void showNumber() { //will display
                for(int index =0;index<left.size();index++){
                    System.out.print(left.get(index)+"=>");
                    System.out.println(right.get(index));
		}
        }
        public void findTerminal() {
            System.out.print("terminal :- ");
            ArrayList terminal = super.findterminal(left,name);
            System.out.print("non-terminal :- ");
            ArrayList nonterminal = super.findnonterminal(right,name);
            super.findingFirst(name,left,right) ;
            
            //re-arrange name
            ArrayList newname = new ArrayList<Integer>(terminal); //copy terminal to newname
            newname.add("$");
            newname.addAll(nonterminal);
            
            System.out.println("new name = "+newname.toString());
            
            name = newname;
        }

}
	


