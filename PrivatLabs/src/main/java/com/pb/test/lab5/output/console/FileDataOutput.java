/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab5.output.console;

import com.pb.test.lab5.output.DataOutput;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Olga
 */
public class FileDataOutput implements DataOutput {

    @Override
    public void Output(String outLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("outfile.txt", true))) {
            writer.write(outLine);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при записи результата запуска в исходящий файл !!!");
        }
    }
}
