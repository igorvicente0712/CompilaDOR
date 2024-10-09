/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author unienicmoraes
 */
package literalmentelang.Lexer;

import java.text.CharacterIterator;

public class Comentario extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        String comment = "";

        if (code.current() == 'o') {
            code.next();
            if(code.current() == 'b'){
                code.next();
                if(code.current() == 's'){
                    code.next();
                    if(code.current() == ':'){
                        code.next();
                        comment += "obs:";

                        comment += readComment(code);

                        if (endComment(code)) {
                            return new Token("Comentario", comment);
                        }
                    }
                }
            }
            
        }

        return null;
    }

    private String readComment(CharacterIterator code) {
        String comment = "";

        while (code.current() != '\r' && code.current() != '\n' && code.current() != CharacterIterator.DONE) {
            comment += code.current();
            code.next();
        }
        //comment += '.';
        return comment;
    }

    private boolean endComment(CharacterIterator code) {
        return code.current() == '\r' || code.current() == '\n' || code.current() == CharacterIterator.DONE;
    }
}