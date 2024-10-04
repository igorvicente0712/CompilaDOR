package Main;
import java.util.List;

public class Parser {
    List<Token> tokens;
    Token token;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token getNextToken() {
        if (tokens.size() > 0)
            return tokens.remove(0);
        return null;
    }

    private void erro(String regra) {
        System.out.println("Regra: " + regra);
        System.out.println("token inválido: " + token.lexema);
        System.exit(0);
    }

    public void main() {
        token = getNextToken();
        if (ifelse()) {
            if (token.tipo == "EOF") {
                System.out.println("sintaticamente correto");
            } else {
                erro("EOF");
            }
        } else {
            erro("chamada ifelse");
        }
    }

    public boolean ifelse() {
        if (matchLexema("if") && condicao() && matchLexema("then") && expressao() && matchLexema("else") && expressao()) {
            return true;
        }
        erro("ifelse");
        return false;
    }

    public boolean condicao() {
        if (matchTipo("id") && operador() && matchTipo("num")) {
            return true;
        }
        erro("condicao");
        return false;
    }

    public boolean operador() {
        if (matchLexema(">") || matchLexema("<") || matchLexema("==")) {
            return true;
        }
        erro("operador");
        return false;
    }

    public boolean expressao() {
        if (matchTipo("id") && matchLexema("=") && matchTipo("num")) {
            return true;
        }
        erro("expressao");
        return false;
    }

    public boolean matchLexema(String lexema) {
        if (lexema.equals(token.lexema)) {
            token = getNextToken();
            return true;
        }
        return false;
    }

    public boolean matchTipo(String tipo) {
        if (tipo.equals(token.tipo)) {
            token = getNextToken();
            return true;
        }
        return false;
    }
}
