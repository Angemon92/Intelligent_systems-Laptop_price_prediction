# Inetelligent_systems-Laptop_price_prediction
   
[Acknowledgements](#ack)

## Problem explanation
When selling laptop, use machine learning to find out best price for laptop based on its characteristics (RAM,HDD,Processor,...).
   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Problem explanation.jpg)   
`Model is trained using EBay API to create laptop dataset which serves as models input.`
   
### Attributes:   
######Descrete:
* ~~"sellingCurrency"~~
* ~~"shippingCurrency"~~
* "returnShippingPaidBy"
* "type"
* "brand"
* "operatingSystem"
* "processirType"
* "releaseYear"
* "graphicProcessingType"
* "condition"

######Continious:   
* ~~"itemID"~~
* "shippingPrice"
* "screenSize"
* "hardDriveGB"
*"ramGB"
*"processorSpeedGHZ"
* **"sellingPrice"**
   
`hint: `~~Striketrought~~` - removed attribute, `**bold**` - Output attribute`


### Exploratory analysis
#### Selling price
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/1 SellingPrice DIstibution.jpg)
   
#### Condition, Processor type, Brand, Release year
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/2 collage.jpg)
#### Graphic processing type, Operating system, Type of computer, Return shipping paid by
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/3 collage.jpg)
   
#### Hard drice, RAM, Processor speed, Screen size
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/4 collage.jpg)

  
##<a name="ack"></a>Acknowledgements
The project was developed as part of the project assignment for the course <a href="http://is.fon.rs">Intelligent Systems</a> at the <a href="http://fon.rs">Faculty of Organization Sciences</a>, University of Belgrade, Serbia.
