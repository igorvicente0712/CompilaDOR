package Lexer;
import java.text.CharacterIterator;

public class BlocoDelimiter extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {

        switch (code.current()) {
            case '{':
                code.next();
                return new Token("ABREBLOCO", "{");

            case '}':
                code.next();
                return new Token("FECHABLOCO", "}");
            case '.':
                code.next();
                return new Token("FIMLINHA", ".");
            case '\n':
                code.next();
                return new Token("NOVALINHA", "barra_ene");
            case '\r':
                code.next();
                return new Token("NOVALINHA", "barra_erre");
            default:
                return null;

        }

    }

}
