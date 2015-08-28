# Intelligent systems-Laptop price prediction
Report content:   
[About the project](#atp)   
[Collecting the data](#cd)  
[Data cleaning and attribute selection](#dcas)   
[Exploratory analysis](#ea)   
[Applying machine learning techniques for regression problem and evaluation](#ml)   
[Acknowledgements](#ack)   
[Licence](#lic)   
   
#<a name="atp"></a>About the project
Everytime we want to sell something, following question appears: `What is the good price for my selling item ?`
In this project, selling item will be computer machine (laptop in following text). 
There are two ways to give the answer:   
1.Surf the internet, looking for prices of laptops with similar characteristics (attributes in the following text) as your own.   
2.Use machine learning to get optimal, **best price** (neither too expensive nor cheap), based on data about nearly 40000 laptops collected from EBay's API.
   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Problem explanation.jpg)   Figure 1: Two ways to found out best price for selling laptop.  
   
The aim of this project is to help the user to **name the price** of laptop that he wants to sell. Every laptop has several attributes what influance its value, and accordingly its price.   
In this project the whole process of determining the optimal price for a product will be shown:
* Collecting and filreting the data
* Exploratory analysis
* Applying machine learning techniques for regression problem and evaluation

#<a name="cd"></a>Collecting the data   
The application developed for collecting data from eBay consist of two components:   
1. The role of the first component is to collect data from EBay. Main graphical form [*CollectingDataForm*] creates new thread [*CollectingDataThread*] that every 12h calls EBay's API [*URLCreator*] two times. First time it collects 10000 laptop IDs, and second time calls API to extract all available data (attributes) for each laptop based on laptops ID. API is returning data in XML format [*XMLHandler*] and all collected data are stored [*IOManager*] in Database folder in serialized format. Names of the files with serialized data are generated using date of creation [*DateManager*].   
2. The second component [*Main*] is programmed to do core cleaning of the  collected data. First it de-serializes [*IOManager*] localy stored data. Core cleaning is based on regular expressions. (e.g.: All brand names to upper case, MB to GB for RAM, MHZ to GHZ for processor speed, ...). Cleaning rules can be found in Laptop class, inside its setter methods   
Class diagram of the application can be seen in the picture below.   
   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/EBay laptops.png)   Figure 2: Class diagram of application.     
   
Only important attributes and methods of classes are shown. The Laptop class is a domain class and it is used by most of the classes in project, which is not shown in plot because of clearer view of application structure.  
*Application is written in JAVA, using NetBeans IDE. Application is using DOM4J library for manipulation with XML.*
   
#<a name="dcas"></a> Data cleaning and attribute selection
Date of collecting data: April, 2015.
   
The following attributes are exluded from the data collection process because small number of laptops has them (roughly 1 in 100 laptops has these attributes): "Wireless", "Warranty", "Graphic card configuration", "Weight", "Item must be returned within", "Refund will be given as", "Processor configuration".     
Attribute "Model" is also excluded because it has high variance. (In particular, possible values of this attribute include  {40A10090US, Latitude E6420, ZV5000, E6400, D620, D630, Mini 10, ...}   
Attributes "sellingCurrency" and "shippingCurrency" taking values {"AUD", "CAD", "EUR", "GBP", "USD"} are also excluded because all selling prices and shipping prices are transformed in USD.   
Attribute laptopID, unique value for each laptop, is used only in collecting and filtering data. The attribut is removed after all the data pre-processing is done.   
The attributes selected for building a prediction model are given below.   

###Categorical attributes:   
* `"returnShippingPaidBy"`: In case of return, shipping will be paid by {"Buyer", "Seller"}.
* `"type"`: Type of laptop {"Laptop", "Netbook", "Notebook", "Portable", "Ultrabook"}.
* `"brand"`: Brand of laptop {"Acer", "Asus", "Compaq", "Dell", "Emachines", "Fujitsu", "Gateway", "HP", "IBM", "Lenovo", "Panasonic", "Samsung", "Sony", "Toshiba"}.
* `"operatingSystem"`: Operating system of laptop {"Chrome OS", "Not included", "Windows 2000", "Windows 7", "Windows 8", "Windows Vista", "Windows XP"}.
* `"processorType"`: Type of CPU {"AMD", "ATOM", "INTEL"}.*INTEL could be separeted in {i3, i5, i7} but in that case less the 0.1% of laptops would have some of these 3 values so the idea is rejected.*
* `"releaseYear"`: Release year of laptop {2000, ... , 2015}.*There were several laptops with value of 2016 for this attribute, but they are excluded from dataset.*
* `"graphicProcessingType"`: Type of GPU {"Dedicated", "Hybrid", "Integrated"}.
* `"condition"`: Condition of laptop {"Brand New", "New", "New other (see details)", "Like New", "For parts or not working", "Manufacturer refurbished", "Seller refurbished", "Used"}.*Reduction is used on {"Brand New", "New", "New other (see details)", "Like New"}. All 4 are united in one "New" value.*  

###Continuous attributes:   
* `"shippingPrice"`: Shipping price in USD.  
   
|   Min.    | 1st Qu.  |  Median  |   Mean   | 3rd Qu.  |   Max.   |
| --------- |:--------:|:--------:|:--------:|:--------:|:--------:|
| 0.000     | 0.000    | 0.000    | 5.782    | 12.000   | 300.000  |   
* `"screenSize"`: Screen size in inches in USD. *Constrain: screenSize <= 20*
   
|   Min.    | 1st Qu.  |  Median  |   Mean   | 3rd Qu.  |   Max.   |
| --------- |:--------:|:--------:|:--------:|:--------:|:--------:|
| 4.500     | 14.000   | 14.100   | 14.260   | 15.600   | 20.000   |   
* `"hardDriveGB"`: Hard drive disk in BG. *Constrain: hardDriveGB <= 4000*
   
|   Min.    | 1st Qu.  |  Median  |   Mean   | 3rd Qu.  |   Max.   |
| --------- |:--------:|:--------:|:--------:|:--------:|:--------:|
| 0.000     | 250.000  | 320.000  | 355.200  | 320.000  | 4000.000 |   
* `"ramGB"`: RAM in GB. *Constrain: ramGB <= 32*
   
|   Min.    | 1st Qu.  |  Median  |   Mean   | 3rd Qu.  |   Max.   |
| --------- |:--------:|:--------:|:--------:|:--------:|:--------:|
| 0.000     | 4.000    | 4.000    | 4.453    | 4.000    | 32.000   |   
* `"processorSpeedGHZ"`: CPU speed in GHZ. *Constrain: processorSpeedGHZ <= 4*
   
|   Min.    | 1st Qu.  |  Median  |   Mean   | 3rd Qu.  |   Max.   |
| --------- |:--------:|:--------:|:--------:|:--------:|:--------:|
| 0.000     | 1.900    | 2.130    | 2.110    | 2.400    | 4.000    |   
* `"sellingPrice"`: **Selling price of laptop.** *Constrain: 20 <= selligPrice <= 2000*   
   
|   Min.    | 1st Qu.  |  Median  |   Mean   | 3rd Qu.  |   Max.   |
| --------- |:--------:|:--------:|:--------:|:--------:|:--------:|
| 20.050    | 79.990   | 169.200  | 259.100  | 340.000  | 2000.000 |   
All constraints for continous attributes are defined subjectivly by human in process of exploring the data. Laptops with attributes that do not satisfy any of constraints are removed from dataset. Near 4000 laptops are removed.   

##<a name="ea"></a>Exploratory analysis   
The plots represented in this section give more information about attributes. The first plot [(Figure 3)](#firstp) is frequency distribution of the output attribute, selling price, represented with histogram plot. Following four plots on [Figure 4](#ffp) and four plots of [Figure 5](#sfp) represent frequency distributions of categorical attributes and the last four plots, [Figure 6](#fourp), show continous attributes and their relationship with selling price.   

######<a name="firstp"></a> Frequency distribution of the selling price   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/1 SellingPrice DIstibution.jpg)   Figure 3: Frequency distribution of selling price   
   
Following eight plots represents frequency distribution over eight categorical attributes.   
The given plots lead to the following observations:
<a name="ffp"></a>   
* Most laptops on eBay are selling as used which is expected.
* Intel inside is the most dominant processor type.
* Four most selling brands are: Dell, HP, Lenovo and Toshiba.
* Right-bottom plot tells us that there are more *newer* laptops, which is also expected. Interesting is that we can be expect that till the end of 2015, most of the selling laptops will be those released in 2015, because date of collecting data is April, 2015.   
   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/2 collage.jpg)   Figure 4: Distribution of attributes: "Condition", "Processor type", "Brand" and "Release year". 
   
<a name="sfp"></a>   
*  Majority of the laptops have integrated graphical processing type.
*  Windows 7 is most frequent operating system in the collected set of laptops.
*  Interesting that near 70% of laptops, are notebooks.
*  In case of return, shipping is usually paid by buyer.
   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/3 collage.jpg)   Figure 5: Distribution of attributes: "Graphic processing type", "Operating system", "Type of computer" and "Return shipping paid by".   
<a name="fourp"></a>   
* The following plots represent relationship between the selling price on the y axis, and a ontinous attribute on the x axis. Intensity of blue represents how many laptops have that values for the selling price and the other attribute. The darker the blue is the more laptops have that combination of values for both attributes.      
* Linear relationship between selling price and attribute is represented as blue straight line. Can be seen that for HDD and RAM *almost* propotionaly incresing GBs increses selling price of laptop (if we visually remove noise, we can clearly see relationship line with 45 degrees from the x axis). For the other two attributes, processor speed and screen size, that is not the case. An increase in GHZ or inches, slightly increses selling price of laptop.
* <a name="expl"></a>We can see that every continous variable has high variance for simmilar selling prices. For example for selling price between 190 and 210 USD, processor speed can go from 1 GHZ to 4 GHZ (left-bottom plot). That may be explained by attribute "condition". Even originally high performance expensive laptops can have price near 200 USD, which is cheap, if they are broken and value for condition is "For parts or not working".
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/4 collage.jpg)   Figure 6: Selling price and continous attributes (Hard drive, RAM, Processor speed, Screen size ) relationship.   
   
##### Imputing missing values
One of the problems was missing values. The plot below shows how many missing values each attribute has. Specially this is problem in release year attribute where nearly 85% of values are missing.
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Missing values.png)   Figure 7: Missing values distribution.   
   
Problem is solved for:
* Categorical attributes: Random sample from existing values of attribute is used to impute missing data.
* Continuous attributes: Median statistic is used to impute missing data.
   
##<a name="ml"></a>Applying machine learning techniques for regression problem and evaluation   
Python open source program "Orange Data Mining" is used to perform process of building and learning machine learning  models (in folder OrangeWorkflow, file EBay_workflow.ows can be imported in Orange program to perform predictions). Models that are learned and evalueted are:   
* Linear regression with L1 regularization (lambda=0.3)
* Linear regression with L2 regularization (lambda=0.3)
* CART - Classification And Regression Tree   
   
Evaluation metrics are: Root Mean Square Error and Mean Apsolute Error. Metrics are calculated  using Cross validation with 5 folds technique.   
Dataset contains 32550 laptops.   
   
Whole flow, models and values of evaluation metrics can be seen in a picture below.   
![alt tag](https://raw.github.com/Angemon92/Inetelligent_systems-Laptop_price_prediction/master/Pictures/Orange workflow.png)   Figure 8: Orange program and working flow.   
   
We can see that all three models are simillar based on RMSE and MAE. Suggestion is to use third model, linear regression with L1 regularization, because L1 will do implicitly dimensionality reduction, and the number of attributes that will influance the selling price in future predictions will be smaller. With L1 we are trying to solve problem that there are high variance of the continuous attribute for similar selling prices (this is explained [here](#expl)). Fact is that learning regression tree is near 10 times slower then learning linear regression, but that is not the case in prediction process.
   
##<a name="ack"></a>Acknowledgements
The project was developed as part of the project assignment for the course <a href="http://is.fon.rs">Intelligent Systems</a> at the <a href="http://fon.rs">Faculty of Organizational Sciences</a>, University of Belgrade, Serbia.
##<a name="lic"></a>Licence

Software developed for purpose of this project is licensed under the MIT License

The MIT License (MIT)

Copyright (c) 2015 Stefan Jovanovic – StefanPo92@gmail.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
