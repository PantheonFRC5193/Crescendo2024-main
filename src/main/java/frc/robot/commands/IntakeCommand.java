package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class IntakeCommand extends Command {

    private Intake m_intake;
    private XboxController m_controller;


    public IntakeCommand(XboxController controller, Intake intake) {
        m_intake = intake;
        m_controller = controller;
        addRequirements(intake);
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {

      }
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
        while(m_controller.getLeftTriggerAxis() >=  0.5){

            m_intake.setPower(m_controller.getLeftTriggerAxis());

        }
        while(m_controller.getLeftTriggerAxis() <0.5){

          m_intake.setPower(0);
        }
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
        m_intake.stop();
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
      

        if(m_controller.getLeftTriggerAxis() < 0.5) {
          return true;
        }else{
          return false;
        }
      }
    
}
