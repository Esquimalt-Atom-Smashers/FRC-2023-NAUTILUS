package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Alex smells, but so does Isaac
public class LEDSubsystem extends SubsystemBase {

    public static double NORMAL = -0.97;
    public static double HIGH_SHOOT = -0.91;
    public static double MEDIUM_SHOOT = -0.91;
    public static double LOW_SHOOT = -0.95;
    public static double CHARGING = -0.95;
    public static double READY = 0.73;
    public static double AUTO = -0.95;

    private final PWM LEDs = new PWM(0);

    public void charging() {
        LEDs.setSpeed(CHARGING);
    }

    public void ready() {
        LEDs.setSpeed(READY);
    }

    public void normal() {
        LEDs.setSpeed(NORMAL);
    }

    public void lowShoot() {
        LEDs.setSpeed(LOW_SHOOT);
    }
    public void mediumShoot() {
        LEDs.setSpeed(MEDIUM_SHOOT);
    }
    public void highShoot() {
        LEDs.setSpeed(HIGH_SHOOT);
    }
    public void auto() {
        LEDs.setSpeed(AUTO);
    }

}
