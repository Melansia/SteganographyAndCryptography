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
                "exit" -> {
                    println("Bye!"); break
                }
                "hide" -> hide()
                "show" -> show()
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
        val encodedMessage = encoded

        try {
            val imageFile = File(inputFileName)
            image = ImageIO.read(imageFile)
            val leastSignificantBits = mutableListOf<Int>()

            for (i in 0 until image.height) {
                for (j in 0 until image.width) {
                    val pixelColor = Color(image.getRGB(j, i))
                    leastSignificantBits += pixelColor.blue
                }
            }

            if (encodedMessage.size > leastSignificantBits.size) {
                return println("The input image is not large enough to hold this message.")
            }

            for (i in encodedMessage.indices) {
                if (leastSignificantBits[i] % 2 == 0) {
                    if (encodedMessage[i] == 1) leastSignificantBits[i] = leastSignificantBits[i] or 1
                } else {
                    if (encodedMessage[i] == 0) leastSignificantBits[i] = (leastSignificantBits[i] xor 1)
                }
            }

            for (i in 0 until image.height) {
                for (j in 0 until image.width) {
                    val pixelColor = Color(image.getRGB(j, i))
                    image.setRGB(
                        j,
                        i,
                        Color(pixelColor.red, pixelColor.green, leastSignificantBits[i * image.width + j]).rgb
                    )
                }
            }
        } catch (e: IIOException) {
            return println("Can't read input file!")
        }
        saveImage(image, outputFileName)
        println("Image ${outputFileName.split("/").last()} is saved.")
    }

    private fun saveImage(image: BufferedImage, imageFile: String) {
        ImageIO.write(image, "png", File(imageFile))
    }

    private fun hideMessage() {
        val encoding = mutableListOf<String>()
        println("Message to hide:")
        val message = readln()
        println("Password:")
        val password = readln()

        val markedMessage = encodeMessage(message, password)
        markedMessage.toByteArray().forEach { encoding += it.toString(2).padStart(8, '0') }
        encoded = encoding.joinToString("").map { it.digitToInt() }
    }

    private fun show() {
        println("Input image file:")
        try {
            val image = ImageIO.read(File(readln()))
            println("Password:")
            val password = readln()

            println("Message:")
            println(decodeMessage(image, password))
        } catch (e: IIOException) {
            return println("Can't read input file!")
        }
    }

    private fun encodeMessage(message: String, password: String): String {
        var passEnc = password
        if (message.length > password.length) {
            var i = 0
            while (passEnc.length != message.length) {
                passEnc += password[i]
                ++i
                if (password.length == i) i = 0
            }
        }
        val encodedMessage = message xor (passEnc)
        return "$encodedMessage$marker"
    }

    private fun decodeMessage(image: BufferedImage, password: String): String {
        val messageAllBits = mutableListOf<Int>()

        for (i in 0 until image.height) {
            for (j in 0 until image.width) {
                val pixelColor = Color(image.getRGB(j, i))
                messageAllBits += (pixelColor.blue).toString(2).takeLast(1).toInt()
            }
        }
        var message = ""
        var passEnc = password

        messageAllBits.joinToString("").split(keyBytesToStop).first().chunked(8)
            .forEach { message += it.toInt(2).toChar() }


        if (message.length > password.length) {
            var i = 0
            while (passEnc.length != message.length) {
                passEnc += password[i]
                ++i
                if (password.length == i) i = 0
            }
        }
        return message xor (passEnc)
    }

    private infix fun String.xor(that: String) = mapIndexed { index, c ->
        that[index].code.xor(c.code)
    }.joinToString("") { it.toChar().toString() }
}

