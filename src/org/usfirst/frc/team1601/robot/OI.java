/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	

	//Drive Train Motors
	WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor),
					leftRearMotor = new WPI_TalonSRX(RobotMap.leftRearMotor),
					rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor),
					rightRearMotor = new WPI_TalonSRX(RobotMap.rightRearMotor),
					middleWheelMotor = new WPI_TalonSRX(RobotMap.middleWheelMotor);
	//Other Motors
	WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(RobotMap.elevatorMotor),
					leftClawMotor = new WPI_TalonSRX(RobotMap.leftClawMotor),
					rightClawMotor = new WPI_TalonSRX(RobotMap.rightClawMotor);
	//Joy Sticks
	Joystick leftJoystick = new Joystick(RobotMap.leftJoystick),
				rightJoystick = new Joystick(RobotMap.rightJoystick);
	
	//DifferentialDrive
	DifferentialDrive differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
	
	DigitalInput bottomLimitSwitch = new DigitalInput(1),
					topLimitSwitch = new DigitalInput(2);
	
	//Constants
	static double leftMotorAdjustConstant = 1,		//This may have to be changed during testing
					rightMotorAdjustConstant = 1,	//This may have to be changed during testing
					driveTrainMotorsMaxSpeed = .80,
					driveTrainMiddleWheelMotorSpeed = .45,
					
					elevatorMaxSpeed = .20,
					elevatorStallSpeed = 0,
					
					maxClawSpeed = .15;
	
	//Boolean
	static boolean limitSwitchesStatus = false;	
	
	//Thread Sleep Time
	static long threadSleepTime = 20;
	
}