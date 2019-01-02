package org.usfirst.frc.team4253.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
	
	// This part is used for declaring variables and objects
	private DifferentialDrive myRobot;
	private WPI_VictorSPX frontLeftMotor;
	private VictorSPX rearLeftMotor;
	private WPI_VictorSPX frontRightMotor;
	private VictorSPX rearRightMotor;
	private VictorSPX arm;
	private VictorSPX arm2;
	private VictorSPX leftIntake;
	private VictorSPX rightIntake;
	private XboxController stick;
	private Timer timer;

	/**
	 * This method is where you initialize variables and objects.
	 * Setup any configurations of the robot here.
	 */
	@Override
	public void robotInit() {
		frontLeftMotor = new WPI_VictorSPX(0);
		frontRightMotor = new WPI_VictorSPX(1);
		rearLeftMotor = new VictorSPX(2);
		rearRightMotor = new VictorSPX(3);
		arm = new VictorSPX(4);
		arm2 = new VictorSPX(7);
		leftIntake = new VictorSPX(5);
		rightIntake = new VictorSPX(6);
		stick = new XboxController(0);
		timer = new Timer();
		
		frontLeftMotor.setInverted(false);
		frontRightMotor.setInverted(false);
		
		rearLeftMotor.set(ControlMode.Follower, 0);
		rearRightMotor.set(ControlMode.Follower, 1);
		
		myRobot = new DifferentialDrive(frontRightMotor, frontLeftMotor);
		
		leftIntake.setInverted(false);
		
		rightIntake.setInverted(true);
		rightIntake.set(ControlMode.Follower, 5);
		
		arm2.set(ControlMode.Follower, 4);
		
		frontLeftMotor.setNeutralMode(NeutralMode.Brake);
		frontRightMotor.setNeutralMode(NeutralMode.Brake);
		rearLeftMotor.setNeutralMode(NeutralMode.Brake);
		rearRightMotor.setNeutralMode(NeutralMode.Brake);
		arm.setNeutralMode(NeutralMode.Brake);
		leftIntake.setNeutralMode(NeutralMode.Brake);
		rightIntake.setNeutralMode(NeutralMode.Brake);
		arm2.setNeutralMode(NeutralMode.Brake);
	}
	
	/**
	 * This is method is where you put code that runs once before auto starts.
	 */
	@Override
	public void autonomousInit() {
//		time.start();
	}
	
	/**
	 * This method is where you put the code to move the robot during auto. 
	 * This is called every 20ms.
	 */
	@Override
	public void autonomousPeriodic() {
//		if (time.get() <= 5.0) {
//			// Run motor
//			myRobot.tankDrive(0.5, 0);
//		} else {
//			// Stop motor
//			myRobot.tankDrive(0, 0);
//			time.stop();
//		}
	}

	/**
	 * This method is where you put the code to drive the robot in teleop.
	 * This is repeatedly called every 20ms.
	 */
	@Override
	public void teleopPeriodic() {
		// Drive
		myRobot.tankDrive(stick.getY(Hand.kRight), stick.getY(Hand.kLeft));
		
		// Intake
		if (stick.getBumper(Hand.kRight)) {
			leftIntake.set(ControlMode.PercentOutput, 1.0);
		} else if (stick.getBumper(Hand.kLeft)) {
			leftIntake.set(ControlMode.PercentOutput, -1.0);
		} else {
			leftIntake.set(ControlMode.PercentOutput, 0);
		}
		
		// Arm
		if (stick.getTriggerAxis(Hand.kRight) > 0.05) {
			arm.set(ControlMode.PercentOutput, stick.getTriggerAxis(Hand.kRight));
		} else {
			arm.set(ControlMode.PercentOutput, -stick.getTriggerAxis(Hand.kLeft));
		}
	}
}
