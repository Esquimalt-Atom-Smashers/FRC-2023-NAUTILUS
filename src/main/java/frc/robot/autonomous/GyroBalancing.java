package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.GyroBalanceCommand;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class GyroBalancing {

    private final SwerveDriveSubsystem subsystem;
    
    public GyroBalancing(SwerveDriveSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    public Command getAutonomousCommand() {
        return new GyroBalanceCommand(subsystem, 5);
    }

}
