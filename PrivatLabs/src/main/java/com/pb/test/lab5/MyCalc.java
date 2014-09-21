/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab5;

//import com.pb.test.lab5.opfactory.myopfacrory.MyOpFactory;
import com.pb.test.lab5.opfactory.myopfacrory.MyOpFactoryHashMap;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class MyCalc {

    public static void main(String[] args) {
        MyOpFactoryHashMap opFact = new MyOpFactoryHashMap();
        Calculator calc=null;
        try {
            calc = new Calculator(opFact,"infile.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Входной файл не найден !!!");
            return;
        }
        calc.exec();
    }
}
