# Vending Machine
Implementation of vending machine that returns optimal change for a given number of pence. Supports unlimited and limited supply of coins. Available coins are `1p`, `2p`, `5p`, `10p`, `20p`, `50p` & `£1`.

## Unlimited inventory 
Contains coins with unlimited count. To run application with unlimited supply the `--unlimited` argument should be provided right after application name in command line.   

```
vending-machine --unlimited
```
## Unlimited inventory
Contains coins with limited count. Initial supply is defined in `inventory.properties` file with format of `denomination = count`   
```
100=11
50=24
20=0
10=99
5=200
2=11
1=23

```
To run application with limited supply of coins the `--limited` argument should be provided.
```
vending-machine --limited
```
Coins are reduces each time when vending happens and on not enough balance the systems will return insufficient coinage error. Property file gets updated accordingly.
## Use case
```
$ java -jar vending-machine-1.0.jar --unlimited
Welcome to Vending Machine!

Press Ctrl+C to quit..

Enter amount: 1891
The least required coins are 22.
£1x18, 50px1, 20px2, 1px1

Enter amount:
```
## Build instructions

To build a project run
```
./gradlew build
```
It will create an executable jar file. 
