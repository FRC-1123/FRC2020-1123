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
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;



public class PickUpSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
  private final CANSparkMax m_motorA;
  private final CANSparkMax[] m_motors;
  private DoubleSolenoid m_Solenoid;

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  double Motor1Voltage = 0.5;
  
  public static final double kDefaultMaxOutput = 1.0;
  protected double m_maxOutput = kDefaultMaxOutput;


  public PickUpSubsystem(CANSparkMax motorA, DoubleSolenoid Solenoid) {

    this.m_motors = new CANSparkMax[6];
    this.m_motorA = motorA;
    this.m_motors[0] = motorA;
    m_Solenoid = Solenoid;
  }

  public void Activate(){
    m_motorA.set(Motor1Voltage);
    m_Solenoid.set(kForward);
   

  // SmartDashboard.putNumber("Shooter Motor 1 RPM ", m_motorFixedAEncoder.getVelocity());
  }

  public void Stop(){
   this.m_motorA.stopMotor();
    m_Solenoid.set(kReverse);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
