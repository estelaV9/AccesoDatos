package Ejer_JDBC1.modelo;

import java.util.Date;

public class Libro {
                                    /** ATRIBUTOS **/
    private int isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private Date anioPublicacion;
    private int numPag;
    private String genero;
                                  /** CONSTRUCTOR **/
    public Libro() {
    }
    public Libro(int isbn, String titulo, String autor, String editorial, Date anioPublicacion, int numPag, String genero) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.numPag = numPag;
        this.genero = genero;
    }
                                /** GETTER Y SETTER **/
    public int getIsbn() { return isbn; }
    public void setIsbn(int isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }
    public Date getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(Date anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public int getNumPag() { return numPag; }
    public void setNumPag(int numPag) { this.numPag = numPag; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }


                                /** TO_STRING **/
    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", numPag=" + numPag +
                ", genero='" + genero + '\'' +
                '}';
    }
}
