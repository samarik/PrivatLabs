/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab3;

import com.pb.test.math.OperationNotFoundException;

/**
 *
 * @author Olga
 */
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
                throw new OperationNotFoundException(op);
        }
    }
}
