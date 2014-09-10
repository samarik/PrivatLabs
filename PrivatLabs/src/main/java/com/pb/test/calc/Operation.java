package com.pb.test.calc;

public class Operation {

    public double oper(String arg1, String op, String arg2) {
        switch (op) {
            case "+":
                return Double.parseDouble(arg1) + Double.parseDouble(arg2);
            case "-":
                return Double.parseDouble(arg1) - Double.parseDouble(arg2);
            case "*":
                return Double.parseDouble(arg1) * Double.parseDouble(arg2);
            case "/":
                return Double.parseDouble(arg1) / Double.parseDouble(arg2);
            default:
                return 0;
        }
    }
}
