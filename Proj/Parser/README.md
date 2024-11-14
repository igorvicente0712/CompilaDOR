<h1>Analisador Sintático</h1>

<h2>Gramaticas livres de contexto</h2>

<h3>Símbolo inicial</h3>

```
Programa -> declar
```

<h3>Declarações Básicas</h3>

```
declar -> first declar | ∈
bloco -> "{" declar "}"
first -> seFazSenao | enquantoLoop | sendoLoop | recebe | AtribVar | Comentario | escreve | "."
```

<h3>Condicional</h3>

```
seFazSenao -> "se" Condicao "faz" bloco senao
senao -> "ou" Condicao "faz" bloco senao | "senao" bloco | ∈
```

<h3>Repetição</h3>

```
enquantoLoop -> "enquanto" Condicao "repete" bloco
```

<h3>For Loop</h3>

```
sendoLoop -> "sendo" InicVar "enquanto" Condicao "vistoQue" Id ("=" Expressao || "++" || "--") "repete" bloco
```

<h3>Atribuições e Inicializações</h3>

```
InicVar -> TipoVariavel AtribVar
AtribVar -> TipoVariavel Id "=" Expressao "."
```

<h3>Tipos de Variáveis</h3>

```
TipoVariavel -> "inteiro" | "decimal" | "texto"
```

<h3>Sistema de Expressões</h3>

```
Expressao -> Termo Expressao’
Expressao’ -> "+" Termo Expressao’ | "-" Termo Expressao’ | ε
Termo -> Fator Termo’
Termo’ -> "*" Fator Termo’ | "/" Fator Termo’ | ε
Fator -> Id | Num | Decimal | "(" Expressao ")"
```

<h3>Condições</h3>

```
Condicao -> Expressao Operador Expressao
```

<h3>Operadores</h3>

```
Operador -> OperadorLogico || OperadorComparacao || OperadorMat || OperadorAtribuicao
OperadorLogico -> "&&" | "||"
OperadorComparacao -> ">" | "<" | "==" | "!=" | ">=" | "<="
OperadorMat -> "+" | "-" | "*" | "/"
OperadorAtribuicao -> "="
```

<h3>Input e Print</h3>

```
recebe -> Id "recebe" TipoVariavel "." | TipoVariavel Id "recebe" TipoVariavel "."
escreve -> "escreve" Texto "." | "escreve" Id "."
```

<h3>Sistema de Comentários</h3>

```
Comentario -> "obs:" [a-zA-Z0-9]* "."
```

<h3>Identificadores e Literais</h3>

```
Variavel -> Id | Num | Decimal | Texto
Id -> [a-zA-Z][a-zA-Z0-9]*
Num -> [0-9]+
Decimal -> Num "," Num
Texto -> """ ([^"\n])* """
```

<h3>Caracteres Especiais e Delimitadores</h3>

```
Delimitador -> "(" | ")"
```

<h3>Exemplos de uso:</h3>

```
Declaração e atribuição:
inteiro x = 10.
decimal y = 3,14.
texto mensagem = "Olá mundo".

Estrutura Se:
se x > 10 faz escreve "Maior que 10" senao escreve "Menor ou igual a 10".

Estrutura enquanto:
enquanto i < 10 repete escreve i.

Estrut sendo repete

sendo i = 0 enquanto i < 3 vistoQue i++ repete {escreve i.}

Input e Print:
escreve "Digite um número:".

x recebe.

escreve "Você digitou: ".

escreve x.

Comentários:
obs: Este é um comentário.

inteiro soma = 0. obs Inicializa a variável soma.


Expressões Complexas:
inteiro resultado = (x + y) * (z - w).

se (a > b && c < d || e == f) {

escreve "Condição complexa".
}

```
