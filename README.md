# Pascal-Compiler
I will be implementing part of a compiler for the language specified below. The language is a subset of Turbo Pascal, lacking its arrays and records for purposes of simplicity. The project will include a scanner, symbol table, parser and (time permitting) type checker and intermediate code generator. 

The specifications for the language's syntax are as follows:
Lexical Conventions
Comments are surrounded by { and }

Blanks between tokens are optional, except reserved words must be surrounded 
by blanks, newlines, the beginning of the program or the final period.

Token id matches a letter or a letter followed by letters and digits"
        letter -> [a-zA-Z]
        digit -> [0-9]
        id -> letter (letter | digit)*

Token num matches unsigned integers
        digits -> digit digit*
        optional_fractional  -> . digits | ε
        optional_exponent  -> ( E | e) (+ | - |ε ) digits) | ε 
        num -> digits optional_fraction optional_exponent

Keywords are reserved and appear in boldface in the grammar

The relational operators (relops) are =,  >,  <,  >=,  <=,  <>

The addops are +, -, OR

The mulops are *, /, DIV, MOD, AND
The lexeme for token assignop is :=
Pascal Subset Syntax
Program -> Header Declarations Block .
Header -> program id ;
Declarations -> VariableDeclarationSection   ProcedureDeclarations
VariableDeclarationSection ->   VAR VariableDeclarations 
                                        | <epsilon>
VariableDeclarations  -> VariableDeclarations  VariableDeclaration
                                         | VariableDeclaration
VariableDeclaration -> IdentifierList : Type ;
IdentifierList  -> IdentifierList , id
                         | id
Type -> integer 
        | real

ProcedureDeclarations -> ProcedureHeader Declarations Block ;
ProcedureHeader -> procedure id Arguments;
Arguments ->  ( ParameterList  )
            | <epsilon>
ParameterList  -> ParameterList ; ParameterDeclaration 
                    | ParameterDeclaration
ParameterDeclaration -> id : Type 
Block  ->  begin Statements end
Statements  ->   Statements ;  Statement | Statement
Statement -> id := Expression
                        | id ( ExpressionList )
                        | Block
                        | if Expression then Statement ElseClause
                        | while Expression do Statement
                        | <epsilon>

ElseClause -> else Statement | <epsilon>
ExpressionList -> ExpressionList , Expression | Expression

Expression -> SimpleExpression relop SimpleExpression
                | SimpleExpression

SimpleExpression -> Term
                        | addop Term
                        | SimpleExpression addop Term
Term -> Term mulop Factor
                | Factor
Factor -> id
        | num
        | ( Expression )
        | not  Factor 

