package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  private Joystick m_joystick = new Joystick(Constants.kJoystickChannel);
  // The robot's subsystems and commands are defined here.
  private final MecanumDriveSubsystem m_driveSubsystem = new MecanumDriveSubsystem();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    logger.info("Mecanum drive subsystem defaulting to driveCartesian.");
    m_driveSubsystem.setDefaultCommand(new RunCommand(() -> m_driveSubsystem.driveCartesian(m_joystick.getY(),
        m_joystick.getX(), m_joystick.getZ(), (1 - m_joystick.getThrottle()) / 2), m_driveSubsystem));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    logger.info("Binding buttons to commands.");

    // Binds the trigger to pivot the robot
    JoystickButton driveModeButton = new JoystickButton(m_joystick, 1);
    driveModeButton.whenHeld(new RunCommand(() -> m_driveSubsystem.pivotCartesian(m_joystick.getY(), m_joystick.getX(),
        m_joystick.getZ(), (1 - m_joystick.getThrottle()) / 2), m_driveSubsystem));

    // This binding will move to the second joystick and likely a different button number
    // Binds button 2 to control Limelight LEDs 
    JoystickButton ledButton = new JoystickButton(m_joystick, 2);
    ledButton.whenPressed(new InstantCommand(LimelightCommand::setLedStatus, new LimelightCamera()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousCommand();
  }
}
