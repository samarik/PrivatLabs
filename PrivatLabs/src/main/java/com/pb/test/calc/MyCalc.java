package com.pb.test.calc;

public class MyCalc {

    public static void main(String[] args) {
        IOData iodt = new IOData();
        while (true) {
            String a, b;
            String op;
            a = iodt.userInput("Введите первый аргумент: ");
            if (a == null) {
                System.out.println("Выход ... ");
                break;
            }
            op = iodt.userInput("Введите операцию(+,-,*,/): ");
            if (!"+-*/".contains(op)) {
                System.out.println("Не верно задана операция !!!");
                continue;
            }
            b = iodt.userInput("Введите второй аргумент: ");
            Operation operation = new Operation();
            System.out.println(a + op + b + " = " + operation.oper(a, op, b));

        }
    }

}
