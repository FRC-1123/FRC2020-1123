package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import frc.robot.commands.*;

public class DashboardControlSystem {

  public static void initialize() {
    Logger logger = Logger.getLogger(frc.robot.DashboardControlSystem.class.getName());

    // TODO: Add controls for autonomous mode
    // ShuffleboardTab Autonomous = Shuffleboard.getTab("Autonomous Tab");

    ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleop\t\t\t\t");
    teleopTab.add("Spin Motors", new SpinShooterMotorsCommand());
    teleopTab.add("Shooter Motors Start",  new StartShooterMotorsCommand());
    teleopTab.add("Shooter Motors Stop", new StopShooterMotorsCommand());
    teleopTab.add("Increase Shooter Motor Speed 50", new IncreaseShooterMotorSpeed50());
    teleopTab.add("Increase Shooter Motor Speed 100", new IncreaseShooterMotorSpeed100());
    teleopTab.add("Decrease Shooter Motor Speed 50", new DecreaseShooterMotorSpeed50());
    teleopTab.add("Decrease Shooter Motor Speed 100", new DecreaseShooterMotorSpeed100());
    teleopTab.add("Shoot", new ShooterShootCommand());
    teleopTab.add("Load", new ShooterLoadCommand());

    // TODO: Add controls for end game climb
    // ShuffleboardTab EndGame = Shuffleboard.getTab("Endgame");
  }
}