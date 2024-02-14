// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;

public class Collector extends SubsystemBase {
  static PWMVictorSPX m_collectorVictorSPX = null;
  static DigitalInput m_collectorStop = null;


  public Collector(){
    m_collectorVictorSPX = new PWMVictorSPX(Constants.CollectorConstants.COLLECTOR_MOTOR_VICTORSPX);
    m_collectorStop = new DigitalInput(Constants.LimitConstants.COLLECTOR_LIMIT_SWITCH);
  }
  public void collect() {
    m_collectorVictorSPX.set(0.25);
  }

  public void collectStop() {
    m_collectorVictorSPX.stopMotor();
  }

  public void collectReverse() {
    m_collectorVictorSPX.set(-0.25);
  }

  public boolean isLimitSwitchPressed() {
    return m_collectorStop.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
