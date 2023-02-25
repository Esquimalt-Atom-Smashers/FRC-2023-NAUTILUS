package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    public static class LEDConstants {
        public static double CHARGING_PWM = 0.20;
        public static double READY_PWM = 0.32;
    }
    private final PWM LEDs = new PWM(0);

    public LEDSubsystem() {

    }

    public void charging() {
        LEDs.setSpeed(LEDConstants.CHARGING_PWM);
    }

    public void ready() {
        LEDs.setSpeed(LEDConstants.READY_PWM);
    }
}
