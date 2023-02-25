package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
    public static class IndexConstants {
        public static final double INDEX_SPEED = 0.25;

        public static final boolean FRONT_MOTOR_INVERTED = false;
        public static final boolean REAR_MOTOR_INVERTED = false;
    }

    private final VictorSPX frontIndex = new VictorSPX(2);
    private final VictorSPX rearIndex = new VictorSPX(1);

    public IndexSubsystem() {
        configureIndexMotors();
    }

    private void configureIndexMotors() {
        frontIndex.setNeutralMode(NeutralMode.Brake);
        frontIndex.setInverted(IndexConstants.FRONT_MOTOR_INVERTED);

        rearIndex.setNeutralMode(NeutralMode.Brake);
        rearIndex.setInverted(IndexConstants.REAR_MOTOR_INVERTED);
    }

    public void forward() {
        frontIndex.set(ControlMode.PercentOutput, -IndexConstants.INDEX_SPEED);
        rearIndex.set(ControlMode.PercentOutput, IndexConstants.INDEX_SPEED);
    }

    public void backward() {
        frontIndex.set(ControlMode.PercentOutput, IndexConstants.INDEX_SPEED);
        rearIndex.set(ControlMode.PercentOutput, -IndexConstants.INDEX_SPEED);
    }

    public void stop() {
        frontIndex.set(ControlMode.PercentOutput, 0);
        rearIndex.set(ControlMode.PercentOutput, 0);
    }


}
