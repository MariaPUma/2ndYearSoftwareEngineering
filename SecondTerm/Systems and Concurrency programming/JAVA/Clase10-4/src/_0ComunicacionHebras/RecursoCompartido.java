package _0ComunicacionHebras;

public class RecursoCompartido {
    private int var;
    
    /*public Variable(T var){
    	this.var = var;
    }*/
    //CS_productor
    public void almacena(int dato){
        var = dato;
    }
    //CS_Consumidor
    public int extrae(){
        return var;
    }
}
