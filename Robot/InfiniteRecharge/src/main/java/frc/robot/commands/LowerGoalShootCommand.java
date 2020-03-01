package frc.robot.commands;

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
public class LowerGoalShootCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  int time = 0;
  int TimeSinceLastShot = 0;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LowerGoalShootCommand() {
    addRequirements(RobotContainer.getInstance().shooter);
    addRequirements(RobotContainer.getInstance().intakeSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    logger.info("got to motor Activate");
    RobotContainer.getInstance().shooter.LowGoalSpin();
    RobotContainer.getInstance().intakeSubsystem.IntakeSlow();
    RobotContainer.getInstance().shooter.ResetNumberOfBallsFired();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time++;
    if(time>50){
      RobotContainer.getInstance().shooter.FireBallAndRetract();
  }
      // RobotContainer.getInstance().intakeSubsystem.IntakeSlow();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    logger.info("got to Lower Goal Shoot Stop");
    RobotContainer.getInstance().shooter.Stop();
    RobotContainer.getInstance().shooter.LoadBall();
    RobotContainer.getInstance().intakeSubsystem.Stop();
    RobotContainer.getInstance().shooter.ResetNumberOfBallsFired();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(RobotContainer.getInstance().shooter.getNumberOfBallsFired() >= 6){
      return true;
    }
    return false;
  }
}