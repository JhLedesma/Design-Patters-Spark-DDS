package Archivo.CargaBatch.Cargadores;

import Archivo.CargaBatch.EmpresaToken;
import DB.Repositorios.RepositorioEmpresas;
import Modelo.Empresa.Empresa;

public class CargadorDeRepositorio extends Cargador {
	@Override
	public boolean existeLaCarga(EmpresaToken token) {
		return RepositorioEmpresas.getInstancia().existeObjeto(token.getNombreEmpresa());
	}

	@Override
	public void realizarAlta(EmpresaToken token) {
		RepositorioEmpresas.getInstancia().agregarObjeto(token.generarEmpresa());
	}

	@Override
	public void realizarModificacion(EmpresaToken token) {
		Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto(token.getNombreEmpresa());
		
		empresa.actualizar(token);
		
		token.obtenerIdentidad(empresa);
	}

}
