package esqueleto;

import java.util.concurrent.Semaphore;

public class Curso {

	//Numero maximo de alumnos cursando simultaneamente la parte de iniciacion
	private final int MAX_ALUMNOS_INI = 10;
	
	//Numero de alumnos por grupo en la parte avanzada
	private final int ALUMNOS_AV = 3;
	
	private Semaphore iniciar_Inicializacion = new Semaphore(1);
	private int alum_inic= 0;
	
	private Semaphore iniciar_Avanzado= new Semaphore(1);
	private int alum_avan=0;
	private int alum_proy=0;
	//BARRERAS
	private Semaphore proy_inicio= new Semaphore(0);
	private Semaphore proy_fin = new Semaphore(0);
	
	//MUTEX
	private Semaphore mutex= new Semaphore(1); 
	
	
	//El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de iniciacion
	public void esperaPlazaIniciacion(int id) throws InterruptedException{
		//Espera si ya hay 10 alumnos cursando esta parte
		iniciar_Inicializacion.acquire();
		mutex.acquire();
		
		//Mensaje a mostrar cuando el alumno pueda conectarse y cursar la parte de iniciacion
		System.out.println("PARTE INICIACION: Alumno " + id + " cursa parte iniciacion");
		alum_inic++;
		
		if(alum_inic<MAX_ALUMNOS_INI)iniciar_Inicializacion.release();
		mutex.release();
		
	}

	//El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public void finIniciacion(int id) throws InterruptedException{
		mutex.acquire();
		//Mensaje a mostrar para indicar que el alumno ha terminado la parte de principiantes
		System.out.println("PARTE INICIACION: Alumno " + id + " termina parte iniciacion");
		//Libera la conexion para que otro alumno pueda usarla
		alum_inic--;
		if(alum_inic==MAX_ALUMNOS_INI-1)iniciar_Inicializacion.release();
		
		
		
		mutex.release();
	}
	
	/* El alumno tendra que esperar:
	 *   - si ya hay un grupo realizando la parte avanzada
	 *   - si todavia no estan los tres miembros del grupo conectados
	 */
	public void esperaPlazaAvanzado(int id) throws InterruptedException{
		//Espera a que no haya otro grupo realizando esta parte
		iniciar_Avanzado.acquire();
		mutex.acquire();
		
		//Espera a que haya tres alumnos conectados
		
		//Mensaje a mostrar si el alumno tiene que esperar al resto de miembros en el grupo

		System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya " + ALUMNOS_AV + " alumnos");
		alum_avan++;
		
		if(alum_avan!=ALUMNOS_AV) {
			iniciar_Avanzado.release();
			
			mutex.release();
			proy_inicio.acquire();
			mutex.acquire();
		}
		
		alum_avan--;	
						
			//Mensaje a mostrar cuando el alumno pueda empezar a cursar la parte avanzada
		System.out.println("PARTE AVANZADA: Hay " + ALUMNOS_AV + " alumnos. Alumno " + id + " empieza el proyecto");
		if(alum_avan>0)proy_inicio.release();
		mutex.release();
		
	}
	
	/* El alumno:
	 *   - informa que ya ha terminado de cursar la parte avanzada 
	 *   - espera hasta que los tres miembros del grupo hayan terminado su parte 
	 */ 
	public void finAvanzado(int id) throws InterruptedException{
		//Espera a que los 3 alumnos terminen su parte avanzada
		mutex.acquire();
		alum_proy++;
		
		//Mensaje a mostrar si el alumno tiene que esperar a que los otros miembros del grupo terminen
		System.out.println("PARTE AVANZADA: Alumno " +  id + " termina su parte del proyecto. Espera al resto");
		if(alum_proy!=ALUMNOS_AV) {
			mutex.release();
			proy_fin.acquire();
			mutex.acquire();
		}else {
			//Mensaje a mostrar cuando los tres alumnos del grupo han terminado su parte
			System.out.println("PARTE AVANZADA: LOS " + ALUMNOS_AV + " ALUMNOS HAN TERMINADO EL CURSO");
		}
		
		alum_proy--;
		if(alum_proy>0)proy_fin.release();
		else {
			iniciar_Avanzado.release();
		}
		mutex.release();
		
		}
}
