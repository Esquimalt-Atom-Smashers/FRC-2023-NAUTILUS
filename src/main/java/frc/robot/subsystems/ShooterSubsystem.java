package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax frontShooter = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rearShooter = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    public ShooterSubsystem() {

    }

    public void shoot(double power) {
        frontShooter.set(power);
        rearShooter.set(power);
    }

}
