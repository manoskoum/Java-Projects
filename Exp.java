/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.manos.exp;


import java.util.Scanner;

/**
 *
 * @author user
 */
public class Exp {

    public static void main(String[] args) {
        
        
        Scanner input=new Scanner(System.in);
        System.out.println("Enter an integer base number :");
        int base=input.nextInt();
        System.out.println("Enter exponent :");
        int exp=input.nextInt();
        
       // for(int i=0;i<=exp;i++){
            System.out.println("Enter exponent :"+ pow(base,exp));
        //}
       // input.close();
        
   
    }
    
     public static int pow(int num,int power){
        return (int) Math.pow(num, power);
    }    
}
