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
    Boolean isDot = false;

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
