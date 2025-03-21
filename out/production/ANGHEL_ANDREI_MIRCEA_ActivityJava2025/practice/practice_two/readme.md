# Program intrumente financiare.

### Cerințe:

1. O clasa instrumente.Instrument, care sa implementeze interfata instrumente.Evaluabil si care contine
   urmatoarele atribute private:
   symbol - String, o colectie de obiecte de tip Operatiune financiara
   si o clasă internă statică Operatiune (tip - TipOperatiune, data - LocalDate,
   preț - număr cu zecimale, cantitate - întreg).

Clasa instrumente.Instrument va avea cel putin:
- constructor cu parametri si fara parametri;
- metode de citire pentru toate atributele;
- posibilitatea de a adăuga operatiuni la un intrumentul financiar.

2. Clasa Operatiune trebuie să publica si imutable.

3. Interfata instrumente.Evaluabil, defineste metoda double valoare(Intrument instrument),
   care returneaza double si primeste ca parametru un Intrument financiar.

4. Un enum TipOperatiune cu valorile CUMPARARE, VANZARE și o metodă pozitie() care să dea directia operatiunii
   financiare (-1 VANZARE, 1 CUMPARARE).

5. O clasa Actiune, derivata din Intrumnet si care implementeaza interfata instrumente.Evaluabil.
   Are in plus un atribut privat double - procentDividend.

6. O clasa  PortofoliuGenerics<T> ce implementeaza o colectie generica de intrumente financiare.
   Are un atribut privat portofoliu ce stocheaza colectia de obiecte <T> si contructori cu si fara parametri.
   Contine urmatoarele metode publice:

    ```
   getProtofoliu(),
   setPortofoliu(),
   adaugaIntrument(),
   getIntrument().
   ```

Sa se creeze un program pentru popularea colectiei de intrumente financiare cu obiecte de tip Intrument
si cu obiecte de tip Actiune.
Sa de afiseze continutul portofoliului, valoarea acestuia si data primei operatiuni financiare efectuate
pentru fiecare intrument financiar continut in portofoliu.

Sa se implementeze metoda static void salvarePortofoliu(String caleFisier, PortofoliuGenerics portofoliu)
throws IOException.
Fisierul in care se salveaza portofoliu este un fisier text in formatul:

```
BCR,918.4,2.5
CUMPARARE,2022,4,3,200.0,3,CUMPARARE,2022,4,3,250.0,2,VANZARE,2022,4,3,204.0,1
OMV,896
CUMPARARE,2022,4,3,200.0,3,CUMPARARE,2022,4,3,250.0,2,VANZARE,2022,4,3,204.0,1
```

Sa se implementeze metoda static PortofoliuGenerics<Instrument> incarcarePortofoliu(String caleFisier)
throws IOException.
Fisierul de intrare este fisierul text in formatul descris mai sus.

bibliografie.rar: biblio4java

---