import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class prime extends PApplet {

//Simple prime finder
//Instead of checking if a number is a prime, it checks if the number IS NOT a prime.
//If it is not not a prime, it is automatically a prime.

boolean prime = true;      //Prime or not.
int number = 3;            //Starting number, since 1 and 2 are always prime and 1 is not relevant to it being a prime.
//In this version, previous primes are not stored, so you cannot change the starting number.
//Because it does not have a list of primes to compare against.
int[] primeNumbers = {2};  //Array which stores all the calculated primes, starts with 2 by default.

boolean auto = false;      //Automatic mode
int numbersPerSec = 30;   //Numbers per second in auto mode.
boolean spacebar = true;  //Detects if the spacebar is pressed so you can manually go through numbers.

PFont f;    //Uses a font to display text (the numbers).

public void setup() {
      //This size allows the program to run up to 6 digits.
  background(0);     //Black background

  //Creates the font
  f = createFont("SourceCodePro-Regular.ttf", 150);
  textFont(f);
  textAlign(CENTER, CENTER);
} 

public void draw() {
  //When spacebar is pressed or automode is on, do calculation
  while (spacebar == true) {
    println("\n", "\n", "\n", "\n", "\n", "\n", "\n", "\n", "\n");  //Simply adds some linebreaks so it looks good in the console.

    //Loop that checks the current number against all saved primes with modulo.
    //If the number can be divided, the result of the modulo is 0, otherwise there's something left.
    //As soon as a %0 is detected, the calculation ends and prime is set to false.
    for (int i = 0; i < primeNumbers.length; i++) {
      if (number % primeNumbers[i] == 0) {
        prime = false;
        println("The number", number, "is not a prime :(");
        println(number, "is dividable by", primeNumbers[i]);
        break;
      }
    }

    //If the number is a prime, display the number in orange and print the information to console.
    if (prime) {
      fill(255, 137, 0);  //Orange
      println("The number", number, "is a prime :D");
      print("All saved primes: ");
      primeNumbers = append(primeNumbers, number);
      //Prints all the saved primenumbers with a comma. The comma is skipped for the last number.
      for (int i = 0; i < primeNumbers.length; i++) {
        print(primeNumbers[i]);
        if (i != primeNumbers.length - 1) {
          print(", ");  //Commas, skipped because of the -1 in the if statement.
        }
      }
    } else {
      //When the number is not a prime, display the number in white and reset the prime to true.
      fill(255);
      prime = true;  //This is done because we are only proving a number IS NOT a prime.
      //Setting this value to true, makes all the calculations and logic a bit more easy.
    }

    background(0);          //Black image, this overwrites the number that is visible before a new one is displayed.
    text(number, 300, 75);  //Display the current tested number.
    number++;               //Increases the number for the next test.
    spacebar = false;       //Resets the spacebar (when in auto this value simply doesn't matter).
  }

  //If the software is in auto mode the spacebar is set to true immediately.
  if (auto) {
    spacebar = true;
    delay(1000 / numbersPerSec);
  }
}

public void keyReleased() {
  //Simply detects if the spacebar is pressed.
  if (key == 32) {
    spacebar = true;
  }
}
  public void settings() {  size(600, 200); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "prime" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
