package System;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraSettings {
	private static UsbCamera Gopro;
	public static void init() {
		Gopro = CameraServer.getInstance().startAutomaticCapture(0);
		Gopro.setResolution(10000, 10000);
	}
/*	public static void capture(){
		
	}
*/
}
