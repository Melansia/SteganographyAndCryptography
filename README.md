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
* [X] If the input is exit, print Bye! and exit.
* [X] If the input is hide, print Hiding message in image. and return to the input loop.
* [X] If the input is show, print Obtaining message from image. and return to the input loop.
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
