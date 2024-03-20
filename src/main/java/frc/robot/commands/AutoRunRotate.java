// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRunRotate extends SequentialCommandGroup {

  /** Creates a new AutoRoutines. */
  public AutoRunRotate (Drivetrain m_drivetrain) {
    addCommands(
      new RunCommand(() -> m_drivetrain.arcadeDrive(() -> 0.5, () -> 0.0)).withTimeout(3),
      new RunCommand(() -> m_drivetrain.arcadeDrive(() -> 0.0, () -> 0.5)).withTimeout(3)
    );
  }
}
