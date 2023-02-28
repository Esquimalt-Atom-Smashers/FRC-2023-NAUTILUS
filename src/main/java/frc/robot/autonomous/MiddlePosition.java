package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class MiddlePosition {

    public MiddlePosition(){}

    public Command getAutonomousCommand(ShooterSubsystem shooter, IndexSubsystem index) {
        return new RunCommand(shooter::highShoot, shooter)
                .andThen(new WaitCommand(1))
                .andThen(new RunCommand(index::indexForward))
                .alongWith(new WaitCommand(2))
                .andThen(new RunCommand(shooter::shootStop))
                .alongWith(new RunCommand(index::indexStop));
    }
}