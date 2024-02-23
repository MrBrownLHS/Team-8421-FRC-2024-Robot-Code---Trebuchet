// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CollectorLauncher;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoLaunchRun extends Command {
  public Command launchRunCommand(Drivetrain m_drivetrain, CollectorLauncher m_collectorlauncher) {
    return new SequentialCommandGroup(
      m_collectorlauncher.collectLaunchCommand(),
      new InstantCommand(() -> m_drivetrain.arcadeDrive(() -> 0.25, () -> 0.0)).withTimeout(3),
      new InstantCommand(() -> m_drivetrain.arcadeDrive(() -> 0.0, () -> 0.5)).withTimeout(5)
    );
  }




  /** Creates a new AutoRoutines. */
  public AutoLaunchRun () {}

  // Use addRequirements() here to declare subsystem dependencies.
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
