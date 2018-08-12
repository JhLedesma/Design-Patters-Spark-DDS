package Modelo.Indicadores;

import DB.Repositorios.RepositorioIndicadores;
import Modelo.Usuarios.Usuario;

import java.math.BigDecimal;

public class GestorDeCreacionDeIndicadores {
    private static GestorDeCreacionDeIndicadores ourInstance = new GestorDeCreacionDeIndicadores();

    public static GestorDeCreacionDeIndicadores getInstance() {
        return ourInstance;
    }

    private GestorDeCreacionDeIndicadores() {
    }

    IndicadorBuilder indicadorBuilder = new IndicadorBuilder();

    public void nombrar(String nombre) {

        this.indicadorBuilder.setNombreIndicador(nombre);

    }

    public void colocar(Expresion expresion) {

        Expresion nuevaExpresion = this.indicadorBuilder.getOperandoAnterior();

        if(nuevaExpresion != null)
            nuevaExpresion.addOperando(expresion);
        else
            nuevaExpresion = expresion;

        this.indicadorBuilder.setOperandoAnterior(nuevaExpresion);

    }

    public void colocarIndicador(String nombre) {

        this.colocar(RepositorioIndicadores.getInstancia().buscarObjeto(nombre));

    }

    public void colocarCuenta(String nombre) {

        this.colocar(new Cuenta_Indicadores(nombre));

    }

    public void colocarNumero(String numero) {

        this.colocar(new Numero(new BigDecimal(numero)));

    }

    public void agregarOperador(String operadorAAsignar) {

        Operacion operador;

        Expresion expresion = this.indicadorBuilder.getOperandoAnterior();

        switch (operadorAAsignar) {

            case "+":
                operador = new Suma(expresion);
                break;

            case "-":
                operador = new Resta(expresion);
                break;

            case "*":
                operador = new Multiplicacion(expresion);
                break;

            case "/":
                operador = new Division(expresion);
                break;

            default:
                throw new RuntimeException("No se puede construir"); // Hacer excepcion posta

        }

        this.indicadorBuilder.setOperandoAnterior(operador);

    }

    public String obtenerFormula() {

        return this.indicadorBuilder.imprimirFormula();

    }

    public void crearIndicador(Usuario usuario) {

        this.indicadorBuilder.crearIndicador(this.indicadorBuilder.getOperandoAnterior(), usuario);

        this.indicadorBuilder = new IndicadorBuilder();

    }
}
