package org.usfirst.frc.team1601.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;

public class ClawSystem implements Runnable {

	Joystick leftJoystick;
	WPI_VictorSPX leftClawMotor, rightClawMotor;
	

	ClawSystem(Joystick leftJoystick, WPI_VictorSPX leftClawMotor, WPI_VictorSPX rightClawMotor) {
		this.leftJoystick = leftJoystick;
		this.leftClawMotor = leftClawMotor;
		this.rightClawMotor = rightClawMotor;
	}

	public static double motorStallSpeed = 0.03;
	
	public void run() {
		while (true) {
			if(leftJoystick.getRawButton(5)) {
				leftClawMotor.set(-.50);
				rightClawMotor.set(.50);
			}
			else if(leftJoystick.getRawButton(3)) {
				leftClawMotor.set(.50);
				rightClawMotor.set(-.50);
			}
			else {
				leftClawMotor.set(-motorStallSpeed);
				rightClawMotor.set(motorStallSpeed);
			}
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}

}