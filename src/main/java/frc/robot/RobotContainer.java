// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.CoPilotConstants;
import frc.robot.Constants.DriverConstants;
import frc.robot.commands.AutoCommands;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private CommandXboxController driveController = new CommandXboxController(DriverConstants.DRIVER_CONTROLLER_PORT);
  private CommandXboxController copilotController = new CommandXboxController(CoPilotConstants.COPILOT_CONTROLLER_PORT);
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Arm m_arm = new Arm();
  private final CollectorLauncher m_notecollectorlauncher = new CollectorLauncher();

  private final SlewRateLimiter forwardBackLimiter = new SlewRateLimiter(1.5);
  private final SlewRateLimiter rotationLimiter = new SlewRateLimiter(1.5);

  private final AutoCommands m_autoCommands = new AutoCommands(m_drivetrain, m_notecollectorlauncher);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // ** The container for the robot. Contains subsystems, OI devices, and
  // commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_chooser.setDefaultOption("Launch Launch Drive", m_autoCommands.autoLaunchDrive());
    m_chooser.addOption("Drive Forward", m_autoCommands.autoDriveForward());
   

    SmartDashboard.putData(m_chooser);

    Shuffleboard.getTab("Main")
        .add("Auto Chooser", m_chooser)
        .withWidget(BuiltInWidgets.kComboBoxChooser).withSize(2, 1)
        .withPosition(0, 0);

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

  private void configureBindings() {
    m_drivetrain.setDefaultCommand(
        m_drivetrain.arcadeDrive(
            () -> -forwardBackLimiter.calculate(driveController.getLeftY()),
            () -> -rotationLimiter.calculate(driveController.getRightX())));

    copilotController.rightBumper().whileTrue(m_arm.pivotforwardCommand());
    copilotController.rightBumper().onFalse(m_arm.armStopCommand());

    copilotController.leftBumper().whileTrue(m_arm.pivotreverseCommand());
    copilotController.leftBumper().onFalse(m_arm.armStopCommand());

    copilotController.a().whileTrue(m_notecollectorlauncher.collectCommand());
    copilotController.a().onFalse(m_notecollectorlauncher.collectlaunchStopCommand());

    copilotController.b().whileTrue(m_notecollectorlauncher.collectReverseCommand());
    copilotController.b().onFalse(m_notecollectorlauncher.collectlaunchStopCommand());

    copilotController.rightTrigger().onTrue(m_notecollectorlauncher.launchCommand());

    copilotController.y().whileTrue((m_arm.chainHangCommand())
        .handleInterrupt(() -> m_arm.armStopCommand()));

    copilotController.x().onTrue(m_notecollectorlauncher.collectlaunchStopCommand());

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
