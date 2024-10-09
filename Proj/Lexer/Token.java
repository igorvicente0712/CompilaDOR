/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package literalmentelang.Lexer;
/**
 *
 * @author unienicmoraes
 */
public class Token {

    private String lexema;
    private String tipo;

    public Token(String tipo, String lexema) {
        this. lexema = lexema;
        this. tipo = tipo;

    }

    public String getLexema() {
        return lexema;

    }

    public String getTipo() {
        return tipo;
    }
    @Override
    public String toString() {
        return "<" + tipo + ", " + lexema + ">";
    }
}