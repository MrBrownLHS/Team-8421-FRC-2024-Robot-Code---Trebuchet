// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;



public class DriveArcade extends Command {

  private final Drivetrain m_driveArcade;
  
  
  public DriveArcade(Drivetrain m_driveArcade) {
    this.m_driveArcade = m_driveArcade;
    
    addRequirements(m_driveArcade);
    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveArcade.arcadeDrive(0,0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveArcade.arcadeDrive(0.25, 0.25);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveArcade.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
