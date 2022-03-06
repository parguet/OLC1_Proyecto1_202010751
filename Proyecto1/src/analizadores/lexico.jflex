package analizadores;
import java_cup.runtime.Symbol;


%%

%column
%full
%class Lexico
%public
%cupsym Simbolos
%line
%char
%unicode
%cup
%ignorecase

//expresion regular
comentarioLineas = "<!"[^"!>"]*"!>"
cadena = ([\"][^\n\"]+[\"])|([\'][^\n\']+[\'])
comentario = ("//" [^"\n"]+)
numero = [0-9]+
letra = [a-zA-Z]
id = {letra}+("_"|{numero})*
simbolos = ("!")|("\"")|("#")|("$")|("%")|("&")|("\'")|("(")|(")")|("*")|("+")|(",")|("-")|(".")|("/")|(":")|(";")|("<")|("=")|(">")|("?")|("@")|("[")|("]")|("^")|("_")|("`")|("{")|("}")
caracterEspecial = ([\"][^\n\"]+[\"][\"])
%%
<YYINITIAL>
{
("{")   {System.out.println("se encontro una llave abierta");               return new Symbol(Simbolos.llavea, yycolumn, yyline, yytext());}
("}")   {System.out.println("se encontro una llave cerrada");               return new Symbol(Simbolos.llavec, yycolumn, yyline, yytext());}
(":")   {System.out.println("se encontro el simbolo dos puntos");           return new Symbol(Simbolos.dospuntos, yycolumn, yyline, yytext());}
(";")   {System.out.println("se encontro el simbolo punto coma");           return new Symbol(Simbolos.puntocoma, yycolumn, yyline, yytext());}
("->")  {System.out.println("se encontro el simbolo flecha");               return new Symbol(Simbolos.flecha, yycolumn, yyline, yytext());}
(".")   {System.out.println("se encontro el simbolo punto");                return new Symbol(Simbolos.punto, yycolumn, yyline, yytext());}
("*")   {System.out.println("se encontro el simbolo asterisco");            return new Symbol(Simbolos.asterisco, yycolumn, yyline, yytext());}
(",")   {System.out.println("se encontro el simbolo coma");                 return new Symbol(Simbolos.coma, yycolumn, yyline, yytext());}
("|")   {System.out.println("se encontro el simbolo or");                   return new Symbol(Simbolos.or, yycolumn, yyline, yytext());}
("+")   {System.out.println("se encontro el simbolo mas");                  return new Symbol(Simbolos.mas, yycolumn, yyline, yytext());}
("~")   {System.out.println("se encontro el simbolo guionondulado");        return new Symbol(Simbolos.guionondulado, yycolumn, yyline, yytext());}
("?")   {System.out.println("se encontro el simbolo ?");                    return new Symbol(Simbolos.interrogacion, yycolumn, yyline, yytext());}
("CONJ")    {System.out.println("se encontro la palabra reservada CONJ");   return new Symbol(Simbolos.conj, yycolumn, yyline, yytext());}
("%%")  {System.out.println("se encontraron los porcentajes");              return new Symbol(Simbolos.porcentajes, yycolumn, yyline, yytext());}
{comentarioLineas}  {System.out.println("se encontro un comentarioLineas");}
{cadena}    {System.out.println("se encontro una cadena");                  return new Symbol(Simbolos.cadena, yycolumn, yyline, yytext());}
{comentario}    {System.out.println("se encontro un comentario");}
{numero}    {System.out.println("se encontro una numero");                  return new Symbol(Simbolos.numero, yycolumn, yyline, yytext());}
{letra} {System.out.println("se encontro una letra");                       return new Symbol(Simbolos.letra, yycolumn, yyline, yytext());}
{id}    {System.out.println("se encontro un id");                           return new Symbol(Simbolos.id, yycolumn, yyline, yytext());}
{simbolos}  {System.out.println("se encontro un simbolo");                  return new Symbol(Simbolos.simbolos, yycolumn, yyline, yytext());}
{caracterEspecial}  {System.out.println("se encontro un caracterEspecial");                  return new Symbol(Simbolos.caracterEspecial, yycolumn, yyline, yytext());}
}

[ \t\r\n\f] { /* ignorar */ }

. {
    System.out.println("Este es un error lexico: " +yytext() + " en linea " + yyline + " y columna " + yycolumn);
}
