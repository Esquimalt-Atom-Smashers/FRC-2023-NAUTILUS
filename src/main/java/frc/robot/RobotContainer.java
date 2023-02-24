// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.swerve.SwerveDriveSubsystem;

/**
 * This class contains the Robot's subsystems, commands, and button mappings.
 */
public class RobotContainer {

  private final SwerveDriveSubsystem swerve = new SwerveDriveSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final IndexSubsystem index = new IndexSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();

//  private final Joystick driverController = new Joystick(0);
  private final CommandGenericHID commandController = new CommandGenericHID(0);

  private final Trigger lowShootButton = commandController.button(4);
  private final Trigger mediumShootButton = commandController.button(3);
  private final Trigger highShootButton = commandController.button(5);
  private final Trigger indexForwardButton = commandController.button(1);
  private final Trigger indexBackwardButton = commandController.button(12);
  private final Trigger intakeForwardButton = commandController.button(1);
  private final Trigger intakeBackwardButton = commandController.button(12);

  public RobotContainer() {
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
    shooter.setDefaultCommand(new RunCommand(shooter::stop, shooter));
  }

  //TODO: Replace RunCommand with actual commands i.e ShootLowCommand.
  private void configureButtonBindings() {
    lowShootButton.whileTrue(new RunCommand(shooter::lowShoot, shooter));
    mediumShootButton.whileTrue(new RunCommand(shooter::mediumShoot, shooter));
    highShootButton.whileTrue(new RunCommand(shooter::highShoot, shooter));

    intakeForwardButton.whileTrue(new RunCommand(intake::forward, intake));
    intakeBackwardButton.whileTrue(new RunCommand(intake::backward, intake));

    indexForwardButton.whileTrue(new RunCommand(index::forward, index));
    indexBackwardButton.whileTrue(new RunCommand(index::backward, index));
  }

}
