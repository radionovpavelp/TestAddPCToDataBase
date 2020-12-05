package TestComputerAdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            //set path to the file with settings
            fileInputStream = new FileInputStream("src\\main\\resources\\conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //possible exception (no file, etc.)
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * method for returning a string with values from a file with settings
     */
    public static String getProperty(String key) {
        if (PROPERTIES.getProperty(key) == null) {
            switch (key) {
                case ("namePC"):
                    return "Laptop radionov";
                case ("introducedDate"):
                    return LocalDate.now().minusYears(10).toString();
                case ("discountedDate"):
                    return LocalDate.now().toString();
                case ("company"):
                    return "ASUS";
            }
        }
            return PROPERTIES.getProperty(key);
    }
}
