package Pg47_AlumnoDAO.CLASES;
import java.util.Date;
public class Alumno {
    /**ATRIBUTOS**/
    private int id;
    private String nombre;
    private Date fechaNac;
    private double notaMedia;
    private String curso;

    /**CONSTRUCTO**/
    public Alumno() {
    }
    public Alumno(int id, String nombre, Date fechaNac, double notaMedia, String curso) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.notaMedia = notaMedia;
        this.curso = curso;
    }

    /**GETTER Y SETTER**/
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setnombre(String nombre) {this.nombre = nombre;}
    public Date getFechaNac() {return fechaNac;}
    public void setFechaNac(Date fechaNac) {this.fechaNac = fechaNac;}
    public double getNotaMedia() {return notaMedia;}
    public void setNotaMedia(double notaMedia) {this.notaMedia = notaMedia;}
    public String getCurso() {return curso;}
    public void setCurso(String curso) {this.curso = curso;}

    /**TOSTRING**/
    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", notaMedia=" + notaMedia +
                ", curso='" + curso + '\'' +
                '}';
    }
}
