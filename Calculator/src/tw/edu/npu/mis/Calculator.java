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

    String mNumber = "0";//存數字的地方...
    String mHoldNum = "";//存數字的地方...
    String mOperation = "";//計算用，判斷現在是要加減乘除哪一個
    List<Double> mMemory_Number = new ArrayList<Double>();//擺放M+M-的記憶數字

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
    /**
     * 這裏做數字的輸入，由View呼叫這方法，並給予參數，同時呼叫父類別去通知View實作Observer的update();
     * @param digit 輸入的數字
     */
    public void appendDigit(int digit) {
        // TODO code application logic here
        if(mNumber.equals("0")) mNumber = String.valueOf(digit);
        else{
            mNumber += digit;
        }
        setChanged();
        notifyObservers();
    }
    /**
     * 
     */
    public void appendDot() {
        // TODO code application logic here
        if (Double.parseDouble(mNumber) % 1 == 0) mNumber += ".";
 
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
                    mNumber = "0";
                    mHoldNum = "";
                    setChanged();
                    notifyObservers();
                    break;
                
            case CLEAR_ENTRY : 
                    if(mNumber.length() != 0) mNumber = "0";
                    mNumber = "0";
                    setChanged();
                    notifyObservers();
                    break;

            case PLUS :
                    performOperation(Operator.EQUAL);
                    if(mNumber.length() != 0)mHoldNum = mNumber;
                    mNumber = "";
                    mOperation = "+";
                    break;
                
            case MINUS : 
                    performOperation(Operator.EQUAL);
                    if(mNumber.length() != 0)mHoldNum = mNumber;
                    mNumber = "";
                    mOperation = "-";    
                    break;               

            case TIMES : 
                    performOperation(Operator.EQUAL);
                    if(mNumber.length() != 0)mHoldNum = mNumber;
                    mNumber = "";
                    mOperation = "*";

                    break;

            case OVER : 
                    performOperation(Operator.EQUAL);
                    if(mNumber.length() != 0)mHoldNum = mNumber;                    
                    mNumber = "";
                    mOperation = "/";
                    break;
                
            case BACKSPACE : 
                    if(mNumber.length() > 0)mNumber = mNumber.replace(mNumber, mNumber.substring(0, mNumber.length()-1));
                    if(mNumber.length() == 0) mNumber = "0";
                    setChanged();
                    notifyObservers();
                    break; 
                
            case EQUAL : 
                    switch(mOperation){
                        case "+" : 
                            if(mNumber.length() == 0) mNumber = "0.0";
                            if(mHoldNum.length() == 0) mHoldNum = "0.0";
                            mNumber = String.valueOf(Double.parseDouble(mHoldNum) + Double.parseDouble(mNumber));
                            if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                            break;
                        case "-" : 
                            if(mNumber.length() == 0) mNumber = "0.0";
                            if(mHoldNum.length() == 0) mHoldNum = "0.0";
                            mNumber = String.valueOf(Double.parseDouble(mHoldNum) - Double.parseDouble(mNumber));
                            if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                            break;
                        case "*" :
                            if(mNumber.length() == 0) mNumber = "0.0";
                            if(mHoldNum.length() == 0) mHoldNum = "0.0";
                            mNumber = String.valueOf(Double.parseDouble(mHoldNum) * Double.parseDouble(mNumber));
                            if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                            break;
                        case "/" : 
                            if(mNumber.length() == 0) mNumber = "1.0";
                            if(mHoldNum.length() == 0) mHoldNum = "0.0";
                            mNumber = String.valueOf(Double.parseDouble(mHoldNum) / Double.parseDouble(mNumber));
                            if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                            break;
                    }
                    setChanged();
                    notifyObservers();
                    break; 
                
            case PLUS_MINUS : 
                    if(mNumber.length() != 0 && Double.parseDouble(mNumber) > 0)mNumber = "-" + mNumber;
                    else if(mNumber.length() != 0 && Double.parseDouble(mNumber) < 0)mNumber = mNumber.substring(1, mNumber.length());
                    setChanged();
                    notifyObservers();
                    break;  
                
            case RECIPROCAL :
                    if(mNumber.length() == 0) mNumber = "0.0";
                    mNumber = String.valueOf(1.0 / Double.parseDouble(mNumber));
                    if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                    setChanged();
                    notifyObservers();
                    break;
                
            case PERCENT :
                    if(mNumber.length() == 0) mNumber = "0.0";
                    mNumber = String.valueOf(Double.parseDouble(mNumber) / 100);
                    if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                    setChanged();
                    notifyObservers();
                    break;
                
            case SQRT :
                    if(mNumber.length() == 0) mNumber = "0.0";
                    mNumber = String.valueOf(Math.sqrt(Double.parseDouble(mNumber)));
                    if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_PLUS :
                    performOperation(Operator.EQUAL);
                    if(mNumber.length() == 0) return;
                    mMemory_Number.add(Math.abs(Double.parseDouble(mNumber)));
                    mNumber = "";
                    mOperation = "";
                    for (Double d : mMemory_Number){
                        System.out.println(d);
                    }
                    break;
                
            case MEM_MINUS :
                    performOperation(Operator.EQUAL);
                    if(mNumber.length() == 0) return;
                    mMemory_Number.add(Math.abs(Double.parseDouble(mNumber)) * -1);
                    mNumber = "";
                    mOperation = "";
                    for (Double d : mMemory_Number){
                        System.out.println(d);
                    }
                    break;
                                
            case MEM_RECALL :
                    if(mNumber.length() == 0) mNumber = "0.0";
                    for (Double d : mMemory_Number){
                        mNumber = String.valueOf(Double.parseDouble(mNumber) + d); 
                    }
                    if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_CLEAR :
                    mMemory_Number.clear();
                    mNumber = "0";
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_SET :
                    mMemory_Number.clear();
                    if(mNumber.length() != 0 && Double.parseDouble(mNumber) > 0)performOperation(Operator.MEM_PLUS);
                    else if(mNumber.length() != 0 && Double.parseDouble(mNumber) < 0)performOperation(Operator.MEM_MINUS);
                    break;
            }
    }
    
    public String getDisplay() {
        // TODO code application logic here
        return mNumber;
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
