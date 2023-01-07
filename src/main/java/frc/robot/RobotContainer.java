// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShootingSubsystem;

/**
 * This class contains the Robot's subsystems, commands, and button mappings.
 */
public class RobotContainer {

  public static final int DRIVER_CONTROLLER_PORT = 1;

  // Subsystems
  private final DriveSubsystem drive = new DriveSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final ShootingSubsystem shooting = new ShootingSubsystem();

  // Controllers
  private final XboxController driverController = new XboxController(1);
  private final XboxController otherController = new XboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Setup default command for Drive Subsystem
    drive.setDefaultCommand(new RunCommand(() -> 
      drive.move(driverController.getRawAxis(3), driverController.getLeftX())
    , drive));

    // Setup default command for Climber Subsystem
    climber.setDefaultCommand(new RunCommand(() -> 
      climber.setMotor(
        otherController.getPOV() == 0 ? -0.1 : 
          otherController.getPOV() == 180 ? 0.1 : 0
      )
    , climber));

    // Setup default command for Intake Subsystem
    // intake.setDefaultCommand(new RunCommand(() -> 
    //   intake.setIntakeSpeed(
    //     driverController.getRightBumper() ? 0 : driverController.getRightTriggerAxis() * -0.6
    //   )
    // , intake));
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
