package frc.robot.subsystems.swerve;

public class SwerveConstants {
    public static final double wheelDiameterMeters = 0.10;
    public static final double driveMotorGearRatio = 1/ 6.75;
    public static final double turningMotorGearRatio = 1 / 21.42857142857143;
    public static final double driveEncoderRot2Meter = driveMotorGearRatio * Math.PI * wheelDiameterMeters;
    public static final double turnEncoderRot2Rad = turningMotorGearRatio * 2 * Math.PI;
    public static final double driveEncoderRPM2MeterPerSec = driveEncoderRot2Meter / 60;
    public static final double turnEncoderRPM2RadPerSec = turnEncoderRot2Rad / 60;
    public static final double pTurning = 0.6;
    public static final double physicalMaxSpeedMetersPerSecond = 3;
}
