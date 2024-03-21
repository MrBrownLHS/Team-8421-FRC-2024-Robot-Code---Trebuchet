// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;



public class CollectorLauncher extends SubsystemBase {
  private WPI_VictorSPX m_leftcollectorVictorSPX = null;
  private WPI_VictorSPX m_launchLeftVictorSPX = null;
  private WPI_VictorSPX m_launchRightVictorSPX = null;
  private DigitalInput m_collectorStop = null;
  private WPI_VictorSPX m_rightcollectorVictorSPX = null;
  private WPI_VictorSPX m_frontcollectorVictorSPX = null;
  
  
  


  public CollectorLauncher(){
    m_leftcollectorVictorSPX = new WPI_VictorSPX(Constants.CollectorConstants.COLLECTOR_MOTOR_LEFT_VICTORSPX);
    m_rightcollectorVictorSPX = new WPI_VictorSPX(Constants.CollectorConstants.COLLECTOR_MOTOR_RIGHT_VICTORSPX);
    m_collectorStop = new DigitalInput(Constants.LimitConstants.COLLECTOR_LIMIT_SWITCH);
    m_launchLeftVictorSPX = new WPI_VictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_LEFT_VICTORSPX);
    m_launchRightVictorSPX = new WPI_VictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_RIGHT_VICTORSPX);  
    m_frontcollectorVictorSPX = new WPI_VictorSPX(Constants.CollectorConstants.COLLECTOR_MOTOR_FRONT_VICTORSPX);   
    
    

    setDefaultCommand(run(() -> {
      this.collectlaunchStopCommand();}));
  }
    

  public Command collectCommand() {
    return run(() -> {
      m_leftcollectorVictorSPX.set(0.25);
      m_rightcollectorVictorSPX.set(-0.25);
      m_frontcollectorVictorSPX.set(0.25);
    }).until(m_collectorStop::get).finallyDo(() -> {
      m_leftcollectorVictorSPX.stopMotor();
      m_rightcollectorVictorSPX.stopMotor();
    });      
  }
// No Limit Switch Collector
  //public Command collectCommand() {
    //return run(() -> {
      //m_leftcollectorVictorSPX.set(-0.5);
      //m_rightcollectorVictorSPX.set(0.5);
    //}).andThen(
     // runOnce(() -> {
      //m_leftcollectorVictorSPX.stopMotor();
      //m_rightcollectorVictorSPX.stopMotor();
   // }));      
   //}
    
  public Command collectReverseCommand() {
    return run(() -> {
      m_leftcollectorVictorSPX.set(0.5);
      m_rightcollectorVictorSPX.set(-0.5);
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
      m_frontcollectorVictorSPX.stopMotor();
    });
  }

  /* 
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
    
  }*/

  public Command launchCommand() {
    return run(() -> {
      m_launchLeftVictorSPX.set(1);
      m_launchRightVictorSPX.set(-1);
      m_leftcollectorVictorSPX.set(0);
      m_rightcollectorVictorSPX.set(0);
    }).withTimeout(1.0).andThen(
        run(() -> {
        m_launchLeftVictorSPX.set(1);
        m_launchRightVictorSPX.set(-1);
        m_leftcollectorVictorSPX.set(-0.5);
        m_rightcollectorVictorSPX.set(0.5);
      }).withTimeout(2),
      runOnce(() -> {
        m_leftcollectorVictorSPX.stopMotor();
        m_rightcollectorVictorSPX.stopMotor();
        m_launchLeftVictorSPX.stopMotor();
        m_launchRightVictorSPX.stopMotor();
      }));   
  }

  /*
  private Command sequence(ParallelRaceGroup withTimeout, ParallelRaceGroup withTimeout2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sequence'");
  }*/


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
