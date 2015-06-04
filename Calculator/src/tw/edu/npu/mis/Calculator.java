/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

import java.util.Observable;
import java.util.Observer;


/**
 * The model class of the calculator application.
 */
public class Calculator extends Observable {

    String Num = "";
    String HoldNum = "";
    Boolean isDot = false;
    String Operation = "";

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
        Num += digit;
        setChanged();
        notifyObservers();
//        System.out.println(Num);
    }
    
    public void appendDot() {
        // TODO code application logic here
        if (!isDot) Num += ".";
        isDot = true;
        setChanged();
        notifyObservers();
//        System.out.println(Num);
    }
    
    public void performOperation(Operator operator) {
        // TODO code application logic here
        switch(operator){
            case CLEAR : 
                    Num = "";
                    HoldNum = "";
                    setChanged();
                    notifyObservers();
                    break;
                
            case CLEAR_ENTRY : 
                if(Num.length() != 0) Num = "";
                    setChanged();
                    notifyObservers();
                    break;

            case PLUS :
//                    System.out.println("PLUS");
                    if(Num.length() != 0)HoldNum = Num;
                    Num = "";
                    Operation = "+";
                    setChanged();
                    notifyObservers();
                    break;
                
            case MINUS : 
                    if(Num.length() != 0)HoldNum = Num;
                    Num = "";
                    Operation = "-";
                    
                    setChanged();
                    notifyObservers();
                    break;               

            case TIMES : 
                    if(Num.length() != 0)HoldNum = Num;
                    Num = "";
                    Operation = "*";
                    setChanged();
                    notifyObservers();
                    break;

            case OVER : 
                    if(Num.length() != 0)HoldNum = Num;
                    Num = "";
                    Operation = "/";
                    setChanged();
                    notifyObservers();
                    break;
                
            case BACKSPACE : 
                    if(Num.length() > 0)Num = Num.replace(Num, Num.substring(0, Num.length()-1).toString());
                    setChanged();
                    notifyObservers();
                    break; 
                
            case EQUAL : 
                    switch(Operation){
                        case "+" : 
                            if(Num.length() == 0) Num = "0.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) + Double.parseDouble(Num));
                            break;
                        case "-" : 
                            if(Num.length() == 0) Num = "0.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) - Double.parseDouble(Num));
                            break;
                        case "*" :
                            if(Num.length() == 0) Num = "0.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) * Double.parseDouble(Num));
                            break;
                        case "/" : 
                            if(Num.length() == 0) Num = "1.0";
                            Num = String.valueOf(Double.parseDouble(HoldNum) / Double.parseDouble(Num));
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
            }
    }
    
    public String getDisplay() {
        // TODO code application logic here
//        System.out.println("Display");
        return Num;
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new View().setVisible(true);
//        System.out.println("main");
    }

}
