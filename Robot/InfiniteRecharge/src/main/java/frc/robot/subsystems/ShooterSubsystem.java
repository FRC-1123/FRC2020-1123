package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
// import com.revrobotics.CANEncoder;
// import com.revrobotics.CANError;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.CAN;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import frc.robot.Robot;



public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

  final TalonFXInvertType kInvertTypeA = TalonFXInvertType.CounterClockwise;
  final TalonFXInvertType kInvertTypeB = TalonFXInvertType.Clockwise;
  final NeutralMode kBrakeDurNeutral = NeutralMode.Coast;
  private final TalonFX m_motorA;
  private final TalonFX m_motorB;
  private final TalonFX[] m_motors;

  private final DoubleSolenoid BallShove;

  private Double WantedSpeed;

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  double Motor1Voltage = 1;
  double Motor2Voltage = -1;

  final static int ConfigTimeOut = 30;

  // FeedbackDevice m_motorAEncoder;
  // FeedbackDevice m_motorBEncoder;
  // FeedbackDevice[] m_motorEncoders;

 

  //PIDController Motor1 = new PIDController(1,1,1);
  //PIDController Motor2 = new PIDController(1,1,1);
  
  public static final double kDefaultMaxOutput = 1.0;
  protected double m_maxOutput = kDefaultMaxOutput;

  RobotContainer m_RobotContainer;


  public ShooterSubsystem(TalonFX motorA, TalonFX motorB, DoubleSolenoid Solenoid) {

    this.m_motors = new TalonFX[2];
    this.m_motorA = motorA;
    this.m_motors[0] = motorA;
    this.m_motorB = motorB;
    this.m_motors[1] = motorB;

    m_motorA.setInverted(kInvertTypeA);
    m_motorB.setInverted(kInvertTypeB);

    m_motorA.setNeutralMode(kBrakeDurNeutral);
    m_motorB.setNeutralMode(kBrakeDurNeutral);

    m_motorA.configOpenloopRamp(0.25);
    m_motorB.configOpenloopRamp(0.25);

    m_motorA.configNominalOutputForward(0, ConfigTimeOut);
		m_motorA.configNominalOutputReverse(0, ConfigTimeOut);
		m_motorA.configPeakOutputForward(1, ConfigTimeOut);
    m_motorA.configPeakOutputReverse(-1, ConfigTimeOut);

    m_motorA.config_kF(0, 1, ConfigTimeOut);
		m_motorA.config_kP(0, 0, ConfigTimeOut);
		m_motorA.config_kI(0, 0, ConfigTimeOut);
    m_motorA.config_kD(0,0, ConfigTimeOut);

    
     
    // m_motorB.configNominalOutputForward(0, 30);
		// m_motorB.configNominalOutputReverse(0, 30);
		// m_motorB.configPeakOutputForward(1, 30);
    // m_motorB.configPeakOutputReverse(-1, 30);
    
    // m_motorB.config_kF(0, 1, ConfigTimeOut);
		// m_motorB.config_kP(0, 0, ConfigTimeOut);
		// m_motorB.config_kI(0, 0, ConfigTimeOut);
		// m_motorB.config_kD(0,0, ConfigTimeOut);


    // m_motorEncoders = new FeedbackDevice[2];
    // m_motorAEncoder = m_motorA.getEncoder();
    // m_motorEncoders[0] = m_motorAEncoder;

    // m_motorBEncoder = m_motorB.getEncoder();
    // m_motorEncoders[1] = m_motorBEncoder;

   // Motor1.setTolerance(50);
    //Motor2.setTolerance(50);
    BallShove = Solenoid;
  }

  public void SpinMotor(){
    // this.m_motorA.set(ControlMode.PercentOutput, 0.5);
    // this.m_motorB.set(ControlMode.PercentOutput, 0.5);

    WantedSpeed = Robot.getShooterMotorSpeed();
    
    this.m_motorA.set(ControlMode.Velocity, WantedSpeed);
    //m_motorB.follow(m_motorA);
  
    logger.info("Shoot was called");

    SmartDashboard.putNumber("Shooter Motor 1 RPM ", m_motorA.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Motor 2 RPM ", m_motorB.getSelectedSensorVelocity());
  }

  public void fireBall(){
    BallShove.set(kReverse);
  }

  public void LoadBall(){
    BallShove.set(kForward);
  }

  public void Stop(){
   this.m_motorA.set(ControlMode.PercentOutput, 0);
   this.m_motorB.set(ControlMode.PercentOutput, 0);
    
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}