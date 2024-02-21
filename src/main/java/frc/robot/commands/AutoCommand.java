// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.*;
import java.util.function.DoubleSupplier;





public class AutoCommand {
  private Drivetrain m_drivetrain;
  private CollectorLauncher m_collectorlauncher;
  private double moveSpeed, rotateSpeed;

public AutoCommand(Drivetrain m_drivetrain, double moveSpeed, double rotateSpeed, 
CollectorLauncher m_collectorlauncher) {
  this.m_drivetrain = m_drivetrain;
  this.m_collectorlauncher = m_collectorlauncher;
  this.moveSpeed = moveSpeed;
  this.rotateSpeed = rotateSpeed;
}

public Command launchRunRotateCommand() {
  return Commands.sequence(
    m_collectorlauncher.collectLaunchCommand().beforeStarting(
      m_drivetrain.arcadeDrive
    )
  )
}
 /** Creates a new autoLaunchDriveRotate. */

  