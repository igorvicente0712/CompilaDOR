import java.util.List;
import java.util.ArrayList;

class Teste {
    private List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        while (!isAtEnd()) {
            statement();
        }
    }

    private void statement() {
        Token token = advance();
        
        switch (token.tipo) {
            case "VAR":
                assignment();
                break;
            case "IF":
                ifStatement();
                break;
            case "WHILE":
                whileStatement();
                break;
            case "FOR":
                forStatement();
                break;
            case "PRINT":
                printStatement();
                break;
            case "INPUT":
                inputStatement();
                break;
            default:
                error("Unexpected token: " + token.lexema);
        }
    }

    private void assignment() {
        Token varName = consume("IDENTIFIER", "Expect variable name.");
        consume("EQUAL", "Expect '=' after variable name.");
        expression();
        consume("SEMICOLON", "Expect ';' after assignment.");
        System.out.println("Assigned value to variable " + varName.lexema);
    }

    private void ifStatement() {
        consume("LEFT_PAREN", "Expect '(' after 'if'.");
        expression();
        consume("RIGHT_PAREN", "Expect ')' after condition.");
        block();
        
        if (match("ELIF")) {
            ifStatement();
        } else if (match("ELSE")) {
            block();
        }
    }

    private void whileStatement() {
        consume("LEFT_PAREN", "Expect '(' after 'while'.");
        expression();
        consume("RIGHT_PAREN", "Expect ')' after condition.");
        block();
    }

    private void forStatement() {
        consume("LEFT_PAREN", "Expect '(' after 'for'.");
        assignment();
        expression();
        consume("SEMICOLON", "Expect ';' after condition.");
        expression();
        consume("RIGHT_PAREN", "Expect ')' after for clauses.");
        block();
    }

    private void printStatement() {
        expression();
        consume("SEMICOLON", "Expect ';' after print statement.");
        System.out.println("Print statement");
    }

    private void inputStatement() {
        Token varName = consume("IDENTIFIER", "Expect variable name.");
        consume("SEMICOLON", "Expect ';' after input.");
        System.out.println("Input value for variable " + varName.lexema);
    }

    private void block() {
        consume("LEFT_BRACE", "Expect '{' at start of block.");
        while (!check("RIGHT_BRACE") && !isAtEnd()) {
            statement();
        }
        consume("RIGHT_BRACE", "Expect '}' after block.");
    }

    private void expression() {
        // Placeholder for expression parsing (e.g., literals, arithmetic, etc.)
        advance();
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean match(String... types) {
        for (String type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(String type) {
        if (isAtEnd()) return false;
        return peek().tipo.equals(type);
    }

    private Token consume(String type, String message) {
        if (check(type)) return advance();
        throw new RuntimeException(message);
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private boolean isAtEnd() {
        return peek().tipo.equals("EOF");
    }

    private void error(String message) {
        System.err.println(message);
    }
}
