package com.pb.test.lab5.operations.myoperation;

import com.pb.test.lab5.operations.Operation;

public class OpMinus implements Operation {

    @Override
    public double doOperation(double a, double b) {
        return a - b;
    }

}
