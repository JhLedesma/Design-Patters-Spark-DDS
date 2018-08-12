package Archivo.CargaBatch.Cargadores;

import Archivo.CargaBatch.EmpresaToken;
import Observers.NotificadorModificacionEmpresa;

public class CargadorDeRespositorioNotificador extends CargadorDeRepositorio {
	
	@Override
	public void realizarModificacion(EmpresaToken token) {

		super.realizarModificacion(token);

		NotificadorModificacionEmpresa.getInstance().notificarObservadores(token);

	}
}
