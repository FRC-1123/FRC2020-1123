/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.CAN;

import edu.wpi.first.wpilibj.MotorSafety;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;



public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
private final CANSparkMax m_motorFixedA;
  private final CANSparkMax m_motorFixedB;
  private final CANSparkMax[] m_motors;

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  CANEncoder m_motorFixedAEncoder;
  CANEncoder m_motorFixedBEncoder;
  CANEncoder[] m_motorEncoders;

  

  public static final double kDefaultMaxOutput = 1.0;
  protected double m_maxOutput = kDefaultMaxOutput;
   
  public ShooterSubsystem(CANSparkMax motorFixedA, CANSparkMax motorFixedB) {

    this.m_motors = new CANSparkMax[6];
    this.m_motorFixedA = motorFixedA;
    this.m_motors[0] = motorFixedA;
    this.m_motorFixedB = motorFixedB;
    this.m_motors[1] = motorFixedB;

    m_motorEncoders = new CANEncoder[6];
   m_motorFixedAEncoder = m_motorFixedA.getEncoder();

    m_motorEncoders[0] = m_motorFixedAEncoder;
    m_motorFixedBEncoder = m_motorFixedB.getEncoder();
    m_motorEncoders[1] = m_motorFixedBEncoder;

    
    

  }

  public void Shoot(){
  //  this.m_motorFixedA.set(1);
  //  this.m_motorFixedB.set(-1);
    logger.info("Shoot was called");

  //  SmartDashboard.putNumber("Shooter Motor 1 RPM ", m_motorFixedAEncoder.getVelocity());
  //  SmartDashboard.putNumber("Shooter Motor 2 RPM ", m_motorFixedBEncoder.getVelocity());
  }

  public void Stop(){
  //  this.m_motorFixedA.stopMotor();
  //  this.m_motorFixedB.stopMotor();
    
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
