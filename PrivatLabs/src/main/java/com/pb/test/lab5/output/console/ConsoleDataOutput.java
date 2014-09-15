package com.pb.test.lab5.output.console;

import com.pb.test.lab5.output.DataOutput;

public class ConsoleDataOutput implements DataOutput {

    @Override
    public void Output(String outLine) {
        System.out.println(outLine);
    }
    
}
