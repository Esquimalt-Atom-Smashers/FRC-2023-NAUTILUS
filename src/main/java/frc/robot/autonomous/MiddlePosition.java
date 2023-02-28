package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveByTimeCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class MiddlePosition {

    public MiddlePosition(){}

    public Command getAutonomousCommand(RobotContainer container) {
        var shooter = container.getShooter();
        var index = container.getIndex();
        var swerve = container.getSwerve();
        return new RunCommand(shooter::highShoot, shooter).withTimeout(1)
                .andThen(new RunCommand(index::indexForward, index).withTimeout(3))
                .andThen(new RunCommand(shooter::shootStop).withTimeout(1))
                .alongWith(new RunCommand(index::indexStop).withTimeout(1));
                //TODO: Drive back over charging station to park outside community.
    }
}