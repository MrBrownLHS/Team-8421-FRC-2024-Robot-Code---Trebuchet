// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class CollectorLauncher extends SubsystemBase {
  private PWMVictorSPX m_leftcollectorVictorSPX = null;
  private PWMVictorSPX m_launchLeftVictorSPX = null;
  private PWMVictorSPX m_launchRightVictorSPX = null;
  private DigitalInput m_collectorStop = null;
  private CollectorLauncher m_collectorlauncher = null;
  private PWMVictorSPX m_rightcollectorVictorSPX = null;
  
  
  


  public CollectorLauncher(){
    m_leftcollectorVictorSPX = new PWMVictorSPX(Constants.CollectorConstants.COLLECTOR_MOTOR_LEFT_VICTORSPX);
    m_rightcollectorVictorSPX = new PWMVictorSPX(Constants.CollectorConstants.COLLECTOR_MOTOR_RIGHT_VICTORSPX);
    m_collectorStop = new DigitalInput(Constants.LimitConstants.COLLECTOR_LIMIT_SWITCH);
    m_launchLeftVictorSPX = new PWMVictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_LEFT_VICTORSPX);
    m_launchRightVictorSPX = new PWMVictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_RIGHT_VICTORSPX);     
    m_collectorlauncher = new CollectorLauncher();
    

    

    setDefaultCommand(run(() -> {
      m_collectorlauncher.collectlaunchStopCommand();}));
  }
    

  //public Command collectCommand() {
    //return run(() -> {
      //m_leftcollectorVictorSPX.set(0.25);
      //m_rightcollectorVictorSPX.set(-0.25);
    //}).until(m_collectorStop::get).finallyDo(() -> {
      //m_leftcollectorVictorSPX.stopMotor();
      //m_rightcollectorVictorSPX.stopMotor();
    //});      
  // }

  public Command collectCommand() {
    return run(() -> {
      m_leftcollectorVictorSPX.set(0.25);
      m_rightcollectorVictorSPX.set(-0.25);
    }).andThen(
      runOnce(() -> {
      m_leftcollectorVictorSPX.stopMotor();
      m_rightcollectorVictorSPX.stopMotor();
    }));      
   }
  
   public Command collectReverseCommand() {
    return run(() -> {
      m_leftcollectorVictorSPX.set(-0.25);
      m_rightcollectorVictorSPX.set(0.25);
    }).andThen(
      runOnce(() -> {
        m_leftcollectorVictorSPX.stopMotor();
        m_rightcollectorVictorSPX.stopMotor();
   }));
    
  }

  public Command collectlaunchStopCommand() {
    return run(() -> {
      m_leftcollectorVictorSPX.stopMotor();
      m_rightcollectorVictorSPX.stopMotor();
      m_launchLeftVictorSPX.stopMotor();
      m_launchRightVictorSPX.stopMotor();
    });
  }

  public Command collectLaunchCommand() {
    return sequence(
      run(() -> {
      m_launchLeftVictorSPX.set(1);
      m_launchRightVictorSPX.set(-1);
      m_leftcollectorVictorSPX.set(0);
      m_rightcollectorVictorSPX.set(0);
    }).withTimeout(2.0),
      run(() -> {
      m_launchLeftVictorSPX.set(1);
      m_launchRightVictorSPX.set(-1);
      m_leftcollectorVictorSPX.set(0.5);
      m_rightcollectorVictorSPX.set(-0.5);
    }).withTimeout(5)
    ).alongWith(
      runOnce(() -> {
        m_leftcollectorVictorSPX.stopMotor();
        m_rightcollectorVictorSPX.stopMotor();
        m_launchLeftVictorSPX.stopMotor();
        m_launchRightVictorSPX.stopMotor();
      }));
    
  }

  private Command sequence(ParallelRaceGroup withTimeout, ParallelRaceGroup withTimeout2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sequence'");
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
