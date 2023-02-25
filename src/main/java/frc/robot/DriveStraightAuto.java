//package frc.robot;
//
//import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.math.controller.ProfiledPIDController;
//import edu.wpi.first.math.geometry.Pose2d;
//import edu.wpi.first.math.geometry.Rotation2d;
//import edu.wpi.first.math.trajectory.Trajectory;
//import edu.wpi.first.math.trajectory.TrajectoryConfig;
//import edu.wpi.first.math.trajectory.TrajectoryGenerator;
//import edu.wpi.first.math.util.Units;
//import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
//import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
//import frc.robot.subsystems.swerve.SwerveDriveSubsystem;
//
//import java.util.List;
//
//public class DriveStraightAuto {
//    private final SwerveDriveSubsystem swerve;
//
//    public DriveStraightAuto(SwerveDriveSubsystem swerve) {
//        this.swerve = swerve;
//    }
//
//    public Command getAutonomousCommand() {
//        TrajectoryConfig config = new TrajectoryConfig(-1, -1)
//                .setKinematics(swerve.kinematics);
//
//        config.setReversed(false);
//
//        Trajectory example = TrajectoryGenerator.generateTrajectory(
//                new Pose2d(0, 0, new Rotation2d(Units.degreesToRadians(90))),
//                List.of(),
//                new Pose2d(0, Units.inchesToMeters(108), new Rotation2d(Units.degreesToRadians(90))),
//                config
//        );
//
//        ProfiledPIDController thetaController = new ProfiledPIDController(-1, 0, 0, -1);
//        thetaController.enableContinuousInput(-Math.PI, Math.PI);
//
////        SwerveControllerCommand swerveCommand = new SwerveControllerCommand(
////                example,
////                swerve.getPose(),
////                swerve.kinematics,
////                new PIDController(-1, 0, 0),
////                new PIDController(-1, 0, 0);
////                thetaController,
////                swerve.setState()
////        );
//    }
//
//}
