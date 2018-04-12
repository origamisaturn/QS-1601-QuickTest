package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class ClawSystem implements Runnable {

	Joystick leftJoystick;
	SpeedControllerGroup clawSystemMotors;
	
	private boolean killThread = false;

	ClawSystem(Joystick leftJoystick, SpeedControllerGroup clawSystemMotors) {
		this.leftJoystick = leftJoystick;
		this.clawSystemMotors = clawSystemMotors;
	}

	public void run() {
		while (!killThread) {
			if (leftJoystick.getPOV() == 0) {
				clawSystemMotors.set(OI.maxClawSpeed);
			} else if (leftJoystick.getPOV() == 180) {
				clawSystemMotors.set(-OI.maxClawSpeed);
			} else {
				clawSystemMotors.set(0);
			}
			
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}

}