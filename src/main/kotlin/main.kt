import java.net.*
import java.util.*

fun main() {

    // Customization
    val proxyHost = "000.000.000.000"
    val proxyPort = 8080

    // Entering login and password
    val scanner = Scanner(System.`in`)
    print("Entering login for proxy: ")
    val proxyUser = scanner.nextLine()
    print("Entering password for proxy: ")
    val proxyPassword = scanner.nextLine()

    // Creating a proxy object specifying the proxy type and its address
    val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(proxyHost, proxyPort))

    // Creating an authentication object for a proxy
    val authenticator = object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(proxyUser, proxyPassword.toCharArray())
        }
    }

    // Setting the default authentication
    Authenticator.setDefault(authenticator)

    // Opening a connection using a proxy
    val connection = URL("http://www.example.com").openConnection(proxy) as HttpURLConnection

    // Setting the request method (for example, GET)
    connection.requestMethod = "GET"

    // Getting a response
    val responseCode = connection.responseCode
    println("Response Code: $responseCode")

    // Reading the response
    val bufferedReader = connection.inputStream.bufferedReader()
    val response = bufferedReader.use { it.readText() }
    println("Response:\n$response")

    // Closing the connection
    connection.disconnect()
}