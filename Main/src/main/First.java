/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.ArrayList;
import java.util.Stack;
/**
 *
 * @author apple
 */
public class First{
    Stack v = new Stack();
    static ArrayList<Integer> terminal =new ArrayList<Integer>();
    static ArrayList<Integer> nonterminal =new ArrayList<Integer>();
    
    private ArrayList<String> name;
    private ArrayList<Integer> left; 
    private ArrayList<ArrayList> right;
    First(ArrayList<String> name, ArrayList<Integer> left,ArrayList<ArrayList> right){
        this.left = left;
        this.right = right;
        this.name = name;
    }
    
    static ArrayList findterminal(ArrayList left,ArrayList item){
        for(int a=0;a<left.size();a++){
            if(!terminal.contains(left.get(a))){
                terminal.add((int)left.get(a));
            }
        }
        System.out.println(terminal.toString());
        return terminal;
    }
    
    static ArrayList findnonterminal(ArrayList<ArrayList> right,ArrayList item){
        for(int L=0;L<right.size();L++){
            
            for(int R=0;R<right.get(L).size();R++){
                if (!terminal.contains(right.get(L).get(R))){
                    nonterminal.add((int)right.get(L).get(R));
                }
                
                
            }
            
        }
        System.out.println(nonterminal.toString());
        return nonterminal;
    }
    
    static void findingFirst(ArrayList name,ArrayList left,ArrayList<ArrayList> right)       
    {
        
        ArrayList<ArrayList> first = new ArrayList<>();
        
        for(int a =0;a<name.size();a++){  //terminals a,First(a)={a} nonterminals A,First(A)={}
            first.add(new ArrayList<Integer>());
           
            if(nonterminal.contains(a)){
                first.get(a).add(a);
            }}
         //System.out.println(first.toString());
         
         int indexEmpty = -1; //where is empty string?
         for(int b =0;b<name.size();b++){
             
             if(name.get(b).equals("empty")){
                indexEmpty = b;
                System.out.println("Empty at "+b );
         }}
        
         
        int change =1;//have change >0,not change =0
        while(change!=0){
            change =0;
                for(int a=0;a<left.size();a++){ //for all grammar
                    int first0 =(int)right.get(a).get(0); //at index0 of right
                        /*System.out.print(left.get(a)+"=>");
                        System.out.print(first0+" ");
                        
                        System.out.println(first.get(first0).toString());*/
                        for(int numFirst=0;numFirst<first.get(first0).size();numFirst++){
                            int obj = (int)first.get(first0).get(numFirst);
                            //System.out.println(obj);
                            
                            if(!first.get((int)left.get(a)).contains(obj)){
                                first.get((int)left.get(a)).add(obj);
                                change=1;
                            
                            }
                            
                        }
                        
         
                }
                //check contain empty string 
                for(int a=0;a<left.size();a++){ //loop grammar
                    int position=0;//position of right that use to find first contain empty string
                    int findin = (int)right.get(a).get(position);
                    int addin;
                    //System.out.println(left.get(a)+"=>"+right.get(a).get(position));
                    
                    if(first.get(findin).contains(indexEmpty)) //position 0 of right has empty string
                    {   if(position+1<right.get(a).size()){ // have next object 
                        position++;
                            findin = (int)right.get(a).get(position);
                            addin = (int)left.get(a);
                            System.out.println("addin"+addin);
                            int max = first.get(findin).size();
                                int i=0;
                                while(i<max){
                                    if(!first.get(addin).contains(first.get(findin).get(i))){
                                    first.get(addin).add(first.get(findin).get(i));}
                                    i++;
                                }
                        System.out.println("poisition : "+position);
                    }   
                    }
                }
                    
                //System.out.println(first.toString());

            
        }
        for(int i=0;i<first.size();i++){
            if(terminal.contains(i)){
            System.out.print("First("+name.get(i)+") = "); 
            System.out.print("{");
            for(int j=0;j<first.get(i).size();j++){
                if(j!=0){System.out.print(",");}
                System.out.print(name.get((int)first.get(i).get(j)));
            }
            System.out.print("}");
            System.out.println();
        }
        }
    }
    
}
