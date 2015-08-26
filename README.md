# Inetelligent_systems-Laptop_price_prediction
   
[About the project](#atp)   
[Collecting the data](#cd)   
[Filtering data and exploratory analysis](#fdea)   
[Applying machine learning techniques for regression problem and evaluation](#ml)   
[Acknowledgements](#ack)   
[Licence](#lic)   
   
##<a name="atp"></a>About the project
Everytime we want to sell something, following question appears: `What is the good price for my selling item ?`
There are two ways to give the answer:   
1.Surf the internet, looking for prices of laptops with similar characteristics (attributes in the following text) as your own.   
2.Use machine learning to get optimal, **best price** (not too expensive neither cheap), based on near 40000 laptops collected from EBay's API.
   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Problem explanation.jpg)   

The aim of this project is to help the user to **name the price** of laptop that he wants to sell. Every laptop has several attributes from which its value depends. 
In this project the whole process will be shown:
* Collecting the data
* Filtering data
* Applying machine learning techniques for regression problem and evaluation

###<a name="cd"></a>Collecting the data   
Application is developed for collecting data and contains two "pieces":   
1. The first piece's role is to collect data from EBay. Main graphical form [*CollectingDataForm*] creates new thread [*CollectingDataThread*] that every 12h calls EBay's API two times. First time it collects 10000 laptop IDs, and second time calls API to extract all available data (attributes) for each laptop based on laptops ID.   
2. The second piece [*Main*] is programmed to do core cleaning of collected data. Core cleaning is based on regular expressions. (ex: All brand names to upper case, MB to GB for RAM, MHZ to GHZ for processor speed, ...). [cleaning rules can be found in Laptop class, inside setter methods]   
Conceptual Class diagram of application can be seen in picture below.
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/EBay laptops.png)
`Only important attributes and methods of classes are shown. Laptop class is domain class and it is used by most of the classes in project.`   
*Application is written in JAVA, using NetBeans IDE.*   
*Application is using DOM4J library for manipulation with XML.*
   
##### Selected  and excluded attributes
Following attributes are exluded from collecting process because small number of laptops has them (every 100th laptop has these attrributes): Wireless, Warranty, Graphic card configuration, Weight, Item must be returned within, Refund will be given as, Processor configuration.     
Attribute "Model" is also excluded because it has high variance. (example: Model e {40A10090US, Latitude E6420, ZV5000, E6400, D620, D630, Mini 10, ...}   
Final collection of attributes contains: [Attribute list](#al)

###<a name="fdea"></a>Filtering data and exploratory analysis
# This chaper is NOT finished yet...   
####<a name="al"></a>Attributes explanation and constraints:     
######Categorical:   
* ~~"sellingCurrency"~~: Selling price currency of laptop {"AUD", "CAD", "EUR", "GBP", "USD"}. *All laptops are filtered to USD currency.* 
* ~~"shippingCurrency"~~: Shipping price currency of laptop {"AUD", "CAD", "EUR", "GBP", "USD"}. *All laptops are filtered to USD currency.*
* "returnShippingPaidBy": In case of return, shipping will be paid by {"Buyer", "Seller"}.
* "type": Type of laptop {"Laptop", "Netbook", "Notebook", "Portable", "Ultrabook"}.
* "brand" Brand of laptop {"Acer", "Asus", "Compaq", "Dell", "Emachines", "Fujitsu", "Gateway", "HP", "IBM", "Lenovo", "Panasonic", "Samsung", "Sony", "Toshiba"}.
* "operatingSystem": Operating system of laptop {"Chrome OS", "Not included", "Windows 2000", "Windows 7", "Windows 8", "Windows Vista", "Windows XP"}.
* "processorType": Type of CPU {"AMD", "ATOM", "INTEL"}.*INTEL could be separeted in {i3, i5, i7} but then less the 0.1% of laptops have some of these 3 values.*
* "releaseYear": Release year of laptop {2000, ... , 2015}.*There was several laptops with value of 2016 in this attribute, but they are excluded from dataset.*
* "graphicProcessingType": Type of GPU {"Dedicated", "Hybrid", "Integrated"}.
* "condition": Condition of laptop {"Brand New", "New", "New other (see details)", "Like New", "For parts or not working", "Manufacturer refurbished", "Seller refurbished", "Used"}.*Reduction is used on {"Brand New", "New", "New other (see details)", "Like New"}. All 4 are united in one "New" value.*  

######Continuous:   
* ~~"itemID"~~
* "shippingPrice"
* "screenSize"
* "hardDriveGB"
* "ramGB"
* "processorSpeedGHZ"
* **"sellingPrice"**
   
`hint: `~~Strikethrough~~` - removed attribute, `**bold**` - Output attribute`
#### Exploratory analysis
###### Selling price
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/1 SellingPrice DIstibution.jpg)
   
###### Condition, Processor type, Brand, Release year
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/2 collage.jpg)
###### Graphic processing type, Operating system, Type of computer, Return shipping paid by
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/3 collage.jpg)
   
###### Hard drive, RAM, Processor speed, Screen size
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/4 collage.jpg)
   
##### Imputing missing values
* Categorical attributes: Random sample from existing values of attribute is used to impute missing data.
* Continuous attributes: Median statistic is used to impute missing data.
   
###<a name="ml"></a>Applying machine learning techniques for regression problem and evaluation   
Python open source program "Orange Data Mining" is used to perform process of building and learning machine learning  models (Orange/EBay Orange workflow.ows contains flow that can be imported in Orange program to perform predictions). Models that are learned and evalueted are:   
* Linear regression with L1 regularization (lambda=0.3)
* Linear regression with L2 regularization (lambda=0.3)
* CART - Classification And Regression Tree
   
Evaluation metrics are: Root Mean Square Error and Mean Apsolute Error.
   
Whole flow, models and values of evaluation metrics can be seen in a picture below.   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Orange workflow.png)
*Evaluation is performed using Cross validation with 5 folds*
   
##<a name="ack"></a>Acknowledgements
The project was developed as part of the project assignment for the course <a href="http://is.fon.rs">Intelligent Systems</a> at the <a href="http://fon.rs">Faculty of Organizational Sciences</a>, University of Belgrade, Serbia.
##<a name="lic"></a>Licence

Software developed for purpose of this project is licensed under the MIT License

The MIT License (MIT)

Copyright (c) 2015 Stefan Jovanovic – StefanPo92@gmail.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
