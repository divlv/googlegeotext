# GoogleGeoText
Get location description i.e. street number, city, country, etc. from GPS coordinates *(tags: java google map geo text find geocode api place)*.

It uses "**com.fasterxml.jackson**" for data mapping from incoming (from http://maps.googleapis.com/maps/api/geocode) JSON with geo data to particular POJOs. I beieve, many of J2EE projects should have Jackson as a dependency already.

## Usage:
    
1) Add **jitpack.io** repository to your POM file:
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```

2) Add dependency to POM:

```xml
        <dependency>
       	    <groupId>com.github.divlv</groupId>
       	    <artifactId>googlegeotext</artifactId>
       	    <version>1.1</version>
       	</dependency>
```

3) Access the **GoogleGeoCode** class in your code like this:
```java
float aLatitude = 53.325025f; // can be "double" or "String" as well
float aLongitude = -6.24032f; // can be "double" or "String" as well
GeoText geoText = new GeoText();
GoogleGeoCode geoTextData = geoText.getTextData(aLatitude, aLongitude);

// Find formatted address string in a Java 8 way ;-)
String formattedAddress = geoTextData.getResults().stream().findFirst().orElse(new Result()).getFormattedAddress();

// Should be: "41 Herbert Park, Dublin, Ireland"
```

4) Explore the resulting "GoogleGeoCode" object for more information, if needed.

5) Enjoy.