// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.SwerveDriveSubsystem;

/**
 * This class contains the Robot's subsystems, commands, and button mappings.
 */
public class RobotContainer {
  // Subsystems
  private final SwerveDriveSubsystem swerve;

  // Controllers
  // private final XboxController driverController = new XboxController(1);
  // private final XboxController otherController = new XboxController(0);

  private final Joystick driverController = new Joystick(0);
  private final XboxController xboxController = new XboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    swerve = new SwerveDriveSubsystem();

    VictorSPX intake = new VictorSPX(0);
    VictorSPX frontIndex = new VictorSPX(2);
    VictorSPX rearIndex = new VictorSPX(1);

    CANSparkMax canSparkMax = new CANSparkMax(10, MotorType.kBrushless);
    CANSparkMax canSparkMax2 = new CANSparkMax(9, MotorType.kBrushless);


    swerve.setDefaultCommand(new RunCommand(() -> {
        // swerve.drive(driverController.getRawAxis(1), driverController.getRawAxis(0), driverController.getTwist())
        // intake.set(ControlMode.PercentOutput, 0.25);
        // frontIndex.set(ControlMode.PercentOutput, 0.25);
        // rearIndex.set(ControlMode.PercentOutput, 0.25);

        canSparkMax.set(0.25);
        canSparkMax2.set(0.25);
  }, swerve));
    

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
