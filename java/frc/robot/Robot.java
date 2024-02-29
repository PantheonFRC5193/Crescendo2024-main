package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.io.File;
import java.io.IOException;
import frc.robot.swervelib.parser.SwerveParser;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot{

    private RobotContainer m_robotContainer;
    //private Command elevatorCommand;
    //private Command intakeCommand;
    //private Command pivotCommand;
    //private Command shooterCommand;


    //Command objects

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  //@Override
  //public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    //if (m_autonomousCommand != null) {
      //m_autonomousCommand.schedule();
    //}
  //}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //if (m_autonomousCommand != null) {
      //m_autonomousCommand.cancel();
    //}
      //Check if command is null
      //If not null, send to command scheduler


    if (m_robotContainer.getSwerveSystem().driveCommand(m_robotContainer.getLeftX(), m_robotContainer.getLeftY(),m_robotContainer.getAngle()) != null){
      m_robotContainer.getSwerveSystem().driveCommand(m_robotContainer.getLeftX(), m_robotContainer.getLeftY(),m_robotContainer.getAngle()).schedule();

    }
    if(m_robotContainer.getPivotCommand() !=null){
      m_robotContainer.getPivotCommand().schedule();
    }
    if(m_robotContainer.getIntakeCommand() !=null){
      m_robotContainer.getIntakeCommand().schedule();
    }
    if(m_robotContainer.getShooterCommand() !=null ){
      m_robotContainer.getShooterCommand().schedule();
    }

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}


