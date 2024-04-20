package Pg47_AlumnoDAO.CLASES;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO { // PARA SACAR LA INFORMACION DEL ALUMNO DE LA DB
    private static Connection conectar() { // ESTE SI QUE PUEDE SER PRIVATE PORQUE LO VAMOS A UTILIZAR DESDE DENTRO
        /*SE PUEDE HACER UNA TRY-CACTH EN VEZ DE HACE UN THROWS PARA HACER ALGO CON ESE ERROR
        SINO PONEMOS LA EXCEPCION HACEMOS ESO
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/instituto";
        con = DriverManager.getConnection(url, "root", "IESRibera23"); // SE PUEDE HACER DE ESTA MANERA
        return con;*/

        //USAMOS EL TRY-CATCH PARA PONER MENSAJE DE ERROR
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/instituto";
            con = DriverManager.getConnection(url, "root", "IESRibera23");
            System.out.println("Conectado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectarme a la base de datos " + e);
        }
        return con;
    }
                                    /** CREAR ALUMNOS **/
    public static int create(Alumno alumno) {
        int columnasAfectadas = -1;
        if (alumno != null) { // COMPROBAMOS QUE NO SEA NULO PORQUE SI NO SALE ERRORES
            Connection con = conectar(); // LLAMO AL METODO PARA CONECTARME A LA DB
            String sql = "INSERT INTO Alumnos (num, nombre, fnac, media, curso)" +
                    "VALUES (?, ?, ?, ?, ?)"; // CONSULTA PARA INSERTAR ALUMNO
            try {
                // TANTAS ? POR CADA DATO QUE QUIERAS AÑADIR (QUE PUEDE PONER DIRECTAMENTE VALUES Y NO PONER (...)
                PreparedStatement sentencia = con.prepareStatement(sql);
                sentencia.setInt(1, alumno.getId());
                sentencia.setString(2, alumno.getNombre());
                sentencia.setDate(3, new Date(alumno.getFechaNac().getTime()));
                sentencia.setDouble(4, alumno.getNotaMedia());
                sentencia.setString(5, alumno.getCurso());

                columnasAfectadas = sentencia.executeUpdate();

                con.close(); // QUE SE CIERRE LA DB DESPUES DE HACER LA SENTENCIA
            } catch (SQLException e) {
                System.out.println("Error al insertar " + e);
            }
        }
        return columnasAfectadas;
    }


                                    /** OBTENER ALUMNO **/
    public static Alumno obtener(int id) {
        Alumno alumno = null;
        String sql = "SELECT * FROM Alumnos WHERE num = ?";
        Connection con = conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet resultSet = sentencia.executeQuery();

            if(resultSet.next()){
                String nombre = resultSet.getString("nombre"); // ME DEVUELVE EL NOMBRE
                Date fechaNac = resultSet.getDate("fnac");
                double notaMedia = resultSet.getDouble("media");
                String curso = resultSet.getString("curso");
                alumno = new Alumno(id, nombre, fechaNac, notaMedia, curso);
            }
            con.close(); // CIERRO MI CONEXION

        } catch (SQLException e) {
            System.out.println("Error al obtener " + e);
        }
        return alumno;
    }

                                    /** ACTUALIZAR **/
    public static int actualizar(Alumno alumno){
        int columnasAfectadas = -1;
        if(alumno != null){
            String sql = "UPDATE Alumnos SET nombre = ?, fnac = ?, media = ?, curso = ? " +
                    "WHERE num = ?";
            try{
               Connection con = conectar();
               PreparedStatement sentencia = con.prepareStatement(sql);
               sentencia.setString(1, alumno.getNombre());
               sentencia.setDate(2, new Date(alumno.getFechaNac().getTime()));
               sentencia.setDouble(3, alumno.getNotaMedia());
               sentencia.setString(4, alumno.getCurso());
               sentencia.setInt(5, alumno.getId());

               columnasAfectadas = sentencia.executeUpdate();
               con.close();

            }catch (SQLException e){
                System.out.println("Error " + e);
            }
        }
        return columnasAfectadas;
    }

                                /** BORRAR **/
    public static int borrar(int id){
        int columnasAfectadas = -1;
        String sql = "DELETE FROM Alumnos WHERE num = ?";
        try{
            Connection con = conectar();
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);

            columnasAfectadas = sentencia.executeUpdate();
            con.close();

        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return columnasAfectadas;
    }

    public static List<Alumno> listar(){
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumnos";
        Alumno alumno;
        try{
            Connection con = conectar();
            PreparedStatement sentencia = con.prepareStatement(sql);
            ResultSet resultSet = sentencia.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("num");
                String nombre = resultSet.getString("nombre"); // ME DEVUELVE EL NOMBRE
                Date fechaNac = resultSet.getDate("fnac");
                double notaMedia = resultSet.getDouble("media");
                String curso = resultSet.getString("curso");
                alumno = new Alumno(id, nombre, fechaNac, notaMedia, curso);
                alumnos.add(alumno);
            }
        }catch (SQLException e){
            System.out.println("Error al listar " + e.getMessage());
        }
        return alumnos;
    }
}
