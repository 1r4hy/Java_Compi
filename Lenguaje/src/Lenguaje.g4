grammar Lenguaje;

// Palabras reservadas
PROGRAM: 'programa';
VAR: 'var';
INT: 'int';
STRING: 'str';
FLOAT: 'float';
VOID: 'void';
FUNC: 'func';
MAIN: 'main';
PRINT: 'print';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
DO: 'do';
END: 'end';

// Operadores
ASSIGN: '=';
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
EQ: '==';
NEQ: '!=';
GT: '>';
LT: '<';
AND: '&&';
OR: '||';

// Símbolos especiales
SEMI: ';';
COLON: ':';
COMMA: ',';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';

// Identificadores y constantes
ID: (LETTER|'_') (LETTER|DIGIT|'_')*;
CTE_INT: DIGIT+;
CTE_FLOAT: DIGIT+ '.' DIGIT* | DIGIT* '.' DIGIT+;
CTE_STRING: '"' ([a-zA-Z0-9_ áéíóúüñÁÉÍÓÚÜÑ] | ' ')* '"';

// Espacios en blanco
WS: [ \t\r\n]+ -> skip;

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];

// Reglas del parser
program
    : PROGRAM ID SEMI vars_opt funcs_p MAIN body END
    ;

vars_opt
    : vars+
    | // epsilon
    ;

funcs_p
    : funcs funcs_p
    | // epsilon
    ;

vars
    : VAR id_list COLON type SEMI
    ;

id_list
    : ID (COMMA ID)*
    ;

type
    : INT
    | FLOAT
    | STRING
    ;

funcs
    : FUNC VOID ID LPAREN atributo RPAREN LBRACK vars_opt body RBRACK SEMI
    ;

atributo
    : ID COLON type atr_opt
    | // epsilon
    ;

atr_opt
    : COMMA atributo
    | // epsilon
    ;

body
    : LBRACE statement_list RBRACE
    ;

statement_list
    : statement+
    | // epsilon
    ;

statement
    : assing
    | condition
    | cycle
    | f_call
    | print
    ;

assing
    : ID ASSIGN expresion SEMI
    ;

condition
    : IF LPAREN expresion RPAREN body (ELSE body)? SEMI
    ;

cycle
    : WHILE LPAREN expresion RPAREN DO body SEMI
    ;

f_call
    : ID LPAREN expresion_list_opt RPAREN SEMI
    ;

expresion_list_opt
    : expresion_list
    | // epsilon
    ;

expresion_list
    : expresion (COMMA expresion)*
    ;

print
    : PRINT LPAREN expresion_list RPAREN SEMI
    ;

expresion
    : andExpr (OR andExpr)* 
    ;

andExpr
    : comparacion (AND comparacion)*
    ;

comparacion
    : aritmetica ((GT | LT | NEQ | EQ) aritmetica)?
    ;

aritmetica
    : termino ((PLUS | MINUS) termino)*
    ;

termino
    : factor ((MULT | DIV) factor)*
    ;

factor
    : LPAREN expresion RPAREN
    | (PLUS | MINUS)? ID
    | (PLUS | MINUS)? cte
    ;

cte
    : CTE_INT
    | CTE_FLOAT
    | CTE_STRING
    ;