package frc.robot.commands;

import  frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class ShooterCommand extends Command {


    private Shooter m_shooter;
    private XboxController m_controller;

    public ShooterCommand(XboxController controller, Shooter shooter){

        m_shooter = shooter;
        m_controller = controller;

        addRequirements(shooter);

    }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    //m_shooter.setPower(0.5*m_controller.getRightTriggerAxis());

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){

    SmartDashboard.putNumber("Right Trigger Axis",m_controller.getRightTriggerAxis());

    while(m_controller.getRightTriggerAxis() >=0.3){

        m_shooter.setPower(m_controller.getRightTriggerAxis());
    }
    while(m_controller.getRightTriggerAxis() <=0.3){

         m_shooter.setPower(0);
    }

 }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_shooter.stop();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_controller.getRightTriggerAxis() < 0.5) {
      return true;
    }else{
      return false;
    }
  }
    
}
