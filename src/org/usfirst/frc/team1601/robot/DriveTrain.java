package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team1601.robot.OI;

public class DriveTrain implements Runnable{

	Joystick leftJoystick, rightJoystick;
	WPI_TalonSRX middleWheelMotor;
	SpeedControllerGroup leftSideDriveTrain, rightSideDriveTrain;
	DifferentialDrive differentialDrive;
		
	private boolean killThread = false;
	
	DriveTrain(Joystick leftJoystick, Joystick rightJoystick, SpeedControllerGroup leftSideDriveTrain, SpeedControllerGroup rightSideDriveTrain, WPI_TalonSRX middleWheelMotor, DifferentialDrive differentialDrive) {
		this.leftJoystick = leftJoystick;
		this.rightJoystick = rightJoystick;
		this.leftSideDriveTrain = leftSideDriveTrain;
		this.rightSideDriveTrain = rightSideDriveTrain;
		this.middleWheelMotor = middleWheelMotor;
		this.differentialDrive = differentialDrive;
		

	}
	
	public void run() {
		while(!killThread) {
			//LeftSide and RightSide tankDrive
			if(Math.abs(leftJoystick.getRawAxis(1) - rightJoystick.getRawAxis(1)) <= .05) {
				differentialDrive.tankDrive(leftJoystick.getRawAxis(1) * OI.leftMotorAdjustConstant * OI.driveTrainMotorsMaxSpeed , rightJoystick.getRawAxis(1) * OI.rightMotorAdjustConstant * OI.driveTrainMotorsMaxSpeed, true);
			}
			else {
				differentialDrive.tankDrive(leftJoystick.getRawAxis(1) * OI.driveTrainMotorsMaxSpeed, rightJoystick.getRawAxis(1) * OI.driveTrainMotorsMaxSpeed, true);
			}
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