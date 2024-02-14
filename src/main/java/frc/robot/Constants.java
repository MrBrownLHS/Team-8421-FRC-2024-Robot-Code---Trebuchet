// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  public static class ArcadeDriveConstants {
    public static final int ARCADEDRIVE_LEFT_FRONT_VICTORSPX = 0;
    public static final int ARCADEDRIVE_LEFT_REAR_VICTORSPX = 1;
    public static final int ARCADEDRIVE_RIGHT_FRONT_VICTORSPX = 2;
    public static final int ARCADEDRIVE_RIGHT_REAR_VICTORSPX = 3;
  }
  public static class DriverConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int DRIVER_CONTROLLER_MOVE_AXIS = 1;
    public static final int DRIVER_COTNROLLER_ROTATE_AXIS = 4;
    
  }
  public static class CoPilotConstants {
    public static final int COPILOT_CONTROLLER_PORT = 1;

  }
  public static class CollectorConstants {
    public static final int COLLECTOR_MOTOR_VICTORSPX = 4;

  }

  public static class LaunchConstants {
    public static final int LAUNCH_MOTOR_LEFT_VICTORSPX = 5;
    public static final int LAUNCH_MOTOR_RIGHT_VICTORSPX = 6;

  }

  public static class PivotConstants {
    public static final int PIVOT_MOTOR_LEFT_VICTORSPX = 7;
    public static final int PIVOT_MOTOR_RIGHT_VICTORSPX = 8;

  }

  public static class LimitConstants {
    public static final int COLLECTOR_LIMIT_SWITCH = 0;
    public static final int ARM_FORWARD_LIMIT_SWITCH = 1;
    public static final int ARM_REVERSE_LIMIT_SWITCH = 2;
  }

  public static class AutonomousConstants {
    

}
}
//XboxOne Controller Map
    //public static final int LEFT_STICK_X = 0;
	  //public static final int LEFT_STICK_Y = 1;
	  //public static final int LEFT_TRIGGER = 2;
	  //public static final int RIGHT_TRIGGER = 3;
	  //public static final int RIGHT_STICK_X = 4;
	  //public static final int RIGHT_STICK_Y = 5;
    //public static final int A = 1;
	  //public static final int B = 2;
	  //public static final int X = 3;
	  //public static final int Y = 4;
	  //public static final int LB = 5;
	  //public static final int RB = 6;
	  //public static final int LOGO_LEFT = 7;
	  //public static final int LOGO_RIGHT = 8;
	  //public static final int LEFT_STICK_BUTTON = 9;
	  //public static final int RIGHT_STICK_BUTTON = 10;