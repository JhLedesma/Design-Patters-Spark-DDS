import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

@Observable
public class Alumno { // De todos estos atributos hay que eliminar los que sean
						// calculables, no olvidar que son provisorios

	private AlumnoJson alu;
	private String token;
	private String asignacion;
	private List<String> asignaciones;
	private List<String> notasDeAsignacion;
	public int legajo;
	public String nombre;
	public String apellido;
	public String ghu;
	private List<Tarea> tareas = new ArrayList<Tarea>();

	public void llenarDatos() {
		alu = RequestService.obtenerDatosDeAlumno(token);
		setLegajo(alu.getCode());
		;
		setNombre(alu.getFirst_name());
		setApellido(alu.getLast_name());
		setGhu(alu.getGithub_user());
		this.setTareas();
		this.setAsignaciones();

	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getGhu() {
		return ghu;
	}

	public void setGhu(String ghu) {
		this.ghu = ghu;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getNotasDeAsignacion() {
		return notasDeAsignacion;
	}

	public void setNotasDeAsignacion(List<String> notas) {
		this.notasDeAsignacion = notas;
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setAsignaciones() {
		asignaciones = this.obtenerNombreDeAsignaciones();
	}

	public List<String> getAsignaciones() {
		return asignaciones;

	}

	public void setTareas() {
		this.tareas = RequestService.obtenerNotasDeAlumno(token).getAssignments();
	}

	public List<String> obtenerNombreDeAsignaciones() {
		return tareas.stream().map(tarea -> tarea.getTitle()).collect(Collectors.toList());
	}

	public void obtenerNotasDeAsignacion(String nombre) {
		int index = this.obtenerNombreDeAsignaciones().indexOf(nombre);
		setNotasDeAsignacion(
				tareas.get(index).getGrades().stream().map(nota -> nota.getValue()).collect(Collectors.toList()));
	}

}