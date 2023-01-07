package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.*;

public class ClimberSubsystem extends SubsystemBase {

    private static final boolean USE_BREAK_MODE = false;

    private final WPI_TalonSRX climberMotor = new WPI_TalonSRX(2);
    private final DutyCycleEncoder climberEncoder = new DutyCycleEncoder(5);

    public ClimberSubsystem() {
        climberMotor.setNeutralMode(USE_BREAK_MODE ? NeutralMode.Brake : NeutralMode.Coast);
    }

    public void setMotor(double value) {
        climberMotor.set(value);
    }

}