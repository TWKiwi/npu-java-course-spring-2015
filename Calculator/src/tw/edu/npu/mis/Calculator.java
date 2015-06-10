/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * The model class of the calculator application.
 */
public class Calculator extends Observable {

    String Num = "0";
    String HoldNum = "";
    Boolean isDot = false;
    String Operation = "";
    List<Double> Memory_Number = new ArrayList<Double>();

    /**
     * The available operators of the calculator.
     */
    public enum Operator {
        CLEAR,       // C
        CLEAR_ENTRY, // CE
        BACKSPACE,   // ⌫
        EQUAL,       // =
        PLUS,        // +
        MINUS,       // -
        TIMES,       // ×
        OVER,        // ⁄
        PLUS_MINUS,  // ±
        RECIPROCAL,  // 1/x
        PERCENT,     // %
        SQRT,        // √
        MEM_CLEAR,   // MC
        MEM_SET,     // MS
        MEM_PLUS,    // M+
        MEM_MINUS,   // M-
        MEM_RECALL   // MR
    }
    
    public void appendDigit(int digit) {
        // TODO code application logic here
        if(Num.equals("0")) Num = String.valueOf(digit);
        else{
            Num += digit;
        }
        setChanged();
        notifyObservers();
    }
    
    public void appendDot() {
        // TODO code application logic here
        if (!isDot) Num += ".";
        isDot = true;
//        try{
//            if (isInteger(Double.parseDouble(Num))) Num += ".";  
//        }catch(NumberFormatException e){
//            System.out.println("別腦殘沒小數狀態下狂點Dot好嗎？");
//        }
        setChanged();
        notifyObservers();
    }
    
    public void performOperation(Operator operator) {
        // TODO code application logic here
        switch(operator){
            case CLEAR : 
                    Num = "0";
                    HoldNum = "";
                    isDot = false;
                    setChanged();
                    notifyObservers();
                    break;
                
            case CLEAR_ENTRY : 
                    if(Num.length() != 0) Num = "0";
                    isDot = false;
                    setChanged();
                    notifyObservers();
                    break;

            case PLUS :
                    performOperation(Operator.EQUAL);
                    if(Num.length() != 0)HoldNum = Num;
                    Num = "";
                    Operation = "+";
                    isDot = false;
                    break;
                
            case MINUS : 
                    performOperation(Operator.EQUAL);
                    if(Num.length() != 0)HoldNum = Num;
                    Num = "";
                    Operation = "-";    
                    isDot = false;
                    break;               

            case TIMES : 
                    performOperation(Operator.EQUAL);
                    if(Num.length() != 0)HoldNum = Num;
                    Num = "";
                    Operation = "*";
                    isDot = false;
                    break;

            case OVER : 
                    performOperation(Operator.EQUAL);
                    if(Num.length() != 0)HoldNum = Num;                    
                    Num = "";
                    Operation = "/";
                    isDot = false;
                    break;
                
            case BACKSPACE : 
                    if(Num.length() > 0)Num = Num.replace(Num, Num.substring(0, Num.length()-1));
                    if(Num.length() == 0) Num = "0";
                    setChanged();
                    notifyObservers();
                    break; 
                
            case EQUAL : 
                    switch(Operation){
                        case "+" : 
                            if(Num.length() == 0) Num = "0.0";
                            if(HoldNum.length() == 0) HoldNum = "0.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) + Double.parseDouble(Num));
                            if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                            break;
                        case "-" : 
                            if(Num.length() == 0) Num = "0.0";
                            if(HoldNum.length() == 0) HoldNum = "0.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) - Double.parseDouble(Num));
                            if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                            break;
                        case "*" :
                            if(Num.length() == 0) Num = "0.0";
                            if(HoldNum.length() == 0) HoldNum = "0.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) * Double.parseDouble(Num));
                            if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                            break;
                        case "/" : 
                            if(Num.length() == 0) Num = "1.0";
                            if(HoldNum.length() == 0) HoldNum = "0.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) / Double.parseDouble(Num));
                            if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                            break;
                    }
                    setChanged();
                    notifyObservers();
                    break; 
                
            case PLUS_MINUS : 
                    if(Num.length() != 0 && Double.parseDouble(Num) > 0)Num = "-" + Num;
                    else if(Num.length() != 0 && Double.parseDouble(Num) < 0)Num = Num.substring(1, Num.length());
                    setChanged();
                    notifyObservers();
                    break;  
                
            case RECIPROCAL :
                    if(Num.length() == 0) Num = "0.0";
                    Num = String.valueOf(1.0 / Double.parseDouble(Num));
                    if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                    setChanged();
                    notifyObservers();
                    break;
                
            case PERCENT :
                    if(Num.length() == 0) Num = "0.0";
                    Num = String.valueOf(Double.parseDouble(Num) / 100);
                    if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                    setChanged();
                    notifyObservers();
                    break;
                
            case SQRT :
                    if(Num.length() == 0) Num = "0.0";
                    Num = String.valueOf(Math.sqrt(Double.parseDouble(Num)));
                    if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_PLUS :
                    performOperation(Operator.EQUAL);
                    if(Num.length() == 0) return;
                    Memory_Number.add(Math.abs(Double.parseDouble(Num)));
                    Num = "";
                    Operation = "";
                    isDot = false;
                    for (Double d : Memory_Number){
                        System.out.println(d);
                    }
                    break;
                
            case MEM_MINUS :
                    performOperation(Operator.EQUAL);
                    if(Num.length() == 0) return;
                    Memory_Number.add(Math.abs(Double.parseDouble(Num)) * -1);
                    Num = "";
                    Operation = "";
                    isDot = false;
                    for (Double d : Memory_Number){
                        System.out.println(d);
                    }
                    break;
                                
            case MEM_RECALL :
                    if(Num.length() == 0) Num = "0.0";
                    for (Double d : Memory_Number){
                        Num = String.valueOf(Double.parseDouble(Num) + d); 
                    }
                    if(isInteger(Double.parseDouble(Num))) Num = String.valueOf((int)Double.parseDouble(Num));
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_CLEAR :
                    Memory_Number.clear();
                    Num = "";
                    isDot = false;
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_SET :
                    performOperation(Operator.CLEAR);
                    if(Num.length() != 0 && Double.parseDouble(Num) > 0)performOperation(Operator.MEM_PLUS);
                    else if(Num.length() != 0 && Double.parseDouble(Num) < 0)performOperation(Operator.MEM_MINUS);
                    setChanged();
                    notifyObservers();
                    break;
            }
    }
    
    public String getDisplay() {
        // TODO code application logic here
        return Num;
    }
    
    public static boolean isInteger(double d) {
        return d % 1.0 == 0;
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new View().setVisible(true);
    }

}
