/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

// Motors Libraries
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.*;

// Drivetrain Motion
import frc.robot.subsystems.MechanumDriveSubsystem;

// Controlling Limelight
import frc.robot.commands.LimelightCommand;
import frc.robot.subsystems.LimelightSubsystem;

// Shooting Power Cells
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ShooterSubsystem;

// Logging Robot Status
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.logging.Logger;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Joystick m_driveJoystick = new Joystick(Constants.driveJoystickChannel);
  // private Joystick m_supportJoystick = new Joystick(Constants.supportJoystickChannel);

  WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.driveFrontLeftChannel);
  WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.driveRearLeftChannel);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.driveFrontRightChannel);
  WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.driveRearRightChannel);

  private MechanumDriveSubsystem m_robotDrive = new MechanumDriveSubsystem(frontLeft, rearLeft, frontRight, rearRight);

  private LimelightSubsystem m_limelightSubsystem = new LimelightSubsystem();
  private LimelightCommand m_limelightCommand = new LimelightCommand(m_limelightSubsystem);

  CANSparkMax motorFixedA = new CANSparkMax(Constants.shootLeftChannel, MotorType.kBrushless);
  CANSparkMax motorFixedB = new CANSparkMax(Constants.shootRightChannel, MotorType.kBrushless);
  private ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem(motorFixedA, motorFixedB);
  private ShooterCommand m_shooterCommand = new ShooterCommand(m_shooterSubsystem);

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton limelightButton = new JoystickButton(m_driveJoystick, Constants.limelightButton);
    limelightButton.whenReleased(m_limelightCommand); // Replace with Limelight Command

    JoystickButton shooterButton = new JoystickButton(m_driveJoystick, Constants.shooterButton);
    shooterButton.whileHeld(m_shooterCommand);
  }

  public void driveRobot() { // WORKS
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
    double ySpeed = m_driveJoystick.getX();
    double xSpeed = -m_driveJoystick.getY();
    double zSpeed = m_driveJoystick.getZ();
    double throttle = (1-m_driveJoystick.getThrottle())/2; // Limits Max Speed
    
    if (m_driveJoystick.getTrigger())
      m_robotDrive.swivelCartesian(ySpeed, xSpeed, zSpeed, throttle);
    else
      m_robotDrive.driveCartesian(ySpeed, xSpeed, zSpeed, throttle, 0.0);

    SmartDashboard.putNumber("xSpeed", ySpeed);
    SmartDashboard.putNumber("ySpeed", xSpeed);
    SmartDashboard.putNumber("zSpeed", zSpeed);
    SmartDashboard.putNumber("Throttle", throttle);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
     return null;
  }
}