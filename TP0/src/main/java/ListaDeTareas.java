import java.util.ArrayList;
import java.util.List;

public class ListaDeTareas {
private List<Tarea> assignments = new ArrayList<Tarea>();

public List<Tarea> getAssignments() {
    return assignments;
}

public void setAssignments(List<Tarea> assignments) {
    this.assignments = assignments;
}

}