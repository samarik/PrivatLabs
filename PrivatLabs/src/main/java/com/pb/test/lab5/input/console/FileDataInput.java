/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab5.input.console;

import com.pb.test.lab5.input.DataInput;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Olga
 */
public class FileDataInput implements DataInput {

    private BufferedReader in;

    public FileDataInput(String fileName) throws FileNotFoundException {
            in = new BufferedReader(new FileReader(fileName));
        
    }

    @Override
    public Double getDouble() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getString() {

        try {
            return in.readLine();
        } catch (IOException ex) {
            System.out.println("Ошибка чтения из файла !!!");
        }
        return null;
    }
}
