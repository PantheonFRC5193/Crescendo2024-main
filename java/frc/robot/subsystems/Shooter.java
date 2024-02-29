package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.*;
//import com.ctre.phoenix6.configs.*;
//import edu.wpi.first.wpilibj2.command.*;

//Subsystem for Shooter, a Falcon motor run by
//integrated TalonFX motor controller

public class Shooter extends SubsystemBase {

    private int shooterID = 29;
    private final TalonFX motor = new TalonFX(shooterID,"rio");

    public Shooter(){

    }

    public void setPower(double power){
        motor.set(power);
    }

    public double getVelocity(){
        return motor.getVelocity().getValue();
    }

    public void setPosition(double position){
        motor.setPosition(position);
    }


    public double getPosition(){
       return motor.getPosition().getValue();
    }

    public void stop(){
        motor.stopMotor();
    }

    
}
