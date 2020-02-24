package frc.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimelightCommand extends CommandBase {
    //private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    public LimelightCommand(){ }

    public static void setLedStatus(){
        Logger logger = Logger.getLogger(LimelightCommand.class.getName());
        logger.info("Limelight LED status is changing.");
    }
}