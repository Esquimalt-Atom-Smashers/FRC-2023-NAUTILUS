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

    index = new IndexSubsystem();
    swerve = new SwerveDriveSubsystem();
    intake = new IntakeSubsystem();
    shooter = new ShooterSubsystem();

    index.setDefaultCommand(new RunCommand( () -> {
      index.index(xboxController.getXButton() ? 0.25 : 0.0);
      index.index(xboxController.getRightBumper() ? 0.25 : 0.0);
    }, index));

    swerve.setDefaultCommand(new RunCommand(() -> {
        swerve.drive(driverController.getRawAxis(1), driverController.getRawAxis(0), driverController.getTwist());
    }, swerve));

    intake.setDefaultCommand(new RunCommand(() -> {
      if (xboxController.getBButton()) {
        intake.forward();
      } else if (xboxController.getBButton() && xboxController.getLeftBumper()) {
        intake.backward();
      } else {
        intake.stop();
      }
    }, intake));
    
    shooter.setDefaultCommand(new RunCommand(() -> shooter.shoot(xboxController.getAButton() ? 0.2 : 0), shooter));
  }

  /**
   * one day we should switch to this
   * 
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

}
