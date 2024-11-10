
package Main;

import Lexer.Lexer;
import Lexer.Token;
import Parser.Parser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        List<Token> tokens = null;
        //String filePath = "C:\\Users\\Nicol\\OneDrive\\Documentos\\NetBeansProjects\\JavaApplication6\\src\\Main\\test.ptgol";
        String filePath = "D:\\Downloads\\Projeto_v2\\Main\\test.ptgol";
        String data = null;
        // String filePath = args[0];
        // String data = "2+5=10+1++";
        try {
            // Read the entire file content into a single string
            data = Files.readString(Paths.get(filePath));
            System.out.println("Codigo original:\n" + data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //lexico
        Lexer lexer = new Lexer(data);
        tokens = lexer.getTokens();
        
        System.out.println("OUTPUT LEXICO:");
        for (Token token : tokens) {
            System.out.println(token);
        }
        
        Parser.createFile();
        Parser.escreve("import java.util.Scanner;\n");
        Parser.escreve("public class Main {\n");
        Parser.escreve("\tpublic static void main(String[] args) {\n");
        Parser.escreve("\tScanner scanner = new Scanner(System.in);\n");
        //Sintatico
        Parser parser = new Parser(tokens);
        parser.main();
        
        Parser.escreve("\t}\n}");
        try {
            System.out.println("Codigo final:\n" + Files.readString(Paths.get("Main.java")));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

}
