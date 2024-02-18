// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivetrain;

public final class AutoForwardTurn {
  /** Example static factory for an autonomous command. */
  public static Command forwardTurn(Drivetrain m_drivetrain) {
    return new RunCommand(() -> m_drivetrain.arcadeDrive(0.5, 0))
    .withTimeout(1)
    .andThen(new RunCommand(() -> m_drivetrain.arcadeDrive(0,0.25))
    .withTimeout(5));
    
  }

  private AutoForwardTurn() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
