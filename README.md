Budget Portal -Test

Goal : 1) Route to Budget.com 2) Select Austin related state and choose 5 date from current date dynamically
3) Select SUV and low price 4) Assert low price 5) Fill the personal details 

Maven Project with below depedency
Selenium
TestNG
Sure-fire plugin
Steps to clone execute the tests

git clone https://github.com/dineshmcac/BudgetTesting
cd BudgetTesting
mvn clean test
Output: Find the output file in the root with the name output.txt


Pre-requisition
Add chrome or gecko driver with exact version that matches our web version  
folder ---- /diver
cheomedriver
gecko

Data:
config.properties
testng.xml
/data/TC001.xlsx
Java Class

Test Class - BuyLowPriceSUV.java
PageObject Class - HomePage, ReviewAndBook, Vehicles
