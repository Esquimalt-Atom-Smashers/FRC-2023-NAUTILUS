package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveByTimeCommand;

public class LeftAndRightPosition {

    public LeftAndRightPosition(){}
    
    public Command getAutonomousCommand(RobotContainer container) {
        var shooter = container.getShooter();
        var index = container.getIndex();
        var swerve = container.getSwerve();
        var intake = container.getIntake();
        swerve.reset();
        return new RunCommand(shooter::highShoot, shooter).withTimeout(1)
                .andThen(new RunCommand(index::indexForward, index).withTimeout(3))
                .andThen(new RunCommand(index::indexStop).withTimeout(1))
                .alongWith(new RunCommand(shooter::shootStop).withTimeout(1))
                .andThen(new DriveByTimeCommand(swerve, 0.1, 0.5, 0))
                .andThen(new RunCommand(swerve::reset, swerve).withTimeout(2))
                .andThen(new DriveByTimeCommand(swerve, 2.9, 0.8, -0.03))
                .alongWith(new RunCommand(intake::forward, intake));
                // .andThen(new RunCommand(() -> swerve.drive(1, 0, 0), swerve).withTimeout(5));
                //TODO: Drive back to go outside community (Extra points if we get a cube and go back and shoot it)
    }

}
