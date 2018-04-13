package org.usfirst.frc.team1601.robot;

import org.usfirst.frc.team1601.robot.OI;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class ElevatorSystem implements Runnable{

	Joystick rightJoystick;
	WPI_TalonSRX elevatorMotor;
	
	public ElevatorSystem(Joystick rightJoystick, WPI_TalonSRX elevatorMotor){
		this.rightJoystick = rightJoystick;
		this.elevatorMotor = elevatorMotor;
	}
	
	public void run() {
		while(true) {
/*			if(rightJoystick.getRawButton(6)) {
				elevatorMotor.set(rightJoystick.getTwist() * OI.elevatorMaxUpSpeed);
			}
			else
			{
				elevatorMotor.set(OI.elevatorStallSpeed);
			}*/
			
			if(rightJoystick.getRawButton(5))
			{
				elevatorMotor.set(OI.elevatorMaxUpSpeed);
			}
			else if(rightJoystick.getRawButton(3)) {
				elevatorMotor.set(OI.elevatorMaxDownSpeed);
			}
			else
			{
				elevatorMotor.set(OI.elevatorStallSpeed);
			}
			
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}
}
