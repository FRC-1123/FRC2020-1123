package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;

import frc.robot.RobotContainer;

public class SetAutonomous3 extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  public SetAutonomous3() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    logger.info("Setting to Autonomous 3");
    RobotContainer.getInstance().setAutonomousCommand(3);
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