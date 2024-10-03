/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author unienicmoraes
 */
public class Comentario {

    @Override
    public Token evaluate(CharacterIterator code) {
        String comment = "";

        
        
        /* EX DE COMENTARIO PADRAO
        if (code.current() == '#') {
            comment += "#";
            code.next();

            comment += readComment(code);

            if (endComment(code)) {
                return new Token("COMENTARIO", comment);
            }
        }*/

        return null;
    }

    private String readComment(CharacterIterator code) {
        String comment = "";

        while (code.current() != '\r' && code.current() != '\n' && code.current() != CharacterIterator.DONE) {
            comment += code.current();
            code.next();
        }

        return comment;
    }

    private boolean endComment(CharacterIterator code) {
        return code.current() == '\r' || code.current() == '\n' || code.current() == CharacterIterator.DONE;
    }
}