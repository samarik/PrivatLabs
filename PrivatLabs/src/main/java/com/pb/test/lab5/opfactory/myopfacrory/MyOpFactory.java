package com.pb.test.lab5.opfactory.myopfacrory;

import com.pb.test.lab5.opfactory.OperationFactory;
import com.pb.test.lab5.operations.Operation;
import com.pb.test.lab5.operations.myoperation.OpDiv;
import com.pb.test.lab5.operations.myoperation.OpMinus;
import com.pb.test.lab5.operations.myoperation.OpMul;
import com.pb.test.lab5.operations.myoperation.OpPlus;
import com.pb.test.math.OperationNotFoundException;

public class MyOpFactory implements OperationFactory {

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
                throw new OperationNotFoundException("Код операции задан неверно !!!  " + op);
        }
    }
}
