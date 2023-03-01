package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private static final double LOW_SHOOT_SPEED = 0.1;
    private static final double MEDIUM_SHOOT_SPEED = 0.3;
    private static final double HIGH_SHOOT_SPEED = 0.4;
    private static final double HIGHER_SHOOT_SPEED = 0.5;

    private final CANSparkMax frontShooter = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rearShooter = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    public ShooterSubsystem() {
        frontShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rearShooter.setIdleMode(CANSparkMax.IdleMode.kBrake);
        frontShooter.enableVoltageCompensation(12.2);
        rearShooter.enableVoltageCompensation(12.2);
        
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

    public void crazyShoot() {
        frontShooter.set(HIGHER_SHOOT_SPEED);
        rearShooter.set(-HIGHER_SHOOT_SPEED);
    }

}
