# BookingGo Technical Test
 
The technical test has been done in one single project with one application that manages different program argumets.
The IDE used is Intellij IDEA 2019.2.3 CE Build #IC - 192.6817.14, built on September 24, 2019. The language used is Java.
Libraries used: org.json, com.sparkjava, org.slf4j (for solving and error with spark). 

### Part 1
For running the first console application, using Intellij, specify this line in the section Debug Configuration:

```
dave 51.470020,-0.454295 3.410632,-2.157533
```

For running the second console application, using Intellij, specify this line in the section Debug Configuration: 
```
all 51.470020,-0.454295 3.410632,-2.157533 3
```

(the last parameter is the number of passengers)

### Part 2
For running this part of the test, using Intellij, specify one of the following two lines in the section Debug Configuration:
```
dave 51.470020,-0.454295 3.410632,-2.157533 api
```

```
all 51.470020,-0.454295 3.410632,-2.157533 3 api
```

Then, using a browser, navigate to 
* [http://localhost:4567/dave](http://localhost:4567/dave) or
* [http://localhost:4567/all](http://localhost:4567/all)
