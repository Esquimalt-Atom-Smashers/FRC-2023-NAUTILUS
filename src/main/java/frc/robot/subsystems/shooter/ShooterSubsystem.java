package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax frontShooter = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final SparkMaxPIDController frontShooterPID = frontShooter.getPIDController();

    private final CANSparkMax rearShooter = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final SparkMaxPIDController rearShooterPID = rearShooter.getPIDController();

    public ShooterSubsystem() {
        configureShooterMotors();
    }

    private void configureShooterMotors() {
        frontShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
        frontShooter.setInverted(ShooterConstants.Motor.FRONT_MOTOR_INVERTED);
        frontShooter.setSmartCurrentLimit(ShooterConstants.Motor.MOTOR_CURRENT_LIMIT);
        frontShooter.enableVoltageCompensation(ShooterConstants.Motor.MAX_VOLTAGE);

        rearShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rearShooter.setInverted(ShooterConstants.Motor.REAR_MOTOR_INVERTED);
        rearShooter.setSmartCurrentLimit(ShooterConstants.Motor.MOTOR_CURRENT_LIMIT);
        rearShooter.enableVoltageCompensation(ShooterConstants.Motor.MAX_VOLTAGE);

        frontShooterPID.setP(ShooterConstants.PID.P);
        frontShooterPID.setI(ShooterConstants.PID.I);
        frontShooterPID.setD(ShooterConstants.PID.D);
        frontShooterPID.setIZone(ShooterConstants.PID.IZ);
    }

    public void stop() {
        frontShooter.set(0);
        rearShooter.set(0);
    }

    public void lowShoot() {
        frontShooterPID.setReference(ShooterConstants.Motor.LOW_SHOOT_RPM, CANSparkMax.ControlType.kVelocity);
        rearShooterPID.setReference(-ShooterConstants.Motor.LOW_SHOOT_RPM, CANSparkMax.ControlType.kVelocity);
    }

    public void mediumShoot() {
        frontShooterPID.setReference(ShooterConstants.Motor.MEDIUM_SHOOT_RPM, CANSparkMax.ControlType.kVelocity);
        rearShooterPID.setReference(-ShooterConstants.Motor.MEDIUM_SHOOT_RPM, CANSparkMax.ControlType.kVelocity);
    }

    public void highShoot() {
        frontShooterPID.setReference(ShooterConstants.Motor.HIGH_SHOOT_RPM, CANSparkMax.ControlType.kVelocity);
        rearShooterPID.setReference(-ShooterConstants.Motor.HIGH_SHOOT_RPM, CANSparkMax.ControlType.kVelocity);
    }
}
