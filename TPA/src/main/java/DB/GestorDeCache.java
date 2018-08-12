package DB;

import Archivo.CargaBatch.EmpresaToken;
import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioPrecalculados;
import Observers.*;
import com.mongodb.client.model.Filters;

public class GestorDeCache implements ObserverModificacionEmpresa{

    private static GestorDeCache ourInstance = new GestorDeCache();

    public static GestorDeCache getInstance() {
        return ourInstance;
    }

    private GestorDeCache() {

    }

   /* public void eliminarEmpresa(String nombreEmpresa) {

        long idEmpresa = RepositorioEmpresas.getInstancia().buscarObjeto(nombreEmpresa).getId();

        RepositorioPrecalculados.getInstancia().deteleByQuery(Filters.eq("idEmpresa",idEmpresa));

    }*/


	@Override
	public void update(EmpresaToken token) {

        long idEmpresa = token.getIdEmpresa();
        long idPeriodo = token.getIdPeriodo();


        RepositorioPrecalculados.getInstancia().deteleByQuery(Filters.and(Filters.eq("idEmpresa",idEmpresa),Filters.eq("idPeriodo",idPeriodo)));

        System.out.println(idEmpresa);
        System.out.println(idPeriodo);

		
	}
}
