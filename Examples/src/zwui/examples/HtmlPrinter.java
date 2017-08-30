package zwui.examples;

import java.io.File;
import java.io.FileWriter;

public class HtmlPrinter {

    public void print(String str){
        try {
            File file = new File(this.getClass().getResource("/output.html").getFile());
            System.out.println(file.getAbsolutePath());
            FileWriter fw = new FileWriter(file);
            fw.write(str);
            fw.close();
            System.out.println("html printed!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
