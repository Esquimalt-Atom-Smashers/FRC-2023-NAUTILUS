package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveByTimeCommand;

public class ShootAndDrive {

    private double driveTime;
    private double driveSpeed;
    private final RobotContainer robot;

    public ShootAndDrive(RobotContainer robot, double driveTime, double driveSpeed) {
        this.driveTime = driveTime;
        this.driveSpeed = driveSpeed;
        this.robot = robot;
    }

    public static Command middleCommand(RobotContainer robot) {
        return new ShootAndDrive(robot, 3.8, 0.33).getAutonomousCommand();
    }
    public static Command leftRightCommand(RobotContainer robot) {
        return new ShootAndDrive(robot, 2.9, 0.8).getAutonomousCommand();
    }

    public Command getAutonomousCommand() {
        var shooter = robot.getShooter();
        var index = robot.getIndex();
        var swerve = robot.getSwerve();
        var intake = robot.getIntake();

        swerve.reset();
        return new RunCommand(shooter::highShoot, shooter).withTimeout(1)
                .andThen(new RunCommand(index::indexForward, index).withTimeout(3))
                .andThen(new RunCommand(index::indexStop).withTimeout(1))
                .alongWith(new RunCommand(shooter::shootStop).withTimeout(1))
                .andThen(new DriveByTimeCommand(swerve, 0.1, 0.1, 0))
                .andThen(new RunCommand(swerve::reset, swerve).withTimeout(2))
                .andThen(new DriveByTimeCommand(swerve, driveTime, driveSpeed, -0.025))
                .alongWith(new RunCommand(intake::forward, intake));
    }
    
}
