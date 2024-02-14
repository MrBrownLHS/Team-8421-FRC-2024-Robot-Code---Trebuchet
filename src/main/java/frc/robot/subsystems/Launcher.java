// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;

public class Launcher extends SubsystemBase {
  static PWMVictorSPX m_LaunchLeftVictorSPX = null;
  static PWMVictorSPX m_LaunchRightVictorSPX = null;
  /** Creates a new Launcher. */
  public Launcher() {
    m_LaunchLeftVictorSPX = new PWMVictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_LEFT_VICTORSPX);
    m_LaunchRightVictorSPX = new PWMVictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_RIGHT_VICTORSPX);

    
  }
  public void noteLaunch(){
    m_LaunchRightVictorSPX.setInverted(true);
    m_LaunchLeftVictorSPX.set(1);
    m_LaunchRightVictorSPX.set(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
