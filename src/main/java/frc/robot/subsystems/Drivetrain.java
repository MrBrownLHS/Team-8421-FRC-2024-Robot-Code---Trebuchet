// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;



public class Drivetrain extends SubsystemBase {

  private final PWMVictorSPX m_leftFrontVictorSPX = new PWMVictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_LEFT_FRONT_VICTORSPX);
  private final PWMVictorSPX m_leftRearVictorSPX = new PWMVictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_LEFT_REAR_VICTORSPX);
  private final PWMVictorSPX m_rightFrontVictorSPX = new PWMVictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_RIGHT_FRONT_VICTORSPX);
  private final PWMVictorSPX m_rightRearVictorSPX = new PWMVictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_RIGHT_REAR_VICTORSPX);
  private final DifferentialDrive m_drivetrain = new DifferentialDrive(m_leftFrontVictorSPX, m_rightFrontVictorSPX);

  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    //Motors set to 50% speed
    m_drivetrain.arcadeDrive(0.5*moveSpeed, 0.5*rotateSpeed);
  }

  public Drivetrain() {
    m_leftFrontVictorSPX.addFollower(m_leftRearVictorSPX);
    m_rightFrontVictorSPX.addFollower(m_rightRearVictorSPX);

    setDefaultCommand(run(() -> {
      m_drivetrain.arcadeDrive(0.0, 0.0);
    }));
  }

  public Command arcadeDrive(DoubleSupplier moveSpeed, DoubleSupplier rotateSpeed){
    return run(() -> {
      m_drivetrain.arcadeDrive(0.5 * moveSpeed.getAsDouble(), 0.5 * rotateSpeed.getAsDouble());
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_drivetrain.arcadeDrive(0, 0);

  }
  
  

  }

