#include <TFT.h>
#include <Servo.h>
#include <SoftwareSerial.h>

//SoftwareSerial BTserial(0, 1); // RX, TX

int in1 = 9; //Declaring the pins where in1 in2 from the driver are wired 
int in2 = 8; //here they are wired with D9 and D8 from Arduino
int in3 = 5;
int in4 = 4; //Make sure it's connected to a pin that can deliver a PWM signal

int ConA = 10; //And we add the pin to control the speed after we remove its jumper 
int ConB = 11;

Servo myservo; //Servo object to control servo.
int val1; //servo left turn adjustment
int val2; //servo right turn adjustment

char c = ' '; //incoming command variable
int speed = 100; //variable for motor speed, which boost() can change

void setup() {
  Serial.begin(9600);
  while (! Serial);
  Serial.println("Enter Values");
  pinMode(in1, OUTPUT); //Declaring the pin modes, obviously they're outputs
  pinMode(in3, OUTPUT);
  
  pinMode(in4, OUTPUT);
  pinMode(in2, OUTPUT);

  pinMode(ConA, OUTPUT);
  pinMode(ConB, OUTPUT);

  myservo.attach(12); //attaches the servo on pin 12 to the servo object.

}

void forward(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);

}


void halt(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, LOW);
  digitalWrite(in4, LOW);

  analogWrite(ConA,0);
  analogWrite(ConB,0);

}

void reverse(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);
}

void forward_left(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);

  myservo.write(val1);
  delay(10);

}

void forward_right(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);

}

void reverse_left(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);

}

void reverse_right(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);

}

void left(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);

}

void right(){

  digitalWrite(in1, LOW);
  digitalWrite(in3, LOW);

  digitalWrite(in2, HIGH);
  digitalWrite(in4, HIGH);

  analogWrite(ConA,speed);
  analogWrite(ConB,speed);

}

void boost() {
  speed = 200;
}

//*****************************************************************//

void loop() {

  if (Serial.available() > 0)
  {
    char input;
    input = Serial.read();
    Serial.write(Serial.read());
    if(input == '1'){//Forward
      forward();
      delay(10);
    }
    else if(input == '2'){ //backward
      reverse();
      delay(10);
    }
    else if(input == '3'){
      forward_left(); 
      delay(10);
    }
    else if(input == '4'){
      reverse_left(); 
      delay(10);
    }
    else if(input == '5'){
      forward_right(); 
      delay(10);
    }
    else if(input == '6'){
      reverse_right(); 
      delay(10);
    }
    else if(input == '7'){
      right(); 
      delay(10);
    }
    else if(input == '8'){
      left(); 
      delay(10);
    }
    else if(input == '9'){
      boost(); 
      delay(10);
    }
    else {
      halt();
    }
   }
 }
