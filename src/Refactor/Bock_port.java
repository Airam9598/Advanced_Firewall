package Refactor;

public class Bock_port {
	public static void block_port(String remoteip, String remoteport) {
		String command = "cmd /c netsh advfirewall firewall add rule name=tests dir=out remoteip="+remoteip+" remoteport="+remoteport+" action=block"; 
		try {
			Process child=Runtime.getRuntime().exec(command);
			child.waitFor();
		} catch (Exception e1) {}
		
		command = "cmd /c netsh advfirewall firewall add rule name=tests dir=in remoteip="+remoteip+" remoteport="+remoteport+" action=block"; 
		try {
			Process child=Runtime.getRuntime().exec(command);
			child.waitFor();
		} catch (Exception e1) {}
	}
}
