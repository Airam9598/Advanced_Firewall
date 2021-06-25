package Refactor;

public class go_to_url {
	

	 public void goToURL(String URL){
	           if (java.awt.Desktop.isDesktopSupported()) {
	            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

	            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
	                try {
	                    java.net.URI uri = new java.net.URI(URL);
	                    desktop.browse(uri);
	                } catch (Exception ex) {
	                }
	            }
	        }
	    }

}
