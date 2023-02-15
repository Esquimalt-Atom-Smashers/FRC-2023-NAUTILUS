package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDriveSubsystem extends SubsystemBase{

    public static boolean IS_FIELD_CENTRIC = false;

    private final double DRIVE_SPEED = 1.0;

    private SwerveModule frontRightModule;
    private SwerveModule frontLeftModule;
    private SwerveModule rearRightModule;
    private SwerveModule rearLeftModule;

    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
            new Translation2d(0.343, 0.343), //13.5 to meter 
            new Translation2d(0.343, -0.343),
            new Translation2d(-0.343, 0.343),
            new Translation2d(-0.343, -0.343)
    );

    private AHRS gyro;

    public SwerveDriveSubsystem() {

        //TODO initialize SwerveModules
        frontRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 1, 2, 11, 0);
        frontLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 5, 6, 12, 0);
        rearRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 4, 3, 13, 0);
        rearLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 8, 7, 14, 0);

        gyro = new AHRS(SPI.Port.kMXP); 

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

    public AHRS getGyro() {
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
