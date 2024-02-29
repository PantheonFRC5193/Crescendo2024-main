package frc.robot;
import frc.robot.subsystems.*;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.*;

import java.io.File;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class RobotContainer {
    
    //

    private XboxController controller = new XboxController(0);

   // private final BooleanSupplier leftBumper =  () -> controller.getLeftBumper();
   // private final BooleanSupplier rightBumper = () -> controller.getRightBumper();
   // private final DoubleSupplier leftTrigger = () -> controller.getLeftTriggerAxis();
    //private final DoubleSupplier rightTrigger = () -> controller.getRightTriggerAxis();


    //private final JoystickButton aButton = new JoystickButton(controller, 1);
    //private final JoystickButton xButton = new JoystickButton(controller, 3);
    //private final JoystickButton bButton = new JoystickButton(controller, 2);
    //private final JoystickButton yButton = new JoystickButton(controller, 4);


    //Swerve Subsystem

    public final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),"swerve"));

    //Bullhead Subsystems

    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Elevator elevator = new Elevator();
    private final Pivot pivot = new Pivot();
    
    //Instantiate command objects
    private final Command intakeCommand = new IntakeCommand(controller,intake);
    private final Command pivotCommand = new PivotCommand(controller,pivot);
    private final Command shootCommand = new ShooterCommand(controller,shooter);
    private final Command elevatorCommand = new ElevatorCommand(controller,elevator);

    //private final BullheadCommand bullhead = new BullheadCommand(controller,elevator,intake,pivot,shooter);

    public RobotContainer() {

        configureButtonBindings();

    }

    private void configureButtonBindings(){ 
        //A
        new JoystickButton(controller, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
        //X
        new JoystickButton(controller, 3).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
        //B
        new JoystickButton(controller, 2).whileTrue(Commands.deferredProxy(() -> drivebase.driveToPose(new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))));
        //LB
        new JoystickButton(controller,6).whileTrue((new InstantCommand(() -> getPivotCommand(),getPivotSystem())));
        //RB
        new JoystickButton(controller,7).whileTrue((new InstantCommand(()->getPivotCommand(),getPivotSystem())));

    }

    public Command getIntakeCommand(){

        return intakeCommand;
    }

    public Command getPivotCommand(){

        return pivotCommand;
    }

    public Command getShooterCommand(){

        return shootCommand;
    }

    public Command getElevatorCommand(){

        return elevatorCommand;

    }

    public SwerveSubsystem getSwerveSystem(){

        return drivebase;
    }

    public XboxController getController(){

        return controller;
    }

    public Subsystem getIntakeSystem()
    {
        return intake;
    }

    public Subsystem getPivotSystem(){

        return pivot;
    }

    public Subsystem getShooterSystem(){

        return shooter;
    }


    //Methods for retrieving stick values on XboxController
    //And for returning angle heading
    //getLeft(X/Y)D returns a double
    //getLeft(X/Y) is a DoubleSupplier interface

    private double getLeftXD(){

        if(Math.abs(controller.getLeftX()) < 0.1)
            return 0;
        else
        return controller.getLeftX();
    }


    public DoubleSupplier getLeftX(){

        DoubleSupplier value = () -> getLeftXD();
        
        return value;
    }

     private double getLeftYD(){

        if(Math.abs(controller.getLeftY()) < 0.1)
            return 0;
        else
        return controller.getLeftY();
    }

    public DoubleSupplier getLeftY()
    {

        DoubleSupplier value = () -> getLeftYD();
        
        return value;
    }

    public DoubleSupplier getAngle(){

        DoubleSupplier m_angle = () -> stickHeading();

           return m_angle;

    }

    //Stick heading calculation and correction back to [0,2pi)

    public double stickHeading(){

        double angleToken = 0;
            
            //Trigonometry using inverse sine and angle corrections based on stick directions to calculate angle heading
            if (getLeftXD() > 0 ){ 
                 //Quadrant 1
                if (getLeftYD() > 0){

                    angleToken = Math.asin(getLeftYD()/getLeftXD());
                    //Quadrant 4
                }else if(getLeftYD()<0){

                    angleToken = Math.asin(getLeftYD()/getLeftXD())+2*Math.PI;

                }
        
            } else if(getLeftXD() < 0){ 
                    //Quadrant 2
                   if (getLeftYD() > 0){
                    angleToken = Math.asin(getLeftYD()/getLeftXD())+Math.PI;
                    //Quadrant 3
                }else if(getLeftYD()<0){

                    angleToken = Math.asin(getLeftYD()/getLeftXD())+Math.PI;

                }

            }else if(getLeftXD()==0 && getLeftYD() >0)
            {
                angleToken =  Math.PI/2;
            }else if (getLeftXD()==0 && getLeftYD() < 0)
            {
                return -1*Math.PI/2;
            }else if (getLeftXD() > 0 && getLeftYD()==0){
                angleToken = 0;
            }else if(getLeftXD() < 0 && getLeftYD() == 0){
                angleToken = Math.PI;
            }else{
                angleToken = 0;
            }
            return angleToken;
           }
    }



