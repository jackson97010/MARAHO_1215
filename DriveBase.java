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

	private static final byte p_stick = 0;
	private static final byte p_LfrontMotor = 0; 
	private static final byte p_LrearMotor = 1;
	private static final byte p_RfrontMotor =  2;
	private static final byte p_RrearMotor = 3;

//put more else motors controller here 	
//private static final byte m_sth = 4...;
	
	private static DifferentialDrive m_Robot;
	private static XboxController m_stick;
	private static WPI_VictorSPX L_frontMotor, R_frontMotor;
	private static VictorSPX L_rearMotor, R_rearMotor;
// more motor controller	
//current time
//private static VictorSPX m_sth....;

	
	@SuppressWarnings("unused")

// put every initialize code here
	
	public static void init(){
		
		R_frontMotor = new WPI_VictorSPX(p_LfrotnMotor);
		R_rearMotor = new VictorSPX(p_RrearMotor);
		L_frontMotor = new WPI_VictorSPX(p_LfrontMotor);
		L_rearMotor = new VictorSPX(p_LrearMotor);
		
		m_stick = new XboxController(p_stick);
		
		m_timer = new Timer();
		m_Robot = new DifferentialDrive(L_frontMotor, R_frontMotor);
// motor safety	and situation
		L_rearMotor.set(ControlMode.follower, p_LfrontMotor);
		R_rearMotor.set(ControlMode.follower, p_RfrontMotor);
		
		L_frontMotor.setInverted(false);
		L_rearMotor.setInverted(false);
		R_frontMotor.setInverted(false);
		R_rearMotor.setInverted(false);
		
        	L_frontMotor.setNeutralMode(NeutralMode.Brake);
        	L_rearMotor.setNeutralMode(NeutralMode.Brake);
        	R_frontMotor.setNeutralMode(NeutralMode.Brake);
		R_rearMotor.setsetNeutralMode(NeutralMode.Brake);

	}

	public static void tankDrive(){
		myRobot.tankDrive(stick.getY(Hand.kLeft), Joystick.getY(Hand.kRight));
	}
//	Trigger&Bumper
//		if(stick.getBumper(Hand.kRight)) {
// 			put something here
// 		}
// 		else {
// 		}
// 		
//		if(stick.getTrigger(Hand.kLeft > 0.5)){
// 		}


}
