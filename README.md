# vending-machine
## General description
This project simulates the operation of a vending machine. The application receives a JSON file and updates 
the functionality of the machine. When a consumer enters a selection, it should report what was selected.

- Provide a loading option for new product lists to update.
- User is able to enter a selection.
- Machine prompts the user for payment and amount in US dollars.
- If user enters in payment, calculation must occur and be reported.
- Machine must state the current state of the transaction.
- Main actions logs for audit purposes.

The application reads the list of products from the json file located in project folder 'resources'. 
A list of products and available operations.

![img.png](img.png)

## Application dependencies
Application uses gson library. Gson is a Java library that can be used to convert Java Objects into their JSON 
representation. It can also be used to convert a JSON string to an equivalent Java object. Gson can work with 
arbitrary Java objects including pre-existing objects that you do not have source-code of.

Maven:
```xml
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.9</version>
</dependency>
```
[Gson User Guide:](https://github.com/google/gson/blob/master/UserGuide.md) This guide contains examples on how to use Gson in code.

## Program executing

1. Download project
2. Open CLI in project folder. Do follow command:
   ``` 
   mvn clean install
   java -jar [patch/to/jar/file]
   ```