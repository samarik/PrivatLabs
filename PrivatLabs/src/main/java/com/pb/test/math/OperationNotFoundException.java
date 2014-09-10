package com.pb.test.math;

public class OperationNotFoundException extends Exception {

    public OperationNotFoundException(String op) {
        super(op);
    }
}
