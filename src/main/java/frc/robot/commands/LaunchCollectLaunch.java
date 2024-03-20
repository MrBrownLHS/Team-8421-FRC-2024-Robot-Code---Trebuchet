// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.CollectorLauncher;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class LaunchCollectLaunch extends SequentialCommandGroup {
  /** Creates a new AutoRoutines. */
  public LaunchCollectLaunch (Drivetrain m_drivetrain, CollectorLauncher m_collectorlauncher) {
    addCommands(
      new RunCommand(() -> m_collectorlauncher.collectLaunchCommand()),
      new RunCommand(() -> m_drivetrain.arcadeDrive(() -> 0.5, () -> 0.0)).withTimeout(3),
      new RunCommand(() -> m_collectorlauncher.collectCommand()).withTimeout(2),
      new RunCommand(() -> m_drivetrain.arcadeDrive(() -> -0.5, () -> 0.0)).withTimeout(3),
      new RunCommand(() -> m_collectorlauncher.collectLaunchCommand()),
      new RunCommand(() -> m_collectorlauncher.collectlaunchStopCommand())      
    );
  }
}
