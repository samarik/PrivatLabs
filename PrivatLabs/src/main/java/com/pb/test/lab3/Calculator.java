package com.pb.test.lab3;

import com.pb.test.lab3.opfactory.myopfacrory.MyOpFactory;
import com.pb.test.lab3.operations.Operation;
import com.pb.test.lab3.input.DataInput;
import com.pb.test.lab3.input.console.ConsoleDataInput;
import com.pb.test.lab3.output.DataOutput;
import com.pb.test.lab3.output.console.ConsoleDataOutput;
import com.pb.test.math.OperationNotFoundException;

public class Calculator {

    private MyOpFactory operFact;
    private DataInput dataInput = new ConsoleDataInput();
    private DataOutput dataOutput = new ConsoleDataOutput();

    Calculator(MyOpFactory opFact) {
        operFact = opFact;
    }

    void exec() {
        while (true) {
            double a, b;
            String op;
            System.out.print("Введите первый аргумент: ");
            try {
                a = dataInput.getDouble();
            } catch (NumberFormatException e) {
                if (e.getMessage().equalsIgnoreCase("Empty string")) {
                    System.out.println("Выход !!!");
                    break;
                } else {
                    System.out.println("Аргумент задан неверно !!!");
                    continue;
                }
            }
            System.out.print("Введите операцию(+,-,*,/): ");
            op = dataInput.getString();
            Operation operation;
            try {
                operation = operFact.getOpInstance(op);
            } catch (OperationNotFoundException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.print("Введите второй аргумент: ");
            try {
                b = dataInput.getDouble();
            } catch (NumberFormatException e) {
                System.out.println("Аргумент задан неверно !!!");
                continue;
            }
            dataOutput.Output(a + op + b + "=" + operation.doOperation(a, b));

        }

    }
}
