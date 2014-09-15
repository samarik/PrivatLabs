/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab5;

import com.pb.test.lab5.opfactory.myopfacrory.MyOpFactory;
import com.pb.test.lab5.opfactory.myopfacrory.MyOpFactoryHashMap;

/**
 *
 * @author Olga
 */
public class MyCalc {

    public static void main(String[] args) {
        MyOpFactoryHashMap opFact = new MyOpFactoryHashMap();
        Calculator calc = new Calculator(opFact);
        calc.exec();
    }
}
