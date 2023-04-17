// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
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

  private final Joystick controller = new Joystick(0);

  // Auto
  // A chooser for autonomous commands
  SendableChooser<ShootAndDrive> chooser = new SendableChooser<>();


  public RobotContainer() {
    chooser.setDefaultOption("Middle Position", new MiddlePosition(this));
    chooser.addOption("Left And Right Position", new LeftAndRightPosition(this));
    SmartDashboard.putData(chooser);
    // End Auto

    leds.normal();

    configureButtonBindings();

    index.setDefaultCommand(new RunCommand(() -> {
      if (controller.getRawButton(1)) index.indexForward();
      else if (controller.getRawButton(12)) index.indexBackward();
      else index.indexStop();
    }, index));

    swerve.setDefaultCommand(new RunCommand(() -> {
      if (controller.getRawButton(9)) {
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

        double multiplier = controller.getRawButton(8) ? 3 : 1;

        if (autoSnap || normalDrive) {
          forward = Math.abs(controller.getRawAxis(0)) > 0.2 ? controller.getRawAxis(0) * multiplier : 0;
          sideways = Math.abs(controller.getRawAxis(1)) > 0.2 ? controller.getRawAxis(1) * multiplier : 0;
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
          angular = Math.abs(controller.getTwist()) > 0.3 ? controller.getTwist() : 0;
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
