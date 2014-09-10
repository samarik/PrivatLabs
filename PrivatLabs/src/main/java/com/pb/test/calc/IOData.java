package com.pb.test.calc;

import java.util.Scanner;

public class IOData {

    public String userInput(String outLine) {
        String inputLine;
        System.out.print(outLine);
        Scanner cs = new Scanner(System.in);
        inputLine = cs.nextLine();
        if (inputLine.length() == 0) {
            return null;
        }
        return inputLine;
    }
}
