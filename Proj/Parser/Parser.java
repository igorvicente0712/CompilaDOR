package Parser;

import Lexer.Token;
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
        System.out.println("Token invalido: " + token.lexema);
        System.exit(0);
    }
    
    private boolean declar() {
        if (firstToken()) {
            return true;
        }
        return true;
    }
    
    private boolean firstToken() {
        if (matchTipo("COMMENT")) {
            firstToken();
            return true;
        } else if (matchLexema("se")) {
            if (ifelse()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("faz")) {
            if (expressao()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("inteiro") || matchLexema("decimal") || matchLexema("texto")) {
            if (inicVariavel()) {
                firstToken();
                return true;
            }
        } else if (matchTipo("ID")) {
            if (atribVariavel()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("enquanto")) {
            if (repeticao()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("sendo")) {
            if (forloop()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("escreve")) {
            if (escreve()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("recebe")) {
            if (recebe()) {
                firstToken();
                return true;
            }
        } else {
            return true;
        }

        erro("FirstToken: " + token);
        return false;
    }

    public void main() {
        token = getNextToken();

        //if (firstToken()) {
        if (declar()) {
            if (matchTipo("EOF")) {
                System.out.println("##### Finalizado #####");
            } else {
                erro("Erro de token");
                System.out.print(token.lexema);
            }
        }
    }
    
    public boolean bloco() {
        if ( matchTipo("ABREBLOCO") 
                && declar()
                && matchTipo("FECHABLOCO")) {
            return true;
        }
        erro("bloco");
        return false;
    }
    
    public boolean ifelse() {
        if (
                condicao()
                && matchLexema("faz")
                && bloco()
                && elsi()) {
            return true;
        }
        erro("ifelse");
        return false;
    }

    public boolean elsi() {
        if (matchLexema("ou") 
                && condicao() 
                && matchLexema("faz")
                && bloco()
                && elsi()) {
            return true;
        }
        else if (matchLexema("senao") && bloco()) {
            return true;
        }
        return true;
    }

    public boolean condicao() {
        //if (matchTipo("ID") && operador() && matchTipo("NUM")) {
        if (expressao() && operador() && expressao()) {
            return true;
        }
        erro("condicao");
        return false;
    }

    public boolean operador() {
        if (matchLexema("||")
                || matchLexema("&&")
                || matchLexema(">")
                || matchLexema("<")
                || matchLexema("==")
                || matchLexema("<=")
                || matchLexema(">=")
                || matchLexema("++")
                || matchLexema("--")
                || matchLexema("=")
                || matchLexema("+")
                || matchLexema("-")
                || matchLexema("/")
                || matchLexema("*")
                || matchLexema("%")) {
            return true;
        }
        erro("operador");
        return false;
    }

    public boolean expressao() { // TODO
        /*if (matchTipo("ID") && operador() && matchTipo("NUM")) {
            return true;
        }*/
        if (T() && expressaoLin()) {
            return true;
        }
        erro("expressao");
        return false;
    }
    
    public boolean expressaoLin() {
        if ((matchLexema("+") && T() && expressaoLin()) 
             || (matchLexema("-") && T() && expressaoLin())) {
            return true;
        }
        return true;
    }
    
    /*public boolean Exp(){
        if(auxMathExp() && T() && Exp()){
            return true;
        }
        return true; // ε
    }*/

 /*public boolean auxMathExp() {
        if (matchLexema("+") || matchLexema("-")) {
            return true;
        }
        erro("auxMathExp");
        return false;
    }*/
    public boolean auxMathF() {
        if (matchLexema("*") || matchLexema("/") || matchLexema("=") || matchLexema("+") || matchLexema("-")) {
            return true;
        } else if (matchLexema("++") || matchLexema("--")) {
            return true;
        }
        erro("auxMathF");
        return false;
    }

    public boolean T() {
        if (F() && Tlin()) {
            return true;
        }
        erro("Expressao -> func T");
        return false;
    }

    public boolean Tlin() {
        if ((matchLexema("*") && F() && Tlin()) 
             || (matchLexema("/") && F() && Tlin())) {
            return true;
        }
        return true; // ε
    }

    public boolean F() {
        if (matchTipo("ID") 
                || matchTipo("NUM")
                || matchTipo("FLOAT")
                || matchTipo("STRING")
                || 
                (matchLexema("(") 
                    && expressao() 
                    && matchLexema(")")
                )) {
            return true;
        }
        erro("Expressao -> func F");
        return false;
    }

    public boolean repeticao() {
        if (condicao()
                && matchLexema("repete")
                && bloco()) {
            return true;
        }
        erro("repeticao");
        return false;
    }

    public boolean rep() {
        if (matchLexema("vistoQue") && INCDEC()) {
            return true;
        }
        return true;
    }

    public boolean INCDEC() {
        if (matchLexema("ID") && operador()) {
            return true;
        }
        erro("INCDEC");
        return false;
    }

    public boolean forloop() {
        if (
                (matchLexema("inteiro") || matchLexema("decimal") || matchLexema("texto"))
                && inicVariavel()
                && matchLexema("enquanto")
                && condicao()
                && matchLexema("repete")
                && bloco()) {
            return true;
        }
        erro("forloop");
        return false;
    }
    
    public boolean inicVariavel() {
        if (matchTipo("ID") && atribVariavel()) {
            return true;
        }
        erro("inicVariavel");
        return false;
    }
    
    public boolean atribVariavel() {
        if (matchLexema("=") && expressao() && matchTipo("FIMLINHA")) {
            return true;
        }
        erro("atribVariavel");
        return false;
    }

    public boolean escreve() {
        if (matchTipo("STRING") || matchTipo("ID")) {
            return true;
        }
        erro("escreve");
        return false;
    }
    
    public boolean recebe() {
        if (matchTipo("ID")) {
            return true;
        }
        erro("recebe");
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
        //System.out.println("matchT -> ante do if sendo: recebe " + tipo + " compara " + token.tipo);
        //System.out.println("Tentando tipo: " + tipo + " com token: " + token.lexema);
        if (tipo.equals(token.tipo)) {
            System.out.println("Aceito tipo " + tipo + " com token: " + token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }
}
