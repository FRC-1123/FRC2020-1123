package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.logging.Logger;

import frc.robot.RobotContainer;


/**
 * An example command that uses an example subsystem.
 */
public class LineUpOnGoal extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  int time = 0;
  int TimeSinceLastShot = 0;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LineUpOnGoal() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getInstance().Limelight);
    addRequirements(RobotContainer.getInstance().driveSubsystem);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      // RobotContainer.getInstance().intakeSubsystem.IntakeSlow();
      if(RobotContainer.getInstance().Limelight.getX()>0){
        RobotContainer.getInstance().driveSubsystem.driveCartesian(1, 0, 0, 0.5);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getInstance().driveSubsystem.driveCartesian(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(RobotContainer.getInstance().Limelight.getX()<0.3 && RobotContainer.getInstance().Limelight.getX()>-0.3){
      return true;
    }
    return false;
  }
}