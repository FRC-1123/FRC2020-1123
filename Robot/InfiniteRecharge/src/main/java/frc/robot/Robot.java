/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.logging.Logger;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.MecanumDriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  private static final int kFrontLeftChannel = 11; // 11,12,13,14
  private static final int kRearLeftChannel = 12;
  private static final int kFrontRightChannel = 13;
  private static final int kRearRightChannel = 14;
  private RobotContainer m_RobotContainer;

  private static final int kJoystickChannel = 0;

  private MecanumDriveTrain m_robotDrive;
  private Joystick m_stick;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight"); 

  @Override
  public void robotInit() {
    
    // You may need to change or remove this to match your robot (for later).
    // frontLeft.setInverted(true);
    // rearLeft.setInverted(true);

     m_stick = new Joystick(kJoystickChannel);
     m_RobotContainer = new RobotContainer();
     LiveWindow.disableAllTelemetry();
     
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
   // double ySpeed = m_stick.getX();
    //double xSpeed = -m_stick.getY();
   // double zSpeed = m_stick.getZ();
   // double throttle = (1-m_stick.getThrottle())/2; // Limits Max Speed
   // AHRS ahrs = new AHRS(SPI.Port.kMXP); 
   // if(m_stick.getTrigger()){
   //   SmartDashboard.putNumber("gyro angle ", ahrs.getAngle());
  //}

    
    // System.out.println("Yspeed" + ySpeed);
    // System.out.println("Xspeed" + xSpeed);
    // System.out.println("Throttle" + throttle);

  // if(m_stick.getTrigger())
    //  m_robotDrive.swivelCartesian(ySpeed, xSpeed, zSpeed, throttle);
    //else
      //m_robotDrive.driveCartesian(ySpeed, xSpeed, zSpeed, throttle, 0.0);

    // // LIMELIGHT CAMERA
    // NetworkTableEntry tx = table.getEntry("tx"); 
    // NetworkTableEntry ty = table.getEntry("ty"); 
    // NetworkTableEntry ta = table.getEntry("ta");

    // //read values periodically
    // double x = tx.getDouble(0.0);
    // double y = ty.getDouble(0.0);
    // double area = ta.getDouble(0.0);

    // //post to smart dashboard periodically
    // SmartDashboard.putNumber("LimelightX", x);
    // SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", area);
  }
}