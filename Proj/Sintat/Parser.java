package literalmentelang.Sintat;

import java.util.List;

public class Parser {

    List<Token> tokens;
    Token token;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token getNextToken() {
        if (tokens.size() > 0) {
            return tokens.remove(0);
        }
        return null;
    }

    private void erro(String regra) {
        System.out.println("Regra: " + regra);
        System.out.println("token inválido: " + token.lexema);
        System.exit(0);
    }

    private boolean firstToken() {
        if (matchLexema("obs:")) {
            if (comentario()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("se")) {
            if (ifelse()) {
                firstToken();
                return true;
            }
        }
        else if(matchTipo("id")){
            if (atribVariavel()) {
                firstToken();
                return true;
            }
        }
        else if (matchLexema("enquanto")) {
            if (repeticao()) {
                firstToken();
                return true;
            }
        }
        
        else if (matchLexema("caso")) {
            if (forloop()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("escreva")) {
            if (escreve()) {
                firstToken();
                return true;
            }
        }
        else {
            return true;
        }

        erro("FirstToken: " + token);
        return false;
    }

    public void main() {
        token = getNextToken();
        
        if (firstToken()) {
            if (matchTipo("EOF")) {
                System.out.print("}}");
            } else {
                erro("Fudeu de vez!!!!");
            }
        }
        
        /*if (ifelse()) {
            if (token.tipo == "EOF") {
                System.out.println("sintaticamente correto");
                } else {
                erro("EOF");
                }
            }else{
            erro("chamada ifelse");
            }*/
    }

    public boolean ifelse() {
        if (
                //matchLexema("if") 
                //&& 
                condicao() 
                && matchLexema("entao") 
                && expressao() 
                && matchLexema("ou") 
                && expressao()
            ) {
            return true;
        }
        erro("ifelse");
        return false;
    }

    private boolean ifStatement() {
        if (condicao() && matchLexema("entao") && expressao()) {
            while (matchLexema("se")) {  // Mais de um se no mesmo escopo
                if (!ifStatement()) erro("Nested if");
            }
            if (matchLexema("senao")) {
                return expressao();
            }
            return true;
        }
        erro("ifStatement");
        return false;
    }

    public boolean condicao() {
        if (matchTipo("id") && operador() && matchTipo("num")) {
            return true;
        }
        else {
            System.out.println("condit");
        }
        System.out.println("condit");
        erro("condicao");
        return false;
    }
    
    public boolean comentario(){
        if (matchLexema("obs:")){
            return true;
        }
        erro("cometario");
        return false;
    }

    public boolean operador() {
        if (
                matchLexema(">") 
                || matchLexema("<") 
                || matchLexema("==") 
                || matchLexema("<=")
                || matchLexema(">=")
            ) {
            return true;
        }
        erro("operador");
        return false;
    }

    public boolean expressao() { // TODO
        if (matchTipo("id") && matchLexema("=") && matchTipo("num")) {
            return true;
        }
        erro("expressao");
        return false;
    }
    
    public boolean repeticao() {
        if (
                condicao()
                && expressao()
            ) {
            return true;
        }
        erro("repeticao");
        return false;
    }
    
    public boolean forloop() {
        if (
                matchTipo("id")
                && matchLexema(":")
                && condicao()
                && matchLexema(":")
                && expressao()
            ) {
            return true;
        }
        erro("forloop");
        return false;
    }
    
    public boolean atribVariavel() {
        if (
                matchLexema("=")
                && expressao()
            ) {
            return true;
        }
        erro("atribVariavel");
        return false;
    }
    
    public boolean escreve() {
        if (expressao()) {
            return true;
        }
        erro("escreve");
        return false;
    }

    public boolean matchLexema(String lexema) {
        //System.out.println("Tentando lexema: " + lexema + " com token: " + token.lexema);
        if (lexema.equals(token.lexema)) {
            System.out.println("Aceito lexema " + lexema + " do tipo: " + token.tipo);
            token = getNextToken();
            //System.out.println("Aceito lexema: " + lexema + ", proximo token: " + token.lexema);
            return true;
        }
        return false;
    }

    public boolean matchTipo(String tipo) {
        if (tipo.equals(token.tipo)) {
            //System.out.println("Tentando tipo: " + tipo + " com token: " + token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }
}
