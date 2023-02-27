package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveByTimeCommand;
import frc.robot.subsystems.index.IndexSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.subsystems.swerve.SwerveDriveSubsystem;

public class LeftAndRightPosition {

    public LeftAndRightPosition(){}

    public Command getAutonomousCommand(SwerveDriveSubsystem swerve, ShooterSubsystem shooter, IndexSubsystem index, IntakeSubsystem intake) {
        return new RunCommand(shooter::highShoot, shooter)
                // Shoot
                .andThen(new WaitCommand(1))
                .andThen(new RunCommand(index::forward))
                .alongWith(new WaitCommand(1.5))
                .andThen(new RunCommand(shooter::stop))
                .alongWith(new RunCommand(index::stop))

                // Drive & Intake
                .alongWith(new DriveByTimeCommand(swerve, 5, 0.5)) //TODO: Calibrate so robot moves 16' 3". Also 5s is too long for a 15s auto
                .alongWith(new WaitCommand(3))
                .andThen(new RunCommand(intake::forward))
                .alongWith(new WaitCommand(2))
                .andThen(new DriveByTimeCommand(swerve, 5, 0.5)) //TODO: Same ^
                .alongWith(new WaitCommand(1))
                .andThen(new RunCommand(intake::stop))
                .alongWith(new WaitCommand(3))

                // Charge Up and Shoot
                .andThen(new RunCommand(shooter::mediumShoot))
                .alongWith(new WaitCommand(1))
                .andThen(new RunCommand(index::forward))
                .alongWith(new WaitCommand(1.5))
                .andThen(new RunCommand(shooter::stop))
                .alongWith(new RunCommand(index::stop));
    }
}
