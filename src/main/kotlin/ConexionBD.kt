import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object ConexionBD {
    private val dbPath = "datos/videojuegos.sqlite"
    private val dbFile = File(dbPath)
    private val urlSqlite = "jdbc:sqlite:${dbFile.absolutePath}"

    private const val HOST = "localhost"
    private const val PORT = 5432
    private const val DATABASE = "videojuegos"
    private const val USER = "postgres"
    private const val PASSWORD = "qwerty2"
    private val urlPostgres = "jdbc:postgresql://$HOST:$PORT/$DATABASE"

    fun getConnectionSqlite(): Connection? {
        return try {
            DriverManager.getConnection(urlSqlite)
        } catch (e: SQLException) {
            println("Error al conectar con la base de datos SQLite: ${e.message}")
            null
        }
    }

    fun getConnectionPostgres(): Connection? {
        return try {
            DriverManager.getConnection(urlPostgres, USER, PASSWORD)
        } catch (e: SQLException) {
            println("Error al conectar con la base de datos PostgreSQL: ${e.message}")
            null
        }
    }

    fun testConnectionSqlite(): Boolean {
        return getConnectionSqlite()?.use {
            println("Conexión establecida con éxito a ${dbFile.absolutePath}")
            true
        } ?: false
    }

    fun testConnectionPostgres(): Boolean {
        return getConnectionPostgres()?.use {
            println("Conexión establecida con éxito a PostgreSQL ($DATABASE)")
            true
        } ?: false
    }

    fun closeConnection(conn: Connection?) {
        try {
            conn?.close()
            println("Conexión cerrada correctamente.")
        } catch (e: SQLException) {
            println("Error al cerrar la conexión: ${e.message}")
        }
    }
}