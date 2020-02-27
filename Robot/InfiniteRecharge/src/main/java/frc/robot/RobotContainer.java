package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The singleton instance of this class.
  private static RobotContainer instance;
  private static final Logger logger = Logger.getLogger(frc.robot.RobotContainer.class.getName());

  public final Joystick driverJoystick = new Joystick(Constants.kJoystickChannel);

  public final MecanumDriveSubsystem driveSubsystem = new MecanumDriveSubsystem();
  public final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public final ShooterSubsystem shooter = new ShooterSubsystem();

  /**
   * Retrieves the single instance of RobotContainer
   */
  public synchronized static RobotContainer getInstance() {
    if (instance == null) {
      instance = new RobotContainer();

      logger.info("Initializing the JoystickControlSystem.");
      JoystickControlSystem.initialize();

      logger.info("Initializing the DashboardControlSystem.");
      DashboardControlSystem.initialize();
    }
    return instance;
  }

  /**
   * The private constructor for this singleton class.
   */
  private RobotContainer() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousCommand(); // Test(m_driveSubsystem);
  }
}