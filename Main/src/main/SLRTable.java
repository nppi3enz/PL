/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Stack;

public class SLRTable{
    static Stack stack = new Stack();
    static int[][] SLRTable;
    static ArrayList<String> TnNT;
    static ArrayList<Integer> left;
    static ArrayList<ArrayList<Integer>> right;
    
    SLRTable(ArrayList<String> TnNT,ArrayList<Integer> left,ArrayList<ArrayList<Integer>> right,
        ArrayList<Integer> Terminal,ArrayList<Integer> Nonterminal,
        ArrayList<ArrayList<Integer>> first,ArrayList<ArrayList<Integer>> follow,ArrayList<String> input){
        
        this.TnNT = TnNT;
        this.left = left;
        this.right = right;
        
        Table SLR = new Table(TnNT,left,right,Terminal,Nonterminal,first,follow);
        SLR.call();
        SLRTable = SLR.table;
        String cur;
        stack.push(0);
        System.out.println("Stack = "+stack);///////////////////////////////////////////////////////////////
        while(!stack.isEmpty()||!input.isEmpty()){
            cur = input.get(0);
            int SoR = SLRTable[(Integer)stack.peek()][findIndex(cur)];
            System.out.println("Seek = "+cur+" , Input = "+input);//////////////////////////////////////////
            if(SoR==1&&cur.equals("$")){
                System.out.println("Accept!!");
                reduce(-1);
                input.remove(0);
                break;
            }else{
                    if(SoR>0){
                System.out.println("Action: Shift "+SLRTable[(Integer)stack.peek()][findIndex(cur)]);///////
                    shift(SoR,cur);
                    input.remove(0);
                }else if(SoR<0){
                    int use = SoR*(-1);
                    System.out.print("Action: Reduce "+use+" to ");////////////////////////////////////////
                    reduce(use);
                }else{
                    System.out.println("Error");
                    break;
                }
                System.out.println("Stack = "+stack+"\n");/////////////////////////////////////////////////
            }
        }
    }
    public static int findIndex(String in){
        if(TnNT.contains(in)){
            return TnNT.indexOf(in);
        }else{return -1;} //return -1 means error
    }
    
    public static void shift(int state,String input){
        stack.push(input);
        stack.push(state);
    }
    
    public static void reduce(int use){
        ArrayList<Integer> R;
        //use == -1 use for checking IsAcceptState
        if(use==-1){ R = (ArrayList)right.get(0).clone();}
        else {R = (ArrayList)right.get(use).clone();}
        while(!R.isEmpty()){ 
            stack.pop();
            stack.pop();
            R.remove(0);
        }
        if(use!=-1){
            int state = SLRTable[(Integer)stack.peek()][left.get(use)];
            stack.add(TnNT.get(left.get(use)));
            stack.add(state);
            System.out.println(state);///////////////////////////////////////////////////////////////////////
        }
    }
    
    
}
