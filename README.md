# Steganography and Cryptography

## *Gregory L*

**Steganography and Cryptography** A very simple Steganography and Cryptography program.

A versatile steganography/cryptography program: which can perform a variety of tasks based on the user's wishes. 
You may want to hide a message within an image, encrypt the text message for extra security, 
or decipher a hidden message you got from someone else.

## Functionality

The following **required** Objectives are completed:


### Stage 1

* [X] Print the message Task (hide, show, exit): and read standard input in a loop.
* [X] If the input is exit, then program prints Bye! and exit.
* [X] If the input is hide, then program prints Hiding message in image. and return to the input loop.
* [X] If the input is show, then program prints Obtaining message from image. and return to the input loop.
* [X] If any other string is input, then print Wrong task: [input String] and return to the input loop.

### Stage 2

* [X] When the user inputs the command hide, the program prompt them for an input image filename with the message Input image file: 
and an output image filename with the message "Output image file:". 
These are used for reading the input image file and writing the output image file, respectively.


* [X] After reading the filenames, the program prints the following messages: 
Input Image: [input filename] and Output Image: [output filename].


* [X] When the input image is read, the least significant bit for each color (Red, Green, and Blue) is set to 1. 
The resulting image will be saved with the provided output image filename in the PNG format.


* [X] A proper method is applied so that the I/O exceptions do not terminate the program. 
In such cases, an exception message should be printed and the program should return to the command loop.

### Stage 3

* [X] When the hide command is given, the program gets the input and output image filenames. 
Then, it prompts the user for the secret message by printing "Message to hide:".


* [X] The program is checking that the image size is adequate for holding the Bytes array. 
If not, it prints an error message with the text "The input image is not large enough to hold this message." 
and return to the menu.


* [X] Each bit of this Bytes Array is saved at the position of the least significant bit of the blue color of each pixel,
The output image is saved in the PNG format.


* [X] When the "show" command is given, the program asks for the image filename (previously saved with the hidden message) by printing "Input image file:". 
The image is opened and the Bytes Array is reconstructed bit by bit; 
the program stops reading it when the bytes with the values 0, 0, 3 are encountered.


* [X] The last 3 bytes (values 0, 0, 3) are removed from the end of the Bytes Array.
Then, the message is restored as a String from the Bytes Array (or 00000000 00000000 00000011 bits).
The program then prints "Message:" and then the message itself on a new line.