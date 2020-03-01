package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.logging.Logger;

public class IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  public IntakeCommand() {
    addRequirements(RobotContainer.getInstance().intakeSubsystem);
  }

  @Override
  public void initialize() {
    // TODO: Should this be placed in execute instead?
    logger.info("Command :: Activate intake.");
    RobotContainer.getInstance().intakeSubsystem.resetStartTime();
    RobotContainer.getInstance().intakeSubsystem.Activate();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.getInstance().intakeSubsystem.Activate();    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // TODO: Should this be place in a separate command?
    logger.info("got to PickUp Stop");
    RobotContainer.getInstance().intakeSubsystem.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}