package Lexer;
import java.text.CharacterIterator;

public class MathOperator extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {

        switch (code.current()) {
            case '+':
                code.next();
                if (code.current() == '+') {
                    code.next();
                    return new Token("INCREMENTO", "++");
                } else {
                    return new Token("PLUS", "+");
                }
                
            case '-':
                code.next();
                if (code.current() == '-') {
                    code.next();
                    return new Token("DECREMENTO", "--");
                } else {
                    return new Token("SUB", "-");
                }

            case '/':
                code.next();
                return new Token("DIV", "/");

            case '*':
                code.next();
                return new Token("MULT", "*");
            
            case '%':
                code.next();
                return new Token("MOD", "%");

            default:
                return null;

        }

    }

}
