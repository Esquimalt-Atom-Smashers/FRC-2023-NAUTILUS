// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
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

  private final SwerveDriveSubsystem swerve = new SwerveDriveSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final IndexSubsystem index = new IndexSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();

  private final Joystick controller = new Joystick(0);

  public RobotContainer() {
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
        swerve.drive(
                Math.abs(controller.getRawAxis(0)) > 0.1 ? controller.getRawAxis(0) : 0,
                Math.abs(controller.getRawAxis(1)) > 0.1 ? controller.getRawAxis(1) : 0,
                Math.abs(controller.getTwist()) > 0.2 ? controller.getTwist() : 0
        );
      }
    }, swerve));

    intake.setDefaultCommand(new RunCommand(() -> {
      if (controller.getRawButton(2)) {
        intake.forward();
      } else if (controller.getRawButton(11)) {
        intake.reverse();
      } else {
        intake.stop();
      }
    }, intake));

    shooter.setDefaultCommand(new RunCommand(() -> {
      if (controller.getRawButton(4)) shooter.lowShoot();
      else if (controller.getRawButton(3)) shooter.mediumShoot();
      else if (controller.getRawButton(5)) shooter.highShoot();
      else if (controller.getRawButton(6)) shooter.crazyShoot();
      else shooter.shootStop();
    }, shooter));
  }

  private void configureButtonBindings() {

  }

}
