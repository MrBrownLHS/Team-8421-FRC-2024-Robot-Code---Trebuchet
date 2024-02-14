// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.CoPilotConstants;
import frc.robot.Constants.DriverConstants;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


// Use this to help with static error https://github.com/TheMathWiz56/2024-Crescendo-Java-Code-Joseph/blob/main/2024%20Crescendo%20Java%20Code%20-%20Joseph/Swerve_Project/src/main/java/frc/robot/RobotContainer.java
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private XboxController driveController = new XboxController(DriverConstants.DRIVER_CONTROLLER_PORT);
  private XboxController copilotController = new XboxController(CoPilotConstants.COPILOT_CONTROLLER_PORT);
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Arm m_armrotateforward = new Arm();
  private final Arm m_armrotatereverse = new Arm();
  private final Launcher m_notelaunch = new Launcher();
  private final Collector m_notedrop = new Collector();
  private final Collector m_notecollect = new Collector();
  private final Collector m_collectorstop = new Collector();
  private final Arm m_armHang = new Arm();
  
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    
   
    
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  
   private void configureBindings() {
    m_drivetrain.setDefaultCommand(
      new RunCommand(
        () ->
            m_drivetrain.arcadeDrive(
              -driveController.getLeftY(), -driveController.getRightX()),
        m_drivetrain));

    new JoystickButton(copilotController, XboxController.Button.kRightBumper.value)
      .onTrue(new InstantCommand(() -> m_armrotateforward.pivotforward()));
    
    new JoystickButton(copilotController, XboxController.Button.kLeftBumper.value)
      .onTrue(new InstantCommand(() -> m_armrotatereverse.pivotreverse()));
          
    new JoystickButton(copilotController, XboxController.Button.kA.value)
      .onTrue(new InstantCommand(() -> m_notecollect.collect()));
    
    new JoystickButton(copilotController, XboxController.Button.kB.value)
      .onTrue(new InstantCommand(() -> m_notedrop.collectReverse()));

    new JoystickButton(copilotController, XboxController.Button.kX.value)
      .onTrue(new InstantCommand(() -> m_collectorstop.collectStop()));

    new JoystickButton(copilotController, XboxController.Axis.kLeftTrigger.value)
      .onTrue(new InstantCommand(() -> m_notelaunch.noteLaunch()));

    new JoystickButton(copilotController, XboxController.Axis.kRightTrigger.value)
      .onTrue(new InstantCommand(() -> m_notedrop.collectReverse()));
    
    new JoystickButton(copilotController, XboxController.Button.kY.value)
      .onTrue(new InstantCommand(() -> m_armHang.chainHang()));
    

    
    
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    
  }
}
