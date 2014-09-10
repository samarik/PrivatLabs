/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab3;

import java.util.Scanner;

/**
 *
 * @author Olga
 */
public class ConsoleDataInput implements DataInput {

    /**
     *
     * @return @throws NotNumberException
     */
    @Override
    public Double getDouble() {
        String inputLine;
        Scanner cs = new Scanner(System.in);
        inputLine = cs.nextLine();
        return Double.parseDouble(inputLine);
    }

    @Override
    public String getString() {
        Scanner cs = new Scanner(System.in);
        return cs.nextLine();
    }

}
