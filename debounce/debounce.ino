const int buttonPin = 2;

unsigned long lastPressedTime;
unsigned long debounceDelay = 100;

volatile int buttonPressCount = 0;


void setup() {
  Serial.begin(9600);
  pinMode(buttonPin, INPUT_PULLUP);

  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, FALLING);
}

void loop() {
  Serial.write('x');
  delay(1000);
}

void buttonPressed() {
   if (millis() > lastPressedTime + debounceDelay) {
      buttonPressCount++;
      lastPressedTime = millis();
      // Serial.print("Press Count: ");
      // Serial.println(buttonPressCount);
      Serial.write(buttonPressCount);
   }
}

