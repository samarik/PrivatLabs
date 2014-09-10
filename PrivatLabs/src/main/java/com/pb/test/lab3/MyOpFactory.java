package com.pb.test.lab3;

import com.pb.test.math.OperationNotFoundException;

class MyOpFactory implements OperationFactory {

    @Override
    public Operation getOpInstance(String op) throws OperationNotFoundException{
        switch (op) {
            case "+":
                return new OpPlus();
            case "-":
                return new OpMinus();
            case "*":
                return new OpMul();
            case "/":
                return new OpDiv();
            default:
                //return null;
                throw new OperationNotFoundException("Код операции задан не верно !!!  " + op);
        }
    }
}
