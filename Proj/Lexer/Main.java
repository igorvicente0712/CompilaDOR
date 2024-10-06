import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Token> tokens = null;
        String filePath = "D:\\GitHub\\CompilaDOR\\Proj\\teste_portugol.pt";
        String data = null;
        // String filePath = args[0];
        // String data = "2+5=10+1++";
        try {
            // Read the entire file content into a single string
            data = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Lexer lexer = new Lexer(data);
            tokens = lexer.getTokens();
            for (Token token : tokens) {
                System.out.println(token);
            }
    }
}