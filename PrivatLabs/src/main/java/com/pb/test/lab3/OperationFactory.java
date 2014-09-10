package com.pb.test.lab3;

import com.pb.test.math.OperationNotFoundException;

/**
 *
 * @author Olga
 */
interface OperationFactory {

    Operation getOpInstance(String op) throws OperationNotFoundException;

}
