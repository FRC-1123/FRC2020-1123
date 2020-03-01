package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class JoystickControlSystem {
  public static Joystick driverJoystick;

  public static void initialize() {
    Logger logger = Logger.getLogger(frc.robot.JoystickControlSystem.class.getName());

    // For convenience reference the subsystems in the container
    driverJoystick = RobotContainer.getInstance().driverJoystick;
    MecanumDriveSubsystem driveSubsystem = RobotContainer.getInstance().driveSubsystem;

    logger.info("Mecanum drive subsystem defaulting to driveCartesian.");
    driveSubsystem.setDefaultCommand(new RunCommand(() -> driveSubsystem.driveCartesian(driverJoystick.getX(),
        -driverJoystick.getY(), driverJoystick.getZ()/2, (1 - driverJoystick.getThrottle()) / 2), driveSubsystem));
    
    logger.info("Driver button 1 bound to pivotCartesian");
    JoystickButton driveModeButton = new JoystickButton(driverJoystick, 1);
    driveModeButton.whenHeld(new RunCommand(() -> driveSubsystem.pivotCartesian(driverJoystick.getX(),
        -driverJoystick.getY(), driverJoystick.getZ(), (1 - driverJoystick.getThrottle()) / 2), driveSubsystem));

    // TODO: Move to the secondary driver control system
    // Binds button 3 to control Limelight LEDs
    JoystickButton ledButton = new JoystickButton(driverJoystick, 3);
    ledButton.whenPressed(new LimelightCommand(new LimelightSubsystem()));

    logger.info("Driver button 2 bound to activate intake.");
    JoystickButton intakeActivateButton = new JoystickButton(driverJoystick, 2);
    intakeActivateButton.toggleWhenActive(new IntakeCommand());

    // TODO: Move to the secondary driver control system
    // Shooter Button bindings
    logger.info("Driver button 7 bound to load shooter.");
    JoystickButton shooterLoadButton = new JoystickButton(driverJoystick, 7);    
    shooterLoadButton.whenPressed(new ShooterLoadCommand());
    
    logger.info("Driver button 8 bound to spin shooter.");
    JoystickButton activateMotorsButton = new JoystickButton(driverJoystick, 8);
    activateMotorsButton.toggleWhenActive(new SpinShooterMotorsCommand());  
    
    logger.info("Driver button 9 bound to shoot.");
    JoystickButton shootButton = new JoystickButton(driverJoystick, 9);    
    shootButton.whenPressed(new ShootOneBallCommand());
  }

  public static double getThrottle() {
    return driverJoystick.getThrottle();
  }
}