package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Alex smells
public class LEDSubsystem extends SubsystemBase {

    //Port = PWM port
    private final AddressableLED strip1 = new AddressableLED(-1);
    private final int strip1Length = Integer.MAX_VALUE;

    public LEDSubsystem() {
        strip1.setLength(strip1Length);
    }

    private void solidColor(Color color, AddressableLED led, int ledLength) {
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(ledLength);
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setLED(i, color);
        }
        led.setData(buffer);
    }

    private void rainbow(int offset, AddressableLED led, int ledLength) {
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(ledLength);
        for (int i = 0; i < buffer.getLength(); i++) {
            int hue = (offset + (i * 180 / buffer.getLength())) % 180;
            buffer.setHSV(i, hue, 255, 128);
        }
        led.setData(buffer);
    }

}
