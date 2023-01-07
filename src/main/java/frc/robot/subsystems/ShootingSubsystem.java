package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class ShootingSubsystem {
    
    private final WPI_VictorSPX towerMotor1 = new WPI_VictorSPX(3);
    private final WPI_VictorSPX towerMotor2 = new WPI_VictorSPX(4);
    private final WPI_VictorSPX shooterMotor1 = new WPI_VictorSPX(5);
    private final WPI_VictorSPX shooterMotor2 = new WPI_VictorSPX(6);

}
