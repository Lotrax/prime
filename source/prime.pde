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

void setup() {
  size(600, 200);    //This size allows the program to run up to 6 digits.
  background(0);     //Black background

  //Creates the font
  f = createFont("SourceCodePro-Regular.ttf", 150);
  textFont(f);
  textAlign(CENTER, CENTER);
} 

void draw() {
  //When spacebar is pressed or automode is on, do calculation
  while (spacebar == true) {
    prime = true;           //start with the assumption that the number is prime
    println("\n", "\n", "\n", "\n", "\n", "\n", "\n", "\n", "\n");  //Simply adds some linebreaks so it looks good in the console.

    //Loop that checks the current number against all saved primes with modulo.
    //Stop checking once the checked prime is larger than sqrt(number).
    float limit = sqrt(number);
    for (int i = 0; i < primeNumbers.length; i++) {
      if (primeNumbers[i] > limit) {
        break;
      }
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
      //When the number is not a prime, display the number in white.
      fill(255);
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

void keyReleased() {
  //Simply detects if the spacebar is pressed.
  if (key == 32) {
    spacebar = true;
  }
}