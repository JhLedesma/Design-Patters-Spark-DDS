package Modelo.Indicadores;

import DB.TiposDeRepositorios.TipoDeRepositorio;
import java.math.BigDecimal;

public class Precalculado implements TipoDeRepositorio {

    private long idUsuario;

    private long idIndicador;

    private long idEmpresa;

    private long idPeriodo;

    double valor;

    public Precalculado(long idUsuario, long idIndicador,long idEmpresa, long idPeriodo, BigDecimal valor){
        this.idUsuario = idUsuario;

        this.idIndicador = idIndicador;

        this.idEmpresa = idEmpresa;

        this.idPeriodo = idPeriodo;

        this.valor = valor.doubleValue();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }



    public long getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(long idIndicador) {
        this.idIndicador = idIndicador;
    }


    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    public long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }


    @Override
    public String getNombre() {
        return null;
    }
}
