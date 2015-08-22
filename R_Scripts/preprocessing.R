dataOriginal = read.csv("../Database/dataSetLaptops.csv",header = T,  stringsAsFactors=T, na.strings = "null",
                colClasses=c("numeric","numeric","factor","numeric","factor","factor","factor","factor",
                             "factor","numeric","numeric","numeric","factor","numeric","factor","factor","factor"))
#apply(dataOriginal,2,typeof)

# Filtering and preprocessing data

data = dataOriginal[,-1] # itemID
 
# HINT: is.na(data$attribute) is added becouse condition on NA is NA insted of T/F. Example: data[ condition ,]

data = data[data$shippingCurrency=="USD" & !is.na(data$shippingCurrency),]
data = data[data$sellingCurrency=="USD" & !is.na(data$sellingCurrency),]

data = data[,-2] #sellingCurrency
data = data[,-3] #shippingCurrency

data = data[data$sellingPrice<=2000 | is.na(data$sellingPrice),]
data = data[data$sellingPrice>20 | is.na(data$sellingPrice),]

data = data[(data$ramGB<=32 | is.na(data$ramGB)),]

data = data[data$processorSpeedGHZ<=4 | is.na(data$processorSpeedGHZ),]

data = data[data$hardDriveGB<=4000 | is.na(data$hardDriveGB),]

data = data[data$screenSize<=20 | is.na(data$screenSize),]


data[data$condition=="Brand New","condition"] = "New"
data[data$condition=="Like New","condition"] = "New"
data[data$condition=="New other (see details)","condition"] = "New"

# Vizualization


par( mfrow = c( 1, 1 ) )

par(las=1)


hist(data$sellingPrice, ylim=c(0,4000),breaks = 100, xaxp  = c(0, 2000,10), col="lightblue", main="Frequency distribution of selling price", xlab="Selling price")

p = plot(data$returnShippingPaidBy, main="Return shipping paid by", xlab="", ylab="", xaxt="n", col="lightblue", ylim=c(0,20000) )
labs <- levels(data$returnShippingPaidBy)
text(cex=1, x=p-.25, y=-1.25, labs, srt=45,xpd=T)

p = plot(data$type, main="Type of computer", xlab="", ylab="", xaxt="n", col="lightblue", ylim=c(0,20000) )
labs <- levels(data$type)
text(cex=1, x=p-.25, y=-1.25, labs, srt=45,xpd=T)

p = plot(data$brand, main="Brand", xlab="", ylab="", xaxt="n", col="lightblue", ylim=c(0,10000) )
labs <- levels(data$brand)
text(cex=1, x=p-.25, y=-1.25, labs, srt=45,xpd=T)

p = plot(data$operatingSystem, main="Operating system", xlab="", ylab="", xaxt="n", col="lightblue", ylim=c(0,12000) )
labs <- levels(data$operatingSystem)
text(cex=1, x=p-.25, y=-1.25, labs, srt=45,xpd=T)

p = plot(data$processorType, main="Processor type", xlab="", ylab="", xaxt="n", col="lightblue", ylim=c(0,20000) )
labs <- levels(data$processorType)
text(cex=1, x=p-.25, y=-1.25, labs, srt=45,xpd=T)

p = plot(data$releaseYear, main="Release year", xlab="", ylab="",  xaxt="n", col="lightblue", ylim=c(0,1200) )
labs <- levels(data$releaseYear)
text(cex=1, x=p-.25, y=-1.25, labs, srt=45,xpd=T)

p = plot(data$graphicsProcessingType, main="Graphic processing type", xlab="", ylab="", xaxt="n", col="lightblue", ylim=c(0,12000) )
labs <- levels(data$graphicsProcessingType)
text(cex=1, x=p-.25, y=-1.25, labs, srt=45,xpd=T)

p = plot(x, main="Condition", xlab="", ylab="", xaxt="n", col="lightblue", ylim=c(0,20000) )
labs <- levels(x)
text(cex=1, x=p-.25, y=-1.25, labs, xpd=T, srt=45)

# Plotting scale attrributes
smoothScatter(data[,c("shippingPrice","sellingPrice")],main="Shipping price over selling price" ,xlab = "Shipping price [USD]", ylab="Selling price")
abline(lm(sellingPrice ~ shippingPrice, data = data), col="blue")

smoothScatter(data[,c("screenSize","sellingPrice")],main="Screen size price over selling price", xlab = "Screen size [inches]", ylab="Selling price")
abline(lm(sellingPrice ~ screenSize, data = data), col="blue")

smoothScatter(data[,c("hardDriveGB","sellingPrice")],main="Hard drive over selling price",  xlab = "Hard drive [GB]", ylab="Selling price")
abline(lm(sellingPrice ~ hardDriveGB, data = data), col="blue")

smoothScatter(data[,c("ramGB","sellingPrice")],main="RAM over selling price",  xlab = "RAM [GB]", ylab="Selling price")
abline(lm(sellingPrice ~ ramGB, data = data), col="blue")

smoothScatter(data[,c("processorSpeedGHZ","sellingPrice")],main="Processor speed over selling price",  xlab = "Processor speed [GHZ]", ylab="Selling price")
abline(lm(sellingPrice ~ processorSpeedGHZ, data = data), col="blue")

###
# interesting
library(ggplot2)
p <- ggplot(data, aes(factor(type), processorSpeedGHZ)) 
p1 = p + geom_boxplot(outlier.shape = 3) 
p1 + geom_point(position = position_jitter(width = 0.2))

p <- ggplot(data, aes(factor(returnShippingPaidBy),shippingPrice)) 
p1 = p + geom_boxplot(outlier.shape = 3) 
p1 + geom_point(position = position_jitter(width = 0.2))

p <- ggplot(data, aes(factor(releaseYear),processorSpeedGHZ)) 
p1 = p + geom_boxplot(outlier.shape = 3) 
p1 + geom_point(position = position_jitter(width = 0.2))
###


# missing values
naValues = c( "Return shipping paid by" = sum(is.na(data$returnShippingPaidBy)),
                 "Type of computer" = sum(is.na(data$type)),
                 "Brand" = sum(is.na(data$brand)),
                 "Operating system" = sum(is.na(data$operatingSystem)),
                 "ProcessorType" = sum(is.na(data$processorType)),
                 "Release year" = sum(is.na(data$releaseYear)),
                 "Graphics processing type" = sum(is.na(data$graphicsProcessingType)),
                 "Condition" = sum(is.na(data$condition)),
                "Shipping price" = sum(is.na(data$shippingPrice)),
                "Screen size" = sum(is.na(data$screenSize)),
                "Hard drive" = sum(is.na(data$hardDriveGB)),
                "RAM" = sum(is.na(data$ramGB)),
                "Processing speed" = sum(is.na(data$processorSpeedGHZ)) )               

p = barplot(naValues, main="Missing values per attribute", xlab="", ylab="", ylim=c(0,30000), xaxt="n")
labs <- as.factor(names(naValues))
text(cex=1, x=p-.25, y=-1.25, labs, xpd=T, srt=45)
rm(p,labs,naValues)

# Imputing values - Method RANDOM
for(j in c("returnShippingPaidBy","type","brand","operatingSystem","processorType","releaseYear","condition","graphicsProcessingType" )){
  for(i in 1:nrow(data)){
    if(is.na(data[i,j])) data[i,j] = sample(levels(data[,j]),1)
  }
}
for(j in c("shippingPrice","screenSize","hardDriveGB","ramGB","processorSpeedGHZ")){
  median = median((data[,j]),na.rm=TRUE)
  for(i in 1:nrow(data)){
    if(is.na(data[i,j])) data[i,j] = median
  }
}
rm(i,j,median)

