entrada = input()
estado = 0
lexema = ""
for c in entrada:
    match estado:
        case 0:
            if c == 'a':
                estado = 1
                lexema += c
            else:
                raise Exception("Token inválido")
        case 1:
            if c == 'a':
                estado = 1
                lexema += c
            elif c == 'b':
                estado = 2
                lexema += c
            else:
                raise Exception("Token inválido")
        case 2:
            if c == 'a':
                estado = 1
                lexema += c
            elif c == 'b':
                estado = 2
                lexema += c
            elif c == ' ':
                print(f"Token reconhecido: <ID, {lexema}>")
                estado = 0
                lexema = ""
            else:
                raise Exception("Token inválido")
            
