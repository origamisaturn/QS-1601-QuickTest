/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1601.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();

	OI oi;
	
	SpeedControllerGroup leftSideDriveTrain, rightSideDriveTrain, clawSystemMotors;
	MiddleWheelDrive myMiddleWheel;
	DriveTrain mydriveTrain;
	ClawSystem myClawSystem;
	ElevatorSystem myElevatorSystem;
	DifferentialDrive differentialDrive;
	//ElevatorSystem myElevatorSystem;
	Timer autoTime;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		SmartDashboard.putNumber("Joystick Twist", oi.rightJoystick.getTwist());
		
		leftSideDriveTrain = new SpeedControllerGroup(oi.leftFrontMotor, oi.leftRearMotor);
		rightSideDriveTrain = new SpeedControllerGroup(oi.rightFrontMotor, oi.rightRearMotor);

		
		myClawSystem = new ClawSystem(oi.leftJoystick, oi.leftClawMotor, oi.rightClawMotor);
		myMiddleWheel = new MiddleWheelDrive(oi.leftJoystick, oi.rightJoystick, oi.middleWheelMotor);

		mydriveTrain = new DriveTrain(oi.leftJoystick, oi.rightJoystick, leftSideDriveTrain, rightSideDriveTrain, oi.middleWheelMotor, differentialDrive);
		myElevatorSystem = new ElevatorSystem(oi.rightJoystick, oi.elevatorMotor);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		autoTime = new Timer();
		autoTime.reset();
		autoTime.start();
	}
	public void autonomousPeriodic()
	{
		double autoRunTime = 4.25;
		if(autoTime.get()<autoRunTime)
		{
			differentialDrive.tankDrive(-OI.leftMotorsMaxSpeed,-OI.rightMotorsMaxSpeed);
		}
		else
		{
			differentialDrive.tankDrive(0,0);
		}
	}
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopInit() {
		
		Thread myClawSystemThread  = new Thread(myClawSystem);
		Thread myDriveTrainThread = new Thread(mydriveTrain);
		Thread myMiddleWheelThread = new Thread(myMiddleWheel);
//		Thread myElevatorSystemThread = new Thread(myElevatorSystem);
		myDriveTrainThread.start();
		myMiddleWheelThread.start();
		myClawSystemThread.start();
//		myElevatorSystemThread.start();

	}
	@Override
	public void teleopPeriodic() {

		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
