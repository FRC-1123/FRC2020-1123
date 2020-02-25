package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * An example command that uses an example subsystem.
 */
public class DecreaseShooterMotorSpeed50 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Logger logger = Logger.getLogger(this.getClass().getName());


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DecreaseShooterMotorSpeed50() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.setShooterMotorSpeed(Robot.getShooterMotorSpeed()-50);
    logger.info("Shooter MotorSpeed = " + Robot.getShooterMotorSpeed());
    SmartDashboard.putNumber("Shooter Motor Speed ", Robot.getShooterMotorSpeed());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //logger.info("Increase Motor Speed was called");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}