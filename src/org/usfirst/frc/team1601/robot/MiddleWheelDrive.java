package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team1601.robot.OI;

public class MiddleWheelDrive implements Runnable{

	Joystick leftJoystick, rightJoystick;
	WPI_TalonSRX middleWheelMotor;
		
	private boolean killThread = false;
	
	MiddleWheelDrive(Joystick leftJoystick, Joystick rightJoystick, WPI_TalonSRX middleWheelMotor) {
		this.leftJoystick = leftJoystick;
		this.rightJoystick = rightJoystick;
		this.middleWheelMotor = middleWheelMotor;
	}
	
	public void run() {
		while(!killThread) {
			//MiddleWheel Drive
			if(leftJoystick.getTrigger()) {
				middleWheelMotor.set(OI.driveTrainMiddleWheelMotorSpeed);
			}
			else if(rightJoystick.getTrigger()) {
				middleWheelMotor.set(-OI.driveTrainMiddleWheelMotorSpeed);
			}
			else {
				middleWheelMotor.set(0);
			}
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}
	
}