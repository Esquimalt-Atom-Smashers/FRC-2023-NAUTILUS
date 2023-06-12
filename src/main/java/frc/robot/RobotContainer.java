// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.autonomous.LeftAndRightPosition;
import frc.robot.autonomous.MiddlePosition;
import frc.robot.autonomous.ShootAndDrive;
import frc.robot.commands.GyroBalanceCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

/**
 * This class contains the Robot's subsystems, commands, and button mappings.
 */
public class RobotContainer {

  private final SwerveDriveSubsystem swerve = new SwerveDriveSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final IndexSubsystem index = new IndexSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final LEDSubsystem leds = new LEDSubsystem();

  // private final Joystick controller = new Joystick(0);
  // private final XboxController xboxController = new XboxController(0);
  private GenericHID controller;

  // Auto
  // A chooser for autonomous commands
  SendableChooser<ShootAndDrive> chooser = new SendableChooser<>();
  private final String XBOX_CONTROLLER_OPTION = "Xbox Controller";
  private final String JOYSTICK_OPTION = "Joystick";
  private final String GAMEPAD = "Gamepad";
  private String pickedController;
  SendableChooser<String> controllerPicker = new SendableChooser<>();

  private double forwardInput, strafeInput, turnInput;


  public RobotContainer() {
    chooser.setDefaultOption("Middle Position", new MiddlePosition(this));
    chooser.addOption("Left And Right Position", new LeftAndRightPosition(this));
    controllerPicker.setDefaultOption("XBox Controller", XBOX_CONTROLLER_OPTION);
    controllerPicker.addOption("Joystick", JOYSTICK_OPTION);
    controllerPicker.addOption("Gamepad", GAMEPAD);
    SmartDashboard.putData(chooser);
    SmartDashboard.putData(controllerPicker);
    // End Auto
    
    
    // GenericHID controller = controllerPicker.getSelected();
    // SmartDashboard.putString("is XBox", String.valueOf(controller instanceof XboxController));

    leds.normal();

    configureButtonBindings();

    index.setDefaultCommand(new RunCommand(() -> {
      if (controller.getRawButton(1)) index.indexForward();
      else if (controller.getRawButton(12)) index.indexBackward();
      else index.indexStop();
    }, index));

    swerve.setDefaultCommand(new RunCommand(() -> {
      setMovementValues();
      // Changed this button index from 12 (on the Joystick). 8 on Xbox is the three lines button
      if (controller.getRawButton(8)) {
        swerve.reset();
      } else {
        double pov = controller.getPOV();

        boolean autoSnap = controller.getRawButton(7) || pov != -1;
        double snapDegree = pov;
        if (snapDegree == -1) snapDegree = 0;
        boolean autoBalance = controller.getRawButton(10);
        boolean normalDrive = !autoSnap && !autoBalance;
        double angular = 0;
        double forward = 0;
        double sideways = 0;

        // double multiplier = controller.getRawButton(8) ? 3 : 1;
        double multiplier = 1;

        if (autoSnap || normalDrive) {
          forward = Math.abs(forwardInput) > 0.2 ? forwardInput * multiplier : 0;
          sideways = Math.abs(strafeInput) > 0.2 ? strafeInput * multiplier : 0;
          //forward = Math.abs(xboxController.getLeftY()) > 0.4 ? xboxController.getLeftY() * multiplier : 0;
          //sideways = Math.abs(xboxController.getLeftX()) > 0.4 ? xboxController.getLeftX() * multiplier : 0;
          // forward = controller2.getAxisType(0);
          // sideways = controller2.getAxisType(1);
        }

        if (autoSnap) {
          double gyro = (swerve.getGyro().getYaw() + snapDegree);
          // if (gyro > 180) gyro -= 360;
          // else if (gyro < 180) gyro += 360;
          SmartDashboard.putString("gyro-adjusted", String.valueOf(gyro));
          angular = -(gyro / 180)*3;
          if (Math.abs(angular) > 1.5) {
            angular = angular / Math.abs(angular) * 1.5;
          }
        } else if (autoBalance) {
          forward = GyroBalanceCommand.calculateDriveAmount(swerve.getGyro().getRoll());
          sideways = 0;
          angular = 0;
        } else {
          angular = Math.abs(turnInput) > 0.3 ? turnInput : 0;
          //angular = Math.abs(xboxController.getRightX()) > 0.3 ? xboxController.getRightX() : 0;
        }
        swerve.drive(
                forward,
                sideways,
                angular
        );
      }
    }, swerve));

    intake.setDefaultCommand(new RunCommand(() -> {
      if (controller.getRawButton(2) || controller.getRawButton(1)) {
        intake.forward();
      } else if (controller.getRawButton(11)) {
        intake.reverse();
      } else {
        intake.stop();
      }
    }, intake));

    shooter.setDefaultCommand(new RunCommand(() -> {
      if (controller.getRawButton(4)) {
        shooter.lowShoot();
        leds.charging();
        leds.lowShoot();
      } else if (controller.getRawButton(3) || controller.getRawButton(6)) {
        shooter.mediumShoot();
        leds.mediumShoot();
      } else if (controller.getRawButton(5)) {
        shooter.highShoot();
        leds.highShoot();
      } else {
        shooter.shootStop();
        leds.normal();
      }
    }, shooter));
  }

  private void setMovementValues() {
    switch (pickedController)
    {
      case XBOX_CONTROLLER_OPTION:
        forwardInput = controller.getRawAxis(1);
        strafeInput = controller.getRawAxis(0);
        turnInput = controller.getRawAxis(4);
        break;
      case JOYSTICK_OPTION:
        forwardInput = controller.getRawAxis(1);
        strafeInput = controller.getRawAxis(0);
        turnInput = controller.getRawAxis(2);
        break;
    }


  }

  public void setController() {
    pickedController = controllerPicker.getSelected();
    switch (pickedController) {
      case XBOX_CONTROLLER_OPTION:
        controller = new XboxController(0);
        break;
      case JOYSTICK_OPTION:
        controller = new Joystick(0);
        break;
      default:
        break;
    }
  }

  private void configureButtonBindings() {

  }

  public Command getAutonomousCommand() {
    return chooser.getSelected().getAutonomousCommand();
  }

  public SwerveDriveSubsystem getSwerve() {
      return swerve;
  }

  public IntakeSubsystem getIntake() {
      return intake;
  }

  public IndexSubsystem getIndex() {
      return index;
  }

  public ShooterSubsystem getShooter() {
      return shooter;
  }

  public LEDSubsystem getLeds() {
      return leds;
  }

}
