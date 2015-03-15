/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.util.ArrayList;
import java.util.Stack;



public class First {
    Stack v = new Stack();
    static ArrayList<Integer> terminal = new ArrayList<Integer>();
    static ArrayList<Integer> nonterminal =new ArrayList<Integer>();
    
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
                if (!terminal.contains(right.get(L).get(R)) && !nonterminal.contains(right.get(L).get(R))){
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
         System.out.println(first.toString());
         
         int indexEmpty; //where is empty string?
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
                        System.out.print(left.get(a)+"=>");
                        System.out.print(first0+" ");
                        
                        System.out.println(first.get(first0).toString());
                        for(int numFirst=0;numFirst<first.get(first0).size();numFirst++){
                            int obj = (int)first.get(first0).get(numFirst);
                            System.out.println(obj);
                            
                            if(!first.get((int)left.get(a)).contains(obj)){
                                first.get((int)left.get(a)).add(obj);
                                change=1;
                            
                            }
                        }
         
                }
                System.out.println(first.toString());

            
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
        }}
         
        
    }
    
}
