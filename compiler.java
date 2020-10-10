import java.io.File;
import java.io.PrintWriter;
import java.io.*;

public class compiler {
    static String line;
    public static void main(String[] args) throws IOException {
        File filein = new File("src/src/in.snail"); //input file
        File fileout = new File("src/src/out.java"); //output file
        String storage;
        String storage2;
        PrintWriter pw = new PrintWriter(new FileWriter(fileout, false));
        BufferedReader br = new BufferedReader(new FileReader(filein)); 
        int legs;
        String whitespaces = "";
        while((line = br.readLine()) != null){
            if (line.length() > 0) {
                whitespaces = "";
                if (line.charAt(0) == ' ') {
                    legs = 0;
                    while (line.charAt(legs) == ' ') {
                        legs++;
                    }
                    whitespaces = line.substring(0, legs);
                    line = line.substring(legs);
                }
                if (line.charAt(line.length() - 1) == ';') {
                    if (line.equals(";")) line = "}";
                    else line = line.substring(0, line.length() - 1) + " {";
                }
                if (line.indexOf(',') != line.lastIndexOf(',') && line.charAt(line.lastIndexOf(',') - 1) == ',') {
                    storage = line.substring(0, line.lastIndexOf(',') - 1);
                    line = (storage2 = line.substring(line.lastIndexOf(',') + 2)) + " = " + storage;
                    if (storage2.equals("out")) line = "System.out.print(" + storage + ')';
                    else if (storage2.equals("libs")) {
                        line = "import " + storage;
                    }
                }
                if (line.length() != 0 && "{};".indexOf(line.charAt(line.length() - 1)) == -1 && line.charAt(0) != '@') line = String.format("%s;", line);
            }
            pw.println(whitespaces + line);
        }
        pw.close();
        pw.flush();
        br.close();
    }
}
