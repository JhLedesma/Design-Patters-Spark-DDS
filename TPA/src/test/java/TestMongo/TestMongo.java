package TestMongo;
/*
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mongodb.client.model.Filters;
*/
import DB.MongoDBManager;
/*import DB.Proveedores.ProveedorBD;
import DB.Proveedores.ProveedorMongoDB;
import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioIndicadores;
import DB.Repositorios.RepositorioMetodologias;
import DB.Repositorios.RepositorioPrecalculados;
import DB.Repositorios.RepositorioUsuarios;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;
import Modelo.Indicadores.Indicador;
import Modelo.Indicadores.Precalculado;
import Modelo.Metodologias.Metodologia;
import Modelo.Usuarios.Usuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)*/
@Deprecated
public class TestMongo extends MongoDBManager {
/*
    @Before
    public void iniciarRepos(){
        RepositorioUsuarios.getInstancia().setProveedor(new ProveedorBD<Usuario>());
        RepositorioEmpresas.getInstancia().setProveedor(new ProveedorBD<Empresa>());
        RepositorioIndicadores.getInstancia().setProveedor(new ProveedorBD<Indicador>());
        RepositorioMetodologias.getInstancia().setProveedor(new ProveedorBD<Metodologia>());
        RepositorioPrecalculados repo = RepositorioPrecalculados.getInstancia();
        RepositorioPrecalculados.getInstancia().setProveedor(new ProveedorMongoDB<>(repo));
    }
    @Test
    public void a1agregarPrecalculado(){
        Usuario usuario = RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com");
        Indicador indicador = RepositorioIndicadores.getInstancia().buscarObjeto("ArrorROE");
        Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Feel-Fort");
        Periodo periodo = empresa.buscarPeriodo(2006);
        Precalculado p = new Precalculado(usuario.getId(),indicador.getId(),empresa.getId(),periodo.getId(), new BigDecimal(50));
        RepositorioPrecalculados.getInstancia().agregarObjeto(p);
    }
    @Test
    public void a2buscarPrecalculadoconQuery(){
        long a = 1;
        Precalculado p = RepositorioPrecalculados.getInstancia().buscarObjetoPorQuery(Filters.eq("idUsuario", a));
        long u = RepositorioUsuarios.getInstancia().buscarObjeto("axel@bags.com").getId();
        long i = RepositorioIndicadores.getInstancia().buscarObjeto("ArrorROE").getId();
        Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto("Feel-Fort");
        long e = empresa.getId();
        long periodo = empresa.buscarPeriodo(2006).getId();
        Precalculado p2 = new Precalculado(u,i,e,periodo,new BigDecimal(50));
        assertEquals(p.getIdUsuario(),p2.getIdUsuario());
        assertEquals(p.getIdIndicador(),p2.getIdIndicador());
        assertEquals(p.getIdEmpresa(), p2.getIdEmpresa());
        assertEquals(p.getIdPeriodo(),p2.getIdPeriodo());
    }
    @Test
    public void a3eliminarPorQuery(){
        long a = 1;
        double b = 50;
        eliminarPorQuery("Precalculado", Filters.and(Filters.eq("idUsuario", a), Filters.eq("valor", b)));
    }
*/
}