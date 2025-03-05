package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val userId : Int,
    val id : Int,
    val title : String,
    val body : String
)

fun main() {

    //  crear cliente http
    val client = HttpClient.newHttpClient()

    // crear solicitud
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
        .GET()
        .build()

    //  Enviar la solicitud con el cliente
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    // obtener string con datos
    val jsonBody = response.body()

    // Deserializar el JSON a una lista de objetos User
    val users: List<Post> = Json.decodeFromString(jsonBody)

    //println(users)

    // Imprimir los usuarios con diversos campos
    println("Lista de Posts:")
    users.forEach { post ->
        println(
            "ID Usuario: ${post.userId}, ID Post: ${post.id}, Título: ${post.title}, Cuerpo: ${post.body}"
        )
    }

}