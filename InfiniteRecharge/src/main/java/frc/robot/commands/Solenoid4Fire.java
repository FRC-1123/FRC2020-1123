package frc.robot.commands;

import frc.robot.subsystems.Subsystem_FloatAxle;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.logging.Logger;


/**
 * An example command that uses an example subsystem.
 */
public class Solenoid4Fire extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  private Subsystem_FloatAxle m_subsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Solenoid4Fire(Subsystem_FloatAxle subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    logger.info("got to Solenoid4Fire");
    m_subsystem.m_4Extend();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    logger.info("got to Solenoid4 Retract");
    m_subsystem.m_4retract();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}