/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.MecanumDriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import java.util.logging.Logger;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousHigh extends CommandBase {
    private final MecanumDriveSubsystem m_subsystemDrive;
    private ShootCommand shoothigh = new ShootCommand();
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    double StartTime;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public AutonomousHigh(MecanumDriveSubsystem subsystem1) {
    m_subsystemDrive = subsystem1;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem1);
    StartTime = Timer.getFPGATimestamp();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double newSpeed = 6900;
    RobotContainer.getInstance().shooter.setSpeed(newSpeed);
    //SetShooterMotorSpeedHighGoal a = new SetShooterMotorSpeedHighGoal();
    //a.initialize();
    // StartTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((Timer.getFPGATimestamp()-StartTime)<2){
      logger.info("in autonomous command");
      shoothigh.initialize();
     // m_subsystemDrive.driveCartesian(0, 5, 0, 0.5);
    }
    else if((Timer.getFPGATimestamp()-StartTime)<12){
      shoothigh.execute(); 
    }
    else {
      m_subsystemDrive.driveCartesian(0, -5, 0, 0.25);
      shoothigh.end(true);
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_subsystemDrive.driveCartesian(0,0,0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if((Timer.getFPGATimestamp()-StartTime)>15){
      logger.info("Ending Autonomous move forward");
      return true;
    }
    return false;
  }
}