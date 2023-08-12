package Assignment.Loggs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyLogger {

        public static void log(String message) throws IOException {
            try{
                File file = new File("./src/Assignment/Loggs/craftyCakesLogs.txt");
                FileWriter fileWriter = new FileWriter(file,true);

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                fileWriter.write(timestamp + " - " + message + "\n");
                fileWriter.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }


        }

}
