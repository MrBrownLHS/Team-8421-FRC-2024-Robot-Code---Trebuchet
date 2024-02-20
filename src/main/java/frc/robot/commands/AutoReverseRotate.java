// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class AutoReverseRotate {
  
  public static Command reverseRotate() {
    return new SequentialCommandGroup(
      new InstantCommand(() -> {
        m_drivetrain.arcadeDrive(-0.25, 0);
      }),
      new

    
  }

  private AutoReverseRotate() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
