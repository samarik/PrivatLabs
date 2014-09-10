package com.pb.test.lab3;

import com.pb.test.math.OperationNotFoundException;

/**
 *
 * @author Olga
 */
public class Calculator {

    private MyOpFactory operFact;
    private DataInput dataInput;
    //private DataInput dataInput;

    Calculator(MyOpFactory opFact) {
        operFact = opFact;
    }

    Calculator() {

    }

    void exec() {
        ConsoleDataInput dataInput = new ConsoleDataInput();
        ConsoleDataOutput dataOutput = new ConsoleDataOutput();
        while (true) {
            double a, b;
            String op;
            System.out.print("Введите первый аргумент: ");
            try {
                a = dataInput.getDouble();
            } catch (Exception e) {
                System.out.println("Аргумент задан не верно !!!");
                break; // выход если первый аргумент задан не корректно или не задан (?)
            }
            System.out.print("Введите операцию(+,-,*,/): ");
            op = dataInput.getString();
            Operation operation;
            try {
                operation = operFact.getOpInstance(op);
            } catch (OperationNotFoundException e) {
                continue;
            }
            System.out.print("Введите второй аргумент: ");
            try {
                b = dataInput.getDouble();
            } catch (Exception e) {
                System.out.println("Аргумент задан не верно !!!");
                continue;
            }
            dataOutput.Output(a + op + b + "=" + operation.doOperation(a, b));

        }

    }
}
