package logic;

import domen.Laptop;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class IOManager {
    
    private static IOManager instance;
    
    public static IOManager getInstance(){
        if(instance==null){
        instance = new IOManager();
        }
        return instance;
    }
    
    public void serializeList(List<Laptop> list, String fileName){
        try
      {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(fileName)));        
        out.writeObject(list);
        out.close();
      }catch(Exception e){      
          e.printStackTrace();
      }
    }
    
    public List<Laptop> deserializeList(String fileName){
        
        List<Laptop> list = null;
        try
          {
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));                     
             list = (List<Laptop>) in.readObject();                       
             in.close();
          }catch(Exception e){
             e.printStackTrace();
            }  
        return list;
    }
    
    
    /**
     * Serialize List<Laptop>
     *
     * @param list - List<Laptop>
     * @param fileName - String
     */
    public void writeXML(List<Laptop> list, String fileName) {
        FileOutputStream os;
        try {
            os = new FileOutputStream(fileName);
            XMLEncoder encoder = new XMLEncoder(os);
            encoder.writeObject(list);
            encoder.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * unSerialize List<Laptop>
     *
     * @param fileName - String
     * @return List<Laptop>
     */
    @SuppressWarnings("unchecked")
    public List<Laptop> readXML(String fileName) {
        List<Laptop> object = null;
        try {
            FileInputStream is;
            is = new FileInputStream(fileName);
            XMLDecoder decoder = new XMLDecoder(is);
            object = (List<Laptop>) decoder.readObject();
            decoder.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return object;
    }
    
      /**
     * Append dataset file with one laptop
     * @param laptop Laptop
     */
    public void writeToDataset(String laptop, String datasetName) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(datasetName, true)))) {
            out.println(laptop);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //      ("itemID,sellingPrice,sellingCurrency," +
//      "shippingPrice,shippindCurrency,returnShippingPaid," +
//	"type,brand,operatingSystem," +
//	"screenSize,hardDriveGB,ramBG," +
//	"processorType,processorSpeedMHZ,releaseYear,graphicsProcessingType,condition");
    }
    
    public void writeHeaderToDataset(String datasetName){
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(datasetName, true)))) {
            out.println("itemID,sellingPrice,sellingCurrency," +
                "shippingPrice,shippingCurrency,returnShippingPaidBy," +
                "type,brand,operatingSystem," +
                "screenSize,hardDriveGB,ramGB," +
                "processorType,processorSpeedGHZ,releaseYear,graphicsProcessingType,condition");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
