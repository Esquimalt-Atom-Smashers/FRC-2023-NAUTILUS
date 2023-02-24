package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    public static class IntakeConstants {
        public static final double INTAKE_SPEED = 0.25;
        public static final boolean MOTOR_INVERTED = true;
    }

    private final VictorSPX intakeMotor = new VictorSPX(0);

    public IntakeSubsystem() {
        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    private void configureIntakeMotor() {
        intakeMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.setInverted(IntakeConstants.MOTOR_INVERTED);
    }

    public void forward() {
        intakeMotor.set(ControlMode.PercentOutput, IntakeConstants.INTAKE_SPEED);
    }

    public void backward() {
        intakeMotor.set(ControlMode.PercentOutput, -IntakeConstants.INTAKE_SPEED);
    }

    public void stop() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }
}