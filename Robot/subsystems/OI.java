/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static final int driveControllerPort = 0;
  private enum _XBoxButton {
    kBumperLeft(5),
    kBumperRight(6),
    kStickLeft(9),
    kStickRight(10),
    kA(1),
    kB(2),
    kX(3),
    kY(4),
    kBack(7),
    kStart(8);
    private final int value;
    _XBoxButton(int value) {
      this.value = value;
    }
  }


  private XboxController controller;
  private double axleDelay;

  public OI() {
    controller = new XboxController(driveControllerPort);

  }

  public double getLeftStickX() {
    return controller.getX(Hand.kLeft);
  }

  public double getLeftStickY() {
    return controller.getY(Hand.kLeft);
  }
  
  public double getRightStickX() {
    return controller.getX(Hand.kRight);
  }

  public double getRightStickY() { 
   return controller.getY(Hand.kRight);
  }

  public double getAxleDelay() {
    return this.axleDelay;
  }

  public void setAxleDelay(double axleDelay) {
    this.axleDelay = axleDelay;
  }

  public static OI create() {
    return new OI();
  }
}
