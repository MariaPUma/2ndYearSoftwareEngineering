package _0_1ComunicacionHebras.copy;
//RC con control de acceso
public class RecursoCompartido {
    private int var;
    private boolean hayDatos= false;
    
    /*public Variable(T var){
    	this.var = var;
    }*/
    
    //CS_productor
    public void almacena(int dato){
    	// CONDICIÓN: productor pueda actualizar varibale
    	while(hayDatos) Thread.yield();
        var = dato;
        System.out.println("Productor "+var);
        hayDatos= true;
    }
    //CS_Consumidor
    public int extrae(){
    	while (!hayDatos)Thread.yield();// espera activa
    	int dato = var;
        System.out.println("Consumidor "+dato);
    	hayDatos= false;
        return dato;
    }
}
