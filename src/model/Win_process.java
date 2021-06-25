package model;

public class Win_process {
	private String PID, Protocolo, remoteip, remoteport, localip, localport,estado,executable;
	public Win_process(String protocolo, String localip, String localport, String remoteip, String remoteport, String estado,String pID,String executable) {
		PID = pID;
		Protocolo = protocolo;
		this.remoteip = remoteip;
		this.remoteport = remoteport;
		this.localip = localip;
		this.localport = localport;
		this.estado = estado;
		this.executable=executable;
	}
	public String getPID() {
		return PID;
	}
	public String getProtocolo() {
		return Protocolo;
	}
	public String getRemoteip() {
		return remoteip;
	}
	public String getRemoteport() {
		return remoteport;
	}
	public String getLocalip() {
		return localip;
	}
	public String getLocalport() {
		return localport;
	}
	public String getEstado() {
		return estado;
	}
	public String getExecutable() {
		return executable;
	}
	

}
