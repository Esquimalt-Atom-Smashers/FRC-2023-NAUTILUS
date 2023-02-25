package frc.robot.subsystems.swerve;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDriveSubsystem extends SubsystemBase{

    public static boolean FIELD_CENTRIC = true;
//frontRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 1, 2, 11, 0);
//        frontLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 5, 6, 12, 0);
//        rearRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 4, 3, 13, 0);
//        rearLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 8, 7, 14, 0);
    private final SwerveModule frontRightModule = new SwerveModule(1, 2, 11);
    private final SwerveModule frontLeftModule = new SwerveModule(5, 6, 12);
    private final SwerveModule rearRightModule = new SwerveModule(4, 3, 1);
    private final SwerveModule rearLeftModule = new SwerveModule(8, 7, 14);

    public SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
            new Translation2d(0.343, 0.343), //13.5 to meter
            new Translation2d(0.343, -0.343),
            new Translation2d(-0.343, 0.343),
            new Translation2d(-0.343, -0.343)
    );

    private AHRS gyro = new AHRS(SPI.Port.kMXP, (byte) 200);

    public SwerveDriveSubsystem() {
        gyro.reset();
    }

    public void drive(double forward, double sideways, double angular, boolean fieldCentric) {
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(fieldCentric
                        ? ChassisSpeeds.fromFieldRelativeSpeeds(forward, sideways, angular, gyro.getRotation2d())
                        : new ChassisSpeeds(forward, sideways, angular));

        frontLeftModule.setState(states[0]);
        frontRightModule.setState(states[1]);
        rearLeftModule.setState(states[2]);
        rearRightModule.setState(states[3]);
    }

    public void reset() {
        gyro.reset();
    }



}
