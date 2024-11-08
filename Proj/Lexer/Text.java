package Lexer;

import java.text.CharacterIterator;

public class Text extends AFD {
  //@Override
  public Token evaluate(CharacterIterator code) {
    String text = "";

    if (code.current() == '"') {
      text += '"';
      code.next();
      text += readString(code);

      if (endString(code)) {
        text += '"';
        code.next();
        return new Token("STRING", text);
      }
    }

    return null;
  }

  private String readString(CharacterIterator code) {
    String text = "";

    while (code.current() != '"' && code.current() != CharacterIterator.DONE) {
      text += code.current();
      code.next();
    }

    return text;
  }

  private boolean endString(CharacterIterator code) {
    return code.current() == '\r' || code.current() == '\n' || code.current() == '"'
        || code.current() == CharacterIterator.DONE;
  }
}