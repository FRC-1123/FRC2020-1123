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

  int P, I, D = 1;
  double setpoint;
  double integralA, previous_errorA, derivativeA, errorA, rcwA = 0;
  double integralB, previous_errorB, derivativeB, errorB, rcwB = 0;
   
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

  public void Shoot(int rmp){
    this.setpoint = rmp;
   // if (previous_errorA != 0 || previous_errorB !=0)
  //  {
      PID();
      if(rcwA > 1){
        logger.info("Motor went too fast forwards");
        this.rcwA = 1;
     }
     if(rcwA < -1){
      logger.info("Motor went too fast backwards");
      this.rcwA = -1;
   }
     
     if(rcwB > 1){
      logger.info("Motor went too fast forwards");
      this.rcwB = 1;
       }

    if(rcwB < -1){
      logger.info("Motor went too fast backwards");
      this.rcwB = -1;
    }
      this.m_motorFixedA.set(rcwA);
      this.m_motorFixedB.set(-rcwB);
  //  }

  //  this.m_motorFixedA.set(1);
  //  this.m_motorFixedB.follow(m_motorFixedA, true)
  //  this.m_motorFixedB.set(-1);
    logger.info("Shoot was called");

   SmartDashboard.putNumber("Shooter Motor 1 RPM ", m_motorFixedAEncoder.getVelocity());
   SmartDashboard.putNumber("Shooter Motor 2 RPM ", m_motorFixedBEncoder.getVelocity());
  }


  private void PID() {
    errorA = setpoint - m_motorFixedAEncoder.getVelocity(); // Error = Target - Actual
    this.integralA += (errorA*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    derivativeA = (errorA - this.previous_errorA) / .02;
    this.rcwA = (P*errorA + I*this.integralA + D*derivativeA)/5800;
    previous_errorA = errorA;
    errorB = setpoint + m_motorFixedBEncoder.getVelocity(); // Error = Target - Actual
    this.integralB += (errorB*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    derivativeB = (errorB - this.previous_errorB) / .02;
    this.rcwA = (P*errorB + I*this.integralB + D*derivativeB)/5800;
    previous_errorB = errorB;
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
