<h1>Analisador Sintático</h1>
<h2>Gramaticas livres de contexto</h2>

Programa -> first

<h2>Declarações Básicas</h2>

<h3>first -> Se statement | Caso | Enquanto | Comer</h3> 

      | AtribVar 
      | Comentario 
      | Cuspir 
      | ";"

```
first -> sefaz first | enquantoLoop first | ... | expressao first | ";" |  ∈
sefaz -> "se" Condicao "faz" first A
A -> "caso" first | ∈
```

<h3>enquantoLoop -> "enquanto" Condicao "repete" first</h3>

Atribuições e Inicializações

<h3>AtribVar -> TipoVariavel id "=" Expressao ";"</h3>

Tipos de Variáveis

<h3>TipoVariavel -> "inteiro" | "flutuante" | "texto"</h3>

Sistema de Expressões

```
expressaoMat -> TexpressaoMat’
expressaoMat’ -> +TexpressaoMat’ | -TexpressaoMat’ | ε
T -> FT´
T´ -> *FT´ | /FT´ | ε
F -> id | num | flutuante | input | ( mathExpressao )

```
<h3>Condições</h3>

Condicao -> id Operador variavel

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
Comer -> "comer" first
Cuspir -> "cuspir" first
```

<h3>Sistema de Comentários</h3>

```
Comentario -> "obs:" [a-zA-Z0-9]* ";"
```

<h3>Identificadores e Literais</h3>

```
Variavel -> Id | num | flutuante | string
Id -> [a-zA-Z][a-zA-Z0-9]*
Num -> [0-9]+
Flutuante -> Num "," Num
String -> """ ([^"\n])* """
```
<h3>Caracteres Especiais e Delimitadores</h3>

```
Delimitador -> "(" | ")"
Exemplos de uso:
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
