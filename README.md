# ConcordanceApp

Welcome to Concordance Application !
===========

— A Java SE project has been implemented on Eclipse IDE. It is created as a Maven project in order to simplify third-party library installation. The program finds the frequencies of the words and their sentence number. 
— JUnit, unit testing, Framework has been integrated into the project in order to perform Test-Driven development.
— Strategy, Singleton, and Template Method Patterns have been applied to ConcordanceApp.
- ConcordanceApp takes two files as input: 1) The text file that includes the sentences that will be analyzed and 2) the English abbreviations file. The program transforms this abbr. into special form in order to count them correctly. Abbreviations file includes abbreviations with dots like  i.e. , dr., e.g., etc. Before demonstrating the result, transformed abbreviations revert back to the original form.
- The results have been sorted in alphabetical order as default. Users can change the ordering strategy and list according to the frequency of words in descending order.


A sample program console is given below:

```console
java -jar concordance-0.0.1-SNAPSHOT.jar /Users/melekoktay/git/ConcordanceApp/concordance/src/main/resources/arista.sample.txt /Users/melekoktay/git/ConcordanceApp/concordance/src/main/resources/english.abbrv.properties
```

arista.sample.txt


english.abbrv.properties


<img width="321" alt="ConcordanceAppOutput" src="https://user-images.githubusercontent.com/6720099/178768086-05dd6fdf-8a40-4516-9090-216c896333da.png">
