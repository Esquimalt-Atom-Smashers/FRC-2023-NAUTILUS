package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

    private final MotorController frontRight = new Spark(0);
    private final MotorController rearRight  = new Spark(1);
    private final MotorController rearLeft = new Spark(2);
    private final MotorController frontLeft = new Spark(3);

    private final MotorControllerGroup leftMotor = new MotorControllerGroup(frontLeft, rearLeft);
    private final MotorControllerGroup rightMotor = new MotorControllerGroup(frontRight, rearRight);

    private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

    /**
     * This method moves the robot using the DifferentialDrive by the given x and y
     * @param moveAmount the amount the robot will move
     * @param rotation   the amount the robot will rotate
     */
    public void move(double moveAmount, double rotation) {
        // This is weird because the robot is built wrong
        robotDrive.arcadeDrive(rotation, -moveAmount);
    }

}
