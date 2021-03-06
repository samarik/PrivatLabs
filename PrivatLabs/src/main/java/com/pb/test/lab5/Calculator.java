package com.pb.test.lab5;

import com.pb.test.lab5.input.DataInput;
import com.pb.test.lab5.input.console.ConsoleDataInput;
import com.pb.test.lab5.input.file.FileDataInput;
import com.pb.test.lab5.operations.Operation;
import com.pb.test.lab5.opfactory.OperationFactory;
import com.pb.test.lab5.output.DataOutput;
import com.pb.test.lab5.output.file.FileDataOutput;
import com.pb.test.math.OperationNotFoundException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {

    private OperationFactory operFact;
    private DataInput dataInput;
    private DataOutput dataOutput = new FileDataOutput();

    Calculator(OperationFactory opFact, String fileName) throws FileNotFoundException {
        dataInput = new FileDataInput(fileName);
        operFact = opFact;
    }

    Calculator(OperationFactory opFact) {
        dataInput = new ConsoleDataInput();
        operFact = opFact;
    }

    void exec() {
        double a, b;
        String op;
        String str;
        while ((str = dataInput.getString()) != null) {
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
            if (strTokeniser.hasMoreTokens()) {
                op = strTokeniser.nextToken();
            } else {
                System.out.println("Операция не задана !!! ");
                continue;
            }
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
                System.out.println("Ошибка !!! (Параметров больше трех)"); // параметров больше трех
                continue;
            }

            try {
                dataOutput.outputWithFormatting(a, b, op, operation.doOperation(a, b));
            } catch (IOException ex) {
                System.out.println("Ошибка при чтении формата из файла formatRus.xml");
            }

        }

    }
}
