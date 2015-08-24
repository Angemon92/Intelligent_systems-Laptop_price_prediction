# Inetelligent_systems-Laptop_price_prediction
   
#Report is NOT finished yet...
   
[About the project](#atp)   
[Collecting the data](#cd)   
[Filtering data and exploratory analysis](#fdea)   
[Applying machine learning techniques for regression problem and evaluation](#ml)   
[Acknowledgements](#ack)   
[Licence](#lic)   
   
##<a name="atp"></a>About the project
Everytime we want to sell something, following question appears: `What is the good price for my selling item ?`
There are two ways to give the answer:   
1.Surf the internet, looking for prices of laptops with similar characteristics (attributes in the following text) as yourown.   
2.Use machine learning to get optimal, **best price** (not too expensive neather cheap), based on near 40000 laptops collected from EBay's API.
   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Problem explanation.jpg)   

The aim of this project is to help the user to **name the price** of laptop that he wants to sell. Every laptop have several attributes from which depend its value. 
In this project whole process will be shown:
* Collecting the data
* Filtering data
* Applying machine learning techniques for regression problem and evaluation

###<a name="cd"></a>Collecting the data   
Application is developed for collecting data and contains two "pieces":   
1. First piece's role is to collect data from EBay. Main graphical form [*CollectingDataForm*] creates new thread [*CollectingDataThread*] that every 12h calls EBay's API two times. First time it collects 10000 laptop's IDs, and second time calls API to extract all available data (attributes) for each laptop based on laptop's ID.   
2. Second piece [*Main*] is programmed to do core cleaning of collected data (ex: All brand names to upper case, MB to GB for RAM, MHZ to GHZ for processor speed, ...). [cleaning rules can be found in Laptop class, inside setter methods]   
Conceptual Class diagram of application can be seen in picture below.
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/EBay laptops.png)
`Only important attributes and methods of classes are shown. Laptop class is domain class and it is used by most of the classes in project.`   
*Application is written in JAVA, using NetBeans IDE.*   
*Application is using DOM4J library for manipulation with XML.*
   
##### Selected  and excluded attributes
Following attributes are exluded from collecting process bacose small number ot laptops has them (every 100'th laptop has these attrributes): Wirless, Warranty, Graphic card configuration, Weight, Item must be returned within, Refund will be given as, Processor configuration.     
Attribute "Model" is also excluded becose it has high variance. (example: Model e {40A10090US, Latitude E6420, ZV5000, E6400, D620, D630, Mini 10, ...}   
Final collection of attributes contains: [Attribute list](#al)

###<a name="fdea"></a>Filtering data and exploratory analysis
   
####<a name="al"></a>Attributes:     
######Categorical:   
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

######Continuous:   
* ~~"itemID"~~
* "shippingPrice"
* "screenSize"
* "hardDriveGB"
* "ramGB"
* "processorSpeedGHZ"
* **"sellingPrice"**
   
`hint: `~~Striketrought~~` - removed attribute, `**bold**` - Output attribute`
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
* For categorical attributes, random sample from existing values from attribute is used to impute missing data.
* For continuous attributes, median statistic is used to impute missing data.
   
###<a name="ml"></a>Applying machine learning techniques for regression problem and evaluation   
Python open source program "Orange Data Mining" is used to perform process of building and learning machine learning  models (Orange/EBay Orange workflow.ows contains flow that can be inported in Orangle program to perform predictions). Models that are learned and evalueted are:   
* Linear regression with L1 reguralization (lambda=0.3)
* Linear regression with L2 reguralization (lambda=0.3)
* CART - Classification And Regression Tree
   
Evaluation metrics are: Root Mean Square Error, Mean Apsolute Error, R squared.
Whole flow, models and values of evaluation metrics can bee seen in a picture below.   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Orange workflow.png)
   
##<a name="ack"></a>Acknowledgements
The project was developed as part of the project assignment for the course <a href="http://is.fon.rs">Intelligent Systems</a> at the <a href="http://fon.rs">Faculty of Organizational Sciences</a>, University of Belgrade, Serbia.
##<a name="lic"></a>Licence

This software is licensed under the MIT License

The MIT License (MIT)

Copyright (c) 2015 Stefan Jovanovic – StefanPo92@gmail.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
