package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;



public class DriveByTimeCommand extends CommandBase {
    private final SwerveDriveSubsystem swerve;

    private double time;
    private double speed;

    private final Timer timer = new Timer();

    public DriveByTimeCommand(SwerveDriveSubsystem swerve, double time, double speed){
        this.swerve = swerve;
        this.time = time;
        this.speed = speed;
        addRequirements(swerve);
    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
        swerve.drive(speed,0, 0);
        while(timer.get() < time) {}
        swerve.drive(0, 0, 0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
