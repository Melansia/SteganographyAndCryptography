import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.IIOException
import javax.imageio.ImageIO

class SteganographyAndCryptography {

    private lateinit var image: BufferedImage
    private lateinit var encoded: List<Int>
    private val keyBytesToStop = "000000000000000000000011"
    private val marker = "\u0000\u0000\u0003"
    fun start() {
        while (true) {
            println("Task (hide, show, exit):")
            when (readln()) {
                "exit" -> { println("Bye!"); break }
                "hide" -> hide()
                "show" -> decodeMessage()
                else -> println("Wrong task: [input String]")
            }
        }
    }

    private fun hide() {
        println("Input image file: ")
        val inputFileName = readln()
        println("Output image file: ")
        val outputFileName = readln()

        hideMessage()
        val message = encoded

        try {
            val imageFile = File(inputFileName)
            image = ImageIO.read(imageFile)
            val leastSignificantBits = mutableListOf<Int>()

            for (i in 0 until image.height){
                for (j in 0 until image.width){
                    val pixelColor = Color(image.getRGB(j, i))
                    leastSignificantBits += pixelColor.blue
                }
            }

            if (message.size > leastSignificantBits.size) {
                return println("The input image is not large enough to hold this message.")
            }

            for (i in message.indices){
                if (leastSignificantBits[i] % 2 == 0) {
                    if (message[i] == 1) leastSignificantBits[i] = leastSignificantBits[i] or 1
                } else {
                    if (message[i] == 0) leastSignificantBits[i] = (leastSignificantBits[i] xor 1)
                }
            }

            for (i in 0 until image.height) {
                for (j in 0 until image.width) {
                    val pixelColor = Color(image.getRGB(j, i))
                    image.setRGB(j, i, Color(pixelColor.red, pixelColor.green, leastSignificantBits[i*image.width + j]).rgb)
                }
            }
        } catch (e: IIOException) {
            return println("Can't read input file!")
        }
        saveImage(image, outputFileName)
        println("Image ${outputFileName.split("/").last()} is saved.")
    }

    private fun hideMessage() {
        val encoding = mutableListOf<String>()
        println("Message to hide:")
        val message = readln()
        val markerToMessage = "$message$marker"

        markerToMessage.toByteArray().forEach { encoding += it.toString(2).padStart(8, '0') }
        encoded = encoding.joinToString("").map { it.digitToInt() }
    }

    private fun decodeMessage() {
        println("Input image file:")
        val image = ImageIO.read(File(readln()))
        println("Message:")
        val messageAllBits = mutableListOf<Int>()

        for (i in 0 until image.height) {
            for (j in 0 until image.width) {
                val pixelColor = Color(image.getRGB(j, i))
                messageAllBits += (pixelColor.blue).toString(2).takeLast(1).toInt()
            }
        }
        messageAllBits.joinToString("").split(keyBytesToStop).first().chunked(8).forEach { print(it.toInt(2).toChar()) }
        println()
    }
    private fun saveImage(image: BufferedImage, imageFile: String) {
        ImageIO.write(image, "png", File(imageFile))
    }
}

