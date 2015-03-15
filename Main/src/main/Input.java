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
public class Input{
    ArrayList<String> name = new ArrayList<String>(); 
    ArrayList<Integer> left = new ArrayList<Integer>(); 
    ArrayList<ArrayList> right = new ArrayList<>();
    
	Input(BufferedReader buff) throws IOException{
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
        public ArrayList<String> getName(){
            return name;
        }
        public ArrayList<Integer> getLeft(){
            return left;
        }
        public ArrayList<ArrayList> getRight(){
            return right;
        }
        
        public void showProduct(){
            //System.out.println(name.toString());
            for(int index =0;index<left.size();index++){
	        System.out.print(name.get(left.get(index))+"=>");
                for(int j=0;j<right.get(index).size();j++) {
                    int temp = (int)right.get(index).get(j);
                    System.out.print(name.get(temp)+" ");
                }
                System.out.println();
                
            }
            
        }
        

}
	


