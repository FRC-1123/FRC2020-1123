package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.logging.Logger;

public class ExtendIntakePiston extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  int time = 0;

  public ExtendIntakePiston() {
    addRequirements(RobotContainer.getInstance().intakeSubsystem);
  }

  @Override
  public void initialize() {
    // TODO: Should this be placed in execute instead?
    // logger.info("Command :: Activate intake.");
    RobotContainer.getInstance().intakeSubsystem.ExtendPiston();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time++;
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // TODO: Should this be place in a separate command?
    // logger.info("got to PickUp Stop");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time>3){
      return true;
    }
    return false;
  }
}