package Lexer;
import java.text.CharacterIterator;

public class AtrOperator extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {

        switch (code.current()) {
            case '=':
                code.next();
                if (code.current() == '=') {
                    code.next();
                    return new Token("COMPARACAO", "==");
                } else {
                    return new Token("ATRIBUICAO", "=");
                }
                
            case '>':
                code.next();
                return new Token("MAIOR", ">");
                
            case '<':
                code.next();
                return new Token("MENOR", "<");

            default:
                return null;

        }

    }

}
