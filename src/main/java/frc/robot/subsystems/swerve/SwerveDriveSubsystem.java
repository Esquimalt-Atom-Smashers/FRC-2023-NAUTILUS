package frc.robot.subsystems.swerve;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDriveSubsystem extends SubsystemBase{

    public static boolean FIELD_CENTRIC = true;
//frontRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 1, 2, 11, 0);
//        frontLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 5, 6, 12, 0);
//        rearRightModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 4, 3, 13, 0);
//        rearLeftModule = Mk4SwerveModuleHelper.createNeo(Mk4SwerveModuleHelper.GearRatio.L2, 8, 7, 14, 0);
    private final SwerveModule frontRightModule = new SwerveModule(
        1, 2, 11, 0,
        false, false, false
    );

    private final SwerveModule frontLeftModule = new SwerveModule(
        5, 6, 12, 0,
        false, false, false
    );

    private final SwerveModule rearRightModule = new SwerveModule(
        4, 3, 13, 0,
        false, false, false
    );

    private final SwerveModule rearLeftModule = new SwerveModule(
        8, 7, 14, 0,
        false, false, false
    );

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
        // Calibrates, zeros yaw
        gyro.reset();
    }

    public void resetTurn(){
        frontLeftModule.resetTurn();
        frontRightModule.resetTurn();
        rearLeftModule.resetTurn();
        rearRightModule.resetTurn();
    }

    public double getHeading(){
        return Math.IEEEremainder(gyro.getYaw(), 360);
    }

    public Rotation2d getRotation2d(){
        return Rotation2d.fromDegrees(getHeading());
    }

    public void stopModules() {
        frontLeftModule.stop();
        frontRightModule.stop();
        rearLeftModule.stop();
        rearRightModule.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates){
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, SwerveConstants.physicalMaxSpeedMetersPerSecond);
        //normalizeWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        frontLeftModule.setDesiredState(desiredStates[0]);
        frontRightModule.setDesiredState(desiredStates[1]);
        rearLeftModule.setDesiredState(desiredStates[2]);
        rearRightModule.setDesiredState(desiredStates[3]);
    }

    public void reset() {
        gyro.reset();
    }

}
