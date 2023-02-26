package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.SwerveDriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends CommandBase {
    /** The subsystem this command requires. */
    private final SwerveDriveSubsystem swerve;

    private final DoubleSupplier forward;
    private final DoubleSupplier sideways;
    private final DoubleSupplier angular;
    private final BooleanSupplier fieldCentric;

    public DefaultDriveCommand(SwerveDriveSubsystem swerve, DoubleSupplier forward, DoubleSupplier sideways, DoubleSupplier angular, BooleanSupplier fieldCentric) {
        this.swerve = swerve;

        this.forward = forward;
        this.sideways = sideways;
        this.angular = angular;
        this.fieldCentric = fieldCentric;

        addRequirements(swerve);
    }

    @Override
    public void execute() {
        swerve.drive(
                forward.getAsDouble(),
                sideways.getAsDouble(),
                angular.getAsDouble(),
                fieldCentric.getAsBoolean());

    }

    @Override
    public void end(boolean interrupted) {
        swerve.drive(0, 0, 0, true);
    }
}
