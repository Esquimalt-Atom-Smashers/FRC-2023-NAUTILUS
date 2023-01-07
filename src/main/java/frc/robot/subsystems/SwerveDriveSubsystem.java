package frc.robot.subsystems;

import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class SwerveDriveSubsystem {

    public static boolean IS_FIELD_CENTRIC = false;

    private final double DRIVE_SPEED = 1.0;

    private SwerveModule frontRightModule;
    private SwerveModule frontLeftModule;
    private SwerveModule rearRightModule;
    private SwerveModule rearLeftModule;

    private final Encoder frontRightEncoder = new Encoder(-1, -1, false, CounterBase.EncodingType.k2X);
    private final Encoder frontLeftEncoder = new Encoder(-1, -1, false, CounterBase.EncodingType.k2X);
    private final Encoder rearRightEncoder = new Encoder(-1, -1, false, CounterBase.EncodingType.k2X);
    private final Encoder rearLeftEncoder = new Encoder(-1, -1, false, CounterBase.EncodingType.k2X);

    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
            new Translation2d(-1, -1),
            new Translation2d(-1, -1),
            new Translation2d(-1, -1),
            new Translation2d(-1, -1)
    );

    private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    public SwerveDriveSubsystem() {

        //TODO initialize SwerveModules
        frontRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L1, -1, -1, -1, -1);
        frontLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L1, -1, -1, -1, -1);
        rearRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L1, -1, -1, -1, -1);
        rearLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L1, -1, -1, -1, -1);

        gyro.calibrate();

    }

    public void drive(double forward, double sideways, double angular) {
        ChassisSpeeds chassis = new ChassisSpeeds(forward, sideways, angular);
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassis);

        frontLeftModule.set(states[0].speedMetersPerSecond, states[0].angle.getRadians());
        frontRightModule.set(states[1].speedMetersPerSecond, states[1].angle.getRadians());
        rearLeftModule.set(states[2].speedMetersPerSecond, states[2].angle.getRadians());
        rearRightModule.set(states[3].speedMetersPerSecond, states[3].angle.getRadians());

    }

    public void drive(double distance, DistanceUnit unit, double timeout) {
        Timer timer = new Timer();

        drive(DRIVE_SPEED, 0, 0);
        while ((timeout < 0 || timer.get() < timeout) &&  distance < unit.toUnit(frontLeftEncoder.getDistance())) {

        }
        drive(0, 0, 0);

    }

    public ADXRS450_Gyro getGyro() {
        return gyro;
    }

    enum DistanceUnit {
        CENTIMETRES(-1),
        INCHES(-1);

        private double conversionFactor;

        private DistanceUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toUnit(double value) {
            return value * conversionFactor;
        }

    }

}
