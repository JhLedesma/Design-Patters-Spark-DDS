import java.util.ArrayList;
import java.util.List;

public class Tarea {
private int id;
private String title;
private String description;
private List<Nota> grades = new ArrayList<Nota>();
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public List<Nota> getGrades() {
    return grades;
}
public void setGrades(List<Nota> grades) {
    this.grades = grades;
}

}