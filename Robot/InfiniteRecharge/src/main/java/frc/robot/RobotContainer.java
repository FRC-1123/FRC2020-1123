package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public final ClimberSubsystem Climber = new ClimberSubsystem();
  public final LimelightSubsystem Limelight = new LimelightSubsystem();
  private int autonomous = 0;
  private String autoString = "";

  /**`
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
    double throttle = JoystickControlSystem.getThrottle();
    if(throttle < -0.5){
      logger.info("returning Autonomous 1");
      return new AutonomousHigh(driveSubsystem);
    }
    if(throttle > 0.5){
      logger.info("returning Autonomous 2");
      return new AutonomousCommandLowGoalShoot(driveSubsystem);
    }
    return new AutonomousCommandTest(driveSubsystem);
    //return new AutonomousHigh(driveSubsystem);
  }



  public Command getAutonomousCommandInt() {
    SmartDashboard.putNumber("Auto", autonomous);
    if(autonomous == 1){
      logger.info("returning Autonomous 1");
      return new AutonomousHigh(driveSubsystem);
    }
    if(autonomous == 2){
      logger.info("returning Autonomous 2");
    }
    if(autonomous == 3){
      logger.info("returning Autonomous 3");
    }
    return new AutonomousCommandLowGoalShoot(driveSubsystem);
    //return new AutonomousHigh(driveSubsystem);
  }

  public Command getAutonomousCommandString() {
    if(autoString.equals("high")){
      logger.info("returning Autonomous 1");
      return new AutonomousHigh(driveSubsystem);
    }
    if(autoString.equals("low")){
      logger.info("returning Autonomous 2");
      return new AutonomousCommandLowGoalShoot(driveSubsystem);
    }
    return new AutonomousCommandLowGoalShoot(driveSubsystem);
    //return new AutonomousHigh(driveSubsystem);
  }

  public void setAutonomousCommand(int NumberOfCommand){
    autonomous = NumberOfCommand;
  }

  public int getAutoNum() {
    return autonomous;
  }

  public void setAutoString(String strAdd) {
    autoString = strAdd;
  }
}