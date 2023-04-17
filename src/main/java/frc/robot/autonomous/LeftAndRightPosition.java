package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveByTimeCommand;

public class LeftAndRightPosition extends ShootAndDrive {

    public LeftAndRightPosition(RobotContainer robot) {
        super(robot, 2.5, 0.8);
    }

}
