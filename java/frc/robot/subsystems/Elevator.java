package frc.robot.subsystems;
import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;


//Thriftybot Elevator Subsystem operated by a Neo Motor
//With a SparkMax Controller

public class Elevator extends SubsystemBase {
    //Sparkmax Constants
    private int elevatorID = 18;
    private CANSparkMax motor = new CANSparkMax(elevatorID,CANSparkLowLevel.MotorType.kBrushless);
    private SparkAbsoluteEncoder encoder;

    //Sensors
    //Touch Sensor 1
    //Touch Sensor 2


    public Elevator()
    {
 
    }
    
    public void setPower(double power){

        motor.set(power);
    }

    public double getVelocity(){

        return encoder.getVelocity();
    }
    
    public double getPosition(){
        return encoder.getPosition();

    }

    public void stop(){
        motor.disable();
    }
    
}
