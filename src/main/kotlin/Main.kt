

fun main() {
    while (true) {
        println("Task (hide, show, exit):")
        when (readln()) {
            "exit" -> {
                println("Bye!")
                break
            }
            "hide" -> println("Hiding message in image.")
            "show" -> println("Obtaining message from image.")
            else -> println("Wrong task: [input String]")
        }
    }
}