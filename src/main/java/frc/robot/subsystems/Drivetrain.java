// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;



public class Drivetrain extends SubsystemBase {

  private final WPI_VictorSPX m_leftFrontVictorSPX = new WPI_VictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_LEFT_FRONT_VICTORSPX);
  private final WPI_VictorSPX m_leftRearVictorSPX = new WPI_VictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_LEFT_REAR_VICTORSPX);
  private final WPI_VictorSPX m_rightFrontVictorSPX = new WPI_VictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_RIGHT_FRONT_VICTORSPX);
  private final WPI_VictorSPX m_rightRearVictorSPX = new WPI_VictorSPX(Constants.ArcadeDriveConstants.ARCADEDRIVE_RIGHT_REAR_VICTORSPX);
  private final DifferentialDrive m_drivetrain = new DifferentialDrive(m_leftFrontVictorSPX, m_leftRearVictorSPX);
  
  public Drivetrain() {

    m_leftRearVictorSPX.follow(m_leftFrontVictorSPX);
    m_rightRearVictorSPX.follow(m_rightFrontVictorSPX);

    m_rightFrontVictorSPX.setInverted(true);
   


    setDefaultCommand(run(() -> {
      m_drivetrain.arcadeDrive(0.0, 0.0);
    }));

  }

  public Command arcadeDrive(DoubleSupplier moveSpeed, DoubleSupplier rotateSpeed){
    return run(() -> {
      m_drivetrain.arcadeDrive(0.75 * moveSpeed.getAsDouble(), 0.75 * rotateSpeed.getAsDouble());
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }

public Object arcadeDrive(double d, double e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'arcadeDrive'");
}
  
  

  }

