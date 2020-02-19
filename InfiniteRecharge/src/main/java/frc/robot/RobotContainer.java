package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  private Joystick m_joystick = new Joystick(Constants.kJoystickChannel);
  // The robot's subsystems and commands are defined here.
   private final MecanumDriveSubsystem m_driveSubsystem = new MecanumDriveSubsystem();
  // private final DoubleSolenoid Solenoid1 = new DoubleSolenoid(1, 0, 7);
  // private final DoubleSolenoid Solenoid2 = new DoubleSolenoid(1, 1, 6);
  // private final DoubleSolenoid Solenoid3 = new DoubleSolenoid(1, 2, 5);
  // private final DoubleSolenoid Solenoid4 = new DoubleSolenoid(1, 3, 4);


  // private final Subsystem_FloatAxle m_Solenoids = new Subsystem_FloatAxle(Solenoid1, Solenoid2,
  //  Solenoid3, Solenoid4);
  // private final Solenoid1Fire Solenoid1Command= new Solenoid1Fire(m_Solenoids);
  // private final Solenoid2Fire Solenoid2Command= new Solenoid2Fire(m_Solenoids);
  // private final Solenoid3Fire Solenoid3Command= new Solenoid3Fire(m_Solenoids);
  // private final Solenoid4Fire Solenoid4Command= new Solenoid4Fire(m_Solenoids);

  // private final CANSparkMax PickUpMotor = new CANSparkMax(19, MotorType.kBrushless);
  // private final DoubleSolenoid PickUpSolenoid = new DoubleSolenoid(1,0,7);

  // private final PickUpSubsystem m_PickUpSubsystem = new PickUpSubsystem(PickUpMotor, PickUpSolenoid);
 //  private final PickUpCommand PickUp = new PickUpCommand(m_PickUpSubsystem);


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
   logger.info("Mecanum drive subsystem defaulting to driveCartesian.");
    m_driveSubsystem.setDefaultCommand(new RunCommand(() -> m_driveSubsystem.driveCartesian(m_joystick.getY(),
       m_joystick.getX(), m_joystick.getZ(), (1 - m_joystick.getThrottle()) / 2), m_driveSubsystem));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    logger.info("Binding buttons to commands.");

    // Binds the trigger to pivot the robot
    JoystickButton driveModeButton = new JoystickButton(m_joystick, 1);
    driveModeButton.whenHeld(new RunCommand(() -> m_driveSubsystem.pivotCartesian(m_joystick.getY(), m_joystick.getX(),
        m_joystick.getZ(), (1 - m_joystick.getThrottle()) / 2), m_driveSubsystem));

    // JoystickButton SolenoidButton1 = new JoystickButton(m_joystick, 8);
    // JoystickButton SolenoidButton2 = new JoystickButton(m_joystick, 7);
    // JoystickButton SolenoidButton3 = new JoystickButton(m_joystick, 9);
    // JoystickButton SolenoidButton4 = new JoystickButton(m_joystick, 10);

    // SolenoidButton1.whenHeld(Solenoid1Command);
    // SolenoidButton2.whenHeld(Solenoid2Command);
    // SolenoidButton3.whenHeld(Solenoid3Command);
    // SolenoidButton4.whenHeld(Solenoid4Command);

    // JoystickButton PickUpButton = new JoystickButton(m_joystick, 2);
    // PickUpButton.toggleWhenActive(PickUp);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousCommandTest(m_driveSubsystem);
  }
}
