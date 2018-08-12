package Controllers;

import Modelo.Usuarios.GestorDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

    public ModelAndView show(Request request, Response response) {

        return new ModelAndView(GestorDeUsuarios.getInstance().obtenerMapa(request), "home.hbs");

    }

}
