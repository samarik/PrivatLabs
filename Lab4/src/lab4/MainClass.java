package lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Olga
 */
public class MainClass {

    public static final Logger LOG = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        LOG.info("Start processing");
        if (args.length < 1) {
            LOG.error("Файл не задан !!! ");
            return;
        }
        String fileName = args[0];
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String string;
            while ((string = in.readLine()) != null) {
                try {
                    saveResult(invokeService(string));
                } catch (ClassNotFoundException e) {
                    LOG.error("Class not found", e);
                }
                catch (NoSuchMethodException e) {
                    LOG.error("Method not found", e); 
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error("Файл не найден !!! " + fileName);
        } catch (Exception e) {
            LOG.error("Ошибка", e);
        }
        LOG.info("Done");
    }

    public static String invokeService(String strFromFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        String[] str = strFromFile.split(" ");
        String className = str[0];
        String methodName = str[1];
        int countOfArguments = str.length - 2;
        String[] args = new String[countOfArguments];
        Class<String>[] argsClasses = new Class[countOfArguments];
        for (int i = 0; i < argsClasses.length; i++) {
            argsClasses[i] = String.class;
        }
        System.arraycopy(str, 2, args, 0, args.length);
        Class c = Class.forName(className);
        Object obj = c.newInstance();
        Method myMethod = c.getMethod(methodName, argsClasses);
        String result = (String) myMethod.invoke(obj, args);
        return result;
    }

    public static void saveResult(String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("outfile.txt", true))) {
            writer.write(result);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            LOG.error("Ошибка при записи результата запуска в исходящий файл !!!");
        }
    }
}
