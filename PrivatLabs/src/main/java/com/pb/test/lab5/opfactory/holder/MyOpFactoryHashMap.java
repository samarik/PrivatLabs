package com.pb.test.lab5.opfactory.holder;

import com.pb.test.lab5.operations.Operation;
import com.pb.test.lab5.operations.myoperation.OpDiv;
import com.pb.test.lab5.operations.myoperation.OpMinus;
import com.pb.test.lab5.operations.myoperation.OpMul;
import com.pb.test.lab5.operations.myoperation.OpPlus;
import com.pb.test.lab5.opfactory.OperationFactory;
import com.pb.test.math.OperationNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Olga
 */
public class MyOpFactoryHashMap implements OperationFactory {

    private final Map<String, Operation> operationHolder = new HashMap<>();

    @Override
    public Operation getOpInstance(String op) throws OperationNotFoundException {
        if (operationHolder.containsKey(op)) {
            return operationHolder.get(op);
        }
        Operation myOperation;
        switch (op) {
            case "+":
                myOperation = new OpPlus();
                break;
            case "-":
                myOperation = new OpMinus();
                break;
            case "*":
                myOperation = new OpMul();
                break;
            case "/":
                myOperation = new OpDiv();
                break;
            default:
                throw new OperationNotFoundException("Код операции задан неверно !!!  " + op);
        }
        operationHolder.put(op, myOperation);
        return myOperation;
    }
}
