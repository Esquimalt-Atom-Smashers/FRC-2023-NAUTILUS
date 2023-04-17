package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveByTimeCommand;
import frc.robot.commands.GyroBalanceCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class MiddlePosition extends ShootAndDrive {

    public MiddlePosition(RobotContainer robot) {
        super(robot, 4, 0.5);
    }

    
    public Command getAutonomousCommand() { 
        var cmd = super.getAutonomousCommand();
        var shooter = robot.getShooter();
        var index = robot.getIndex();
        var swerve = robot.getSwerve();
        var intake = robot.getIntake();
        var leds = robot.getLeds();
        
        return cmd
            .andThen(new DriveByTimeCommand(swerve, 1, 0, 0))
            .andThen(new DriveByTimeCommand(swerve, 2.3, -0.5, 0))
            .andThen(new GyroBalanceCommand(swerve, 5));

    }

}