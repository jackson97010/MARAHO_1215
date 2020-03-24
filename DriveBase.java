package frc.System;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveBase {

	private static final byte p_stick = 0;
	private static final byte p_LfrontMotor = 0; 
	private static final byte p_LrearMotor = 1;
	private static final byte p_RfrontMotor = 2;
	private static final byte p_RrearMotor = 3;
	
	private static final byte p__PanelSol = 0;
  	// private static final byte p_RampMotor = 4;

//put more else motors controller here 	
//private static final byte m_sth = 4...;
	
	private static DifferentialDrive m_Robot;
	private static XboxController m_stick;
	private static WPI_VictorSPX L_frontMotor, R_frontMotor;
	private static VictorSPX L_rearMotor, R_rearMotor;
	private static Solenoid panel;

//  private static VictorSPX RampMotor;
// more motor controller	
//current time
//private static VictorSPX m_sth....;

	

// put every initialize code here
	
	public static void init(){
		
		panel = new Solenoid(p__PanelSol);
		
		R_frontMotor = new WPI_VictorSPX(p_RfrontMotor);
		R_rearMotor = new VictorSPX(p_RrearMotor);
		L_frontMotor = new WPI_VictorSPX(p_LfrontMotor);
		L_rearMotor = new VictorSPX(p_LrearMotor);
    //  RampMotor = new VictorSPX(p_RampMotor);
        
		m_stick = new XboxController(p_stick);
		
		m_Robot = new DifferentialDrive(L_frontMotor, R_frontMotor);
// motor safety	and situation
		L_rearMotor.set(ControlMode.Follower, p_LfrontMotor);
		R_rearMotor.set(ControlMode.Follower, p_RfrontMotor);
       

		L_frontMotor.setInverted(true);
		L_rearMotor.setInverted(true);
		R_frontMotor.setInverted(true);
		R_rearMotor.setInverted(true);
		
        L_frontMotor.setNeutralMode(NeutralMode.Brake);
        L_rearMotor.setNeutralMode(NeutralMode.Brake);
        R_frontMotor.setNeutralMode(NeutralMode.Brake);
        R_rearMotor.setNeutralMode(NeutralMode.Brake);
//      RampMotor.setNeutralMode(NeutralMode.Brake);

	}

	public static void tankDrive(){
//      tankDrive settings		
		m_Robot.tankDrive(m_stick.getY(Hand.kLeft)/2, m_stick.getY(Hand.kRight)/2);
        // Solenoid settings		
		if(m_stick.getBumper(Hand.kLeft)){
			panel.set(true);
		}
		else if (m_stick.getBumper(Hand.kRight))
			panel.set(false);
		 }

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
// 直接使用motor	
	public static void DirectControl(double left, double right){
		m_Robot.tankDrive(left, right);
	}
	// public static void Panel(Boolean sit){
	// 	DriveBase.panel.set(sit);
	// }



}
