// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.CoPilotConstants;
import frc.robot.Constants.DriverConstants;
import frc.robot.commands.AutoLaunchRun;
import frc.robot.commands.AutoRunRotate;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private CommandXboxController driveController = new CommandXboxController(DriverConstants.DRIVER_CONTROLLER_PORT);
  private CommandXboxController copilotController = new CommandXboxController(CoPilotConstants.COPILOT_CONTROLLER_PORT);
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Arm m_arm = new Arm();
  private final CollectorLauncher m_notecollectorlauncher = new CollectorLauncher();

  private final Command m_autoRunRotate = new AutoRunRotate();
  private final Command m_autoLaunchRun = new AutoLaunchRun();

  SendableChooser<Command> m_chooser = new SendableChooser<>();
  

      
    //** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_chooser.setDefaultOption("Run Rotate", m_autoRunRotate);
    m_chooser.addOption("Launch Run Rotate", m_autoLaunchRun);

    Shuffleboard.getTab("Autonomous").add(m_chooser);

    Shuffleboard.getTab("DriveTrain").add(m_chooser);
    Shuffleboard.getTab("Arm").add(m_chooser);
    Shuffleboard.getTab("Collector-Launcher").add(m_chooser);

    CommandScheduler.getInstance()
        .onCommandInitialize(
            command ->
                Shuffleboard.addEventMarker(
                    "Command initialized", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandExecute(
            command ->
                Shuffleboard.addEventMarker(
                    "Command executed", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandFinish(
            command ->
                Shuffleboard.addEventMarker(
                    "Command finished", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandInterrupt(
            command ->
                Shuffleboard.addEventMarker(
                    "Command interrupted", command.getName(), EventImportance.kNormal));

           
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
      m_drivetrain.arcadeDrive(() -> -driveController.getLeftY(), () -> -driveController.getRightX())
    );
        
    copilotController.rightBumper().onTrue(new InstantCommand(() -> m_arm.pivotforwardCommand()));

    copilotController.leftBumper().onTrue(new InstantCommand(() -> m_arm.pivotreverseCommand()));

    copilotController.a().onTrue(new InstantCommand(() -> m_notecollectorlauncher.collectCommand()));

    copilotController.b().whileTrue(new InstantCommand(() -> m_notecollectorlauncher.collectReverseCommand()));

    copilotController.rightTrigger().onTrue(new InstantCommand(() -> m_notecollectorlauncher.collectLaunchCommand()));

    copilotController.y().whileTrue(new InstantCommand(() -> m_arm.chainHangCommand()));

    copilotController.x().onTrue(new InstantCommand(() -> m_notecollectorlauncher.collectlaunchStopCommand()));




    
    
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
    return m_chooser.getSelected();
   
    
    // An example command will be run in autonomous

    
  }
}
