package frc.robot.commands;

import java.sql.Time;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class GyroBalanceCommand extends CommandBase {

    private final Timer timer = new Timer();

    private SwerveDriveSubsystem subsystem;
    private double time;

    public GyroBalanceCommand(SwerveDriveSubsystem subsystem, double time) {
        this.subsystem = subsystem;
        this.time = time;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        var gyro = subsystem.getGyro();
        while (timer.get() < time) {
            subsystem.drive(0, -calculateDriveAmount(gyro.getRoll()), 0);
            try { Thread.sleep(10); }
            catch (Exception ignored) { }
        }
        subsystem.drive(0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    public static double calculateDriveAmount(double roll) {
        return Math.abs(roll) < 2 ? 0 : (roll / 180)*2;
    }
    
}
