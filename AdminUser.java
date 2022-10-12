package com.caresoft.clinicapp;
import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {

    private String role;
    private ArrayList<String> securityIncidents;
    
    public AdminUser(Integer adminUserID, String role) {
    	super(adminUserID);
    	this.role = role;
    	this.securityIncidents = new ArrayList<String>();
    }
    
    public boolean assignPin(int pin) {
    	String pinString = Integer.toString(pin);
    	int pinLength = pinString.length();
    	if (pinLength == 6) {
    		this.pin = pin;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean accessAuthorized(Integer confirmedAuthID) {
    	if (confirmedAuthID == this.id) {
    		return true;
    	}
    	else {
    		this.authIncident();
    		return false;
    	}
    }
    
    @Override
	public ArrayList<String> reportSecurityIncidents() {
		return securityIncidents;
    }
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<String> getSecurityIncidents() {
		return this.securityIncidents;
	}

	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}

	
}
