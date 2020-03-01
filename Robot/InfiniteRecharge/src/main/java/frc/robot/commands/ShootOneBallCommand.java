package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer;

import java.util.logging.Logger;

public class ShootOneBallCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  int time = 0;

  public ShootOneBallCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getInstance().shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = 0;
    RobotContainer.getInstance().shooter.LoadBall();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getInstance().shooter.fireBall();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time > 60){
      return true;
    }
    return false;
  }
}