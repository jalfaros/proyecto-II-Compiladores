package analysisContext;

import org.antlr.v4.runtime.*;

class Ident{
    Token tok;
    String className;
    String type;
    int nivel;
    int valor; //Este valor hay que cambiarlo
    boolean initialited;
    ParserRuleContext declCtx;



    public Ident(Token t, String tp, ParserRuleContext decl, int nivelActual, String clssNam ) {
        tok = t;
        type = tp;
        nivel = nivelActual;
        valor = 0;
        declCtx = decl;
        initialited = false;
        className = clssNam;
    }


    public void setValue(int v){
        valor = v;
    }

    public int getNivel() {
        return nivel;
    }
}