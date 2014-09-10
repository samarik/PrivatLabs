package com.pb.test.lab3.input.console;

import com.pb.test.lab3.input.DataInput;
import java.util.Scanner;

public class ConsoleDataInput implements DataInput {

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
