package Controllers;

import DB.Repositorios.RepositorioIndicadores;
import DB.Repositorios.RepositorioUsuarios;
import Modelo.Indicadores.GestorDeCreacionDeIndicadores;
import Modelo.Indicadores.Indicador;
import Modelo.Usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;

import Modelo.Usuarios.GestorDeUsuarios;

public class IndicadoresCreacionController {

    public ModelAndView show(Request request, Response response) {

        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") != null){
            return new ModelAndView(mapa, "indicadoresCreacion.hbs");
        } else {
            response.redirect("/login");
            return null;
        }
    }

    public Void redireccionarCreacion(Request request, Response response) {

        GestorDeCreacionDeIndicadores.getInstance().nombrar(request.queryParams("nombre"));

        response.redirect("/indicadores/creacion/" + request.queryParams("nombre"));

        return null;

    }

    public ModelAndView colocarOperando(Request request, Response response) {

        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") == null) {

            response.redirect("/login");
            return null;

        }

        mapa.put("formula", GestorDeCreacionDeIndicadores.getInstance().obtenerFormula());

        return new ModelAndView(mapa, "indicadoresCreacion_elegirOperando.hbs");

    }

    public Void operandoColocado(Request request, Response response) {

        response.redirect("/indicadores/creacion/" + request.params("nombre") + "/" + request.queryParams("cosaAOperar"));

        return null;

    }

    public ModelAndView colocarIndicador(Request request, Response response) {

        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") == null) {

            response.redirect("/login");
            return null;

        }

        mapa.put("formula", GestorDeCreacionDeIndicadores.getInstance().obtenerFormula());

        Usuario usuario = RepositorioUsuarios.getInstancia().buscarObjeto((String) mapa.get("email"));

        List<Indicador> indicadores = RepositorioIndicadores.getInstancia().buscarListaDeObjetosDeUsuario(usuario);

        mapa.put("indicadores", indicadores);

        return new ModelAndView(mapa, "indicadoresCreacion_elegirOperando_indicador.hbs");

    }

    public ModelAndView colocarCuenta(Request request, Response response) {
        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") == null) {

            response.redirect("/login");
            return null;

        }

        mapa.put("formula", GestorDeCreacionDeIndicadores.getInstance().obtenerFormula());

        return new ModelAndView(mapa, "indicadoresCreacion_elegirOperando_cuenta.hbs");
    }

    public ModelAndView colocarNumero(Request request, Response response) {
        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") == null) {

            response.redirect("/login");
            return null;

        }

        mapa.put("formula", GestorDeCreacionDeIndicadores.getInstance().obtenerFormula());

        return new ModelAndView(mapa, "indicadoresCreacion_elegirOperando_numero.hbs");
    }

    public Void redireccionarOperadorDesdeIndicador(Request request, Response response) {

        GestorDeCreacionDeIndicadores.getInstance().colocarIndicador(request.queryParams("nombre"));

        response.redirect("/indicadores/creacion/" + request.params("nombre") + "/operadores");

        return null;

    }

    public Void redireccionarOperadorDesdeCuenta(Request request, Response response) {

        GestorDeCreacionDeIndicadores.getInstance().colocarCuenta(request.queryParams("nombre"));

        response.redirect("/indicadores/creacion/" + request.params("nombre") + "/operadores");

        return null;

    }

    public Void redireccionarOperadorDesdeNumero(Request request, Response response) {

        GestorDeCreacionDeIndicadores.getInstance().colocarNumero(request.queryParams("numero"));

        response.redirect("/indicadores/creacion/" + request.params("nombre") + "/operadores");

        return null;

    }

    public ModelAndView mostrarOperadores(Request request, Response response) {

        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") == null) {

            response.redirect("/login");
            return null;

        }

        mapa.put("formula", GestorDeCreacionDeIndicadores.getInstance().obtenerFormula());

        return new ModelAndView(mapa, "indicadoresCreacion_elegirOperador.hbs");

    }

    public Void redireccionarOperadorElegido(Request request, Response response) {

        String accion = request.queryParams("operador");

        if(accion.equals("Crear")) {
            response.redirect("/indicadores/creacion/" + request.params("nombre") + "/creado");
            return null;

        }

        GestorDeCreacionDeIndicadores.getInstance().agregarOperador(request.queryParams("operador"));

        response.redirect("/indicadores/creacion/" + request.params("nombre"));

        return null;
    }

    public ModelAndView crearIndicador(Request request, Response response) {

        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") == null) {

            response.redirect("/login");
            return null;

        }

        mapa.put("formula", GestorDeCreacionDeIndicadores.getInstance().obtenerFormula());

        Usuario usuario = RepositorioUsuarios.getInstancia().buscarObjeto((String) mapa.get("email"));

        GestorDeCreacionDeIndicadores.getInstance().crearIndicador(usuario);

        return new ModelAndView(mapa, "indicadoresCreacion_creado.hbs");

    }
}
