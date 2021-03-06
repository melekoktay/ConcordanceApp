# ConcordanceApp

Welcome to Concordance Application
===========

- A Java SE project has been implemented on Eclipse IDE. It is created as a Maven project in order to simplify third-party library installation. The program finds the frequencies of the words and their sentence number. 
- JUnit, unit testing, Framework has been integrated into the project in order to perform Test-Driven development.
- Strategy, Singleton, and Template Method Patterns have been applied to ConcordanceApp.
- ConcordanceApp takes two files as input: 1) The text file that includes the sentences that will be analyzed and 2) the English abbreviations file. The program transforms this abbr. into special form in order to count them correctly. Abbreviations file includes abbreviations with dots like  i.e. , dr., e.g., etc. Before demonstrating the result, transformed abbreviations revert back to the original form.
- The results have been sorted in alphabetical order as default. Users can change the ordering strategy and list according to the frequency of words in descending order.
- If text file that includes the sentences is greater than **500 MB** , Application stop execution and inform user about the status

**Dependendencies**: minimum **Java** JRE version **1.8** and **JUnit** version **4.13.2**

**Development Environment**: 
- Eclipse IDE for Java Developers Version: **2021-12 (4.22.0)**
- MacBook Pro , Dual-Core Intel Core i5, **8 GB** Memory


A sample program console is given below:

```console
java -jar concordance-0.0.1-SNAPSHOT.jar /Users/melekoktay/git/ConcordanceApp/concordance/src/main/resources/arista.sample.txt /Users/melekoktay/git/ConcordanceApp/concordance/src/main/resources/english.abbrv.properties
```

arista.sample.txt input text file
===========

<img width="588" alt="aristaSampleFile" src="https://user-images.githubusercontent.com/6720099/178770613-f30c5a2e-63f6-4a79-8924-ce8ca7e180bb.png">



english.abbrv.properties 
===========
<img width="249" alt="abbrvFile" src="https://user-images.githubusercontent.com/6720099/178770703-e53d7b96-a60e-4b3e-9a53-5720c2685504.png">


Program Output
===========

<img width="321" alt="ConcordanceAppOutput" src="https://user-images.githubusercontent.com/6720099/178771059-1696dd51-67a1-455d-9f62-a92d7eaa9244.png">



