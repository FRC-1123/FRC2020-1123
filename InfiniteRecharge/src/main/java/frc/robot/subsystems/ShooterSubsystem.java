/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

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



public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

  final TalonFXInvertType kInvertType = TalonFXInvertType.CounterClockwise;
  final NeutralMode kBrakeDurNeutral = NeutralMode.Coast;
  private final TalonFX m_motorA;
  private final TalonFX m_motorB;
  private final TalonFX[] m_motors;

  private final DoubleSolenoid BallShove;
  private final DoubleSolenoid BallBlock;

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  double Motor1Voltage = 1;
  double Motor2Voltage = -1;

  // FeedbackDevice m_motorAEncoder;
  // FeedbackDevice m_motorBEncoder;
  // FeedbackDevice[] m_motorEncoders;

 

  //PIDController Motor1 = new PIDController(1,1,1);
  //PIDController Motor2 = new PIDController(1,1,1);
  
  public static final double kDefaultMaxOutput = 1.0;
  protected double m_maxOutput = kDefaultMaxOutput;


  public ShooterSubsystem(TalonFX motorA, TalonFX motorB, DoubleSolenoid SolenoidA,
   DoubleSolenoid SolenoidB) {

    this.m_motors = new TalonFX[2];
    this.m_motorA = motorA;
    this.m_motors[0] = motorA;
    this.m_motorB = motorB;
    this.m_motors[1] = motorB;

    m_motorA.setInverted(kInvertType);
    m_motorB.setInverted(kInvertType);

    m_motorA.setNeutralMode(kBrakeDurNeutral);
    m_motorB.setNeutralMode(kBrakeDurNeutral);

    m_motorA.configOpenloopRamp(0.25);
    m_motorB.configOpenloopRamp(0.25);


    // m_motorEncoders = new FeedbackDevice[2];
    // m_motorAEncoder = m_motorA.getEncoder();
    // m_motorEncoders[0] = m_motorAEncoder;

    // m_motorBEncoder = m_motorB.getEncoder();
    // m_motorEncoders[1] = m_motorBEncoder;

   // Motor1.setTolerance(50);
    //Motor2.setTolerance(50);

    BallShove = SolenoidA;
    BallBlock = SolenoidB;

  }

  public void SpinMotor(){
    //Motor1Voltage = Motor1.calculate(m_motorFixedAEncoder.getVelocity(), 5000);
    //Motor2Voltage = Motor2.calculate(m_motorFixedBEncoder.getVelocity(), 5000);

    // if(Motor1Voltage > 1){
    //   Motor1Voltage = 1;
    //   logger.info("Motor1 tried to go too fast");
    // }
    // if(Motor1Voltage < -1){
    //   Motor1Voltage = -1;
    //   logger.info("Motor1 tried to go too fast");
    // }
    // if(Motor2Voltage > 1){
    //   Motor2Voltage = 1; 
    //   logger.info("Motor2 tried to go too fast");
    // }
    // if(Motor2Voltage < -1){
    //   Motor2Voltage = -1;
    //   logger.info("Motor2 tried to go too fast");
    // }

   this.m_motorA.set(ControlMode.PercentOutput, 0.95);
   this.m_motorB.set(ControlMode.PercentOutput, -0.95);

    logger.info("Shoot was called");

    SmartDashboard.putNumber("Shooter Motor 1 RPM ", m_motorA.getSelectedSensorVelocity()/2048);
    SmartDashboard.putNumber("Shooter Motor 2 RPM ", m_motorB.getSelectedSensorVelocity()/2048);
  }

  public void fireBall(){
    BallShove.set(kForward);
    BallBlock.set(kReverse);
  }

  public void LoadBall(){
    BallShove.set(kReverse);
    BallBlock.set(kForward);
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
