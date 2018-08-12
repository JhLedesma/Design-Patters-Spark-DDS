package Observers;

import Archivo.CargaBatch.EmpresaToken;

import java.util.List;

public abstract class TipoDeNotificador {

    public void notificarObservadores(EmpresaToken token, List<ObserverModificacionEmpresa> observadores){

        observadores.forEach(observer -> observer.update(token));

    }

}
