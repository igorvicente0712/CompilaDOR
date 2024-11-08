package Lexer;

import java.text.CharacterIterator;

public class Reservada extends AFD {

    String reservadas[] = {"se", "faz", "ou_se", "sendo", "enquanto", "repete", "recebe", "escreve", "eh"};

    @Override
    public Token evaluate(CharacterIterator code) {
        String reservada = "";

        for (int i = 0; i < reservadas.length; i++) {
            for (char c : reservadas[i].toCharArray()) {
                if (code.current() == c) {
                    reservada += code.current();
                    code.next();
                    //System.out.println("for: " + reservada);

                }
            }

            if (endReservada(code)) {
                if (reservada.equals(reservadas[i])) {
                    return new Token("Reservada_" + reservadas[i], reservada);
                }
            }
        }

        return null;
    }

    private boolean endReservada(CharacterIterator code) {
        return code.current() == ' '
                || code.current() == '('
                || code.current() == ')'
                || code.current() == CharacterIterator.DONE;
    }
}
