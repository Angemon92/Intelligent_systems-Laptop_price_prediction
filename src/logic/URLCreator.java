package logic;

/**
 *
 * @author Stefan
 */
public class URLCreator {
    
    private static URLCreator instance;
    
    public static URLCreator getInstance() {
        if (instance == null) {
            instance = new URLCreator();
        }
        return instance;
    }
    
    /**
     * Create URL for eBay API
     *
     * @param pageNumber of max 100
     * @param numberOfItems of max 1000 per one page
     * @param options attributes specified
     * @return String URL for eBay API
     */
    public String createAllItemsIDURL(String pageNumber, String numberOfItems) {

        String getAllLaptopsURL = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&"
                + "SERVICE-VERSION=1.9.0&SECURITY-APPNAME=StefanJo-8deb-4770-b5ef-48b5eab273ed&"
                + "RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD&paginationInput.pageNumber=" + pageNumber
                + "&paginationInput.entriesPerPage=" + numberOfItems
                + "&categoryId=177&outputSelector=AspectHistogram";
//	System.out.println("URL for all itemID: " + eBay_API_URL);
        return getAllLaptopsURL;
    }

    /**
     * Create URL for eBay API
     *
     * @param itemID for witch all attributes have to be set
     * @return String URL for eBay API
     */
    public String createOneFullItemURL(String itemID) {

        String getOneFullLaptopURL = "http://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=XML&"
                + "appid=StefanJo-8deb-4770-b5ef-48b5eab273ed&"
                + "siteid=0&version=515&ItemID=" + itemID
                + "&IncludeSelector=Description,ItemSpecifics";
//	System.out.println("URL for one full item: " + getOneFullLaptop);
        return getOneFullLaptopURL;
    }
}
