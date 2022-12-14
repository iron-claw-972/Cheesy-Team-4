package frc.robot.subsystems;

import javax.management.MBeanAttributeInfo;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.util.MotorFactory;

public class Outtake extends SubsystemBase{
    
    private final Servo m_motor;
    private final PIDController m_pid = new PIDController(Constants.outtake.P, Constants.outtake.I, Constants.outtake.D);
    private boolean pidEnabled = false;
    private double setpoint;

    public Outtake() {
        m_motor = new Servo(Constants.outtake.kMotorId);
    }

    @Override
    public void periodic(){
        if(pidEnabled){
            // setMotor(m_pid.calculate(getMotor(), setpoint));
        }
    }

    public void startPid(double setpoint){
        resetPid();
        zeroMotor();
        setPidEnabled(true);
        setSetpoint(setpoint);
    }

    public void zeroMotor(){
        m_motor.setPosition(0);
    }

    public void resetPid(){
        m_pid.reset();
    }

    public void setMotor(double position){
        m_motor.set(position);
    }
    public void setAngle(double angle){
        m_motor.setAngle(angle);
    }
    public double getMotor(){
        return m_motor.getPosition();
    }
    public void setPidEnabled(boolean enabled){
        pidEnabled=enabled;
    }
    public void setSetpoint(double setpoint){
        this.setpoint=setpoint;
        setAngle(setpoint);
        System.out.println("Outtake PID set to "+Math.round(setpoint));
    }
}
