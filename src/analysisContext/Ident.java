package analysisContext;

import org.antlr.v4.runtime.*;

import java.util.List;

class Ident{

    static class Params{
        String name;
        String type;

        public Params(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    Token tok;
    String className;
    String type;
    int nivel;
    int valor; //Este valor hay que cambiarlo
    boolean initialited;
    ParserRuleContext declCtx;
    List<Params> listParams;



    public Ident(Token t, String tp, ParserRuleContext decl, int nivelActual, String clssNam, List<Params> params ) {
        tok = t;
        type = tp;
        nivel = nivelActual;
        valor = 0;
        declCtx = decl;
        initialited = false;
        className = clssNam;
        listParams = params;
    }


    public void setValue(int v){
        valor = v;
    }

    public int getNivel() {
        return nivel;
    }
}