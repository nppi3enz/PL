package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Table {
    static ArrayList<String> TnNT; 
    static ArrayList<Integer> left;
    static ArrayList<ArrayList<Integer>> right;
    static ArrayList<Integer> Terminal;
    static ArrayList<Integer> Nonterminal;
    static ArrayList<ArrayList<Integer>> first;
    static ArrayList<ArrayList<Integer>> follow;
    
    static ArrayList<ArrayList<Integer>> item = new ArrayList();
    static ArrayList<ArrayList<Integer>> state = new ArrayList();
    static ArrayList<ArrayList<Integer>> kernel = new ArrayList();
    static ArrayList<Integer> sTable = new ArrayList();
    static int dot; //last number
    static int[] lookAt;
    static int[][] table;
    
    Table(ArrayList<String> TnNT,ArrayList<Integer> left,ArrayList<ArrayList<Integer>> right,
          ArrayList<Integer> Terminal,ArrayList<Integer> Nonterminal,
          ArrayList<ArrayList<Integer>> first,ArrayList<ArrayList<Integer>> follow){
        this.TnNT = TnNT;
        this.left = left;
        this.right = right;
        this.Terminal = Terminal;
        this.Nonterminal = Nonterminal;
        this.first = first;
        this.follow = follow;
        dot = TnNT.size();
        lookAt = new int[Nonterminal.size()];
    }
    public static void call(){
        genItem();
        table(); 
    }
    public static void genItem(){
        int index = 0;
        for(int i=0;i<right.size();i++){
            if(index<lookAt.length&&(Integer)left.get(i)==(Integer)Nonterminal.get(index)){ //Table link for finding closure
                lookAt[index] = item.size();
                index++;
            }
            int count = 0;
            while(count<=right.get(i).size()){
                item.add((ArrayList<Integer>)right.get(i).clone());
                item.get(item.size()-1).add(count, dot);
                item.get(item.size()-1).add(0, left.get(i));//index 0 keeps left, index > 0 keep right 
                count++;
            }
        }        
    }
    
    public static void table(){
        findShiftTo();
        table = new int[state.size()][TnNT.size()];
        while(!sTable.isEmpty()){
            table[sTable.remove(0)][sTable.remove(0)] = sTable.remove(0);
        }
        findReduceAccept();
        printTable(table);
    }
    
    public static void findReduceAccept(){
        int index,inDot,a,b;
        for(int i=0;i<state.size();i++){
            for(int j=0;j<state.get(i).size();j++){
                index = state.get(i).get(j);
                inDot = item.get(index).indexOf(dot);
                if(inDot==item.get(index).size()-1){
                    a = item.get(index).get(0);
                    int reIndex = reduceBy(item.get(index));
                    for(int m=0;m<follow.get(a).size();m++){
                        b = follow.get(a).get(m);
                        table[i][b] = (-1)*reIndex;
                    }
                }
            }
        }
    }
    public static int reduceBy(ArrayList<Integer> reBy){
        int re = -2;
        Stack<Integer> L = new Stack();
        for(int i=0;i<left.size();i++){
            if(left.get(i)==reBy.get(0)){
                if(i==0){
                    return re = -1;//Accept state
                }
                else{L.push(i);}
            }
        }
        while(!L.isEmpty()){
            int index = 1;
            int rIndex = L.pop();
            for(int i=0;i<right.get(rIndex).size();i++){
                if(right.get(rIndex).get(i)!=reBy.get(index)){
                    break;
                }else if(i==right.get(rIndex).size()-1&&index+1==reBy.size()-1&&reBy.get(index+1)==dot){
                    return re = rIndex;
                }
                index++;
            }
        }
        return re;
    }
    public static void findShiftTo(){        
        state.add(closure(0)); //start here
        kernel.add(new ArrayList(Arrays.asList(0)));
        int count = 0;
        for(int n=0;n<state.size();n++) {
            for(int i=0;i<TnNT.size();i++) {
                if(!TnNT.get(i).equals("$")) {
                    int shiftTo = goTo(count,i); //////////////////////input in Table 
                    if(shiftTo>=0){
                        sTable.add(count);
                        sTable.add(i);
                        sTable.add(shiftTo);
                        //System.out.println("goTo("+count+","+TnNT.get(i)+")");
                        //System.out.println("\tshift @ "+shiftTo);
                    }
                }
            }
            count++;
        }
    }
    
    public static int goTo(int s, int read){
        ArrayList<Integer> closureAll = new ArrayList();
        ArrayList<Integer> closureAllnew  = new ArrayList();
        
        for(int i=0;i<state.get(s).size();i++) { //loop closure 
            int temp = state.get(s).get(i);//use ith in item
            int pos = item.get(temp).indexOf(dot);//find dot in the item
            if( pos+1<item.get(temp).size()&&item.get(temp).get(pos+1)==read&&temp+1<item.size()){//seek if After dot is read
                closureAll.add(temp+1);//move dot
                ArrayList<Integer> nextClosureAll = new ArrayList (closure(temp+1));//find closure
                for(int j=0;j<nextClosureAll.size();j++){//closureAll union nextClosureAll
                    if(!closureAll.contains(nextClosureAll.get(j))) {
                       closureAllnew.add(nextClosureAll.get(j));
                    }
                }
            }      
        }
        closureAllnew.addAll(closureAll);
        Boolean check = true;
        int indexS = -1;
        for(int i=0;i<kernel.size();i++){
            for(int j=0;j<kernel.get(i).size()&&j<closureAll.size();j++){
                if(kernel.get(i).get(j)!=closureAll.get(j)) {
                    break;
                }else if((j==closureAll.size()-1)&&(kernel.get(i).get(j)==closureAll.get(j))){
                    indexS = i;
                    check = false;
                }
            }  
        }
        if(check) {
            if(closureAllnew.size()!=0) {
                kernel.add(closureAll);
                state.add(closureAllnew);
                return state.size()-1;
            }
            return -1;
        } else {
            return indexS;
        }
    }
    
    public static ArrayList<Integer> closure(int I){
        ArrayList<Integer> closure = new ArrayList();
        Stack<Integer> used = new Stack();
        Stack<Integer> next = new Stack();
        closure.add(I);
        int C = 0;//C is Terminal n Nonterminal
        for(int i=0;i<item.get(I).size();i++){
            if(i!=item.get(I).size()-1&&item.get(I).get(i)==dot){
                C = item.get(I).get(i+1);
                break;
            }
        }
        if(Nonterminal.contains(C)) {
            findClosure(C,closure,used,next); //generate next closure
        }
        return closure;
    }
    public static void findClosure(int C,ArrayList<Integer> closure,Stack<Integer> used,Stack<Integer> next){
        used.add(C);
        int begin = 0;//length of finding C's closure
        int last = 0;
        for(int i=0;i<lookAt.length;i++){
            if(Nonterminal.get(i)==C){
                begin = lookAt[i];
                if(begin==lookAt[lookAt.length-1]){
                    last = item.size();
                }else{ last = lookAt[i+1];}
                break;
            }
        }
        for(int i=begin;i<last;i++){
            if(item.get(i).get(1)==dot){
                closure.add(i);
            }
        }
        for(int i=0;i<closure.size();i++){
            if(!used.contains(item.get(closure.get(i)).get(2))){
                next.push(item.get(closure.get(i)).get(2));
            }
        }
        while(!next.isEmpty()){
            findClosure(next.pop(),closure,used,next);
        }
    }
    
    public static void printTable(int[][] table){
        for(int i=0;i<TnNT.size();i++){
            System.out.print("\t"+TnNT.get(i));
        }System.out.println();
        for(int i=0;i<TnNT.size();i++){
            System.out.print("-------"+"\t");
        }System.out.println();
        for(int i=0;i<state.size();i++){
            System.out.print(i+"|");
            for(int j=0;j<TnNT.size();j++){
                System.out.print("\t"+table[i][j]);
            }
            System.out.println();
        }
    }
}
