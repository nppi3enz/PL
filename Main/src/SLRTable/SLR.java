/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLRTable;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class SLR {
    static ArrayList<String> TnNT = new ArrayList(Arrays.asList("ID",";","[","NUM","]","int","void","(",")",",","{","}",
                                                                "if" ,"else","return","=","$","program","declaration-list",
                                                                "declaration","var-declaration","type-specifier",
                                                                "fun-declaration","params","param-list","param",
                                                                "compound-stmt","local-declarations","statement-list",
                                                                "statement","expression-stmt","selection-stmt","return-stmt",
                                                                "expression","var","factor","call","args","arg-list"));
                                                                //TnNT = Name
    static ArrayList<Integer> left = new ArrayList(Arrays.asList(17,18,18,19,19,20,20,21,21,22,23,23,24,24,25,25,26,27,27,28,
                                                                 28,29,29,29,29,30,30,31,31,32,32,33,33,34,34,35,35,35,35,36,
                                                                 37,37,38,38));
    static ArrayList<ArrayList<Integer>> right = new ArrayList(Arrays.asList(
            new ArrayList(Arrays.asList(18)),
            new ArrayList(Arrays.asList(18,19)),
            new ArrayList(Arrays.asList(19)),
            new ArrayList(Arrays.asList(20)),
            new ArrayList(Arrays.asList(22)),
            new ArrayList(Arrays.asList(21,0,1)),
            new ArrayList(Arrays.asList(21,0,2,3,4,1)),
            new ArrayList(Arrays.asList(5)),
            new ArrayList(Arrays.asList(6)),
            new ArrayList(Arrays.asList(21,0,7,23,8,26)),
            new ArrayList(Arrays.asList(24)),
            new ArrayList(Arrays.asList(6)),
            new ArrayList(Arrays.asList(24,9,25)),
            new ArrayList(Arrays.asList(25)),
            new ArrayList(Arrays.asList(21,0)),
            new ArrayList(Arrays.asList(21,0,2,4)),
            new ArrayList(Arrays.asList(10,27,28,11)),
            new ArrayList(Arrays.asList(27,20)),
            new ArrayList(Arrays.asList()),
            new ArrayList(Arrays.asList(28,29)),
            new ArrayList(Arrays.asList()),
            new ArrayList(Arrays.asList(30)),
            new ArrayList(Arrays.asList(26)),
            new ArrayList(Arrays.asList(31)),
            new ArrayList(Arrays.asList(32)),
            new ArrayList(Arrays.asList(33,1)),
            new ArrayList(Arrays.asList(1)),
            new ArrayList(Arrays.asList(12,7,33,8,29)),
            new ArrayList(Arrays.asList(12,7,33,8,29,13,29)),
            new ArrayList(Arrays.asList(14,1)),
            new ArrayList(Arrays.asList(14,33,1)),
            new ArrayList(Arrays.asList(34,15,33)),
            new ArrayList(Arrays.asList(35)),
            new ArrayList(Arrays.asList(0)),
            new ArrayList(Arrays.asList(0,2,33,4)),
            new ArrayList(Arrays.asList(7,33,8)),
            new ArrayList(Arrays.asList(34)),
            new ArrayList(Arrays.asList(36)),
            new ArrayList(Arrays.asList(3)),
            new ArrayList(Arrays.asList(0,7,37,8)),
            new ArrayList(Arrays.asList(38)),
            new ArrayList(Arrays.asList()),
            new ArrayList(Arrays.asList(38,9,33)),
            new ArrayList(Arrays.asList(33))));

    static Stack stack = new Stack();
    static int[][] Table = new int[74][39];
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Table SLR = new Table(TnNT,left,right,Terminal,Nonterminal,first,follow);
        //SLR.call();
        //SLRTable = SLR.table;
        
        ArrayList<String> input = new ArrayList<String>();
        FileReader fs = new FileReader("input2.txt");
	BufferedReader buff = new BufferedReader(fs);
        String line=null; 
        
	while ( (line = buff.readLine()) != null ){
            String[] data = line.split(" ");
            for(int i=0;i<data.length;i++){
                if(TnNT.contains(data[i])){
                    input.add(data[i]);
                }else{
                    if(isNum(data[i])){
                        input.add("NUM");
                    }else{input.add("ID");}
                }
            }
        }
        System.out.println("Input: "+input.toString()+"\n");
        Table[0] = new int[]{   0,  0,  0,  0,  0,  6,  7,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  2,  3,  5,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[1] = new int[]{   0,  0,  0,  0,  0,  6,  7,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  8,  3,  5,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[2] = new int[]{   0,  0,  0,  0,  0, -2, -2,  0,  0,  0,  0,  0,  0,  0,  0,  0, -2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[3] = new int[]{   0,  0,  0,  0,  0, -3, -3,  0,  0,  0,  0,  0,  0,  0,  0,  0, -3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[4] = new int[]{   0,  0,  0,  0,  0, -4, -4,  0,  0,  0,  0,  0,  0,  0,  0,  0, -4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[5] = new int[]{   9,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[6] = new int[]{  -7,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[7] = new int[]{  -8,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[8] = new int[]{   0,  0,  0,  0,  0, -1, -1,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[9] = new int[]{   0, 10, 11,  0,  0,  0,  0, 12,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[10] = new int[]{ -5, -5,  0, -5,  0, -5, -5, -5,  0,  0, -5, -5, -5, -5, -5,  0, -5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[11] = new int[]{  0,  0,  0, 13,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[12] = new int[]{  0,  0,  0,  0,  0,  6, 16,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 18,  0, 14, 15, 17,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[13] = new int[]{  0,  0,  0,  0, 19,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[14] = new int[]{  0,  0,  0,  0,  0,  0,  0,  0, 20,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[15] = new int[]{  0,  0,  0,  0,  0,  0,  0,  0,-10, 21,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}; 
        Table[16] = new int[]{ -8,  0,  0,  0,  0,  0,  0,  0,-11,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}; 
        Table[17] = new int[]{  0,  0,  0,  0,  0,  0,  0,  0,-13,-13,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}; 
        Table[18] = new int[]{ 22,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}; 
        Table[19] = new int[]{  0, 23,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}; 
        Table[20] = new int[]{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 25,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 24,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[21] = new int[]{  0,  0,	0,  0,	0,  6,	7,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0, 18,	0,  0,	0, 26,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0};
       	Table[22] = new int[]{  0,  0, 27,  0,	0,  0,	0,  0,-14,-14,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0};
      	Table[23] = new int[]{ -6, -6,	0, -6,	0, -6, -6, -6,	0,  0, -6, -6, -6, -6, -6,  0, -6,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0};
      	Table[24] = new int[]{  0,  0,	0,  0,	0, -9, -9,  0,	0,  0,	0,  0,	0,  0,	0,  0, -9,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0};
       	Table[25] = new int[]{-18,-18,	0,-18,	0,-18,-18,-18,	0,  0,-18,-18,-18,-18,-18,  0,-18,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0, 28,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0};
      	Table[26] = new int[]{  0,  0,	0,  0,	0,  0,	0,  0,-12,-12,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,  0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
      	Table[27] = new int[]{0,	0,	0,	0,	29,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
       	Table[28] = new int[]{-20,	-20,	0,	-20,	0,	6,	7,	-20,	0,	0,	-20,	-20,	-20,	0,	-20,	0,	0,	0,	0,	0,	31,	32,	0,	0,	0,	0,	0,	0,	30,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[29] = new int[]{0,	0,	0,	0,	0,	0,	0,	0,	-15,	-15,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
       	Table[30] = new int[]{45,	40,	0,	48,	0,	0,	0,	46,	0,	0,	25,	33,	41,	0,	42,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	36,	0,	0,	34,	35,	37,	38,	39,	43,	44,	47,	0,	0};
        Table[31] = new int[]{-17,	-17,	0,	-17,	0,	-17,	-17,	-17,	0,	0,	-17,	-17,	-17,	-17,	-17,	0,	-17,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[32] = new int[]{49,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[33] = new int[]{-16,	-16,	0,	-16,	0,	-16,	-16,	-16,	0,	0,	-16,	-16,	-16,	-16,	-16,	0,	-16,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[34] = new int[]{-19,	-19,	0,	-19,	0,	0,	0,	-19,	0,	0,	-19,	-19,	-19,	0,	-19,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[35] = new int[]{-21,	-21,	0,	-21,	0,	0,	0,	-21,	0,	0,	-21,	-21,	-21,	-21,	-21,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[36] = new int[]{-22,    -22,  0,  -22,  0,  0,  0,  -22,  0,  0,  -22, -22,  -22,  -22,  -22,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  24, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[37] = new int[]{-23,    -23,  0,  -23,  0,  0,  0,  -23,  0,  0,  -23, -23,  -23,  -23,  -23,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  24, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[38] = new int[]{-24,    -24,  0,  -24,  0,  0,  0,  -24,  0,  0,  -24, -24,  -24,  -24,  -24,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  24, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[39] = new int[]{0,    50,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  24, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[40] = new int[]{-26,    -26,  0,  -26,  0,  0,  0,  -26,  0,  0,  -26, -26,  -26, -26,  -26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  24, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
        Table[41] = new int[]{0,0,  0, 0, 0, 0,	0, 51,0, 0,  0,	0, 0, 0, 0, 0, 0, 0, 0,	0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0, 0,	0, 0, 0, 0, 0, 0, 0};
        Table[42] = new int[]{	45,	52,	0,	48,	0,	0,	0,	46,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	53,	43,	44,	47,	0,	0};
        Table[43] = new int[]{	0,	-36,	0,	0,	-36,	0,	0,	0,	-36,	-36,	0,	0,	0,	0,	0,	54,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[44] = new int[]{	0,	-32,	0,	0,	-32,	0,	0,	0,	-32,	-32,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[45] = new int[]{	0,	-33,	55,	0,	-33,	0,	0,	56,	-33,	-33,	0,	0,	0,	0,	0,	-33,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[46] = new int[]{	45,	0,	0,	48,	0,	0,	0,	46,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	57,	43,	44,	47,	0,	0};
        Table[47] = new int[]{	0,	-37,	0,	0,	-37,	0,	0,	0,	-37,	-37,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[48] = new int[]{	0,	-38,	0,	0,	-38,	0,	0,	0,	-38,	-38,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[49] = new int[]{	0,	10,	11,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[50] = new int[]{	-25,	-25,	0,	-25,	0,	0,	0,	-25,	0,	0,	-25,	-25,	-25,	-25,	-25,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[51] = new int[]{	45,	0,	0,	48,	0,	0,	0,	46,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	58,	43,	44,	47,	0,	0};
        Table[52] = new int[]{	-29,	-29,	0,	-29,	0,	0,	0,	-29,	0,	0,	-29,	-29,	-29,	-29,	-29,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[53] = new int[]{	0,	59,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[54] = new int[]{	45,	0,	0,	48,	0,	0,	0,	46,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	60,	43,	44,	47,	0,	0};
        Table[55] = new int[]{	45,	0,	0,	48,	0,	0,	0,	46,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	61,	43,	44,	47,	0,	0};
        Table[56] = new int[]{45,	0,	0,	48,	0,	0,	0,	46,	-41,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	64,	43,	44,	47,	62,	63};
        Table[57] = new int[]{0,	0,	0,	0,	0,	0,	0,	0,	65,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[58] = new int[]{0,	0,	0,	0,	0,	0,	0,	0,	66,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[59] = new int[]{-30,	-30,	0,	-30,	0,	0,	0,	-30,	0,	0,	-30,	-30,	-30,	-30,	-30,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[60] = new int[]{0,	-31,	0,	0,	-31,	0,	0,	0,	-31,	-31,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[61] = new int[]{0,	0,	0,	0,	67,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[62] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 68, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Table[63] = new int[]{0,	0,	0,	0,	0,	0,	0,	0,	-40,	69,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[64] = new int[]{0,	0,	0,	0,	0,	0,	0,	0,	-43,	-43,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[65] = new int[]{0,	-35,	0,	0,	-35,	0,	0,	0,	-35,	-35,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[66] = new int[]{45,	40,	0,	48,	0,	0,	0,	46,	0,	0,	25,	0,	41,	0,	42,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	36,	0,	0,	70,	35,	37,	38,	39,	43,	44,	47,	0,	0};
        Table[67] = new int[]{0,	-34,	0,	0,	-34,	0,	0,	0,	-34,	-34,	0,	0,	0,	0,	0,	-34,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[68] = new int[]{0,	-39,	0,	0,	-39,	0,	0,	0,	-39,	-39,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[69] = new int[]{45,	0,	0,	48,	0,	0,	0,	46,	-41,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	71,	43,	44,	47,	0,	0};
        Table[70] = new int[]{-27,	-27,	0,	-27,	0,	0,	0,	-27,	0,	0,	-27,	-27,	-27,	-27,	-27,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[71] = new int[]{0,	0,	0,	0,	0,	0,	0,	0,	-42,	-42,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};
        Table[72] = new int[]{45,	40,	0,	48,	0,	0,	0,	46,	0,	0,	25,	0,	41,	0,	42,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	36,	0,	0,	73,	35,	37,	38,	39,	43,	44,	47,	0,	0};
        Table[73] = new int[]{-28,	-28,	0,	-28,	0,	0,	0,	-28,	0,	0,	-28,	-28,	-28,	-28,	-28,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0};

        input.add("$");
        String cur;
        stack.push(0);
        System.out.println("Stack = "+stack+"\n");///////////////////////////////////////////////////////////////
        //Table[70][13] = 72;//choose shift 
        while(!stack.isEmpty()||!input.isEmpty()){
            cur = input.get(0);
            int SoR = Table[(Integer)stack.peek()][findIndex(cur)];
            System.out.println("Seek = "+cur+" , Input = "+input);//////////////////////////////////////////
            if(SoR==1&&cur.equals("$")){
                reduce(-1);
                System.out.println("\n"+"Accept!!");
                input.remove(0);
                break;
            }else{
                    if(SoR>0){
                System.out.println("Action: Shift "+Table[(Integer)stack.peek()][findIndex(cur)]);///////
                    shift(SoR,cur);
                    input.remove(0);
                }else if(SoR<0){
                    int use = SoR*(-1);
                    System.out.print("Action: Reduce "+use);////////////////////////////////////////
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
        if(use==-1){ 
            R = (ArrayList)right.get(0).clone();
            System.out.print(" ( "+TnNT.get(left.get(0))+" ->");///////////////////////////////////////////////////
        }else {
            R = (ArrayList)right.get(use).clone();
            System.out.print(" ( "+TnNT.get(left.get(use))+" ->");///////////////////////////////////////////////////
        }
        for(int i=0;i<R.size();i++){
            System.out.print(" "+TnNT.get(R.get(i)));
        }System.out.print(" ) ");
        while(!R.isEmpty()){ 
            stack.pop();
            stack.pop();
            R.remove(0);
        }
        if(use!=-1){
            int state = Table[(Integer)stack.peek()][left.get(use)];
            stack.add(TnNT.get(left.get(use)));
            stack.add(state);
            System.out.println("to "+state);///////////////////////////////////////////////////////////////////////
        }
    }
    
    public static boolean isNum(String str){
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
}
