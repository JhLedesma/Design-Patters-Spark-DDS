package DB.Proveedores;

import java.util.List;

import DB.TiposDeRepositorios.TipoDeRepositorio;

public interface Proveedor<T extends TipoDeRepositorio> {

	T darObjeto(String unNombre, String unTipo);

	List<T> darLista(String unTipo);

	List<String> darListaNombres(String unTipo);

	void agregar(T unObjeto);

	void agregarLista(List<T> listaObjetos);
	
	void modificar(T unObjeto);

	void eliminar(T unObjeto);

	T ejecutarQuery(Object query);

	List<T> EjecutarQueryReturnList(Object query) throws ClassNotFoundException;

	void eliminarConQuery(Object query);

	void refrescar();

	void sincronizar(T unObjeto);
}
