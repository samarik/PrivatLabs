/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab3;

/**
 *
 * @author Olga
 */
public class MyCalc {

    public static void main(String[] args) {
        MyOpFactory opFact = new MyOpFactory();
        Calculator calc = new Calculator(opFact);
        calc.exec();
    }
}
