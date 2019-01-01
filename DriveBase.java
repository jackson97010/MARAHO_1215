package System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {

	private static ADXRS450_Gyro m_gyro;
	private static Timer m_timer;
	private static int situation = 0;
	private static String showOK; 
	private static final int p_joystick = 0;
	
	private static final int p_rightMotor = 0; 
	private static final int p_leftMotor =  1;
	private static final int p_shooter =  2;
	
	private static DifferentialDrive m_Robot;
	@SuppressWarnings("unused")
	private static XboxController joystick;
	private static WPI_VictorSPX m_rightMotor, m_leftMotor;
	private static VictorSPX m_shooter;
	private static double currentTimer ;

	
	@SuppressWarnings("unused")


	public static void init(){
		
		m_rightMotor = new WPI_VictorSPX(p_rightMotor);
		m_leftMotor = new WPI_VictorSPX(p_leftMotor);
		m_shooter = new VictorSPX(p_shooter);
		joystick = new XboxController(p_joystick);
		m_gyro= new ADXRS450_Gyro();
		m_timer = new Timer();
		m_Robot = new DifferentialDrive(m_rightMotor, m_leftMotor);
		
		m_leftMotor.setInverted(false);
		m_rightMotor.setInverted(false);
		m_shooter.setInverted(false);
        m_leftMotor.setNeutralMode(NeutralMode.Brake);
        m_rightMotor.setNeutralMode(NeutralMode.Brake);
        m_shooter.setNeutralMode(NeutralMode.Brake);

		SmartDashboard.putNumber("m_leftSpeed ",0);
		SmartDashboard.putNumber("m_rightSpeed ",0);
		SmartDashboard.putNumber("m_shooterSpeed",0);
	}
	public static void AutoInit() {
		m_gyro.reset();
		m_timer.start();
		currentTimer = m_timer.get();
		SmartDashboard.putNumber("currentTime", 0);
		SmartDashboard.putNumber("GyroAngle", 0);
	}
	public static void tankDrive(){
		if(joystick.getRawButton(9)&&joystick.getRawButton(10)) {
//			myRobot.tankDrive(Joystick.getY(Hand.kLeft)/2, Joystick.getY(Hand.kRight));
			System.out.print("aBC");
		}
		else{
//			myRobot.tankDrive(Joystick.getY(Hand.kLeft)*-0.7,Joystick.getY(Hand.kRight)*-0.7);
			System.out.print("cab");
		}
		if(joystick.getBumper(Hand.kLeft)) {  				
				m_shooter.set(ControlMode.PercentOutput, -0.8);
			}
		
		else if(joystick.getBumper(Hand.kRight)) {
				m_shooter.set(ControlMode.PercentOutput, 0.5);
			}
		/*else if(Joystick.getYButton()){
			m_shooter.set(ControlMode.PercentOutput, 0.5);
		}*/
		else {
			m_shooter.set(ControlMode.PercentOutput, 0);
		}

/*		SmartDashboard.putNumber("m_leftSpeed ",((WPI_VictorSPX)m_leftMotor).get());
		SmartDashboard.putNumber("m_rightSpeed ",((WPI_VictorSPX) m_rightMotor).get());
		SmartDashboard.putNumber("m_shooterSpeed", ((WPI_VictorSPX) m_shooter).get());
*/	
	}
	public static void autoPerio() {
		switch(situation) {	
			case 0:
				if(showOK == "case1 OK") {
					situation = 2;
					System.out.print(showOK);
					break;
				}
				else if (showOK == "case2 OK") {
					situation = 3;
					System.out.print(showOK);
					break;	
				}
			case 1:
				if (currentTimer < 2) {
                    m_Robot.tankDrive(0.5- m_gyro.getAngle()*0.03, 0.5+m_gyro.getAngle()*0.03);
                    
				}
				else {
					situation = 0;
					showOK = "case1 OK";	
				}
				break;
			case 2:
				if(currentTimer >2.2 &&currentTimer < 3){
					m_Robot.tankDrive(0, 0);
				}
				else {
					situation = 0;
					showOK = "case2 OK";
				}
				break;
			case 3:
				if(currentTimer >3 && currentTimer <5) {
					
				}
		}
		
	}
}