// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  static PWMVictorSPX m_ArmLeftVictorSPX = null;
  static PWMVictorSPX m_ArmRightVictorSPX = null;
  static DigitalInput m_forwardArmStop = null;
  static DigitalInput m_reverseArmStop = null;

  /** Creates a new Arm. */
  public Arm() {
    m_ArmLeftVictorSPX = new PWMVictorSPX(Constants.PivotConstants.PIVOT_MOTOR_LEFT_VICTORSPX);
    m_ArmRightVictorSPX = new PWMVictorSPX(Constants.PivotConstants.PIVOT_MOTOR_RIGHT_VICTORSPX);
    m_forwardArmStop = new DigitalInput(Constants.LimitConstants.ARM_FORWARD_LIMIT_SWITCH);
    m_reverseArmStop = new DigitalInput(Constants.LimitConstants.ARM_REVERSE_LIMIT_SWITCH);

  }

  public void pivotforward() {
    if (!m_forwardArmStop.get()) {
        m_ArmLeftVictorSPX.set(0.25);
        m_ArmRightVictorSPX.set(-0.25);
    } else {
        m_ArmLeftVictorSPX.stopMotor();
        m_ArmRightVictorSPX.stopMotor();
    }
    }

  public void pivotreverse() {
    if (!m_reverseArmStop.get()) {
        m_ArmLeftVictorSPX.set(-0.25);
        m_ArmRightVictorSPX.set(0.25);
    } else {
        m_ArmLeftVictorSPX.stopMotor();
        m_ArmRightVictorSPX.stopMotor();
    }
    }
  public void stopArm() {
    m_ArmLeftVictorSPX.stopMotor();
    m_ArmRightVictorSPX.stopMotor();
  }

  public void chainHang() {
    m_ArmLeftVictorSPX.set(0);
    m_ArmRightVictorSPX.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
