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
	private static final byte m_stick = 0;
	
	private static final byte L_frontMotor = 0; 
	private static final byte L_rearMotor = 1;
	private static final byte R_frontMotor =  2;
	private static final byte R_rearMotor = 3;

//put more else motors controller here 	
//private static final byte m_sth = 4...;
	
	private static DifferentialDrive m_Robot;
	@SuppressWarnings("unused")
	private static XboxController joystick;
	private static WPI_VictorSPX L_frontMotor, R_frontMotor;
	private static VictorSPX L_rearMotor, R_rearMotor;
// more motor controller	
//current time
//private static VictorSPX m_sth....;
	private static double c_time ;

	
	@SuppressWarnings("unused")

// put every initialize code here
	
	public static void init(){
		
		R_frontMotor = new WPI_VictorSPX(p_rightMotor);
		L_frontMotor = new WPI_VictorSPX(p_leftMotor);
		
		m_stick = new XboxController(p_joystick);
		m_gyro= new ADXRS450_Gyro();
		m_timer = new Timer();
		m_Robot = new DifferentialDrive(L_frontMotor, R_frontMotor);
		
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
// 	public static void AutoInit() {
// 		m_gyro.reset();
// 		m_timer.start();
// 		currentTimer = m_timer.get();
// 		SmartDashboard.putNumber("currentTime", 0);
// 		SmartDashboard.putNumber("GyroAngle", 0);
// 	}
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
// 	public static void autoPerio() {
		
// 	}
}
