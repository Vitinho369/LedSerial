int statusLed = 0;
const int LED = 12;

void setup() {
  Serial.begin(9600);
  pinMode(LED, OUTPUT);
  digitalWrite(LED, LOW);
}

void loop() {

    if(Serial.available() > 0){
        statusLed = Serial.read();
        
        if(statusLed == '0'){
          digitalWrite(LED, LOW);
          Serial.println("Desliguei");
        }else if(statusLed == '1'){
          digitalWrite(LED, HIGH);
          Serial.println("Liguei");
        }
    }
}