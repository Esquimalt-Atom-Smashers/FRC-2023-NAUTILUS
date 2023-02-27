// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autonomous.LeftAndRightPosition;
import frc.robot.autonomous.MiddlePosition;
import frc.robot.subsystems.index.IndexSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.swerve.SwerveDriveSubsystem;

/**
 * This class contains the Robot's subsystems, commands, and button mappings.
 */
public class RobotContainer {
  private final SwerveDriveSubsystem swerve = new SwerveDriveSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final IndexSubsystem index = new IndexSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final LEDSubsystem led = new LEDSubsystem();

  private final CommandGenericHID commandController = new CommandGenericHID(0);

  private final Trigger lowShootButton = commandController.button(4);
  private final Trigger mediumShootButton = commandController.button(3);
  private final Trigger highShootButton = commandController.button(5);
  private final Trigger indexForwardButton = commandController.button(1);
  private final Trigger indexBackwardButton = commandController.button(12);
  private final Trigger intakeForwardButton = commandController.button(1);
  private final Trigger intakeBackwardButton = commandController.button(12);
  private final Trigger gyroReset = commandController.button(-1);

  // Auto Stuff
  // A chooser for autonomous commands
  SendableChooser<Command> chooser = new SendableChooser<>();

  LeftAndRightPosition leftAndRightPosition = new LeftAndRightPosition();
  MiddlePosition middlePosition = new MiddlePosition();

  public RobotContainer() {

    // More Auto Stuff
    // Add commands to the autonomous command chooser
    chooser.setDefaultOption("Left And Right Position", leftAndRightPosition.getAutonomousCommand(swerve, shooter, index, intake));
    chooser.setDefaultOption("Middle Position", middlePosition.getAutonomousCommand(shooter, index));
    SmartDashboard.putData(chooser);
    // End Auto Stuff

    new RunCommand(led::_default);
    configureButtonBindings();

    //TODO: Confirm axis values
    swerve.setDefaultCommand(new DefaultDriveCommand(
            swerve,
            () -> commandController.getRawAxis(0),
            () -> commandController.getRawAxis(2),
            () -> commandController.getRawAxis(1),
            () -> true
    ));

    index.setDefaultCommand(new RunCommand(index::stop, index));
    intake.setDefaultCommand(new RunCommand(intake::stop, intake));
    shooter.setDefaultCommand(new RunCommand(shooter::stop, shooter).andThen(new RunCommand(led::_default)));
  }

  //TODO: Replace RunCommand with actual commands i.e ShootLowCommand.
  private void configureButtonBindings() {
    lowShootButton.onTrue(new RunCommand(shooter::lowShoot, shooter) //May need to switch to whileTrue()
            .alongWith(new RunCommand(led::charging, led))
            .alongWith(new WaitCommand(1))
            .andThen(new RunCommand(led::ready, led)));
    mediumShootButton.onTrue(new RunCommand(shooter::mediumShoot, shooter)
            .alongWith(new RunCommand(led::charging, led))
            .alongWith(new WaitCommand(1))
            .andThen(new RunCommand(led::ready, led)));
    highShootButton.onTrue(new RunCommand(shooter::highShoot, shooter)
            .alongWith(new RunCommand(led::charging, led))
            .alongWith(new WaitCommand(1))
            .andThen(new RunCommand(led::ready, led)));

    intakeForwardButton.onTrue(new RunCommand(intake::forward, intake));
    intakeBackwardButton.onTrue(new RunCommand(intake::backward, intake));

    indexForwardButton.onTrue(new RunCommand(index::forward, index).alongWith(new RunCommand(intake::forward, intake)));
    indexBackwardButton.onTrue(new RunCommand(index::backward, index));

    gyroReset.onTrue(new RunCommand(swerve::reset, swerve));
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }

}