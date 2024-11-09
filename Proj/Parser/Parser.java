package Parser;

import Lexer.Token;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        } else if (matchLexema("se", "if (")) {
            if (ifelse()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("faz", " ")) {
            if (expressao()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("inteiro", "Int ") || matchLexema("decimal", "Float ") || matchLexema("texto", "String ")) {
            if (inicVariavel()) {
                firstToken();
                return true;
            }
        } else if (matchTipo("ID", token.lexema)) {
            if (atribVariavel()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("enquanto", "while (")) {
            if (repeticao()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("sendo", "for (")) {
            if (forloop()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("escreve", "System.out.println(")) {
            if (escreve()) {
                firstToken();
                return true;
            }
        } else if (matchLexema("recebe", "Input.next")) {
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
        if ( matchTipo("ABREBLOCO", "{\n\t") 
                && declar()
                && matchTipo("FECHABLOCO", "}\n")) {
            return true;
        }
        erro("bloco");
        return false;
    }
    
    public boolean ifelse() {
        if (
                condicao()
                && matchLexema("faz", " ")
                && bloco()
                && elsi()) {
            return true;
        }
        erro("ifelse");
        return false;
    }

    public boolean elsi() {
        if (matchLexema("ou", "else if (") 
                && condicao() 
                && matchLexema("faz", " ")
                && bloco()
                && elsi()) {
            return true;
        }
        else if (matchLexema("senao", "else ") && bloco()) {
            return true;
        }
        return true;
    }

    public boolean condicao() {
        if (expressao() && operador() && expressao()) {
            traduz(")");
            return true;
        }
        erro("condicao");
        return false;
    }

    public boolean operador() {
        if (matchLexema("||", "||")
                || matchLexema("&&", "&&")
                || matchLexema(">", ">")
                || matchLexema("<", "<")
                || matchLexema("==", "==")
                || matchLexema("<=", "<=")
                || matchLexema(">=", ">=")
                || matchLexema("++", "++")
                || matchLexema("--", "--")
                || matchLexema("=", "=")
                || matchLexema("+", "+")
                || matchLexema("-", "-")
                || matchLexema("/", "/")
                || matchLexema("*", "*")
                || matchLexema("%", "%")) {
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
        if ((matchLexema("+", "+") && T() && expressaoLin()) 
             || (matchLexema("-", "-") && T() && expressaoLin())) {
            return true;
        }
        return true;
    }
    

    public boolean auxMathF() {
        if (matchLexema("*", "*") || matchLexema("/", "/") || matchLexema("=", "=") || matchLexema("+", "+") || matchLexema("-", "-")) {
            return true;
        } else if (matchLexema("++", "++") || matchLexema("--", "--")) {
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
        if ((matchLexema("*", "*") && F() && Tlin()) 
             || (matchLexema("/", "/") && F() && Tlin())) {
            return true;
        }
        return true; // ε
    }

    public boolean F() {
        if (matchTipo("ID", token.lexema) 
                || matchTipo("NUM", token.lexema)
                || matchTipo("FLOAT", token.lexema)
                || matchTipo("STRING", token.lexema)
                || 
                (matchLexema("(", "(") 
                    && expressao() 
                    && matchLexema(")", ")")
                )) {
            return true;
        }
        erro("Expressao -> func F");
        return false;
    }

    public boolean repeticao() {
        if (condicao()
                && matchLexema("repete", "")
                && bloco()) {
            return true;
        }
        erro("repeticao");
        return false;
    }
    
    // Alternativa do INCDEC
    public boolean INCDEC() {
        if (matchTipo("ID", token.lexema) && (matchLexema("++", "++") || matchLexema("--", "--") )) {
            return true;
        }
        erro("INCDEC");
        return false;
    }

    public boolean forloop() {
        if (
                (matchLexema("inteiro", "Int ") || matchLexema("decimal", "Float ") || matchLexema("texto", "String "))
                && inicVariavel()
                && matchLexema("enquanto", " (")
                && condicao()
                && rep()
                && matchLexema("repete", " ")
                && bloco()) {
            return true;
        }
        erro("forloop");
        return false;
    }
    
    public boolean rep() {
        if (matchLexema("vistoQue", "; ") && iterForLoop()) {
            traduz(") ");
            return true;
        }
        return true;
    }
    
    public boolean iterForLoop() {
        if (matchTipo("ID", token.lexema) && 
                (
                    (matchLexema("++", "++") || matchLexema("--", "--") )
                ) || (matchLexema("=", "=") && expressao())
            ) {
            return true;
        }
        erro("iterForLoop");
        return false;
    }
    
    public boolean inicVariavel() {
        if (matchTipo("ID", token.lexema) && atribVariavel()) {
            return true;
        }
        erro("inicVariavel");
        return false;
    }
    
    public boolean atribVariavel() {
        if (matchLexema("=", "=") && expressao() && matchTipo("FIMLINHA", ";\n")) {
            return true;
        }
        erro("atribVariavel");
        return false;
    }

    public boolean escreve() {
        if ((matchTipo("STRING", token.lexema) || matchTipo("ID", token.lexema)) && matchTipo("FIMLINHA", ");\n")) {
            return true;
        }
        erro("escreve");
        return false;
    }
    
    public boolean recebe() {
        if (matchTipo("ID", "(" + token.lexema + ")")) {
            return true;
        }
        erro("recebe");
        return false;
    }

    public boolean matchLexema(String lexema) {
        //System.out.println("Tentando lexema: " + lexema + " com token: " + token.lexema);
        if (lexema.equals(token.lexema)) {
            //System.out.println("Aceito lexema " + lexema + " do tipo: " + token.tipo);
            token = getNextToken();
            //System.out.println("Aceito lexema: " + lexema + ", proximo token: " + token.lexema);
            return true;
        }
        //erro(lexema);
        return false;
    }

    public boolean matchLexema(String lexema, String code) {
        //System.out.println("Tentando lexema: " + lexema + " com token: " + token.lexema);
        if (lexema.equals(token.lexema)) {
            traduz(code);
            //System.out.println("Aceito lexema " + lexema + " do tipo: " + token.tipo);
            token = getNextToken();
            //System.out.println("Aceito lexema: " + lexema + ", proximo token: " + token.lexema);
            return true;
        }
        //erro(lexema);
        return false;
    }

    public boolean matchTipo(String tipo) {
        //System.out.println("matchT -> ante do if sendo: recebe " + tipo + " compara " + token.tipo);
        //System.out.println("Tentando tipo: " + tipo + " com token: " + token.lexema);
        if (tipo.equals(token.tipo)) {
            //System.out.println("Aceito tipo " + tipo + " com token: " + token.lexema);
            token = getNextToken();
            return true;
        }
        //erro(tipo);
        return false;
    }

    public boolean matchTipo(String tipo, String code) {
        //System.out.println("matchT -> ante do if sendo: recebe " + tipo + " compara " + token.tipo);
        //System.out.println("Tentando tipo: " + tipo + " com token: " + token.lexema);
        if (tipo.equals(token.tipo)) {
            traduz(code);
            //System.out.println("Aceito tipo " + tipo + " com token: " + token.lexema);
            token = getNextToken();
            return true;
        }
        //erro(tipo);
        return false;
    }

    private void traduz(String code) {
        System.out.print(code);
        escreve(code);
    }
    
    public static void createFile() {
      try {
        File myObj = new File("filename.txt");
        if (myObj.createNewFile()) {
          System.out.println("File created: " + myObj.getName());
        } else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    
    public static void escreve(String traduzido) {
    try {
      FileWriter myWriter = new FileWriter("filename.txt");
      myWriter.write(traduzido);
      myWriter.close();
      //System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
