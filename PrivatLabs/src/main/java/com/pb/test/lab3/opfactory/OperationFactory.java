package com.pb.test.lab3.opfactory;

import com.pb.test.lab3.operations.Operation;
import com.pb.test.math.OperationNotFoundException;

/**
 *
 * @author Olga
 */
public interface OperationFactory {

    Operation getOpInstance(String op) throws OperationNotFoundException;

}
