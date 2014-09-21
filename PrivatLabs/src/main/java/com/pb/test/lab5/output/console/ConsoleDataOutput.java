package com.pb.test.lab5.output.console;

import com.pb.test.lab5.output.DataOutput;

public class ConsoleDataOutput implements DataOutput {

    @Override
    public void output(String outLine) {
        System.out.println(outLine);
    }

    @Override
    public void outputWithFormatting(double firstArg, double secondArg, String operation, double result) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
