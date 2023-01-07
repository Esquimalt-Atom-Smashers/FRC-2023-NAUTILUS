package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    
    private final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(1);

    public void setIntakeSpeed(double speed) {
        intakeMotor.set(speed);
    }

}
