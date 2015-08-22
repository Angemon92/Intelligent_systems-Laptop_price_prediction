package logic;

import domen.Laptop;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLHandler {

	private static XMLHandler instance;
	SAXReader reader;
	Document document;

	public static XMLHandler getInstance(){
		if(instance==null){
			instance = new XMLHandler();
		}
		return instance;
	}

	/**
	 * Fill list with Laptops, only itemID is seted.
	 * @return List<Laptop> with only attribute itemID setted
	 */
	public List<Laptop> getCoreLaptopList(boolean onlyOnePage, boolean monitor) {

		List<Laptop> laptopList = new LinkedList<Laptop>();
		int totalPageNumber;

		try {
			reader = new SAXReader();       
			totalPageNumber = getNumberOfPages(reader,document);
			if(monitor) System.out.println("Total pages available:" + totalPageNumber);
			if(onlyOnePage) totalPageNumber = 1;

			Element root; // xml root element           
			Element searchResultsElement; // xml element where all items are       
			List<Element> itemElementList; // list of all xml elements where are item in each element

			int numberOfItems = 0; // total number of items readed

			//go trough all pages 		
			for (int i = 1; i <= totalPageNumber; i++) {

				if(monitor) System.out.println("Reading page: " + i);

				document = reader.read(URLCreator.getInstance().createAllItemsIDURL(i + "", "1000"));
				root = document.getRootElement(); // findItemsByKeywordsResponse tag		
				searchResultsElement = root.element("searchResult");
				itemElementList = searchResultsElement.elements();

				// read all itemID's from items and set itemID attribute
				for (Element itemElement : itemElementList) {
					Laptop laptop = new Laptop();
					setLaptopCoreAttributes(itemElement, laptop);
					laptopList.add(laptop);
					numberOfItems++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return laptopList;
	}

	/**
	 * get document only to extract how many number of pages there are for all laptops on eBay at this moment.
	 * @return 
	 */
	public int getNumberOfPages(SAXReader reader, Document document) throws DocumentException{
		
		int totalPageNumber = 0;
		
		try{
			document = reader.read( URLCreator.getInstance().createAllItemsIDURL("1", "1000") );

			//enter root element
			Element root = document.getRootElement(); // findItemsByKeywordsResponse tag

			//get max number of pages
			Element paginationOutputElement = root.element("paginationOutput");
			totalPageNumber = Integer.parseInt(paginationOutputElement.elementText("totalPages"));

			// cant get more then 100 pages (API constraint)
			if (totalPageNumber >= 100) totalPageNumber = 100;
		}catch(Exception e){
			System.out.println("!pages!");
		}
		return totalPageNumber;
	}


	public void fillLaptopList(List<Laptop> list, boolean monitor) {
		int i = 0;
		if(monitor) System.out.println("Setting remaining values for laptops:");
		for (Laptop laptop : list) {

			if(monitor){
				if (i % 10 == 0) System.out.print(i + "\n");
				else System.out.print(".");
			}

			setOneFullLaptop(laptop);

			i++;
		}
		if(monitor) System.out.println("\nTotal laptop completed: " + i);
	}


	/**
	 * Create xml doc with all atributes for one laptop
	 */
	public void setOneFullLaptop(Laptop laptop) {

		try {

			reader = new SAXReader();
			document = reader.read(URLCreator.getInstance().createOneFullItemURL(laptop.getItemID()));

			Element root = document.getRootElement(); // GetSingleItemResponse element
			Element item = root.element("Item"); // Item element
			Element itemSpecifics = item.element("ItemSpecifics"); // ItemSpecifics element 
			List<Element> itemElements = itemSpecifics.elements(); // list of elements NameValueList 

			for (Element element : itemElements) {

				String attributeName = element.elementText("Name");
				String attributeValue = element.elementText("Value");

				switch (attributeName) {

				case "Return shipping will be paid by":
					laptop.setReturnShippingPaid(attributeValue);
					break;

					//case "Product Line":	
				case "Product Type":
				case "Type":
					laptop.setType(attributeValue);
					break;

				case "Brand":
					laptop.setBrand(attributeValue);
					break;

				case "Operating System":
				case "Operating System ****":
				case "Operating System:":
					laptop.setOperatingSystem(attributeValue);
					break;

				case "Screen Size":
				case "ScreenSize":
				case "LCD Size":
					laptop.setScreenSize(attributeValue);
					break;

				case "Hard Drive Capacity":
				case "Hard Drive":
					laptop.setHardDrive(attributeValue);
					break;

				case "Memory":
				case "M�moire RAM":
				case "Memory (RAM)":
					laptop.setRam(attributeValue);
					break;

					//Processor Configuration
				case "Processor Type":
				case "Processor":
				case "Processor Name":
				case "Processor Model":
					laptop.setProcessorType(attributeValue);
					break;

				case "Processor Speed":
					laptop.setProcessorSpeed(attributeValue);
					break;

				case "Release Year":
					laptop.setReleaseYear(attributeValue);
					break;

				case "Graphics Processing Type":
					laptop.setGraphicsProcessingType(attributeValue);
					break;

				default: ; // do nothing
				}
			}

		} catch (Exception e) {
			System.out.println("!setFull!");
		}
	}

	/**
	 * For given XML element, extract:
	 * itemID, sellingPrice, sellingCurrency, shippingPrice and shippingCurrency.
	 * @param itemElement
	 * @return Laptop object
	 */
	private Laptop setLaptopCoreAttributes(Element itemElement, Laptop laptop) {
		try{
			//read item ID
			String itemID = itemElement.elementText("itemId");
			laptop.setItemID(itemID);

			//read conditionID
			Element conditionElement = itemElement.element("condition");
			String condition = conditionElement.elementText("conditionDisplayName");
			laptop.setCondition(condition);

			//read selling currency and price
			Element sellingStatusElement = itemElement.element("sellingStatus");

			String sellingCurrencyId = sellingStatusElement.element("currentPrice").attributeValue("currencyId");
			laptop.setSellingCurrency(sellingCurrencyId);

			String currentPrice = sellingStatusElement.elementText("currentPrice");
			laptop.setSellingPrice(currentPrice);

			//read shipping currency and price
			Element shippingInfoElement = itemElement.element("shippingInfo");

			String shippingServiceCost = shippingInfoElement.elementText("shippingServiceCost");
			laptop.setShippingPrice(shippingServiceCost);

			//sometimes there is no shipping currency
			String shippingCurrencyId;
			try {
				shippingCurrencyId = shippingInfoElement.element("shippingServiceCost").attributeValue("currencyId");
			} catch (Exception e) {
				shippingCurrencyId = sellingCurrencyId;
			}
			laptop.setShippingCurrency(shippingCurrencyId);
		}catch(Exception e){
			System.out.println("!setCore!");
		}
		return laptop;
	}

	/**
	 * Calculate frequency for every value from attribute
	 *
	 * @param valuesForAttributList
	 */
	public void statistic(List<String> valuesForAttributList) {

		HashMap<String, Integer> frequencymap = new HashMap<String, Integer>();

		for (String value : valuesForAttributList) {
			if (frequencymap.containsKey(value)) {
				frequencymap.put(value, frequencymap.get(value) + 1);
			} else {
				frequencymap.put(value, 1);
			}
		}

		System.out.println("---   Statistics   ---");
		System.out.println("---   F ( x ) = Frequency of x ---");
		for (String name : frequencymap.keySet()) {
			String key = name;
			String value = frequencymap.get(name).toString();
			System.out.println("F( " + key + " ) = " + value);
		}
	}

}
