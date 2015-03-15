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
    static ArrayList<Integer> nonterminal =new ArrayList<Integer>();
    static ArrayList<Integer> terminal =new ArrayList<Integer>();
    
    private ArrayList<String> name;
    private ArrayList<Integer> left; 
    private ArrayList<ArrayList> right;
    First(ArrayList<String> name, ArrayList<Integer> left,ArrayList<ArrayList> right){
        this.left = left;
        this.right = right;
        this.name = name;
    }
    
    static ArrayList findnonterminal(ArrayList left,ArrayList item){
        for(int a=0;a<left.size();a++){
            if(!nonterminal.contains(left.get(a))){
                nonterminal.add((int)left.get(a));
            }
        }
        System.out.println(nonterminal.toString());
        return nonterminal;
    }
    
    static ArrayList findterminal(ArrayList<ArrayList> right,ArrayList item){
        for(int L=0;L<right.size();L++){
            
            for(int R=0;R<right.get(L).size();R++){
                if (!nonterminal.contains(right.get(L).get(R))  && !terminal.contains(right.get(L).get(R))){
                    terminal.add((int)right.get(L).get(R));
                }
            }
            
        }
        System.out.println(terminal.toString());
        return terminal;
    }
    
    static ArrayList<ArrayList> findingFirst(ArrayList name,ArrayList left,ArrayList<ArrayList> right)       
    {
        ArrayList all = new ArrayList(nonterminal);
        all.add("$");
        all.addAll(terminal);
        System.out.println("All :-"+all.toString());
        ArrayList<ArrayList> first = new ArrayList<>();
        
        for(int a =0;a<name.size();a++){  //nonterminals a,First(a)={a} terminals A,First(A)={}
            first.add(new ArrayList<Integer>());
           
            if(terminal.contains(a)){
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
            if(nonterminal.contains(i)){
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
        return first;
    }
     static void findingFollow(ArrayList name,ArrayList left,ArrayList<ArrayList> right,ArrayList<ArrayList> first){
          ArrayList<ArrayList> follow = new ArrayList<>();
          for(int a =0;a<name.size();a++){  //create empty Arraylist follow
            follow.add(new ArrayList<Integer>());
           }
          
                            int indexEmpty = -1; //where is empty string?
                                for(int b =0;b<name.size();b++){
                                    if(name.get(b).equals("empty"))
                                    {   indexEmpty = b;
                                        System.out.println("Empty at "+b );
                                    }}
                            int $ = -1; //where is empty string?
                                for(int b =0;b<name.size();b++){
                                    if(name.get(b).equals("$"))
                                    {   $ = b;
                                        System.out.println("$ at "+b );
                                    }}
        
        follow.get(0).add($);//Follow(Start Symbol) = {$}
        System.out.println("follow "+follow.toString());
         
         for(int a=0;a<left.size();a++){//for each production A=>A1 A2...An
             int A =(int)left.get(a);//find A in right
                for(int i =0;i<right.size();i++) 
                {   for(int j=0;j<right.get(i).size();j++)
                    {   if(right.get(i).get(j).equals(A)) //found A in right
                        {   
                                
                                int addfollow = (int)right.get(i).get(j);
                                
                                if(j==right.get(i).size()-1)
                                { //add findFollow to addTo
                                    int findFollow = (int)left.get(i);
                                    int addTo   = addfollow;
                                    
                                    int max2 = follow.get(findFollow).size();
                                    int count2 =0;
    
                                    while(count2<max2)
                                        {
                                            if(!follow.get(addTo).contains(follow.get(findFollow).get(count2)))
                                                {  
                                                    follow.get(addTo).add(follow.get(findFollow).get(count2));
                                                    
                                                }
                                               count2++;
                                        }

                                }
                                int position =j+1;
                                while(position<right.get(i).size())
                                {   

                                    
                                    int findfirst =(int)right.get(i).get(position);
                                    
                                
                                        int max = first.get(findfirst).size();
                                        int count =0;
                                            while(count<max)
                                            { if(!follow.get(addfollow).contains(first.get(findfirst).get(count)))
                                                if(!first.get(findfirst).get(count).equals(indexEmpty))
                                                {   follow.get(addfollow).add(first.get(findfirst).get(count));
                                                    
                                                }
                                            count++;
                                            }
                                    

                                    if(!first.get(findfirst).contains(indexEmpty))
                                    {//dont have empty add findfirst in addfollow
                                        position = Integer.MAX_VALUE;
                                    }
                                    else{//first have empty string
                                        position++;
                                            if(position==right.get(i).size()-1)
                                                {   int findFollow =(int)left.get(i);
                                                    int addTo   = addfollow;
                                            
                                                    int max2 = follow.get(findFollow).size();
                                                    int count2 =0;
    
                                                    while(count2<max2)
                                                        {
                                                            
                                                            if(!follow.get(addTo).contains(follow.get(findFollow).get(count2)))
                                                        {  System.out.println(count);
                                                            System.out.println("to"+follow.get(addTo).toString());
                                                            System.out.println("add"+follow.get(findFollow).toString());
                                                            
                                                            follow.get(addTo).add(follow.get(findFollow).get(count2));
                                                            
                                                            
                                                            System.out.println("Follow : "+name.get(findFollow));
                                                            System.out.println(follow.get(addTo).toString());
                                                        }
                                                    count2++;
                                                        }
                                            
                                        }
                                        
                                        }
   
                                    
                                   
                                    
                                }
                                
                        
                        }
                        
                    }    
                }
         }
         for(int i=0;i<follow.size();i++){
            if(nonterminal.contains(i)){
            System.out.print("Follow("+name.get(i)+") \t\t : "); 
            System.out.print("{");
            for(int j=0;j<follow.get(i).size();j++){
                if(j!=0){System.out.print(",");}
                System.out.print(name.get((int)follow.get(i).get(j)));
            }
            System.out.print("}");
            System.out.println();
        }
        }
     }     
    
}
