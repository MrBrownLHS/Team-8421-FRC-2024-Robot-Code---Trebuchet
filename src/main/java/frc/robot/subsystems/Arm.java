// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private CANSparkMax m_ArmLeftSparkMax = null;
  private CANSparkMax m_ArmRightSparkMax = null;
  private DigitalInput m_forwardArmStop = null;
  private DigitalInput m_reverseArmStop = null;
  


  /** Creates a new Arm. */
  public Arm() {
    m_ArmLeftSparkMax = new CANSparkMax(Constants.PivotConstants.PIVOT_MOTOR_LEFT_SPARKMAX, MotorType.kBrushed);
    m_ArmRightSparkMax = new CANSparkMax(Constants.PivotConstants.PIVOT_MOTOR_RIGHT_SPARKMAX, MotorType.kBrushed);
    m_forwardArmStop = new DigitalInput(Constants.LimitConstants.ARM_FORWARD_LIMIT_SWITCH);
    m_reverseArmStop = new DigitalInput(Constants.LimitConstants.ARM_REVERSE_LIMIT_SWITCH);

    


    

    setDefaultCommand(run(() -> {
      this.armStopCommand();
    }));
  }

  public Command armStopCommand() {
    return run(() -> {
      m_ArmLeftSparkMax.stopMotor();
      m_ArmLeftSparkMax.stopMotor();
    });
  }

  public Command pivotforwardCommand() {
    return run(() -> {
      m_ArmLeftSparkMax.set(0);
      m_ArmRightSparkMax.set(0);
    }).until(m_forwardArmStop::get).finallyDo(() -> {
      m_ArmLeftSparkMax.stopMotor();
      m_ArmLeftSparkMax.stopMotor();
    });
     
        
    //.andThen(
      //run(() ->{
        //m_ArmLeftSparkMax.stopMotor();
        //m_ArmRightSparkMax.stopMotor();
      //}));
            
   }

  public Command pivotreverseCommand() {
    return run(() -> {
      m_ArmLeftSparkMax.set(0.2);
      m_ArmRightSparkMax.set(-0.2);
    }).until(m_reverseArmStop::get).finallyDo(() -> {
      m_ArmLeftSparkMax.stopMotor();
      m_ArmLeftSparkMax.stopMotor();
    });
    
    //.andThen(
      //run(() ->{
        //m_ArmLeftSparkMax.stopMotor();
        //m_ArmRightSparkMax.stopMotor();
      //}));
     
   }

  public Command chainHangCommand() {
    return run(() -> {
      m_ArmLeftSparkMax.set(-0.75);
      m_ArmRightSparkMax.set(0.75);
    }).andThen(
      runOnce(() ->{
        m_ArmLeftSparkMax.stopMotor();
        m_ArmRightSparkMax.stopMotor();
      }));
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
