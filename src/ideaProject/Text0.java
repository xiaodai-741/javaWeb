import java.io.*;
import java.util.Scanner;

public class Text0 {
    public static void main(String[] args) {
        try {
            byte []bs = new byte[1024];
            BufferedInputStream bos = new BufferedInputStream(new FileInputStream(new File("D:\\b.txt")));
            bos.read(bs);
            String message = new String(bs);
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
