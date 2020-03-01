package frc.robot.subsystems;

import java.sql.Time;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;

import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;


/**
 * Creates a new ShooterSubsystem.
 */
public class ShooterSubsystem extends SubsystemBase {
  private Logger logger = Logger.getLogger(this.getClass().getName());
  private TalonFX motorA = new TalonFX(Constants.ShooterMotor1CanID);
  private TalonFX motorB = new TalonFX(Constants.ShooterMotor2CanID);
  private DoubleSolenoid ballRamSolenoid = new DoubleSolenoid(Constants.ShooterRamPCM,
      Constants.ShooterRamForwardModule, Constants.ShooterRamReverseModule);
  private double motorSetPoint = 0.0;
  private boolean subsystemActive = false;

  final static int ConfigTimeOut = 30;

  double throttle;

  Joystick driverJoystick = new Joystick(1);

  int Time = 0;
  int TimeSinceBallFire = 0;
  boolean FireBall = true;
  boolean BallLoaded = false;
  int NumberOfBallsFired = 0;

  public ShooterSubsystem() {
    // Wipe any prior motor settings
    motorA.configFactoryDefault();
    motorB.configFactoryDefault();

    motorA.setNeutralMode(NeutralMode.Coast);
    motorB.setNeutralMode(NeutralMode.Coast);

    // Set motor direction
    motorA.setInverted(TalonFXInvertType.CounterClockwise);
    motorB.setInverted(TalonFXInvertType.Clockwise);

    // TODO: Set motor current limits

    /* TODO: Is it necessary to set these inital values?
     * motorA.configNominalOutputForward(0, ConfigTimeOut);
     * motorA.configNominalOutputReverse(0, ConfigTimeOut);
     * motorA.configPeakOutputForward(1, ConfigTimeOut);
     * motorA.configPeakOutputReverse(-1, ConfigTimeOut);
     * 
     * motorB.configNominalOutputForward(0, ConfigTimeOut);
     * motorB.configNominalOutputReverse(0, ConfigTimeOut);
     * motorB.configPeakOutputForward(1, ConfigTimeOut);
     * motorB.configPeakOutputReverse(-1, ConfigTimeOut);
     */

    // Configure intial PID values
    motorA.config_kF(0, 1, ConfigTimeOut);
    motorA.config_kP(0, 0, ConfigTimeOut);
    motorA.config_kI(0, 0, ConfigTimeOut);
    motorA.config_kD(0, 0, ConfigTimeOut);

    motorA.configOpenloopRamp(0.4);
    motorB.configOpenloopRamp(0.4);

  }

  public void SpinMotor(double desiredSpeed) {
    // motorSetPoint = desiredSpeed;
    //  motorA.set(ControlMode.Velocity, motorSetPoint);
    // if(motorA.getSelectedSensorVelocity()<motorSetPoint){
    //   motorA.set(ControlMode.PercentOutput, 1);
    //   motorB.set(ControlMode.PercentOutput, 1);
    // }
    // else{
    //   motorA.set(ControlMode.PercentOutput, motorA.getMotorOutputPercent() *0.9);
    //   motorB.set(ControlMode.PercentOutput, motorA.getMotorOutputPercent() *0.9);
    // }
    // motorA.set(ControlMode.PercentOutput, 0.7);
    // motorB.follow(motorA);
    motorA.set(ControlMode.PercentOutput, motorSetPoint/10000);
    motorB.set(ControlMode.PercentOutput, motorSetPoint/10000);


    subsystemActive = true;

    logger.info("Shooter trying to spin at " + motorSetPoint);
    SmartDashboard.putNumber("Shooter Motor 1 RPM ", motorA.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Motor 2 RPM ", motorB.getSelectedSensorVelocity());
  }
  public void LowGoalSpin(){
    motorA.set(ControlMode.PercentOutput, 0.2);
    motorB.set(ControlMode.PercentOutput, 0.2);
  }

  public void fireBall() {
    ballRamSolenoid.set(Value.kReverse);
    logger.info("Shooter piston fired.");
  }

  public void LoadBall() {
    ballRamSolenoid.set(Value.kForward);
    logger.info("Shooter piston retracted.");
  }

  public void FireBallAndRetract(){
    if(FireBall == true ) {
      fireBall();
      FireBall = false;
      TimeSinceBallFire = Time;
      BallLoaded = false; 
      logger.info("Got to Fire Ball in FireBallAndRetract");
    }
    
    if (Time - TimeSinceBallFire > 60 && BallLoaded == false){
      LoadBall();
      NumberOfBallsFired++;
      BallLoaded = true;
      SmartDashboard.putNumber("Number Of Balls fired to low goal recently ", NumberOfBallsFired);
      logger.info("Got to Load Ball in FireBallAndRetract");
    }

    if(Time - TimeSinceBallFire > 100){
      FireBall = true;
      logger.info("Got to fireBall Boolean in FireBallAndRetract" + FireBall);
    }
  }
  public void FireBallAndRetractHigh(){
    if(FireBall == true ) {
      fireBall();
      FireBall = false;
      TimeSinceBallFire = Time;
      BallLoaded = false; 
      logger.info("Got to Fire Ball in FireBallAndRetract");
    }
    
    if (Time - TimeSinceBallFire > 60 && BallLoaded == false){
      LoadBall();
      NumberOfBallsFired++;
      BallLoaded = true;
      SmartDashboard.putNumber("Number Of Balls fired to low goal recently ", NumberOfBallsFired);
      logger.info("Got to Load Ball in FireBallAndRetract");
    }

    if(Time - TimeSinceBallFire > 210){
      FireBall = true;
      logger.info("Got to fireBall Boolean in FireBallAndRetract" + FireBall);
    }
  }
  public int getNumberOfBallsFired(){
    return NumberOfBallsFired;
  }

  public void ResetNumberOfBallsFired(){
    NumberOfBallsFired = 0;
  }

  public void Stop() {
    motorA.set(ControlMode.PercentOutput, 0);
    motorB.set(ControlMode.PercentOutput, 0);
    subsystemActive = false;
  }

  @Override
  public void periodic() {
    // TODO: Update dashboard motor speed via NetworkTables
    Time++;
    // motorSetPoint =  DashboardControlSystem.getSliderSpeed();
  }

  public boolean isActive(){
    return this.subsystemActive;
  }

  public double getSetSpeed(){
    return motorSetPoint;
  }

  public void setSpeed(double desiredSpeed){
    this.motorSetPoint = desiredSpeed;
    // Update the running motors with the new speed.
    // if(isActive())
    //   this.SpinMotor(Motor);
  }

  /**
   * Set the feed forward gain value, kf.
   * @param kfVal
   */
  public void setForwardGain(double kfVal) {
    motorA.config_kF(0, kfVal, ConfigTimeOut);
  }

  /**
   * Set the proportial gain value, P.
   * @param pVal
   */
  public void setPValue(double pVal) {
    motorA.config_kP(0, pVal, ConfigTimeOut);
  }

  /**
   * Set the integral value, I.
   * @param iVal
   */
  public void setIValue(double iVal) {
    motorA.config_kI(0, iVal, ConfigTimeOut);
  }

  /**
   * Set the derivative value, D.
   * @param dVal
   */
  public void setDValue(double dVal) {
    motorA.config_kD(0, dVal, ConfigTimeOut);
  }
}