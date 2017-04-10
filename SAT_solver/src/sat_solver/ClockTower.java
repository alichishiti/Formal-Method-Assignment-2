/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sat_solver;



import static java.lang.Math.pow;
import java.util.*;  
/**
 *
 * @author gul
 */
public class ClockTower {
    
    char[] binaryOperators;
    String expression;
   ArrayList<Character> propositionList;
   HashMap<Character,Integer> propositionToValue;
    
    public ClockTower(String expression){
        this.propositionToValue= new HashMap<Character,Integer>();
        this.propositionList = new ArrayList<Character>();
        this.binaryOperators=new char[]{'&', '|', '>', '='};
        this.expression=expression;
    }
    
    public void extractPropositions(){
//    Character.isLetter(element)
        char element;
        boolean addProposition = true;
        for(int i = 0 ; i < this.expression.length();i++){
            element = this.expression.charAt(i);
            
            if(Character.isLetter(element)){
                addProposition = true;
                
                for (int j = 0; j < this.propositionList.size(); j++){
                    if(this.propositionList.get(j) == element){
                        addProposition = false;
                        break;
                    }        
                }

                if(addProposition){
                    this.propositionList.add(element);
                    addProposition = true;
                }
            }
        }
        
        System.out.println(propositionList);
    }
    
    public void MapPropositionValues(){
        
        
        for(int i = 0 ; i< pow(2,propositionList.size());i++){
            String binary= Integer.toBinaryString(i);
            binary= append(binary,propositionList.size());
            for(int j =0 ; j< binary.length(); j++){
                propositionToValue.put(propositionList.get(j), 
                        Integer.parseInt(binary.substring(j,j+1)));
                }
            System.out.println(propositionToValue);
        }
        
        
    }
    
    public void Calculate(){
       
    
    }
    
    private String append(String binary, int length){
    
        while(binary.length()!=length){
        
            binary="0"+binary;
        }
        return binary;
    }
    
    
    
    
}
