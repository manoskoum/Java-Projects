package com.manos.calculator;

import java.util.Scanner;


public class Calc {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter 2 numbers");
        int num1=input.nextInt();
        int num2=input.nextInt();
        System.out.println("Choose an operation(+,-,*,/,%) : ");
        char operator=input.next().charAt(0);
        double result=0.0;
        
        switch(operator){
            case '+':
                result=num1+num2;
                break;
                
            case '-':
                result=num1-num2;
                break;
                
            case '*':
                result=num1*num2;
                break;
            
            case '/':
                result=num1/num2;
                break;
            
            case '%':
                result=num1%num2;
                break; 
              
            default:
               System.out.println("Invalid choice operator");
               break;
        }
        System.out.println(num1+" "+operator+" "+num2+" = "+ result);
        input.close();
                
                
        }
        
        
        
        
    }

