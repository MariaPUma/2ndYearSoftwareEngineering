package codigoTema4;

public class PrincipalCurrentT {

	public static void main(String[] args) {
        UnaHebra h= new UnaHebra(0);
        OtraHebra o = new OtraHebra(1,h);
        h.start();
        o.start();
	}
}
