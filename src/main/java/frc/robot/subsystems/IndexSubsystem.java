package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {

    private final double INDEX_SPEED = 0.25;

    private final VictorSPX frontIndex = new VictorSPX(2);
    private final VictorSPX rearIndex = new VictorSPX(1);

    public IndexSubsystem() {
        frontIndex.setNeutralMode(NeutralMode.Brake);
        rearIndex.setNeutralMode(NeutralMode.Brake);
    }

    public void indexForward() {
        frontIndex.set(ControlMode.PercentOutput, -INDEX_SPEED);
        rearIndex.set(ControlMode.PercentOutput, INDEX_SPEED);
    }

    public void indexBackward() {
        frontIndex.set(ControlMode.PercentOutput, INDEX_SPEED);
        rearIndex.set(ControlMode.PercentOutput, -INDEX_SPEED);
    }

    public void indexStop() {
        frontIndex.set(ControlMode.PercentOutput, 0);
        rearIndex.set(ControlMode.PercentOutput, 0);
    }


}
