package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.index.IndexSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.subsystems.swerve.SwerveDriveSubsystem;

public class RightPosition {

    private final ShooterSubsystem shooter = new ShooterSubsystem();
    private final IndexSubsystem index = new IndexSubsystem();

    public Command getAutonomousCommand() {
        return new RunCommand(shooter::highShoot, shooter)
                .andThen(new WaitCommand(1))
                .alongWith(new RunCommand(index::forward))
                .alongWith(new WaitCommand(2))
                .andThen(new RunCommand(shooter::stop))
                .andThen(new RunCommand(index::stop));
    }
}
