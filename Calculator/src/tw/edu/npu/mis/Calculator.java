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

    String mNumber = "0";//當前的運算結果
    String mHoldNum = "";//存數字的地方
    String mOperation = "";//計算用，判斷現在是要加減乘除哪一個
    List<Double> mMemoryNumber = new ArrayList<Double>();//擺放M+M-的記憶數字

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
     * 這裏做數字的輸入，由View呼叫這方法，並給予參數，然後呼叫父類別方法去通知View所實作的Observer的update();
     * @param digit 輸入的數字
     */
    public void appendDigit(int digit) {
        if(mNumber.equals("0")) mNumber = String.valueOf(digit);
        else{
            mNumber += digit;
        }
        setChanged();
        notifyObservers();
    }
    /**
     * 非小數就允許加點點，然後呼叫父類別方法去通知View所實作Observer的update();
     */
    public void appendDot() {
        if (Double.parseDouble(mNumber) % 1 == 0) mNumber += "."; 
        setChanged();
        notifyObservers();
    }
    /**
     * 所有運算方法加減乘除brabrabrabra...
     * @param operator 列舉的內容值，判斷當下要做什麼事
     */
    public void performOperation(Operator operator) {
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
                            mHoldNum = "0";
                            break;
                        case "-" : 
                            if(mNumber.length() == 0) mNumber = "0.0";
                            if(mHoldNum.length() == 0) mHoldNum = "0.0";
                            mNumber = String.valueOf(Double.parseDouble(mHoldNum) - Double.parseDouble(mNumber));
                            if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                            mHoldNum = "0";
                            break;
                        case "*" :
                            if(mNumber.length() == 0) mNumber = "0.0";
                            if(mHoldNum.length() == 0) mHoldNum = "0.0";
                            mNumber = String.valueOf(Double.parseDouble(mHoldNum) * Double.parseDouble(mNumber));
                            if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                            mHoldNum = "0";
                            break;
                        case "/" : 
                            if(mNumber.length() == 0) mNumber = "1.0";
                            if(mHoldNum.length() == 0) mHoldNum = "0.0";
                            mNumber = String.valueOf(Double.parseDouble(mHoldNum) / Double.parseDouble(mNumber));
                            if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                            mHoldNum = "0";
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
                    mMemoryNumber.add(Math.abs(Double.parseDouble(mNumber)));
                    mNumber = "";
                    mOperation = "";
                    for (Double d : mMemoryNumber){
                        System.out.println(d);
                    }
                    break;
                
            case MEM_MINUS :
                    performOperation(Operator.EQUAL);
                    if(mNumber.length() == 0) return;
                    mMemoryNumber.add(Math.abs(Double.parseDouble(mNumber)) * -1);
                    mNumber = "";
                    mOperation = "";
                    for (Double d : mMemoryNumber){
                        System.out.println(d);
                    }
                    break;
                                
            case MEM_RECALL :
                    if(mNumber.length() == 0) mNumber = "0.0";
                    for (Double d : mMemoryNumber){
                        mNumber = String.valueOf(Double.parseDouble(mNumber) + d); 
                    }
                    if(isInteger(Double.parseDouble(mNumber))) mNumber = String.valueOf((int)Double.parseDouble(mNumber));
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_CLEAR :
                    mMemoryNumber.clear();
                    mNumber = "0";
                    setChanged();
                    notifyObservers();
                    break;
                
            case MEM_SET :
                    mMemoryNumber.clear();
                    if(mNumber.length() != 0 && Double.parseDouble(mNumber) > 0)performOperation(Operator.MEM_PLUS);
                    else if(mNumber.length() != 0 && Double.parseDouble(mNumber) < 0)performOperation(Operator.MEM_MINUS);
                    break;
            }
    }
    /**
     * 回傳當前的運算結果
     * @return 運算結果
     */
    public String getDisplay() {
        return mNumber;
    }
    /**
     * 回傳是否為整數或小數
     * @param d 輸入要檢測得值
     * @return 回傳布林值
     */
    public static boolean isInteger(double d) {
        return d % 1.0 == 0;
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new View().setVisible(true);
    }

}
