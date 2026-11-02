
fun main() {

    val conn = ConexionBD.getConnection()
    if (conn != null) {
        println("Conectado a la BD correctamente.")
        ConexionBD.closeConnection(conn)
    }

    // Listar videojuegos
    println("Lista de videojuegos:")
    VideojuegoDAO.listarVideojuegos().forEach {
        println(" - [${it.id}] ${it.titulo} (${it.genero}), año: ${it.anio}, nota: ${it.nota}")
    }

    // Consultar videojuego por ID
    val videojuego = VideojuegoDAO.consultarPorId(3)
    if (videojuego != null) {
        println("Videojuego encontrado: [${videojuego.id}] ${videojuego.titulo} (${videojuego.genero}), año: ${videojuego.anio}, nota: ${videojuego.nota}")
    } else {
        println("No se encontró ningún videojuego con ese ID.")
    }

    // Insertar videojuego nuevo
    VideojuegoDAO.insertarVideojuego(
        Videojuego(
            titulo = "Elden Ring",
            genero = "Souls-like",
            anio = 2022,
            nota = 9.6
        )
    )

    // Actualizar videojuego
    VideojuegoDAO.actualizarVideojuego(
        Videojuego(
            id = 1,
            titulo = "The Legend of Zelda: Breath of the Wild",
            genero = "Aventura",
            anio = 2017,
            nota = 8.5
        )
    )

    // Eliminar videojuego con ID 2
    VideojuegoDAO.eliminarVideojuego(2)
}
