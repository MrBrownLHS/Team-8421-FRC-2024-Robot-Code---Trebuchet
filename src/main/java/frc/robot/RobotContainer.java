// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.CoPilotConstants;
import frc.robot.Constants.DriverConstants;
import frc.robot.commands.AutoLaunchRun;
import frc.robot.commands.AutoRunRotate;
import frc.robot.commands.LaunchCollectLaunch;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



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

  private final SlewRateLimiter forwardBackLimiter = new SlewRateLimiter(2);
  private final SlewRateLimiter rotationLimiter = new SlewRateLimiter(2);

  private final Command m_autoRunRotate = new AutoRunRotate(m_drivetrain);
  private final Command m_autoLaunchRun = new AutoLaunchRun(m_drivetrain, m_notecollectorlauncher);
  private final Command m_launchCollectLaunch = new LaunchCollectLaunch(m_drivetrain, m_notecollectorlauncher);


  SendableChooser<Command> m_chooser = new SendableChooser<>();
  

      
    //** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_chooser.setDefaultOption("Run Rotate", m_autoRunRotate);
    m_chooser.addOption("Launch Run Rotate", m_autoLaunchRun);
    m_chooser.addOption("Launch Collect Launch", m_launchCollectLaunch);

    SmartDashboard.putData(m_chooser);

           
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
      m_drivetrain.arcadeDrive(
        () -> -forwardBackLimiter.calculate(driveController.getLeftY()), 
        () -> rotationLimiter.calculate(driveController.getRightX()))
    );
    
    /*
    copilotController.rightBumper().whileTrue(new InstantCommand(() -> m_arm.pivotforwardCommand()));

    copilotController.leftBumper().whileTrue(new InstantCommand(() -> m_arm.pivotreverseCommand()));

    copilotController.a().whileTrue(new InstantCommand(() -> m_notecollectorlauncher.collectCommand()));

    copilotController.b().whileTrue(new InstantCommand(() -> m_notecollectorlauncher.collectReverseCommand()));

    copilotController.rightTrigger().onTrue(new InstantCommand(() -> m_notecollectorlauncher.collectLaunchCommand()));

    copilotController.y().whileTrue(new InstantCommand(() -> m_arm.chainHangCommand()));

    copilotController.x().onTrue(new InstantCommand(() -> m_notecollectorlauncher.collectlaunchStopCommand()));
    */
    //If the code below doesn't work try using new RunCommands
    //copilotController.rightBumper().whileTrue(new RunCommand(()-> m_arm.pivotforwardCommand()));
    copilotController.rightBumper().whileTrue(m_arm.pivotforwardCommand());
    copilotController.rightBumper().onFalse(m_arm.getDefaultCommand());

    copilotController.leftBumper().whileTrue(m_arm.pivotreverseCommand());
    copilotController.leftBumper().onFalse(m_arm.getDefaultCommand());

    copilotController.a().onTrue(m_notecollectorlauncher.collectCommand());
    copilotController.a().onFalse(m_notecollectorlauncher.collectlaunchStopCommand());

    copilotController.b().whileTrue(m_notecollectorlauncher.collectReverseCommand());
    copilotController.b().onFalse(m_notecollectorlauncher.collectlaunchStopCommand());

    copilotController.rightTrigger().onTrue(m_notecollectorlauncher.launchCommand());

    copilotController.y().whileTrue(m_arm.chainHangCommand());

    copilotController.x().onTrue(m_notecollectorlauncher.collectlaunchStopCommand());


    
    
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
