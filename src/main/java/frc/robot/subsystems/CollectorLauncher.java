// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class CollectorLauncher extends SubsystemBase {
  private PWMVictorSPX m_collectorVictorSPX = null;
  private PWMVictorSPX m_launchLeftVictorSPX = null;
  private PWMVictorSPX m_launchRightVictorSPX = null;
  private DigitalInput m_collectorStop = null;
  private CollectorLauncher m_collectorlauncher = null;
  private PWMVictorSPX m_rollerVictorSPX = null;
  private Debouncer m_collectDebounce = null;
  
  
  


  public CollectorLauncher(){
    m_collectorVictorSPX = new PWMVictorSPX(Constants.CollectorConstants.COLLECTOR_MOTOR_VICTORSPX);
    m_collectorStop = new DigitalInput(Constants.LimitConstants.COLLECTOR_LIMIT_SWITCH);
    m_launchLeftVictorSPX = new PWMVictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_LEFT_VICTORSPX);
    m_launchRightVictorSPX = new PWMVictorSPX(Constants.LaunchConstants.LAUNCH_MOTOR_RIGHT_VICTORSPX);     
    m_collectorlauncher = new CollectorLauncher();
    m_rollerVictorSPX = new PWMVictorSPX(Constants.CollectorConstants.FRONT_ROLLER_MOTOR_VICTORSPX);

    m_collectorVictorSPX.addFollower(m_rollerVictorSPX);

    m_collectDebounce = new Debouncer(0.1, DebounceType.kBoth);




    setDefaultCommand(run(() -> {
      m_collectorlauncher.collectlaunchStopCommand();}));
  }
    

  public Command collectCommand() {
    return run(() -> {
      m_collectorVictorSPX.set(0.25);
    }).until(m_collectorStop::get).finallyDo(() -> {
      m_collectorVictorSPX.stopMotor();
    });      
   }
    
  public Command collectReverseCommand() {
    return run(() -> {
      m_collectorVictorSPX.set(-0.25);
    }).andThen(
      runOnce(() -> {
        m_collectorVictorSPX.stopMotor();
   }));
    
  }

  public Command collectlaunchStopCommand() {
    return run(() -> {
      m_collectorVictorSPX.stopMotor();
      m_launchLeftVictorSPX.stopMotor();
      m_launchRightVictorSPX.stopMotor();
    });
  }

  public Command collectLaunchCommand() {
    return sequence(
      run(() -> {
      m_launchLeftVictorSPX.set(1);
      m_launchRightVictorSPX.set(-1);
      m_collectorVictorSPX.set(0);
    }).withTimeout(2.0),
      run(() -> {
      m_launchLeftVictorSPX.set(1);
      m_launchRightVictorSPX.set(-1);
      m_collectorVictorSPX.set(0.5);
    }).withTimeout(5)
    ).alongWith(
      runOnce(() -> {
        m_collectorVictorSPX.stopMotor();
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
