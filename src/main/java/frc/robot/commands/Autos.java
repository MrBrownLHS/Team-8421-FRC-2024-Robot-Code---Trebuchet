// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;


public final class Autos {
  public static Command autoLaunchReverse(Drivetrain m_drivetrain, CollectorLauncher m_collectorlauncher) {
    return Commands.sequence(
      m_collectorlauncher.collectLaunchCommand()
      .(m_drivetrain.arcadeDrive(DoubleSupplier moveSpeed, DoubleSupplier rotateSpeed));

    })
      
      
    )
    
      

    )

  }
  

}
