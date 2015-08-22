/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domen.Laptop;
import gui_collecter.mainFrameForm;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class LaptopWorkerThread implements Runnable{
    
    int name = 0;
    boolean consolMonitorProcess;
    boolean onlyOnePageForTest = false;
    
    public LaptopWorkerThread(boolean consolMonitorProcess){
        this.consolMonitorProcess = consolMonitorProcess;
    }
    
    
    
    public void run() {
        try {
            mainFrameForm.instance.setTxtToForm("Started worker" + name + ": " + DateManager.getInstance().getSimpleDateAndTime());

            List<Laptop> ll;

            ll = XMLHandler.getInstance().getCoreLaptopList(onlyOnePageForTest, consolMonitorProcess);
            XMLHandler.getInstance().fillLaptopList(ll, consolMonitorProcess);
            
            
            IOManager.getInstance().serializeList(ll, "Database/newList"+DateManager.getInstance().getSimpleDate()+".ser");
            
            updateMainList(ll);
            
            mainFrameForm.instance.setTxtToForm("Finished worker" + name + ": " + DateManager.getInstance().getSimpleDateAndTime());
            name++;
        } catch (Exception e) {
            mainFrameForm.instance.setTxtToForm("Worker"+name+" has got exception...");
        }
    }
    
    public void updateMainList(List<Laptop> newList){
        
    	try{
        List<Laptop> mainList = IOManager.getInstance().deserializeList("Database/mainList.ser");
        
        int br = 0;
        for (Laptop laptop : newList) {
            if(!mainList.contains(laptop)){
                mainList.add(laptop);
                br++;
            }
        }    
        mainFrameForm.instance.setTxtToForm("Total added to main list:" + br);
        IOManager.getInstance().serializeList(mainList,"Database/mainList.ser");
    	}catch(Exception e){
    		System.out.println("!updateMainList!");
    	}
    }
            
            
    
 }


    

