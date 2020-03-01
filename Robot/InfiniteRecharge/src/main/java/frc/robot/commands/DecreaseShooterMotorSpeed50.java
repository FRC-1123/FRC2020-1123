package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotContainer;

import java.util.logging.Logger;

public class DecreaseShooterMotorSpeed50 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  int time = 0;

  public DecreaseShooterMotorSpeed50() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getInstance().shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double newSpeed = RobotContainer.getInstance().shooter.getSetSpeed()-50;
    RobotContainer.getInstance().shooter.setSpeed(newSpeed);
    logger.info("Shooter MotorSpeed = " + newSpeed);
    SmartDashboard.putNumber("Shooter Motor Speed ", newSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //logger.info("Increase Motor Speed was called");
    time++;
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