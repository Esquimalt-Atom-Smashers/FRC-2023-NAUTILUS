package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveByTimeCommand;
import frc.robot.commands.GyroBalanceCommand;

public class ShootAndDrive {

    private double driveTime;
    private double driveSpeed;
    protected final RobotContainer robot;

    public ShootAndDrive(RobotContainer robot, double driveTime, double driveSpeed) {
        this.driveTime = driveTime;
        this.driveSpeed = driveSpeed;
        this.robot = robot;
    }

    public Command getAutonomousCommand() { 
        var shooter = robot.getShooter();
        var index = robot.getIndex();
        var swerve = robot.getSwerve();
        var intake = robot.getIntake();
        var leds = robot.getLeds();
        shooter.shootStop();
        index.indexStop();
        intake.stop();

        leds.auto();

        swerve.reset();
        
        return new RunCommand(shooter::highShoot, shooter).withTimeout(1)
                .andThen(new RunCommand(index::indexForward, index).withTimeout(0.5))
                .andThen(new RunCommand(shooter::shootStop, shooter).withTimeout(0.05))
                .andThen(new RunCommand(index::indexStop, index).withTimeout(0.05))
                .andThen(new DriveByTimeCommand(swerve, 0.05, 0.1, 0))
                .andThen(new RunCommand(swerve::reset, swerve).withTimeout(0.05))
                .andThen(new DriveByTimeCommand(swerve, driveTime, driveSpeed, -0.025));
    }
    
}
