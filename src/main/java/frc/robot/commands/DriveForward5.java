// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveForward5 extends Command {
private double expireTime;
private double timeout;
private double timeSinceInitialized() {
  return 0;
}

}
  /** Creates a new DriveForward5. */
  public DriveForward5(double seconds) {
    addRequirements(RobotContainer.m_drivetrain);
    timeout = seconds;
    // Use addRequirements() here to declare subsystem dependencies.
  }
  protected void startTimer() {
    expireTime = timeSinceInitialized() + timeout;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    withTimeout(5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_drivetrain.arcadeDrive(0.25,0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.arcadeDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
