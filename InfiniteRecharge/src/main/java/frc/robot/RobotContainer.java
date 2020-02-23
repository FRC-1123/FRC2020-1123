package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;



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

  //PickUp motors and solenoids
  private final CANSparkMax PickUpMotor = new CANSparkMax(Constants.PickUpMotorCanID, MotorType.kBrushless);
  private final DoubleSolenoid PickUpSolenoid = new DoubleSolenoid(1,0,7);
  //PickUp subsystem and command
  private final PickUpSubsystem m_PickUpSubsystem = new PickUpSubsystem(PickUpMotor, PickUpSolenoid);
  private final PickUpCommand PickUp = new PickUpCommand(m_PickUpSubsystem);

  //Shooter Motors and solenoids
  // private final CANSparkMax ShooterMotor1 = new CANSparkMax(Constants.ShooterMotor1CanID, MotorType.kBrushless);
  // private final CANSparkMax ShooterMotor2 = new CANSparkMax(Constants.ShooterMotor2CanID, MotorType.kBrushless);
  private final TalonFX ShooterMotor1 = new TalonFX(Constants.ShooterMotor1CanID);
  private final TalonFX ShooterMotor2 = new TalonFX(Constants.ShooterMotor2CanID);


  private final DoubleSolenoid ShooterSolenoid1 = new DoubleSolenoid(1, 1, 6);
  private final DoubleSolenoid ShooterSolenoid2 = new DoubleSolenoid(1, 2, 5);

  //Shooter subsystem and commands
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem(ShooterMotor1,
   ShooterMotor2, ShooterSolenoid1, ShooterSolenoid2);
   private final SpinShooterMotorsCommand m_SpinShooterMotorsCommand = new 
   SpinShooterMotorsCommand(m_ShooterSubsystem);
   private final ShooterLoadCommand m_ShooterLoadCommand = new ShooterLoadCommand(m_ShooterSubsystem);
   private final ShooterShootCommand m_ShooterShootCommand = new ShooterShootCommand(m_ShooterSubsystem);

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

    // This binding will move to the second joystick and likely a different button number
    // Binds button 2 to control Limelight LEDs 
    JoystickButton ledButton = new JoystickButton(m_joystick, 2);
    ledButton.whenPressed(new InstantCommand(LimelightCommand::setLedStatus, new LimelightCamera()));

    //PickUp Button binding
    JoystickButton PickUpButton = new JoystickButton(m_joystick, 2);
    PickUpButton.whenHeld(PickUp);

    //Shooter Button bindings
    JoystickButton SpinMotorsButton = new JoystickButton(m_joystick, 8);
    JoystickButton shooterLoadJoystickButton = new JoystickButton(m_joystick, 7);
    JoystickButton shooterShootJoystickButton = new JoystickButton(m_joystick, 9);
    SpinMotorsButton.toggleWhenActive(m_SpinShooterMotorsCommand);
    shooterLoadJoystickButton.whenPressed(m_ShooterLoadCommand);
    shooterShootJoystickButton.whenPressed(m_ShooterShootCommand);

    ShuffleboardTab Autonomous = Shuffleboard.getTab("Autonomous Tab");

    // ShuffleboardTab Teleop = Shuffleboard.getTab("Teleop Tab\t\t\t\t");
    // Teleop.add("Spin Motors", m_SpinShooterMotorsCommand);
    // Teleop.add("Shoot", m_ShooterShootCommand);
    // Teleop.add("Load", m_ShooterLoadCommand);
    // Teleop.add("Tester", new TestCommand());

    ShuffleboardTab EndGame = Shuffleboard.getTab("Endgame");
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
