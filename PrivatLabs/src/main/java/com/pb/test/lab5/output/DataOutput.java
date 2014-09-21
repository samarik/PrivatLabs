package com.pb.test.lab5.output;

import java.io.IOException;

public interface DataOutput {

    void output(String outLine);
    
    void outputWithFormatting(double firstArg, double secondArg, String operation, double result) throws IOException;
    
}
