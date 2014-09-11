package com.pb.test.lab3.operations.myoperation;

import com.pb.test.lab3.operations.Operation;

/**
 *
 * @author Olga
 */
public class OpPlus implements Operation {

    @Override
    public double doOperation(double a, double b) {
        return a + b;
    }

}
