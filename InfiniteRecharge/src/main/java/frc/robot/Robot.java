package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.wpilibj.Compressor;

public class Robot extends TimedRobot {
  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private Compressor c = new Compressor(0);
  public static double ShooterMotorSpeed = 0; 

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    logger.info("Team 1123 robot is initializing.");

    CommandScheduler.getInstance().cancelAll();
    logger.info("All prior scheduled commands are cancelled.");

    LiveWindow.disableAllTelemetry();
    logger.info("All LiveWindow telementery is disabled to avoid loop overrun errors.");

    // By calling getInstance the RobotContainer will construct itself
    RobotContainer.getInstance();
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
    // TODO: Execute motor stop
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    logger.info("The robot is initializing autonomous mode.");
    c.setClosedLoopControl(true);

    CommandScheduler.getInstance().cancelAll();
    logger.info("All prior scheduled commands are cancelled.");

    Command m_autonomousCommand = RobotContainer.getInstance().getAutonomousCommand();
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
    if (RobotContainer.getInstance().getAutonomousCommand() != null) {
      RobotContainer.getInstance().getAutonomousCommand().cancel();
      logger.info("The prior scheduled autonomous command is cancelled.");
      c.setClosedLoopControl(true);
    }
    Shuffleboard.selectTab("Teleop");
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //logger.info("The robot has entered teleop periodic.");
    // This makes sure that the autonomous stops running when
    // teleop starts running.
    if (RobotContainer.getInstance().getAutonomousCommand() != null) {
      RobotContainer.getInstance().getAutonomousCommand().cancel();
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