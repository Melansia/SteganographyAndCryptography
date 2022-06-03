import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.IIOException
import javax.imageio.ImageIO

class SteganographyAndCryptography {

    private lateinit var image: BufferedImage
    fun start() {
        while (true) {
            println("Task (hide, show, exit):")
            when (readln()) {
                "exit" -> {
                    println("Bye!")
                    break
                }
                "hide" -> hide()
                "show" -> println("Obtaining message from image.")
                else -> println("Wrong task: [input String]")
            }
        }
    }

    private fun hide() {
        println("Input image file: ")
        val inputFileName = readln()
        println("Output image file: ")
        val outputFileName = readln()

        try {
            val imageFile = File(inputFileName)
            image = ImageIO.read(imageFile)
        } catch (e: IIOException) {
            return println("Can;t read input file!")
        }

        println("Input image file: $inputFileName")
        println("Output image file: $outputFileName")

        for (i in 0 until image.width) {
            for (j in 0 until image.height) {

                val color = Color(image.getRGB(i, j))
                val rgb = Color(
                    setLeastSignificantBitToOne(color.red),
                    setLeastSignificantBitToOne(color.green),
                    setLeastSignificantBitToOne(color.blue)
                ).rgb
                image.setRGB(i, j, rgb)
            }
        }
        saveImage(image, outputFileName)
        println("Image ${outputFileName} is saved.")
    }

    private fun setLeastSignificantBitToOne(pixel: Int): Int {
        return if (pixel % 2 == 0) pixel + 1 else pixel
    }

    private fun saveImage(image: BufferedImage, imageFile: String) {
        ImageIO.write(image, "png", File(imageFile))
    }

}

fun main() {
    SteganographyAndCryptography().start()
}