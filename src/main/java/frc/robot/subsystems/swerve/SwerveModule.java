package frc.robot.subsystems.swerve;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.*;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule {
    private final CANSparkMax driveMotor;
    private final CANSparkMax turnMotor;

    private final SparkMaxPIDController drivePIDController;
    private final SparkMaxPIDController turnPIDController;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turnEncoder;

    private final CANCoder canCoder;

    private final SimpleMotorFeedforward driveFF = new SimpleMotorFeedforward(SwerveConstants.DriveMotor.KS,
            SwerveConstants.DriveMotor.KV, SwerveConstants.DriveMotor.KA);

    private double resetIteration = 0;

    //TODO: If efficiency is a problem remove the method calls to configure the hardware, just add it all in constructor.
    public SwerveModule(int driveMotorId, int turnMotorId, int canCoderId) {
        driveMotor = new CANSparkMax(driveMotorId, CANSparkMaxLowLevel.MotorType.kBrushless);
        drivePIDController = driveMotor.getPIDController();
        driveEncoder = driveMotor.getEncoder();
        configureDrive();

        turnMotor = new CANSparkMax(turnMotorId, CANSparkMaxLowLevel.MotorType.kBrushless);
        turnPIDController = driveMotor.getPIDController();
        turnEncoder = driveMotor.getEncoder();
        configureTurn();

        canCoder = new CANCoder(canCoderId);
        configureCANCoder();

        resetEncoders();
    }

    /** Configures the drive motor, drive PID controller, and the drive relative Encoder. */
    private void configureDrive() {
        driveMotor.restoreFactoryDefaults();

        driveMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        driveMotor.setInverted(SwerveConstants.DriveMotor.MOTOR_INVERTED);
        driveMotor.enableVoltageCompensation(SwerveConstants.DriveMotor.MAX_VOLTAGE);
        driveMotor.setSmartCurrentLimit(SwerveConstants.DriveMotor.MOTOR_CURRENT_LIMIT);
        driveMotor.setClosedLoopRampRate(0.05);

        drivePIDController.setP(SwerveConstants.DriveMotor.PID_P, 0);
        drivePIDController.setI(SwerveConstants.DriveMotor.PID_I, 0);
        drivePIDController.setD(SwerveConstants.DriveMotor.PID_D, 0);
        drivePIDController.setIZone(SwerveConstants.DriveMotor.PID_IZ, 0);
        drivePIDController.setFF(SwerveConstants.DriveMotor.PID_F, 0);
        drivePIDController.setOutputRange(-1, 1, 0);

        driveEncoder.setPositionConversionFactor(SwerveConstants.DriveMotor.MK4_DRIVE_REDUCTION * Math.PI * SwerveConstants.DriveMotor.MK4_WHEEL_DIAMETER);
        driveEncoder.setVelocityConversionFactor(SwerveConstants.DriveMotor.MK4_DRIVE_REDUCTION * Math.PI * SwerveConstants.DriveMotor.MK4_WHEEL_DIAMETER / 60);
    }

    /** Configures the turn motor, turn PID controller, and the turn relative Encoder. */
    private void configureTurn() {
        turnMotor.restoreFactoryDefaults();

        turnMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        turnMotor.setInverted(SwerveConstants.TurnMotor.MOTOR_INVERTED);
        turnMotor.enableVoltageCompensation(SwerveConstants.TurnMotor.MAX_VOLTAGE);
        turnMotor.setSmartCurrentLimit(SwerveConstants.TurnMotor.MOTOR_CURRENT_LIMIT);

        turnPIDController.setP(SwerveConstants.TurnMotor.PID_P, 0);
        turnPIDController.setI(SwerveConstants.TurnMotor.PID_I, 0);
        turnPIDController.setD(SwerveConstants.TurnMotor.PID_D, 0);
        turnPIDController.setIZone(SwerveConstants.TurnMotor.PID_IZ, 0);
        turnPIDController.setFF(SwerveConstants.TurnMotor.PID_F, 0);
        turnPIDController.setOutputRange(-1, 1, 0);
        turnPIDController.setSmartMotionMaxAccel(SwerveConstants.TurnMotor.PID_MAX_ACCEL, 0);
        turnPIDController.setSmartMotionMaxAccel(SwerveConstants.TurnMotor.PID_MAX_ACCEL, 0);

        turnEncoder.setPositionConversionFactor(2.0 * Math.PI * SwerveConstants.TurnMotor.MK4_ROTATION_REDUCTION);
        turnEncoder.setVelocityConversionFactor(2.0 * Math.PI * SwerveConstants.TurnMotor.MK4_ROTATION_REDUCTION / 60);
    }

    /** Configures the CANCoder. */
    private void configureCANCoder() {
        canCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
        canCoder.configSensorDirection(false);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveSpeed(), new Rotation2d(turnEncoder.getPosition()));
    }

    public void setState(SwerveModuleState desiredState) {
        double maxAngle = turnEncoder.getPosition();

        if (maxAngle > Math.PI) {
            maxAngle -= 2.0 * Math.PI;
        }

        if (maxAngle < -Math.PI) {
            maxAngle += 2.0 * Math.PI;
        }

        SwerveModuleState state = SwerveModuleState.optimize(desiredState, new Rotation2d(maxAngle));

        double tempDriveFF = driveFF.calculate(state.speedMetersPerSecond);
        drivePIDController.setReference(state.speedMetersPerSecond, CANSparkMax.ControlType.kVelocity,
                0, tempDriveFF, SparkMaxPIDController.ArbFFUnits.kVoltage);
        setReferenceAngle(state.angle.getRadians());
    }

    public void setReferenceAngle(double referenceAngleRad) {
        double currentAngleRadians = turnEncoder.getPosition();

        if (turnEncoder.getVelocity() < SwerveConstants.Encoder.ENCODER_RESET_MAX_ANGULAR_VELOCITY) {
            if (++resetIteration >= SwerveConstants.Encoder.ENCODER_RESET_ITERATIONS) {
                resetIteration = 0;
                double absoluteAngle = getAbsoluteAngle();
                turnEncoder.setPosition(absoluteAngle);
                currentAngleRadians = absoluteAngle;
            }
        } else {
            resetIteration = 0;
        }

        double currentAngleRadiansMod = currentAngleRadians % (2.0 * Math.PI);
        if (currentAngleRadiansMod < 0.0) {
            currentAngleRadiansMod += 2.0 * Math.PI;
        }

        double adjustedReferenceAngleRadians = referenceAngleRad + currentAngleRadians - currentAngleRadiansMod;
        if (referenceAngleRad - currentAngleRadiansMod > Math.PI) {
            adjustedReferenceAngleRadians -= 2.0 * Math.PI;
        } else if (referenceAngleRad - currentAngleRadiansMod < -Math.PI) {
            adjustedReferenceAngleRadians += 2.0 * Math.PI;
        }

        turnPIDController.setReference(adjustedReferenceAngleRadians, CANSparkMax.ControlType.kPosition, 0);
    }

    /**
     * Returns the absolute angle of the CANcoder.
     *
     * @return the absolute angle of CANcoder
     */
    public double getAbsoluteAngle() {
        double angle = Math.toRadians(canCoder.getAbsolutePosition());
        angle %= 2 * Math.PI;
        return angle < 0.0 ? angle + 2.0 * Math.PI : angle;
    }

    /** Resets the drive and turn relative encoders. */
    public void resetEncoders() {
        driveEncoder.setPosition(0);
        turnEncoder.setPosition(getAbsoluteAngle());
    }

    /**
     * Returns the velocity of the drive motor in Rotations per minute (RPM).
     *
     * @return the velocity of the drive motor
     */
    public double getDriveSpeed() {
        return driveEncoder.getVelocity();
    }
}
