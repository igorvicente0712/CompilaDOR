<h1>Analisador Sintático</h1>
<h2>Gramaticas livres de contexto</h2>
<h3>temVozes-nasParedes-> if-else-> ok</h3>

```
declaracaoif -> temVozes ( expressaobooleana ) blococodigo [nasParedes blococodigo]
expressaobooleana -> expressao (operadorcomparacao expressao)*
operador_comparacao -> == | != | < | > | <= | >=
bloco_codigo -> { [declaracao]* }
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
