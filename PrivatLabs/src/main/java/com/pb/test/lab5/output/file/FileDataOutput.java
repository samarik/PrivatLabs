/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab5.output.file;

import com.pb.test.lab5.output.DataOutput;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class FileDataOutput implements DataOutput {

    @Override
    public void output(String outLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("outfile.txt", true))) {
            writer.write(outLine);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при записи результата запуска в исходящий файл !!!");
        }
    }

    @Override
    public void outputWithFormatting(double firstArg, double secondArg, String operation, double result) {
        try {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("src/main/resources/formatRus.xml"));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("outfile.txt", true))) {
                String outline = MessageFormat.format(prop.getProperty("message"), prop.getProperty(operation), firstArg, secondArg, result);
                writer.write(outline);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                System.out.println("Ошибка при записи результата запуска в исходящий файл !!!");
            }
        } catch (IOException ex) {
            System.out.println("Ошибка при чтении файла!!!");
        }
    }
}
