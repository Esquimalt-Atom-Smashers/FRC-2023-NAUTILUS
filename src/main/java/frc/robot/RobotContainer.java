// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
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

  private final Joystick driverController = new Joystick(0);
  private final XboxController xboxController = new XboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
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
