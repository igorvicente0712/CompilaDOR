
package Main;

import Lexer.Lexer;
import Lexer.Token;
import Parser.Parser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Token> tokens = null;
        String filePath = "C:\\Users\\Nicol\\OneDrive\\Documentos\\NetBeansProjects\\JavaApplication6\\src\\Main\\test.ptgol";
        String data = null;
        // String filePath = args[0];
        // String data = "2+5=10+1++";
        try {
            // Read the entire file content into a single string
            data = Files.readString(Paths.get(filePath));
            System.out.println("txt: "+data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //lexico
        Lexer lexer = new Lexer(data);
        tokens = lexer.getTokens();
        /*
        System.out.println("OUTPUT LÉXICO:");
        for (Token token : tokens) {
            System.out.println(token);
        }*/
        
        //Sintatico
        Parser parser = new Parser(tokens);
        parser.main();
    }

}
