package com.pb.test.lab5.opfactory.myopfacrory;

import com.pb.test.lab5.operations.Operation;
import com.pb.test.lab5.operations.myoperation.OpDiv;
import com.pb.test.lab5.operations.myoperation.OpMinus;
import com.pb.test.lab5.operations.myoperation.OpMul;
import com.pb.test.lab5.operations.myoperation.OpPlus;
import com.pb.test.lab5.opfactory.OperationFactory;
import com.pb.test.math.OperationNotFoundException;
import java.util.HashMap;

/**
 *
 * @author Olga
 */
public class MyOpFactoryHashMap implements OperationFactory {

    HashMap<String, Operation> map = new HashMap<String, Operation>();

    @Override
    public Operation getOpInstance(String op) throws OperationNotFoundException {
        if (map.containsKey(op)) {
            return map.get(op);
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
        map.put(op, myOperation);
        return myOperation;
    }
}
