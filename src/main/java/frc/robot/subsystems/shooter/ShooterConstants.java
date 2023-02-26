package frc.robot.subsystems.shooter;

public class ShooterConstants {
    public static class Motor {
        public static final double LOW_SHOOT_RPM = 100;
        public static final double MEDIUM_SHOOT_RPM = 300;
        public static final double HIGH_SHOOT_RPM = 400;

        public static final boolean FRONT_MOTOR_INVERTED = false;
        public static final boolean REAR_MOTOR_INVERTED = true;
        public static final int MOTOR_CURRENT_LIMIT = 30;
        public static final double MAX_VOLTAGE = -1;
    }

    public static class PID {

        public static final double P = -1;
        public static final double I = 0;
        public static final double D = 0;
        public static final double FF = -1;
        public static final double IZ = 0;

    }
}
