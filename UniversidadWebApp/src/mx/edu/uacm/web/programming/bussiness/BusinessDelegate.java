package mx.edu.uacm.web.programming.bussiness;

import java.util.ArrayList;

public class BusinessDelegate { 
    
	private BusinessLookUp lookupService = new BusinessLookUp(); 
    private BusinessService businessService; 
    
    private String serviceType; 
  
    public void setServiceType(String serviceType) { 
        this.serviceType = serviceType; 
    } 
  
    /*public ArrayList<> doTask() { 
        businessService = lookupService.getBusinessService(serviceType); 
        businessService.doProcessing();         
    } */
} 
