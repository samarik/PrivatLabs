package com.pb.test.lab5;

//import com.pb.test.lab5.opfactory.myopfacrory.MyOpFactory;
import com.pb.test.lab5.opfactory.OperationFactory;
import com.pb.test.lab5.opfactory.holder.MyOpFactoryHashMap;
import java.io.FileNotFoundException;

/**
 *
 * @author Olga
 */
public class MyCalc {

    public static void main(String[] args) {
        OperationFactory opFact = new MyOpFactoryHashMap();
        Calculator calc = null;
        try {
            calc = new Calculator(opFact, "src/main/resources/infile.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found !!!");
            return;
        }
        calc.exec();
    }
}
