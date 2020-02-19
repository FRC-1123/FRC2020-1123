/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;


/**
 * Add your docs here.
 */
public class Subsystem_FloatAxle extends SubsystemBase {

  private DoubleSolenoid m_1;
  private DoubleSolenoid m_2;
  private DoubleSolenoid m_3;
  private DoubleSolenoid m_4;

  public Subsystem_FloatAxle(DoubleSolenoid M_1, DoubleSolenoid M_2, DoubleSolenoid M_3,
   DoubleSolenoid M_4) {
    m_1 = M_1;
    m_2 = M_2;
    m_3 = M_3;
    m_4 = M_4;
  }

  public void m_1Extend() {
    m_1.set(kForward);
  }

  public void m_1retract() {
    m_1.set(kReverse);
  }
  public void m_2Extend() {
    m_2.set(kForward);
  }

  public void m_2retract() {
    m_2.set(kReverse);
  }
  public void m_3Extend() {
    m_3.set(kForward);
  }

  public void m_3retract() {
    m_3.set(kReverse);
  }
  public void m_4Extend() {
    m_4.set(kForward);
  }

  public void m_4retract() {
    m_4.set(kReverse);
  }

}
