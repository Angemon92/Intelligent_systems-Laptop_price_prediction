package domen;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Objects;

public class Laptop implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static String noExist = "null";
	
	private String itemID = noExist; //done
	private String sellingPrice = noExist; //done
	private String sellingCurrency = noExist; //done
	private String shippingPrice = noExist; //done
	private String shippingCurrency = noExist; //done
	
	private String returnShippingPaid = noExist; //done
	private String type = noExist; //done
	private String brand = noExist; //done
	private String operatingSystem = noExist; //done
	private String screenSize = noExist; //done
	private String hardDriveGB = noExist; //done
	private String ramGB = noExist; //done
	private String processorType = noExist ;
	private String processorSpeed = noExist;
	private String releaseYear = noExist; //done
	private String graphicsProcessingType = noExist; //done
	private String condition = noExist; // done

	public Laptop() {
	}
	
//---------- done
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
//---------- done
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String currentPrice) {
		this.sellingPrice = currentPrice;
	}
//---------- done
	public String getSellingCurrency() {		
		return sellingCurrency;
	}
	public void setSellingCurrency(String sellingCurrency) {
		this.sellingCurrency = sellingCurrency;
	}
//---------- done
	public String getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(String shippingPrice) {
			
		if(shippingPrice == null || shippingPrice.equals("NO")) shippingPrice = noExist;
		if(shippingPrice.equals("null")) shippingPrice = noExist;
		
		this.shippingPrice = shippingPrice;
		
	}
//---------- done
	public String getShippingCurrency() {
		return shippingCurrency;
	}
	public void setShippingCurrency(String shippindCurrency) {
		this.shippingCurrency = shippindCurrency;
	}
//---------- done
	public String getReturnShippingPaid() {
		return returnShippingPaid;
	}
	public void setReturnShippingPaid(String returnShippingPaid) { // values: Seller or Buyer
		try{
			if(returnShippingPaid.equals(null)); //exception!
		}
		catch(Exception e){
			returnShippingPaid = noExist;
		}
		this.returnShippingPaid = returnShippingPaid;
	}
//---------- done //to add: "Product Line"=ThinkPad,latitude,Satellite,IdeaPad,EliteBook,Inspiron  "Model"=Satellite,
	public String getType() {	//Unusual: Portable, HP Laptop Notebook Computer, Gaming Laptop, Chromebook, Toshiba Laptop Notebook Computer, Mini Laptop, Semi-Rugged Laptop, Mini PC    // Error: 3144, CF-18
		return type;
	}
	public void setType(String type) {	// Values: Notebook, Notebook PCs, laptop, Laptop, Laptop/Notebook, Notebook Laptop, Tablet/Laptop Convertible,PC portable, Netbook, Ultrabook
		boolean modified = false;
		if(type.toUpperCase().contains("NOTEBOOK")){type="Notebook"; modified=true;}
		if(type.toUpperCase().contains("LAPTOP") || type.toUpperCase().contains("CHROMEBOOK")){type="Laptop"; modified=true;}
		if(type.toUpperCase().contains("NOTEBOOK") && type.toUpperCase().contains("LAPTOP")){type="Notebook/Laptop"; modified=true;}
		if((type.toUpperCase().contains("TABLET") && type.toUpperCase().contains("LAPTOP")) || type.toUpperCase().contains("TABLETS")){type="Tablet/Laptop"; modified=true;}
		if(type.toUpperCase().contains("NETBOOK")){type="Netbook"; modified=true;}
		if(type.toUpperCase().contains("ULTRABOOK")){type="Ultrabook"; modified=true;}
		if(type.toUpperCase().contains("PORTABLE")){type="Portable"; modified=true;}

		if(!modified){
			//writeError(itemID, "type", type);
			type = noExist;
		}	
		this.type = type;
	}
//----------- done // to add: "Manufacturer"=Lenovo, "Manufacturer Model"=ThinkPad T430
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		boolean modified = false;
		brand = brand.toUpperCase();
		
		if(brand.contains("DELL")){ brand="Dell"; modified=true;}
		if(brand.contains("LENOVO")){ brand="Lenovo"; modified=true;}
		if(brand.contains("HP")){ brand="HP"; modified=true;}
		if(brand.contains("ASUS")){ brand="Asus"; modified=true;}
		if(brand.contains("ACER")){ brand="Acer"; modified=true;}
		if(brand.contains("PANASONIC")){ brand="Panasonic"; modified=true;}
		if(brand.contains("SONY")){ brand="Sony"; modified=true;}
		if(brand.contains("TOSHIBA")){ brand="Toshiba"; modified=true;}
		if(brand.contains("FUJITSU")){ brand="Fujitsu"; modified=true;}
		if(brand.contains("IBM")){ brand="IBM"; modified=true;}
		if(brand.contains("GATEWAY")){ brand="Gateway"; modified=true;}
		if(brand.contains("COMPAQ")){ brand="Compaq"; modified=true;}
		if(brand.contains("SAMSUNG")){ brand="Samsung"; modified=true;}
		if(brand.contains("HEWLETT PACKARD")){ brand="HP"; modified=true;}
		if(brand.contains("EMACHINES")){ brand="Emachines"; modified=true;}
		if(!modified){ 
			//writeError(itemID,"brand",brand);
			brand = noExist;
		}		
		this.brand = brand;
	}
//---------- done
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		boolean modified = false;
		
		operatingSystem = operatingSystem.toUpperCase();
		operatingSystem = operatingSystem.replaceAll(" ", "");
		
		if(operatingSystem.contains("WINDOWS2000")){ operatingSystem="Windows 2000"; modified=true;}
		if(operatingSystem.contains("WINDOWSXP")){ operatingSystem="Windows XP"; modified=true;}
		if(operatingSystem.contains("WINDOWSVISTA")){ operatingSystem="Windows Vista"; modified=true;}
		if(operatingSystem.contains("WINDOWS7")){ operatingSystem="Windows 7"; modified=true;}
		if(operatingSystem.contains("WINDOWS8")){ operatingSystem="Windows 8"; modified=true;}
		if(operatingSystem.contains("WINDOWS8.1")){ operatingSystem="Windows 8.1"; modified=true;}
		if(operatingSystem.contains("CHROMEOS")){ operatingSystem="Chrome OS"; modified=true;}
		
		if(operatingSystem.contains("NOTINCLUDED")){ operatingSystem="Not included"; modified=true;}
		
		if(!modified){ 
			//writeError(itemID,"operatingSystem",operatingSystem);
			operatingSystem = noExist;
		}		
		this.operatingSystem = operatingSystem;
	}
//---------- done
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {		
		boolean modified = false;
		screenSize.replaceAll(" ", "");
		
		
		// if have 10.00", 10.0", 10"
		if(screenSize.contains("\"")){		
			screenSize = screenSize.substring(0, screenSize.indexOf("\""));
			try{
				double realNumber = Double.parseDouble(screenSize);
                                screenSize = realNumber + "";
				modified=true;
			}catch(Exception e){}	
		}else{ // if its alredy number
			try{
				double realNumber = Double.parseDouble(screenSize);
				screenSize = realNumber + "";
                                modified=true;
			}catch(Exception e){}		
		}
		
		if(!modified){ 
			//writeError(itemID,"screenSize",screenSize);
			screenSize = noExist;
		}
		this.screenSize = screenSize;
	}
//----------- done	
	public String getHardDrive() {
		return hardDriveGB;
	}
	public void setHardDrive(String hardDrive) { // unusual: 1.5 TB + 64GB, 
		boolean modified = false;
		hardDrive = hardDrive.toUpperCase();
		hardDrive = hardDrive.replaceAll(" ", "");
		
                boolean mb = hardDrive.contains("MB");
                boolean gb = hardDrive.contains("GB"); //|| hardDrive.contains("G");
                boolean tb = hardDrive.contains("TB"); //|| hardDrive.contains("1TB");
                               
                if(mb && !gb && !tb && !modified){
                    hardDrive = hardDrive.substring(0, hardDrive.indexOf("MB"));
			try{
                            double realNumber = Double.parseDouble(hardDrive)/1000;
                            hardDrive = realNumber + "";
                            modified=true;
			}catch(Exception e){}
                }
                if(!mb && gb && !tb && !modified){
                    hardDrive = hardDrive.substring(0, hardDrive.indexOf("GB"));
			try{
                            double realNumber = Double.parseDouble(hardDrive);
                            hardDrive = realNumber + "";
                            modified=true;
			}catch(Exception e){}
                }
                if(!mb && !gb && tb && !modified){
                    hardDrive = hardDrive.substring(0, hardDrive.indexOf("TB"));
			try{
                            double realNumber = Double.parseDouble(hardDrive)*1000;
                            hardDrive = realNumber + "";
                            modified=true;
			}catch(Exception e){}
                }
                
                
                
//		if(hardDrive.contains("MB")){ // if its MB
//			hardDrive = hardDrive.substring(0, hardDrive.indexOf("MB"));
//			try{
//				double realNumber = Double.parseDouble(hardDrive)/1024;
//				hardDrive = realNumber + "";
//				modified=true;
//			}catch(Exception e){}
//		}else if(hardDrive.contains("GB")){ // if its BG	
//			hardDrive = hardDrive.substring(0, hardDrive.indexOf("GB"));
//			try{
//				double realNumber = Double.parseDouble(hardDrive);
//				modified=true;
//			}catch(Exception e){}
//		}else if(hardDrive.contains("G")){
//			hardDrive = hardDrive.substring(0, hardDrive.indexOf("G"));
//			try{
//				double realNumber = Double.parseDouble(hardDrive);
//				modified=true;
//			}catch(Exception e){}
//			
//		}else if(hardDrive.contains("TB")){ // if its TB
//			hardDrive = hardDrive.substring(0, hardDrive.indexOf("TB"));
//			try{
//				double realNumber = Double.parseDouble(hardDrive)*1024;
//				hardDrive = realNumber+"";
//				modified=true;
//			}catch(Exception e){}
//			
//		}else if(hardDrive.contains("1TB")){ // if its TB
//				hardDrive = 1024 + "";
//				modified=true;
//		} else {// if its already number
//			try{
//				double realNumber = Double.parseDouble(hardDrive);
//				modified=true;
//			}catch(Exception e){}		
//		}
		
		if(!modified){ 
			//writeError(itemID,"hardDrive",hardDrive);
			hardDrive = noExist;
		}
		this.hardDriveGB = hardDrive;
	}
// ---------- done
	public String getRam() {
		return ramGB;
	}
	public void setRam(String ram) {
		boolean modified = false;
		ram = ram.toUpperCase();
		ram = ram.replaceAll(" ", "");
		
                
                boolean mb = ram.contains("MB");
                boolean gb = ram.contains("GB"); //|| ram.contains("G");
                boolean tb = ram.contains("TB"); //|| ram.contains("1TB");
                
                if(ram.contains("256")){
                    ram = "0.25";
                    modified = true;
                }
                if(ram.contains("500")){
                    ram = "0.5";
                    modified = true;
                }
                if(ram.contains("1024") || ram.contains("1000")){
                    ram = "1";
                    modified = true;
                }
                if(ram.contains("3072")){
                    ram = "3";
                    modified = true;
                }
                if(ram.contains("4096")){
                    ram = "4";
                    modified = true;
                }
                
                if(mb && !gb && !tb && !modified){
                    ram = ram.substring(0, ram.indexOf("MB"));
			try{
                            double realNumber = Double.parseDouble(ram)/1000;
                            ram = realNumber + "";
                            modified=true;
			}catch(Exception e){}
                }
                if(!mb && gb && !tb && !modified){
                    ram = ram.substring(0, ram.indexOf("GB"));
			try{
                            double realNumber = Double.parseDouble(ram);
                            ram = realNumber + "";
                            modified=true;
			}catch(Exception e){}
                }
                if(!mb && !gb && tb && !modified){
                    ram = ram.substring(0, ram.indexOf("TB"));
			try{
                            double realNumber = Double.parseDouble(ram)*1000;
                            ram = realNumber + "";
                            modified=true;
			}catch(Exception e){}
                }
                
//		if(ram.contains("MB")){ // if its MB	
//			ram = ram.substring(0, ram.indexOf("MB"));
//			try{
//				double realNumber = Double.parseDouble(ram)/1024;
//				ram = realNumber+"";
//				modified=true;
//			}catch(Exception e){}
//			
//		}else if(ram.contains("G")){ // if its GB, "GYGABITES,G,GIG,..."
//			ram = ram.substring(0, ram.indexOf("G"));
//			try{
//				double realNumber = Double.parseDouble(ram);
//				modified=true;
//			}catch(Exception e){}
//			
//		}else if(ram.contains("GB")){ // if its GB
//			ram = ram.substring(0, ram.indexOf("GB"));
//			try{
//				double realNumber = Double.parseDouble(ram);
//				modified=true;
//			}catch(Exception e){}
//			
//		}else{// if its already number 
//			try{
//				double realNumber = Double.parseDouble(ram);		
//				if(ram.equals("512")) ram = 512/1024 + "";
//				modified=true;
//			}catch(Exception e){}		
//		}
		
		if(!modified){
			//writeError(itemID,"ram",ram);
			ram = noExist;
		}
		
		this.ramGB = ram;
	}
// ---------- 
	public String getProcessorType() {
		return processorType;
	}
	public void setProcessorType(String processorType) {
		boolean modified = false;
		processorType = processorType.toUpperCase();
		processorType = processorType.replaceAll(" ", "");

		// 1)
//		// { AMD = 92, INTEL = 285, ATOM = 9,  "null" = 273 } => sum = 659
//		if(processorType.contains("INTELCOREI3")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI31STGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI32NDGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI33RDGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI34THGEN")) { processorType = "INTEL"; modified=true;} // Intel� 4th Generation Core� i3
//		if(processorType.contains("INTELCOREI5")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI51STGEN")) { processorType = "INTEL"; modified=true;} // Intel Core i5 M520
//		if(processorType.contains("INTELCOREI52NDGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI53RDGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI54THGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI7")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI71STGEN")) { processorType = "INTEL"; modified=true;} // Intel Core i7 m620, core i7-3537u 2.GHz
//		if(processorType.contains("INTELCOREI72NDGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI73RDGEN")) { processorType = "INTEL"; modified=true;}
//		if(processorType.contains("INTELCOREI74THGEN")) { processorType = "INTEL"; modified=true;} // Intel� 4th Generation Core� i7

		// 2)
//		// { AMD = 92, ATOM = 9, Intel = 578, i3 = 0, i5=6, i7=3}
//		if(processorType.contains("INTEL")) { processorType = "Intel"; modified=true;}
//		if(processorType.contains("I3")) { processorType = "i3"; modified=true;}
//		if(processorType.contains("I5")) { processorType = "i5"; modified=true;}
//		if(processorType.contains("I7")) { processorType = "i7"; modified=true;}
//		if(processorType.contains("AMD")) { processorType = "AMD"; modified=true;}
//		if(processorType.contains("ATOM")) { processorType = "ATOM"; modified=true;}
		
		
		// 3)
		// { AMD = 92, INTEL = 578, ATOM = 9 ,"null" = 273 } => sum = 952/1000
		if(processorType.contains("INTEL")) { processorType = "INTEL"; modified=true;}
		if(processorType.contains("AMD") && !modified) { processorType = "AMD"; modified=true;}
		if(processorType.contains("ATOM") && !modified) { processorType = "ATOM"; modified=true;}
		
		if(!modified){
			//writeError(itemID, "processorType", processorType);
			processorType = noExist;
		}
		
		this.processorType = processorType;
	}
// ---------- done
	public String getProcessorSpeed() {
		return processorSpeed;
	}
	public void setProcessorSpeed(String processorSpeed) {
		boolean modified = false;
		processorSpeed = processorSpeed.toUpperCase();
		processorSpeed = processorSpeed.replaceAll(" ", "");
                
		// if have GHZ
		if(processorSpeed.contains("GHZ") || processorSpeed.contains("G")){
			processorSpeed = processorSpeed.substring(0, processorSpeed.indexOf("G"));
			try{
				double realNumber = Double.parseDouble(processorSpeed);
				modified = true;
			}catch(Exception e){}
		}else if(processorSpeed.contains("MHZ")){ // if have MHZ
			processorSpeed = processorSpeed.substring(0, processorSpeed.indexOf("MHZ"));
			try{
				double realNumber = Double.parseDouble(processorSpeed);
				processorSpeed = realNumber/1000 + ""; // transform to GHZ
				modified=true;
				
			}catch(Exception e){}	
		} else{ // its only number, let it stay, and preprocess it in next phase.
			try{
				double realNumber = Double.parseDouble(processorSpeed);
				if(realNumber>10) processorSpeed = realNumber + ""; // it has to be in MHZ, so transform to GHZ
				modified=true;
			}catch(Exception e){}
		}
		
                if(modified){
                    try{
			double realNumber = Double.parseDouble(processorSpeed);
			if(realNumber>500) processorSpeed = realNumber/1000 + ""; 
		}catch(Exception e){}
                
                }
                
                
		if(!modified){
			//writeError(itemID,"processorSpeed",processorSpeed);
			processorSpeed = noExist;
		}
		this.processorSpeed = processorSpeed;
	}
//---------- done
	public String getReleaseYear() {	// Errors: null, ASUS, Not Applicable
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {	// Values: 2008,2011,2014,...	
		if(releaseYear.equals(null) || releaseYear.length()<4|| (releaseYear.length()>4 && !releaseYear.contains("20")) || !releaseYear.startsWith("20")){
			//writeError(itemID,"releaseYear",releaseYear);
			releaseYear = noExist;
		}
		if(releaseYear.length()>4 && releaseYear.contains("20")) releaseYear=releaseYear.substring(releaseYear.indexOf("20"), releaseYear.indexOf("20")+4);
		
                if(releaseYear.equals("2016")) releaseYear=noExist;
                
                this.releaseYear = releaseYear;
	}
//---------- done
	public String getGraphicsProcessingType() {	// Errors: "�AMD Radeon HD 8510G graphics with up to 4224MB to", "nVidia Quadro NVS 160M", "GeForce GTX 840M","15.6"", "Radeon HD 8280",...
		return graphicsProcessingType;
	}
	public void setGraphicsProcessingType(String graphicsProcessingType) {	// Values: Integrated/On-Board Graphics, Dedicated Graphics, Intergrated, Hybrid Graphics - INTEL HD , Hybrid Graphics, Intel� HD Graphics 4400, Intel HD,... (intel=integreted) 
		boolean modified=false;
		graphicsProcessingType = graphicsProcessingType.toUpperCase();
		
		// assumption !
		if(graphicsProcessingType.toUpperCase().contains("AMD") || graphicsProcessingType.contains("RADEON")) {graphicsProcessingType="Dedicated"; modified=true;}
		if(graphicsProcessingType.toUpperCase().contains("NVIDIA") || graphicsProcessingType.contains("GEFORCE")) {graphicsProcessingType="Dedicated"; modified=true;}
		
		if(graphicsProcessingType.toUpperCase().contains("INTEGRATED") || graphicsProcessingType.contains("ON-BOARD") || graphicsProcessingType.contains("ONBOARD") || graphicsProcessingType.contains("INTEGRATED") || graphicsProcessingType.toUpperCase().contains("INTEL")){ graphicsProcessingType="Integrated"; modified=true;}
		if(graphicsProcessingType.toUpperCase().contains("HYBRID")) {graphicsProcessingType="Hybrid"; modified=true;}
		if(graphicsProcessingType.toUpperCase().contains("DEDICATED")) {graphicsProcessingType="Dedicated"; modified=true;}
		
		if(!modified){
			//writeError(itemID,"GraphicsProcessingType",graphicsProcessingType);
			graphicsProcessingType = noExist;
		}
		
		this.graphicsProcessingType = graphicsProcessingType;
	}
//---------- done
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		// if(condition.equals("New other (see details)")) condition = "A brand-new new, unused item with no signs of wear. Packaging may be missing or opened. The item may be a factory second or have defects."
		this.condition = condition;
	}
	
	
	
	
	/**
	 *  Write atributes value if it is unusual (cant be formalized), and write to "errorList.txt" .
	 * @param itemID
	 * @param attributeName
	 * @param attributeValue
	 */
	public void writeError(String itemID, String attributeName, String attributeValue){
		try( PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("errorList.txt", true))) ){
			out.println(itemID+" - "+attributeName+"="+attributeValue);
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	/**
	 * Delete all context from "erroeList.txt" file.
	 */
	public static void clearErrorListFile(){
		try {
			PrintWriter pw = new PrintWriter("errorList.txt");
			pw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
// All attributes for dataset:	
//	itemID, sellingPrice, sellingCurrency, shippingPrice,
//	shippindCurrency, returnShippingPaid, type, brand, 
//	operatingSystem, screenSize, hardDrive, ram, processorType,
//	processorSpeed, releaseYear, graphicsProcessingType, condition
	
	public String toString() {
		String row =  itemID + "," + sellingPrice + "," + sellingCurrency + "," + shippingPrice
				+ "," + shippingCurrency + "," + returnShippingPaid + "," + type + "," + brand
				+ ","+ operatingSystem + "," + screenSize + "," + hardDriveGB + "," + ramGB + "," + processorType
				+ "," + processorSpeed + "," + releaseYear + "," + graphicsProcessingType + "," + condition;
		
		return row;
	}
	
	
//	// Original setters and getters
//	public String getItemID() {
//		return itemID;
//	}
//
//	public void setItemID(String itemID) {
//		this.itemID = itemID;
//	}
//
//	public String getSellingPrice() {
//		return sellingPrice;
//	}
//
//	public void setSellingPrice(String sellingPrice) {
//		this.sellingPrice = sellingPrice;
//	}
//
//	public String getSellingCurrency() {
//		return sellingCurrency;
//	}
//
//	public void setSellingCurrency(String sellingCurrency) {
//		this.sellingCurrency = sellingCurrency;
//	}
//
//	public String getShippingPrice() {
//		return shippingPrice;
//	}
//
//	public void setShippingPrice(String shippingPrice) {
//		this.shippingPrice = shippingPrice;
//	}
//
//	public String getShippingCurrency() {
//		return shippingCurrency;
//	}
//
//	public void setShippingCurrency(String shippindCurrency) {
//		this.shippingCurrency = shippindCurrency;
//	}
//
//	public String getReturnShippingPaid() {
//		return returnShippingPaid;
//	}
//
//	public void setReturnShippingPaid(String returnShippingPaid) {
//		this.returnShippingPaid = returnShippingPaid;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//	public String getOperatingSystem() {
//		return operatingSystem;
//	}
//
//	public void setOperatingSystem(String operatingSystem) {
//		this.operatingSystem = operatingSystem;
//	}
//
//	public String getScreenSize() {
//		return screenSize;
//	}
//
//	public void setScreenSize(String screenSize) {
//		this.screenSize = screenSize;
//	}
//
//	public String getHardDrive() {
//		return hardDriveGB;
//	}
//
//	public void setHardDrive(String hardDrive) {
//		this.hardDriveGB = hardDrive;
//	}
//
//	public String getRam() {
//		return ramGB;
//	}
//
//	public void setRam(String ram) {
//		this.ramGB = ram;
//	}
//
//	public String getProcessorType() {
//		return processorType;
//	}
//
//	public void setProcessorType(String processorType) {
//		this.processorType = processorType;
//	}
//
//	public String getProcessorSpeed() {
//		return processorSpeed;
//	}
//
//	public void setProcessorSpeed(String processorSpeed) {
//		this.processorSpeed = processorSpeed;
//	}
//
//	public String getReleaseYear() {
//		return releaseYear;
//	}
//
//	public void setReleaseYear(String releaseYear) {
//		this.releaseYear = releaseYear;
//	}
//
//	public String getGraphicsProcessingType() {
//		return graphicsProcessingType;
//	}
//
//	public void setGraphicsProcessingType(String graphicsProcessingType) {
//		this.graphicsProcessingType = graphicsProcessingType;
//	}
//
//	public String getCondition() {
//		return condition;
//	}
//
//	public void setCondition(String condition) {
//		this.condition = condition;
//	}
//
//	public static String getNoExist() {
//		return noExist;
//	}
//
//	public static void setNoExist(String noExist) {
//		Laptop.noExist = noExist;
//	}

    public boolean equals(Object obj) {
        if (obj!=null && obj instanceof Laptop){
            Laptop l = (Laptop)obj;
            if (this.getItemID().equals(l.getItemID())) {
                return true;
            }
        }
        return false;
    }
	

        
}
