package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    private final double INTAKE_SPEED = 0.25;

    private final VictorSPX intakeMotor = new VictorSPX(-1);

    public IntakeSubsystem() {
        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    public CommandBase intakeForward() {
        return this.runOnce(() -> intakeMotor.set(ControlMode.PercentOutput, -INTAKE_SPEED));
    }

    public CommandBase intakeBackward() {
        return this.runOnce(() -> intakeMotor.set(ControlMode.PercentOutput, INTAKE_SPEED));
    }

    public CommandBase intakeStop() {
        return this.runOnce(() -> intakeMotor.set(ControlMode.PercentOutput, 0));
    }
}