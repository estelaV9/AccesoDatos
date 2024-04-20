package Ejer_JDBC1.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private static Connection conectar() {
        //USAMOS EL TRY-CATCH PARA PONER MENSAJE DE ERROR
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/Libreria";
            con = DriverManager.getConnection(url, "root", "IESRibera23");
            System.out.println("Conectado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectarme a la base de datos " + e);
        }
        return con;
    }
                        /** CREAR LIBROS **/
    public static int create(Libro libro) {
        int columnasAfectadas = -1;
        if (libro != null) { // COMPROBAMOS QUE NO SEA NULO PORQUE SI NO SALE ERRORES
            Connection con = conectar();
            String sql = "INSERT INTO Libros " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement sentencia = con.prepareStatement(sql);
                sentencia.setInt(1, libro.getIsbn());
                sentencia.setString(2, libro.getTitulo());
                sentencia.setString(3, libro.getAutor());
                sentencia.setString(4, libro.getEditorial());
                sentencia.setDate(5, new Date(libro.getAnioPublicacion().getTime()));
                sentencia.setInt(6, libro.getNumPag());
                sentencia.setString(7, libro.getGenero());

                columnasAfectadas = sentencia.executeUpdate();

                con.close();
            } catch (SQLException e) {
                System.out.println("Error al insertar " + e);
            }
        }
        return columnasAfectadas;
    }

        /** OBTENER LIBROS **/
    public static Libro obtener(int isbn) {
        Libro libro = null;
        String sql = "SELECT * FROM Libros WHERE isbn = ?";
        Connection con = conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, isbn);
            ResultSet resultSet = sentencia.executeQuery();

            if(resultSet.next()){
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String editorial = resultSet.getString("editorial");
                Date anioPubli = resultSet.getDate("anioPublicacion");
                int numPaginas = resultSet.getInt("numPag");
                String genero = resultSet.getString("genero");
                libro = new Libro(isbn, titulo, autor, editorial, anioPubli, numPaginas, genero);
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener " + e);
        }
        return libro;
    }

    /** ACTUALIZAR **/
    public static int actualizar(Libro libro){
        int columnasAfectadas = -1;
        if(libro != null){
            String sql = "UPDATE Libros SET titulo = ?, autor = ?, editorial = ?, anioPublicacion = ?," +
                    " numPag = ?, genero = ? " +
                    "WHERE isbn = ?";
            try{
                Connection con = conectar();
                PreparedStatement sentencia = con.prepareStatement(sql);
                sentencia.setString(1, libro.getTitulo());
                sentencia.setString(2, libro.getAutor());
                sentencia.setString(3, libro.getEditorial());
                sentencia.setDate(4, new Date(libro.getAnioPublicacion().getTime()));
                sentencia.setInt(5, libro.getNumPag());
                sentencia.setString(6, libro.getGenero());
                sentencia.setInt(7, libro.getIsbn());

                columnasAfectadas = sentencia.executeUpdate();
                con.close();

            }catch (SQLException e){
                System.out.println("Error " + e);
            }
        }
        return columnasAfectadas;
    }

    /** BORRAR **/
    public static int borrar(int isbn){
        int columnasAfectadas = -1;
        String sql = "DELETE FROM Libros WHERE isbn = ?";
        try{
            Connection con = conectar();
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, isbn);

            columnasAfectadas = sentencia.executeUpdate();
            con.close();

        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return columnasAfectadas;
    }

    public static List<Libro> listar(){
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libros";
        Libro libro;
        try{
            Connection con = conectar();
            PreparedStatement sentencia = con.prepareStatement(sql);
            ResultSet resultSet = sentencia.executeQuery();
            while(resultSet.next()){
                int isbn = resultSet.getInt("isbn");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String editorial = resultSet.getString("editorial");
                Date anioPubli = resultSet.getDate("anioPublicacion");
                int numPaginas = resultSet.getInt("numPag");
                String genero = resultSet.getString("genero");
                libro = new Libro(isbn, titulo, autor, editorial, anioPubli, numPaginas, genero);
                libros.add(libro);
            }
        }catch (SQLException e){
            System.out.println("Error al listar " + e.getMessage());
        }
        return libros;
    }

    /*LISTAR TODOS LOS LIBROS QUE TENGAN EL MISMO TITULO*/
    public static List<Libro> listarNombres( String nombre){
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libros WHERE titulo = ?";
        Libro libro;
        try{
            Connection con = conectar();
            PreparedStatement sentencia = con.prepareStatement(sql);
            ResultSet resultSet = sentencia.executeQuery();
            while(resultSet.next()){
                sentencia.setString(1, nombre);
                con.close();
            }
        }catch (SQLException e){
            System.out.println("Error al listar " + e.getMessage());
        }
        return libros;
    }

}
