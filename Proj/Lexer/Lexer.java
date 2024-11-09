package Lexer;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;

    public Lexer(String code) {
        tokens = new ArrayList<>();
        this.code = new StringCharacterIterator(code);
        afds = new ArrayList<>();
        afds.add(new Comentario());
        afds.add(new Reservada());
        afds.add(new AtrOperator());
        afds.add(new MathOperator());
        afds.add(new Number());
        afds.add(new ID());
        afds.add(new Text());
        afds.add(new BlocoDelimiter());
    }

    public void skipWhiteSpace() {
        while (code.current() == ' ' || code.current() == '\n' || code.current() == '\r') {
            code.next();
        }

    }

    public List<Token> getTokens() {
        boolean accepted;
        while (code.current() != CharacterIterator.DONE) {
            accepted = false;
            skipWhiteSpace();
            if (code.current() == CharacterIterator.DONE) {
                break;
            }
            /* DESCOMENTE PARA VER TOKENS FEITOS
            for (Token token : tokens) {
                System.out.println(token);
            }
            */

            for (AFD afd : afds) {
                int pos = code.getIndex();
                Token t = afd.evaluate(code);
                if (t != null) {
                    accepted = true;
                    tokens.add(t);
                    break;
                } else {
                    code.setIndex(pos);
                }
            }

            if (accepted) {
                continue;
            }
            throw new RuntimeException("Error: Token not recognized: " + code.current() );
        }
        tokens.add(new Token("EOF", "$"));
        return tokens;
    }
}
