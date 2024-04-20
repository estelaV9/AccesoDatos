import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        // PRIMERO SE EJECUTA LA BASE DE DATOS EN MYSQL
        // SIEMPRE QUE VAYAMOS A USAR LA DB SIEMPRE IR A PROYECTSTRUCTURE > DEPENDENCIS > + > .JAR
        String url = "jdbc:mysql://localhost:3306/instituto"; //COMO SE LLAMA LA BD
        String username = "root";
        String password = "IESRibera23";
        Connection connection = DriverManager.getConnection(url, username, password);

        // REALIZAR UN SELECT
        /*Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM alumnos";
        ResultSet resultSet = statement.executeQuery(sqlQuery); // NO DEVUELVE NADA PERO NO TIENE QUE DAR ERROR

        // REALIZAR UN UPDATE / DELETE
        /* TEMA 16 : PAGINA 42    EJEMPLO INSTITUTO NOTA MEDIA
        A partir de la base de datos creada con el archivo .sql.
        Incrementar la nota media en un punto a todos los alumnos del curso "1B".
        Usar una consulta SQL que haga uso de UPDATE.*

        Statement statementUpdate = connection.createStatement();
        String sqlUpdate = "UPDATE alumnos SET media = media + 1 WHERE curso = '1B'";
        int rowsAfectedUpdate = statementUpdate.executeUpdate(sqlUpdate); // PARA UPDATE ES executeUpdate
                                                                // Y CAMBIAMOS EL ResultSet por INT
        System.out.println("COLUMNAS AFECTADAS : " + rowsAfectedUpdate); //COLUMNAS AFECTADAS : 3


        /**SQL INJECTION --> CUANDO INTERACTUAMOS CON EL USUAIRO PUEDE PONER LO QUE QUIERA COMO EL '1' = '1 Y
         * BORRAR TODOS LOS DATOS YA QUE DEJAMOS AL USUARIO LA OPCION DE ESCRIBIR Y NOS PUEDE BORRAR TODA LA DB
        // main
        Scanner reader = new Scanner(System.in);
        System.out.println("Escribe el nombre : ");
        String nombre = reader.nextLine(); // PONEMOS nextLine PARA QUE COJA EL APELLIDO
        Statement statementDelete = connection.createStatement();
        String sqlDelete = "DELETE FROM alumnos WHERE nombre = '" + nombre + "'";
        int rowsAfectedDelete = statementDelete.executeUpdate(sqlDelete);
        System.out.println("COLUMNAS AFECTADAS : " + rowsAfectedDelete);

        // JUAN' or '1' = '1 ---> ESTO SIEMPRE SE CUMPLE ASIQUE UN HACKER PUEDE ELIMINAR TODOS LOS DATOS

                /**PARA EVITAR EL SQL INJECTION USAMOS prepareStatement
        String sql = "SELECT nombre, media FROM alumnos WHERE curso = '?'"; //LA CONSULTA VA ARRIBA
        PreparedStatement statementInjection = connection.prepareStatement(sql); //SE PONE prepareStatement
        statementInjection.setString(1, nombre); // EL 1 REFERENCIA AL ORDEN DEL ?
        int rowsAfectedInjection = statement.executeUpdate(sql);
        System.out.println("Columnas afectadas : " + rowsAfectedInjection);*/




        /**RESULSET --> SE PARECE A UN ITERADOR. **/
        /*PAGINA 52: EJEMPLO INSTITUTO MOSTRAR ALUMNOS NOTA CORTE
        A partir de la base de datos creada con el archivo .sql.
        Escribir un programa que muestre todos los alumnos de un curso cuya nota es mayor que cierta nota de
        corte. Tanto el curso como la nota de corte será introducido por el usuario.*/

        String curso = "1B";
        double notaCorte = 5.0;
        String sqlResulset = "SELECT nombre, media FROM Alumnos WHERE curso" +
                " = ? AND media > ?";
        PreparedStatement statementResulset =
                connection.prepareStatement(sqlResulset);
        statementResulset.setString(1, curso);
        statementResulset.setDouble(2, notaCorte);
        ResultSet resultSetResulset = statementResulset.executeQuery();

        // RECORRER EL BUCLE PARA MOSTRAR LOS DATOS DE ESA CONSULTA
        System.out.println("*************ALUMNOS DE 1B CON MAS DE UN 5**************");
        while(resultSetResulset.next()){
            System.out.println("Nombre del alumno : " + resultSetResulset.getString(1)
                    + " con una media de "  + resultSetResulset.getString("media")
                    + "\n----------------------------------------------------------");
        }

    }
}