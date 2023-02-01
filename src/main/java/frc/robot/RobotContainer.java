// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.SwerveDriveSubsystem;

/**
 * This class contains the Robot's subsystems, commands, and button mappings.
 */
public class RobotContainer {
  // Subsystems
  private final SwerveDriveSubsystem swerve = new SwerveDriveSubsystem();

  // Controllers
  // private final XboxController driverController = new XboxController(1);
  // private final XboxController otherController = new XboxController(0);

  private final Joystick driverController = new Joystick(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    swerve.setDefaultCommand(new RunCommand(() -> 
      swerve.drive(driverController.getRawAxis(1), driverController.getRawAxis(0), driverController.getTwist())
    , swerve));
    
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
