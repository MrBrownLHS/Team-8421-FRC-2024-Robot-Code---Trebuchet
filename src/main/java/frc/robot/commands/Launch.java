// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Launcher;

public class Launch extends Command {
  private boolean isLauncherStarted;
  private boolean isCollectorStarted;
  private double startTime; 
  /** Creates a new Launch. */
  public Launch() {
    // Use addRequirements() here to declare subsystem dependencies.
    isLauncherStarted = false;
    isCollectorStarted = false;
    addRequirements(RobotContainer.m_notelaunch, RobotContainer.m_notecollect);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!isLauncherStarted) {
      Launcher.noteLaunch();
      isLauncherStarted = true;
      startTime = Timer.getFPGATimestamp();
    }

    if (Timer.getFPGATimestamp() - startTime >= 1.0 && !isCollectorStarted) {
      Collector.collect();
      isCollectorStarted = true;

    }

  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Launcher.noteLaunch();
    Collector.collect();
    isCollectorStarted = false;
    isLauncherStarted = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
