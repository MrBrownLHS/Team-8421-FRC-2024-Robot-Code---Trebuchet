// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.CollectorLauncher;
import frc.robot.subsystems.Drivetrain;

public final class AutoLaunchReverse {

  private Drivetrain m_drivetrain;
  private CollectorLauncher m_collectlaunch;

  public AutoLaunchReverse(Drivetrain m_drivetrain, CollectorLauncher m_collectlaunch) {
    this.m_drivetrain = m_drivetrain;
    this.m_collectlaunch = m_collectlaunch;
  }

  /** Example static factory for an autonomous command. */
  public Command launchReverse(){
    return Commands.sequence(
      m_drivetrain.arcadeDrive(0.1, 0),
      m_drivetrain.arcadeDrive(0,0),
      m_collectlaunch.collectLaunchCommand().withTimeout(8),
      m_collectlaunch.collectlaunchStopCommand(),
      m_drivetrain.arcadeDrive(-0.25,0));


  }
  

  private AutoLaunchReverse() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
