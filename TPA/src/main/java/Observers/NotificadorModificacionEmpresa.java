package Observers;
import Archivo.CargaBatch.EmpresaToken;

import java.util.LinkedList;
import java.util.List;

public class NotificadorModificacionEmpresa {

	public static NotificadorModificacionEmpresa instance;

	private TipoDeNotificador tipoDeNotificador = new TipoPosta();
	
	private List<ObserverModificacionEmpresa> observadores = new LinkedList<>();

	public static NotificadorModificacionEmpresa getInstance() {

		if (instance == null){

			instance = new NotificadorModificacionEmpresa();



			return instance;

		}

		else {

			return  instance;
		}
	}


	public void notificarObservadores(EmpresaToken token) {
		tipoDeNotificador.notificarObservadores(token,observadores);
	}

	public void agregarObservador(ObserverModificacionEmpresa observer) {

		observadores.add(observer);

	}
	
}