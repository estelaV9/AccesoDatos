# AccesoDatos.
## Introducción.
La mayoría de las aplicaciones, tras recoger una serie de datos, los procesan y generan nuevos datos. Estos datos necesitan ser almacenados de forma permanente para su posterior uso. Existen diferentes opciones para esto:
 - Ficheros
 - Base de datos

### API JDBC.
Las APIs (Interfaces de Programación de Aplicaciones) de JDBC (Java Database Connectivity) son un conjunto de clases e interfaces en Java que proporcionan métodos para acceder y manipular datos almacenados en una base de datos relacional. JDBC permite a los desarrolladores escribir aplicaciones en Java que pueden interactuar con bases de datos a través del estándar SQL. Las principales son :
 - _**java.sql.DriverManager**_ : permite manipular distintos drivers. Con cada driver se puede acceder a un SGBD distinto.
 - _**java.sql.Connection**_ : Esta interfaz representa una conexión con una base de datos. Permite establecer conexiones con la base de datos, crear objetos de declaración y administrar transacciones.
 - _**java.sql.Statement**_ : Esta interfaz se utiliza para ejecutar declaraciones SQL simples sin parámetros. Es útil para realizar operaciones como consultas, actualizaciones, inserciones o eliminaciones en la base de datos.
 - _**java.sql.PreparedStatement**_ : Similar a Statement, pero precompila la sentencia SQL antes de ejecutarla. Esto puede mejorar el rendimiento cuando se ejecuta la misma sentencia varias veces con diferentes valores de parámetros.
 - _**java.sql.ResultSet**_ : Esta interfaz representa un conjunto de resultados de una consulta SQL. Permite navegar a través de los registros devueltos por una consulta y recuperar los valores de las columnas.

### Conexión.
Antes de interactuar con una base de datos, es fundamental establecer una conexión entre nuestra aplicación y el Sistema de Gestión de Bases de Datos (SGBD). La conexión debe mantenerse abierta mientras se necesite y cerrarse adecuadamente cuando ya no se requiera.
- **Método para Establecer la Conexión.**
Para crear una conexión, utilizamos el método estático _**DriverManager.getConnection()**_. Este método toma la URL de la base de datos, el nombre de usuario y la contraseña como parámetros y devuelve un objeto Connection.
```java
private static Connection conectar() { 
    Connection con = null; // SE INICIALIZA EN NULL
    try {
        String url = "jdbc:mysql://localhost:3306/_nombre de la base de datos_";
        con = DriverManager.getConnection(url, "_nombre del usuario_", "_contraseña del usuario_");
        System.out.println("Conectado correctamente"); // MENSAJE PARA CONFIRMAR QUE SE HA CONECTADO CORRECTAMENTE
    } catch (SQLException e) {
        System.out.println("Error al conectarme a la base de datos " + e);
    }
    return con;
}
```

- **Manejo de excepciones.**
Es importante capturar y manejar adecuadamente las excepciones que pueden ocurrir durante la conexión. En el ejemplo proporcionado, se utiliza un bloque try-catch para manejar cualquier posible error. <br>
También se puede optar por declarar que el método conectar() lanza una excepción SQLException, lo que permite manejar la excepción en un nivel superior, es decir, que el método no se encarga de manejar la excepción internamente, sino que la pasa a la parte del código que llama a ese método.
```java
private static Connection conectar() throws SQLException { 
    Connection con = null;
    try {
        String url = "jdbc:mysql://localhost:3306/_nombre de la base de datos_";
        con = DriverManager.getConnection(url, "_nombre del usuario_", "_contraseña del usuario_");
        System.out.println("Conectado correctamente"); // MENSAJE PARA CONFIRMAR QUE SE HA CONECTADO CORRECTAMENTE
    } catch (SQLException e) {
        System.out.println("Error al conectarme a la base de datos " + e);
    }
    return con;
}
```
- **Cerrar conexión.**
Cuando ya no se requiere la conexión, se cierra.
```java
Connection con = conectar(); // LLAMO AL MÉTODO PARA CONECTARME A LA DB
con.close(); // CIERRO LA CONEXIÓN 
```

### Ejecución de sentencias  .
- Ejecución de consultas : **SELECT**.
Primero se crea un Statement con _**createStatement**_, y después, el método _**executeQuery()**_ de Statement ejecuta una consulta y devuelve el resultado de esta mediante un objeto de tipo _**ResulSet**_.
```java
 Statement statement = connection.createStatement(); // STATEMENT
 String sqlQuery = "SELECT * FROM _mytable_"; // GUARDAMOS EN UN STRING LA CONSULTA QUE QUEREMOS HACER
 ResultSet resultSet = statement.executeQuery(sqlQuery) // SE EJECUTA Y DEVUELVE LA CONSULTA 
```
- Ejecución de sentencias : **INSERT, UPDATE, DELETE**.
Estas ejecuciones no devuelven un resultado, sino que realizan una operación.
Primero se crea un Statement con _**createStatement**_, y después, el método _**executeUpdate()**_ de Statement devuelve el número de filas que se han modificado.
```java
 Statement statement = connection.createStatement(); // STATEMENT
 String sqlUpdate = "UPDATE _mytable_ SET _column1_ = 'new value' WHERE _id_ = 1";
 int rowsUpdated = statement.executeUpdate(sqlUpdate) // NOS DEVUELVE EL NUMERO DE FILAS QUE HAN SIDO MODIFICADAS
```





