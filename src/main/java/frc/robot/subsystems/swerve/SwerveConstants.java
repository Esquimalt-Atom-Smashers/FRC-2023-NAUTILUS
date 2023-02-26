package frc.robot.subsystems.swerve;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

//TODO: Get these constants to be dialed in.
public class SwerveConstants {

    public static class DriveMotor {
        /** Motor specific settings */
        public static final boolean MOTOR_INVERTED = false;
        public static final double MAX_VOLTAGE = -1;
        public static final int MOTOR_CURRENT_LIMIT = 30;

        /** Motor specs */
        public static final double MK4_DRIVE_REDUCTION = -1;
        public static final double MK4_WHEEL_DIAMETER = -1; //Meters

        /** Specific PID settings */
        public static final double PID_P = 0.08;
        public static final double PID_I = 0;
        public static final double PID_D = 0;
        public static final double PID_F = 0.00156;
        public static final double PID_IZ = 0;
        public static final double KS = -1;
        public static final double KV = -1;
        public static final double KA = -1;
    }

    public static class TurnMotor {
        /** Motor specific settings */
        public static final boolean MOTOR_INVERTED = false;
        public static final double MAX_VOLTAGE = -1;
        public static final int MOTOR_CURRENT_LIMIT = 30;

        /** Motor specs */
        public static final double MK4_ROTATION_REDUCTION = -1;
        public static final double MK4_WHEEL_DIAMETER = -1; //Meters

        /** Specific PID settings */
        public static final double PID_P = 1.5;
        public static final double PID_I = 0;
        public static final double PID_D = 0;
        public static final double PID_F = 0.00156;
        public static final double PID_IZ = 0;
        public static final double PID_MAX_ACCEL = -1; //RPM
        public static final double PID_MAX_VELOCITY = -1; //RPM
    }

    public static class Encoder {
        public static final int ENCODER_RESET_ITERATIONS = 500;
        public static final double ENCODER_RESET_MAX_ANGULAR_VELOCITY = Math.toRadians(0.5);
    }

}
