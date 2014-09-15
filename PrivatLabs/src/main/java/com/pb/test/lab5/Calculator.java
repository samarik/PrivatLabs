package com.pb.test.lab5;

import com.pb.test.lab5.input.DataInput;
import com.pb.test.lab5.input.console.ConsoleDataInput;
import com.pb.test.lab5.operations.Operation;
import com.pb.test.lab5.opfactory.OperationFactory;
import com.pb.test.lab5.output.DataOutput;
import com.pb.test.lab5.output.console.ConsoleDataOutput;
import com.pb.test.math.OperationNotFoundException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Calculator {

    private OperationFactory operFact;
    private DataInput dataInput = new ConsoleDataInput();
    private DataOutput dataOutput = new ConsoleDataOutput();

    Calculator(OperationFactory opFact) {
        operFact = opFact;
    }

    void exec() {
        while (true) {
            double a, b;
            String op;
            System.out.print("Введите - Аргумент1 Операция Аргумент2: ");
            String str = dataInput.getString();
            if ((str.length() == 0) || str.equals(" ")) {
                System.out.println("Выход !!!");
                break;
            }
            StringTokenizer strTokeniser = new StringTokenizer(str, " ", false);
            try {
                a = Double.parseDouble(strTokeniser.nextToken());
            } catch (NumberFormatException e) {
                System.out.println("Аргумент1 задан неверно !!!");
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("Аргумент1 не задан !!!");
                continue;
            }
            op = strTokeniser.nextToken();
            Operation operation;
            try {
                operation = operFact.getOpInstance(op);
            } catch (OperationNotFoundException e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                b = Double.parseDouble(strTokeniser.nextToken());
            } catch (NumberFormatException e) {
                System.out.println("Аргумент2 задан неверно !!!");
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("Аргумент2 не задан !!!");
                continue;
            }
            if (strTokeniser.hasMoreElements() == (true)) {
                System.out.println("Ошибка при вводе  !!!"); // параметров больше трех
                continue;
            }
            dataOutput.Output(a + op + b + "=" + operation.doOperation(a, b));

        }

    }
}
