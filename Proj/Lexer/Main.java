package literalmentelang.Lexer;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Token> tokens = null;
        //String data = args[0];
        String data = "obs:teste";
        Lexer lexer = new Lexer(data);
        tokens = lexer.getTokens();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}