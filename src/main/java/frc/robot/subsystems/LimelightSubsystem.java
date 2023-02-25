package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
    public boolean hasTarget = false;

    public double tv = 0; //Valid target 0 or 1
    public double tx = 0; //x offset from crosshair
    public double ty = 0; //y offset from crosshair
    public double ta = 0; //Target area
    public double[] tid = new double[6]; // Id of primary apriltag

    public LimelightSubsystem() {

    }

    public void turnOnLimelight() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }

    public void turnOffLimelight() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }

    public void updateLimelight() {
//        tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
//        tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
//        ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
//        ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
        tid = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDoubleArray(new double[6]);

        hasTarget = tid[0] == 0;
    }

    public boolean hasTarget() {
        return hasTarget;
    }
}
