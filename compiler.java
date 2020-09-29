import java.io.File;
import java.io.PrintWriter;
import java.io.*;

public class java{
    static String line;
    public static void main(String[] args) throws IOException {
        File filein = new File("in.snail"); // The file we are reading/writing to
        File fileout = new File("out.java");
        String storage;
        String storage2;
        PrintWriter pw = new PrintWriter(new FileWriter(fileout, false)); // Change the true to false if you don't want to append, this is going to write to our file
        BufferedReader br = new BufferedReader(new FileReader(filein)); // This is going to read from our file instead
        int importi = 2;
        while((line = br.readLine()) != null){
            if (importi == 2) importi = 1;
            if (line.indexOf(',') != line.lastIndexOf(',') && line.charAt(0) == ',') {
                line = line.substring(1);
                storage = line.substring(0, line.lastIndexOf(','));
                line = (storage2 = line.substring(line.lastIndexOf(',') + 2)) + " = " + storage;
                if (storage2.equals("out")) line = "System.out.print(" + storage + ')';
                else if (storage2.equals("libs")) {
                    line = "import " + storage;
                    importi = 2;
                }
            }
            if (line.length() != 0 && "{};".indexOf(line.charAt(line.length() - 1)) == -1) line += ';';
            if (importi == 1) {
                line = "public class out {" + line;
                importi = 8;
            }
            pw.println(line);
        }
        pw.println("}");
        pw.close();
        pw.flush();
        br.close(); // We have to close the BufferedReader to prevent memory leaks
    }
    public static void rplc(String target, String arrow){
        if (line.equals(target)) line = arrow;
    }
}
