package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    logger.info("Team 1123 robot is initializing.");
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    CommandScheduler.getInstance().cancelAll();
    logger.info("All prior scheduled commands are cancelled.");

    LiveWindow.disableAllTelemetry();
    logger.info("All LiveWindow telementery is disabled.");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   */
  @Override
  public void robotPeriodic() {
    // This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    logger.info("The robot is initializing disabled mode.");
    CommandScheduler.getInstance().cancelAll();
    logger.info("All prior scheduled commands are cancelled.");
  }

  @Override
  public void disabledPeriodic() {
    // TODO: Execute motor safety stop
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    logger.info("The robot is initializing autonomous mode.");

    CommandScheduler.getInstance().cancelAll();
    logger.info("All prior scheduled commands are cancelled.");

    Command m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    logger.info("The autonomous command is scheduled.");
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //logger.info("The robot has entered autonomous periodic.");
    //logger.info("The command scheduler is running.");
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    logger.info("The robot is intializing teleop mode.");
    // This makes sure that the autonomous stops running when
    // teleop starts running.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      logger.info("The prior scheduled autonomous command is cancelled.");
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //logger.info("The robot has entered teleop periodic.");
    // This makes sure that the autonomous stops running when
    // teleop starts running.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    CommandScheduler.getInstance().run();
    //logger.info("The command scheduler is running.");
  }

  @Override
  public void testInit() {
    logger.info("The robot is initializing test mode.");
    CommandScheduler.getInstance().cancelAll();
    logger.info("All prior scheduled commands are cancelled.");
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    //logger.info("The robot has entered test periodic.");
    // TODO: Implement a test sequence which exercises robot functionality
    CommandScheduler.getInstance().run();
    //logger.info("The command scheduler is running.");
  }
}
