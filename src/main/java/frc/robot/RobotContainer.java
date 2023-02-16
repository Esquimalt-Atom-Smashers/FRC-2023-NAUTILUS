// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

/**
 * This class contains the Robot's subsystems, commands, and button mappings.
 */
public class RobotContainer {

  private final SwerveDriveSubsystem swerve;
  private final ShooterSubsystem shooter;
  private final IndexSubsystem index;
  private final IntakeSubsystem intake;


  private final CommandGenericHID driverController = new CommandGenericHID(1);

  private final Trigger xButton = new Trigger();

  private final Trigger indexForward = driverController.button(0);
  private final Trigger indexBackward = driverController.button(11);
  private final Trigger intakeForward = driverController.button(1);
  private final Trigger intakeBackward = driverController.button(10);
  private final Trigger lowButton = driverController.button(3);
  private final Trigger mediumButton = driverController.button(2);
  private final Trigger highButton = driverController.button(4);

  public RobotContainer() {
    index = new IndexSubsystem();
    swerve = new SwerveDriveSubsystem();
    intake = new IntakeSubsystem();
    shooter = new ShooterSubsystem();

    configureButtonBindings();

    swerve.setDefaultCommand(swerve.drive(driverController.getRawAxis(1), driverController.getRawAxis(0), driverController.getRawAxis(2)));
    shooter.setDefaultCommand(shooter.shootStop());
    index.setDefaultCommand(index.indexStop());
    intake.setDefaultCommand(intake.intakeStop());
  }

  private void configureButtonBindings() {
    lowButton.onTrue(shooter.lowShoot());
    mediumButton.onTrue(shooter.mediumShoot());
    highButton.onTrue(shooter.highShoot());

    indexForward.onTrue(index.indexForward());
    indexBackward.onTrue(index.indexBackward());

    intakeForward.onTrue(intake.intakeForward());
    intakeBackward.onTrue(intake.intakeBackward());
  }

}
