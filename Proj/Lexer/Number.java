package Lexer;
import java.text.CharacterIterator;

public class Number extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        if (Character.isDigit(code.current())) {
            String number = readNumber(code);
            
            if (endNumber(code)) {
                // Verifica se o número contém ponto decimal
                if (number.contains(",")) {
                    return new Token("FLOAT", number);
                } else {
                    return new Token("NUM", number);
                }
            }
        }
        return null;
    }

    private String readNumber(CharacterIterator code) {
        String number = "";
        boolean hasDecimalPoint = false;
        
        // Lê a parte inteira
        while (Character.isDigit(code.current())) {
            number += code.current();
            code.next();
        }
        
        // Verifica se tem ponto decimal
        if (code.current() == ',') {
            hasDecimalPoint = true;
            number += code.current();
            code.next();
            
            // Lê a parte decimal
            while (Character.isDigit(code.current())) {
                number += code.current();
                code.next();
            }
        }
        
        return number;
    }

    private boolean endNumber(CharacterIterator code) {
        return code.current() == ' ' ||
               code.current() == '+' ||
               code.current() == '=' ||
               code.current() == '-' ||
               code.current() == '.' ||
               code.current() == '/' ||
               code.current() == '*' ||
               code.current() == '\n' ||
               code.current() == '\r' ||
               code.current() == CharacterIterator.DONE;
    }
}