package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.*;


//Subsystem for Pivot Arm run by Falcon motor with integrated 
//TalonFX motor controller
//

public class Pivot extends SubsystemBase {
    
    private int pivotID = 30;
    private TalonFX motor = new TalonFX(pivotID,"rio");

    private DigitalInput limit1 = new DigitalInput(2);
    private DigitalInput limit2 = new DigitalInput(0);

    public Pivot()
    {

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

    public Boolean getLimit1(){

        return limit1.get();

    }

    public Boolean getLimit2(){

        return limit2.get();
    }

    public void stop(){
        motor.stopMotor();
    }




}
