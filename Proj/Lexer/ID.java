package Lexer;

import java.text.CharacterIterator;

public class ID extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        if (Character.isLetter(code.current())) {
            String var = readID(code);

            if (endID(code)) {
                return new Token("ID", var);
            }
        }

        return null;
    }

    private String readID(CharacterIterator code) {
        String id = "";

        while (Character.isLetter(code.current())) {
            id += code.current();
            code.next();
        }

        return id;
    }

    private boolean endID(CharacterIterator code) {
        return code.current() == ' '
                || code.current() == '+'
                || code.current() == '-'
                || code.current() == '*'
                || code.current() == '('
                || code.current() == ')'
                || code.current() == '{'
                || code.current() == '}'
                || code.current() == '['
                || code.current() == ']'
                || code.current() == ';'
                || code.current() == '.'
                || code.current() == '\r'
                || code.current() == '\n'
                || code.current() == CharacterIterator.DONE;
    }

}
