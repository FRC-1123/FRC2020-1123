/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Driver Joystick
    public static final int driveJoystickChannel = 0;
    public static final int pivotButton = 1;
    public static final int limelightButton = 2;

    // Support Joystick
    public static final int supportJoystickChannel = 1;
    public static final int shooterButton = 3;

    // Drive Motors
    public static final int driveFrontLeftChannel = 11;
    public static final int driveRearLeftChannel = 12;
    public static final int driveFrontRightChannel = 13;
    public static final int driveRearRightChannel = 14;

    // Shooter Motors
    public static final int shootLeftChannel = 16;
    public static final int shootRightChannel = 18;

}
