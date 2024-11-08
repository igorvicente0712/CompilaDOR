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
first -> seFazSenao | enquantoLoop | sendoLoop | Comer | AtribVar | Comentario | Cuspir | ";"
```

<h3>Condicional</h3>

```
seFazSenao -> "se" Condicao "faz" bloco senao
senao -> "ou_se" Condicao "faz" bloco senao | "senao" bloco | ∈
```

<h3>Repetição</h3>

```
enquantoLoop -> "enquanto" Condicao "repete" bloco
```

<h3>For Loop</h3>

```
sendoLoop -> "sendo" AtribVar "ate" Condicao bloco
```

<h3>Atribuições e Inicializações</h3>

```
AtribVar -> TipoVariavel Id "=" Expressao ";"
```

<h3>Tipos de Variáveis</h3>h3>

```
TipoVariavel -> "inteiro" | "flutuante" | "texto"
```

<h3>Sistema de Expressões</h3>

```
expressaoMat -> TexpressaoMat’
expressaoMat’ -> +TexpressaoMat’ | -TexpressaoMat’ | ε
T -> FT´
T´ -> *FT´ | /FT´ | ε
F -> id | num | flutuante | input | ( mathExpressao )

```

<h3>Condições</h3>

```
Condicao -> id Operador variavel
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
Comer -> "comer" texto | "comer" Id
Cuspir -> "cuspir" texto | "cuspir" Id
```

<h3>Sistema de Comentários</h3>

```
Comentario -> "obs:" [a-zA-Z0-9]* ";"
```

<h3>Identificadores e Literais</h3>

```
Variavel -> Id | Num | Flutuante | texto
Id -> [a-zA-Z][a-zA-Z0-9]*
Num -> [0-9]+
Flutuante -> Num "," Num
texto -> """ ([^"\n])* """
```

<h3>Caracteres Especiais e Delimitadores</h3>

```
Delimitador -> "(" | ")"
```

<h3>Exemplos de uso:</h3>

```
Declaração e atribuição:
inteiro x = 10;
flutuante y = 3.14;
texto mensagem = "Olá mundo";

Estrutura Se:
se x > 10 faz cuspir("Maior que 10") senao cuspir "Menor ou igual a 10";

Estrutura enquanto:
enquanto i < 10 repete cuspir(i);

Estrut sendo repete

sendo i = 0 até i < 3 repete cuspir(i);

Input e Print:
cuspir("Digite um número:");

comer(x);

cuspir("Você digitou: ");

cuspir(x);

Comentários:
obs: Este é um comentário;

inteiro soma = 0; obs Inicializa a variável soma;


Expressões Complexas:
inteiro resultado = (x + y) * (z - w);

se (a > b && c < d || e == f) {

cuspir("Condição complexa");
}

```
