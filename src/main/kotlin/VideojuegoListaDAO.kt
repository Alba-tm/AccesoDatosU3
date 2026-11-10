import java.sql.Connection
import java.sql.SQLException

fun anyadirALista(idLista: Int, idVideojuego: Int, fecha: String) {
    var conn: Connection? = null
    try {
        conn = ConexionBD.getConnection()
        conn?.autoCommit = false

        val sql = "SELECT id FROM videojuegos WHERE id = ?"

        conn?.prepareStatement(sql).use { stmt ->
            stmt?.setInt(1, idVideojuego)

            stmt?.executeQuery().use { rs ->
                rs?.next()?.let {
                    if (!it) {
                        throw SQLException("El videojuego no existe.")
                    }
                }
            }
        }

        conn?.prepareStatement("INSERT INTO listas_videojuegos(id_lista, id_videojuego, fecha_añadido) VALUES (?, ?, ?)")
        conn?.prepareStatement(sql).use { stmt ->
            stmt?.setInt(1, idLista)
            stmt?.setInt(2, idVideojuego)
            stmt?.setString(3, fecha)
            stmt?.executeUpdate()
        }  

        conn?.commit()
        println("Videojuego añadido a la lista correctamente.")

    } catch (e: SQLException) {
        conn?.rollback()
        println("Error en la transacción: ${e.message}")
        println("Se han deshecho los cambios.")

    } finally {
        conn?.autoCommit = true
        conn?.close()
    }
}