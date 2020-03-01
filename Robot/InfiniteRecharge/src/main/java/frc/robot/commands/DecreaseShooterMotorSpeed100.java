package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;

import frc.robot.RobotContainer;

public class DecreaseShooterMotorSpeed100 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  int time = 0;

  public DecreaseShooterMotorSpeed100() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double newSpeed = RobotContainer.getInstance().shooter.getSetSpeed()-100;
    RobotContainer.getInstance().shooter.setSpeed(newSpeed);
    logger.info("Shooter MotorSpeed = " + newSpeed);
    SmartDashboard.putNumber("Shooter Motor Speed ", newSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time++;
    //logger.info("Increase Motor Speed was called");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
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