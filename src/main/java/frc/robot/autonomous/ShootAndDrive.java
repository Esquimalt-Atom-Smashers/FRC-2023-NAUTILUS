package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveByTimeCommand;

public class ShootAndDrive {

    private double driveTime;
    private double driveSpeed;

    public ShootAndDrive(double driveTime, double driveSpeed) {
        this.driveTime = driveTime;
        this.driveSpeed = driveSpeed;
    }

    public static Command middleCommand(RobotContainer container) {
        return new ShootAndDrive(3.8, 0.33).getAutonomousCommand(container);
    }
    public static Command leftRightCommand(RobotContainer container) {
        return new ShootAndDrive(2.9, 0.8).getAutonomousCommand(container);
    }

    public Command getAutonomousCommand(RobotContainer container) {
        var shooter = container.getShooter();
        var index = container.getIndex();
        var swerve = container.getSwerve();
        var intake = container.getIntake();

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
