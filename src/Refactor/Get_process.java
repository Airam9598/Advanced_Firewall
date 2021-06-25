package Refactor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import model.Win_process;

public class Get_process {
	public static HashMap<Integer, Win_process> get_process2() {
		String cadena;
		HashMap<Integer,Win_process> procesmap=new HashMap<Integer, Win_process>();
		try {
			Process child=Runtime.getRuntime().exec("cmd /c netstat -on > text.txt");
			child.waitFor();
			BufferedReader br = new BufferedReader(new FileReader("./text.txt"));
			for(int i=0; i<3;i++)br.readLine();
			while((cadena = br.readLine())!=null) {
				cadena=cadena.trim();
				cadena = cadena.replaceAll("\\s+", " ");
				String[] parts=cadena.split(" ");
				if(parts.length==5) {
					String[] div = parts[1].split(":");
					String[] div2 = parts[2].split(":");
					procesmap.put(procesmap.size(),new Win_process(parts[0],div[0],div[1],div2[0],div2[1],parts[3],parts[4],get_executable(parts[4])));
				}
				
			}
			br.close();
			return procesmap;
		}catch(Exception e ) {
			System.out.println(e);
			return procesmap;
		}
	}
	public HashMap<Integer, Win_process> get_process() {
		String cadena;
		HashMap<Integer,Win_process> procesmap=new HashMap<Integer, Win_process>();
		try {
			Process p=Runtime.getRuntime().exec("cmd /c netstat -on");
			BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
			for(int i=0; i<3;i++)br.readLine();
			while((cadena = br.readLine())!=null) {
				cadena=cadena.trim();
				cadena = cadena.replaceAll("\\s+", " ");
				String[] parts=cadena.split(" ");
				if(parts.length==5) {
					String[] div = parts[1].split(":");
					String[] div2 = parts[2].split(":");
					procesmap.put(procesmap.size(),new Win_process(parts[0],div[0],div[1],div2[0],div2[1],parts[3],parts[4],get_executable(parts[4])));
				}
				
			}
			br.close();
			return procesmap;
		}catch(Exception e ) {
			System.out.println(e);
			return procesmap;
		}
		
	}
	private static String get_executable(String pid) {
		 try {
  		     Process p=Runtime.getRuntime().exec("cmd /c tasklist /FI \"PID eq "+pid+"\"");
  		     BufferedReader s=new BufferedReader(new InputStreamReader(p.getInputStream()));
  		     p.waitFor();
  		     for(int i=0;i<3;i++) {s.readLine();}
  		     String data=s.readLine();
  		     return data.substring(0,data.indexOf(" "));
  		    } catch (Exception e1) {}
		return "";
		
	}
}

