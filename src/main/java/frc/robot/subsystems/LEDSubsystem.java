package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    public static class LEDConstants {
        public static double CHARGING_PWM = -0.91;
        public static double READY_PWM = 0.73;
        public static double DEFAULT = -0.97;

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

    public void _default() {
        LEDs.setSpeed(LEDConstants.DEFAULT);
    }
}
