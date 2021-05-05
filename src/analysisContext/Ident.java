package analysisContext;

import org.antlr.v4.runtime.*;

class Ident{
    Token tok;
    String type;
    int nivel;
    int valor;
    int nivelActual;
    ParserRuleContext declCtx;


    public Ident(Token tok, String type, ParserRuleContext decl, int nivelActual) {
        this.tok = tok;
        this.type = type;
        this.nivel = nivelActual;
        this.valor = 0;
        this.declCtx = decl;
    }

    public void setValue(int v){
        valor = v;
    }

    public int getNivel() {
        return nivel;
    }
}