package frc.robot.subsystems;

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

  public ShooterSubsystem() {
    // Wipe any prior motor settings
    motorA.configFactoryDefault();
    motorB.configFactoryDefault();

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
  }

  public void SpinMotor(double desiredSpeed) {
    motorSetPoint = desiredSpeed;
    motorA.setNeutralMode(NeutralMode.Coast);
    motorB.setNeutralMode(NeutralMode.Coast);

    motorA.set(ControlMode.Velocity, desiredSpeed);
    motorB.follow(motorA);

    subsystemActive = true;

    logger.info("Shooter spinning at " + desiredSpeed);
    SmartDashboard.putNumber("Shooter Motor 1 RPM ", motorA.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Motor 2 RPM ", motorB.getSelectedSensorVelocity());
  }

  public void fireBall() {
    ballRamSolenoid.set(Value.kReverse);
    logger.info("Shooter piston fired.");
  }

  public void LoadBall() {
    ballRamSolenoid.set(Value.kForward);
    logger.info("Shooter piston retracted.");
  }

  public void Stop() {
    motorA.set(ControlMode.PercentOutput, 0);
    motorB.set(ControlMode.PercentOutput, 0);
    subsystemActive = false;
  }

  @Override
  public void periodic() {
    // TODO: Update dashboard motor speed via NetworkTables
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
    if(isActive())
      this.SpinMotor(desiredSpeed);
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