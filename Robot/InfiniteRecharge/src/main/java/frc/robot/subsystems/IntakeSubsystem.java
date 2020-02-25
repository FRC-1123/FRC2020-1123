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

  public IntakeSubsystem() {
    // TODO: Should we confirm the motor is accessible?
    // intakeMotor.getFirmwareString();
  }

  public void Activate() {
    intakeSolenoid.set(Value.kForward);
    logger.info("Intake extended.");

    intakeMotor.set(0.5);
    logger.info("Intake activated.");
  }

  public void Stop() {
    intakeMotor.stopMotor();
    intakeSolenoid.set(Value.kReverse);
    logger.info("Intake stopped and retracted.");
  }

  @Override
  public void periodic() {
    // TODO: Update dashboard motor speed via NetworkTables
  }
}
