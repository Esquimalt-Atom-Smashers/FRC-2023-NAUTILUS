package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private static final double LOW_SHOOT_SPEED = 0.1;
    private static final double MEDIUM_SHOOT_SPEED = 0.3;
    private static final double HIGH_SHOOT_SPEED = 0.4;

    private final CANSparkMax frontShooter = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rearShooter = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    public ShooterSubsystem() {
        frontShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rearShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void shootStop() {
        frontShooter.set(0);
        rearShooter.set(0);
    }

    public void lowShoot() {
        frontShooter.set(LOW_SHOOT_SPEED);
        rearShooter.set(-LOW_SHOOT_SPEED);
    }
    public void mediumShoot() {
        frontShooter.set(MEDIUM_SHOOT_SPEED);
        rearShooter.set(-MEDIUM_SHOOT_SPEED);
    }

    public void highShoot() {
        frontShooter.set(HIGH_SHOOT_SPEED);
        rearShooter.set(-HIGH_SHOOT_SPEED);
    }

}
