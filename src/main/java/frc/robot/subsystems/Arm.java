// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private PWMVictorSPX m_ArmLeftVictorSPX = null;
  private PWMVictorSPX m_ArmRightVictorSPX = null;
  private DigitalInput m_forwardArmStop = null;
  private DigitalInput m_reverseArmStop = null;
  private Arm m_arm = null;

  /** Creates a new Arm. */
  public Arm() {
    m_ArmLeftVictorSPX = new PWMVictorSPX(Constants.PivotConstants.PIVOT_MOTOR_LEFT_VICTORSPX);
    m_ArmRightVictorSPX = new PWMVictorSPX(Constants.PivotConstants.PIVOT_MOTOR_RIGHT_VICTORSPX);
    m_forwardArmStop = new DigitalInput(Constants.LimitConstants.ARM_FORWARD_LIMIT_SWITCH);
    m_reverseArmStop = new DigitalInput(Constants.LimitConstants.ARM_REVERSE_LIMIT_SWITCH);
    m_arm = new Arm();

    setDefaultCommand(run(() -> {
      m_arm.pivotforwardCommand();
    }));
  }

  public Command pivotforwardCommand() {
    return run(() -> {
      m_ArmLeftVictorSPX.set(0.25);
      m_ArmRightVictorSPX.set(-0.25);
    }).until(m_forwardArmStop::get).finallyDo(() -> {
      m_ArmLeftVictorSPX.stopMotor();
      m_ArmLeftVictorSPX.stopMotor();
    });      
   }

  public Command pivotreverseCommand() {
    return run(() -> {
      m_ArmLeftVictorSPX.set(-0.25);
      m_ArmRightVictorSPX.set(0.25);
    }).until(m_reverseArmStop::get).finallyDo(() -> {
      m_ArmLeftVictorSPX.stopMotor();
      m_ArmLeftVictorSPX.stopMotor();
    }); 
   }

  public Command chainHangCommand() {
    return run(() -> {
      m_ArmLeftVictorSPX.set(0.25);
      m_ArmRightVictorSPX.set(-0.25);
    }).andThen(
      runOnce(() ->{
        m_ArmLeftVictorSPX.stopMotor();
        m_ArmRightVictorSPX.stopMotor();
      }));
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
