<h1>Analisador Sintático</h1>
<h2>Gramaticas livres de contexto</h2>

<h3>First-> ok</h3>

```
first -> se first | caso first | enquanto first | comer | variavel first | comentario first | cuspir | ε
```

<h3>atrVar-> Atribuir variável-> ok</h3>

```
atribVariavel -> tipoVariavel variavel "=" mathExpressao "."
tipoVariavel -> taOk | gaviao | caixaPreta
variavel -> id | num | flutuante | string

id -> [a-zA-Z]⁺
num -> [0-9]⁺
flutuante -> num.num
string -> " (id + num + flutuante) "
```

<h3>se-> if-> ok</h3>

```
se -> se condicao positivo first negativo
negativo -> negativo first | ε
condicao -> [variavel operador variavel]⁺ | ε
variavel -> id | num | flutuante | string | ( mathExpressao )
operador -> ">" | "<" | "==" | "!=" | "&&" | "||" | "+" | "-" | "\" | "*" | "/" | "="
expressao -> first ";"

id -> [a-zA-Z]⁺
num -> [0-9]⁺
flutuante -> num⁺.num⁺
string -> "(id+num+flutuante)"
```
<h3>caso-> for-> ok</h3>

```
caso- -> caso- varContador ":" condicao ":" first 
varContador -> tipoVariavel variavel operador num
condicao -> variavel operador variavel

id -> [a-zA-Z]⁺
num -> [0-9]⁺ | ε
flutuante -> num⁺.num⁺ | ε
string -> "(id+num+flutuante)"
```
<h3>enquanto-> While-> ok</h3>

```
while -> enquanto condicao  first
condicao -> variavel operador variavel
varivel -> id | num | flutuante | string | mathExpressao
operador -> ">" | "<" | "<=" | ">=" | "+=" | "-=" | "++" | "--"

id -> [a-zA-Z]⁺
num -> [0-9]⁺
flutuante -> num.num
string -> "\"" (id | num | flutuante) "\""
```
<h3>Comer-> Input-> ok</h3>

```
comer -> comer ( expressao ) ;
expressao -> id | num | flutuante | string
id -> [a-zA-Z]⁺
num -> [0-9]⁺
flutuante -> num . num
string -> " (id | num | flutuante)⁺ "
```
Exemplos:
>comer(variavel);

>Comer(123);

<h3>Cuspir ->Print -> ok</h3>

```
cuspir -> cuspir ( expressao )
cuspir -> cuspir ( texto ) ;
texto -> id | num | flutuante | string
id -> [a-zA-Z]⁺
num -> [0-9]⁺
flutuante -> num . num
string -> " (id | num | flutuante)⁺ "
```
Exemplos:

>cuspir(variavel);

>cuspir(123);

>cuspir(3.14);

>cuspir("Ola 123 3.14");

<h3>Comentário-> ok</h3>

```
comentario -> /// texto

texto -> [a-zA-Z0-9]⁺
```
Exemplos:

>/// texto

>///(123);
