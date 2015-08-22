/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domen.Laptop;
import java.util.LinkedList;
import java.util.List;
import logic.IOManager;


/**
 *
 * @author Stefan
 */
public class MainEBay {
    
    public static void main(String[] args) {
       
        List<Laptop> ll = new LinkedList<Laptop>();
        
        ll = IOManager.getInstance().deserializeList("Database/mainList.ser");
        
        System.out.println(ll.size());
        
        // preprocess attributes with condition in setters 
        
        for (Laptop l : ll) {	
            l.setSellingPrice(l.getSellingPrice());         
            l.setSellingCurrency(l.getSellingCurrency()); 
            l.setShippingPrice(l.getShippingPrice()); 
            l.setShippingCurrency(l.getShippingCurrency()); 
            l.setReturnShippingPaid(l.getReturnShippingPaid());
            l.setType(l.getType());
            l.setBrand(l.getBrand());
            l.setOperatingSystem(l.getOperatingSystem());       
            l.setScreenSize(l.getScreenSize());
            l.setHardDrive(l.getHardDrive());
            l.setRam(l.getRam());          
            l.setProcessorType(l.getProcessorType());
            l.setProcessorSpeed(l.getProcessorSpeed());
            l.setReleaseYear(l.getReleaseYear());
            l.setGraphicsProcessingType(l.getGraphicsProcessingType());
            l.setCondition(l.getCondition());
        }
        
        IOManager.getInstance().writeHeaderToDataset("Database/dataSetLaptops.csv");
        
        for (Laptop l : ll) {
            IOManager.getInstance().writeToDataset(l.toString(), "Database/dataSetLaptops.csv");           
        }           
        
    }
  
    
}
