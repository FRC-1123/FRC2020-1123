package frc.robot.subsystems;

import java.util.logging.Logger;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  private final CANSparkMax intakeMotor = new CANSparkMax(Constants.IntakeMotorCanID, MotorType.kBrushless);
  private final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(Constants.IntakePistonPCM,
      Constants.IntakePistonForwardModule, Constants.IntakePistonReverseModule);
    double Time = 0.0;
    public double StartTime = 0.0;

  public IntakeSubsystem() {
    // TODO: Should we confirm the motor is accessible?
    // intakeMotor.getFirmwareString();
  }

  public void Activate() {
    intakeSolenoid.set(Value.kReverse);
    logger.info("Intake extended.");
    if(Time-StartTime>25){
      intakeMotor.set(-1);
      logger.info("Intake activated.");
    }
  }

  public void resetStartTime(){
    StartTime = Time;
  }

  public void IntakeSlow(){
    intakeSolenoid.set(Value.kReverse);
    logger.info("Intake extended.");

    intakeMotor.set(-0.35);
    logger.info("Intake activated.");
  }

  public void IntakeSlowHigh(){
    intakeSolenoid.set(Value.kReverse);
    logger.info("Intake extended.");

    intakeMotor.set(-0.25);
    logger.info("Intake activated.");
  }

  public void Stop() {
    intakeMotor.stopMotor();
    intakeSolenoid.set(Value.kForward);
    logger.info("Intake stopped and retracted.");
    Time = 0;
  }

  public void RetractPiston(){
    intakeSolenoid.set(Value.kReverse);
  }

  public void ExtendPiston(){
    intakeSolenoid.set(Value.kForward);
  }

  @Override
  public void periodic() {
    // TODO: Update dashboard motor speed via NetworkTables
    Time++;
  }
}
