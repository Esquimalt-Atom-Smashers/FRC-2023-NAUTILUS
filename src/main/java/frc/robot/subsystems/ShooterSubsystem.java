package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    public static class ShooterConstants {
        public static final double LOW_SHOOT_SPEED = 0.1;
        public static final double MEDIUM_SHOOT_SPEED = 0.3;
        public static final double HIGH_SHOOT_SPEED = 0.4;

        public static final boolean FRONT_MOTOR_INVERTED = false;
        public static final boolean REAR_MOTOR_INVERTED = true;
        public static final int MOTOR_CURRENT_LIMIT = 30;
        public static final double MAX_VOLTAGE = -1;
    }

    private final CANSparkMax frontShooter = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rearShooter = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    public ShooterSubsystem() {
        configureShooterMotors();
    }

    private void configureShooterMotors() {
        frontShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
        frontShooter.setInverted(ShooterConstants.FRONT_MOTOR_INVERTED);
        frontShooter.setSmartCurrentLimit(ShooterConstants.MOTOR_CURRENT_LIMIT);
        frontShooter.enableVoltageCompensation(ShooterConstants.MAX_VOLTAGE);

        rearShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rearShooter.setInverted(ShooterConstants.REAR_MOTOR_INVERTED);
        rearShooter.setSmartCurrentLimit(ShooterConstants.MOTOR_CURRENT_LIMIT);
        rearShooter.enableVoltageCompensation(ShooterConstants.MAX_VOLTAGE);
    }

    public void stop() {
        frontShooter.set(0);
        rearShooter.set(0);
    }

    public void lowShoot() {
        frontShooter.set(ShooterConstants.LOW_SHOOT_SPEED);
        rearShooter.set(ShooterConstants.LOW_SHOOT_SPEED);
    }
    public void mediumShoot() {
        frontShooter.set(ShooterConstants.MEDIUM_SHOOT_SPEED);
        rearShooter.set(ShooterConstants.MEDIUM_SHOOT_SPEED);
    }

    public void highShoot() {
        frontShooter.set(ShooterConstants.HIGH_SHOOT_SPEED);
        rearShooter.set(ShooterConstants.HIGH_SHOOT_SPEED);
    }

//    public void crazyShoot() {
//        frontShooter.set(1);
//        rearShooter.set(-1);
//    }
}
