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

import edu.wpi.first.wpilibj.controller.PIDController;



public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
private final CANSparkMax m_motorFixedA;
  private final CANSparkMax m_motorFixedB;
  private final CANSparkMax[] m_motors;

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  double Motor1Voltage;
  double Motor2Voltage;

  CANEncoder m_motorFixedAEncoder;
  CANEncoder m_motorFixedBEncoder;
  CANEncoder[] m_motorEncoders;

  PIDController Motor1 = new PIDController(1,1,1);
    PIDController Motor2 = new PIDController(1,1,1);

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

    Motor1.setTolerance(100);
    Motor2.setTolerance(100);

  }

  public void Shoot(){
    Motor1Voltage = Motor1.calculate(m_motorFixedAEncoder.getVelocity(), 5000);
    Motor2Voltage = Motor2.calculate(m_motorFixedBEncoder.getVelocity(), -5000);

    if(Motor1Voltage > 1){
      Motor1Voltage = 1;
      logger.info("Motor1 tried to go too fast");
    }
    if(Motor1Voltage < -1){
      Motor1Voltage = -1;
      logger.info("Motor1 tried to go too fast");
    }
    if(Motor2Voltage > 1){
      Motor2Voltage = 1; 
      logger.info("Motor2 tried to go too fast");
    }
    if(Motor2Voltage < -1){
      Motor2Voltage = -1;
      logger.info("Motor2 tried to go too fast");
    }

   this.m_motorFixedA.set(Motor1Voltage);
   this.m_motorFixedB.set(Motor2Voltage);
    logger.info("Shoot was called");

   SmartDashboard.putNumber("Shooter Motor 1 RPM ", m_motorFixedAEncoder.getVelocity());
   SmartDashboard.putNumber("Shooter Motor 2 RPM ", m_motorFixedBEncoder.getVelocity());
  }

  public void Stop(){
   this.m_motorFixedA.stopMotor();
   this.m_motorFixedB.stopMotor();
    
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
