// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.CollectorLauncher;

/** Add your docs here. */
public class AutoCommands {
    private Drivetrain m_drivetrain;
    private CollectorLauncher m_collectorlauncher;


    public AutoCommands(Drivetrain m_drivetrain, CollectorLauncher m_collectorlauncher) {
        this.m_collectorlauncher = m_collectorlauncher;
        this.m_drivetrain = m_drivetrain;

    }

    public Command autoDriveForward() {
        return Commands.sequence(
            m_drivetrain.arcadeDrive(() -> 0.5, () -> 0)
            .withTimeout(2),
            m_drivetrain.driveStop(0, 0));
    }
    
    public Command autoLaunchDrive() {
        return Commands.sequence(
        m_collectorlauncher.launchCommand()
        .andThen(m_drivetrain.arcadeDrive(() -> 0.5, () -> 0))
        .withTimeout(2),
        m_drivetrain.driveStop(0, 0));
        
    }

}
