# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer


1: Partitionnement de l'espace d'entrée

Le partitionnement de l'espace d'entrée permet d'identifier les caractéristiques et les blocs à tester pour chaque méthode. Voici les caractéristiques et blocs identifiés :
Méthode isValidDate

            Jour (day)
                Bloc valide : 1 à 31
                Bloc invalide : < 1 ou > 31
            Mois (month)
                Bloc valide : 1 à 12
                Bloc invalide : < 1 ou > 12
            Année (year)
                Bloc valide : ≥ 0
                Bloc invalide : < 0
            Cas spéciaux :
                Mois de février (année bissextile ou non)
                Mois avec 30 jours (avril, juin, septembre, novembre)
                Mois avec 31 jours

Méthode isLeapYear

            Année
                Bloc divisible par 4 mais non divisible par 100 (année bissextile)
                Bloc divisible par 400 (année bissextile)
                Bloc divisible par 100 mais non par 400 (non bissextile)
                Bloc non divisible par 4 (non bissextile)

Méthode nextDate
        
            Cas normaux :
                Pas de changement de mois ou d'année
            Changements de mois :
                Fin de mois (31 jours, 30 jours, 28/29 jours pour février)
            Changements d'année :
                31 décembre
            Cas spéciaux :
                Année bissextile

Méthode previousDate

            Cas normaux :
                Pas de changement de mois ou d'année
            Changements de mois :
                Début du mois (1er jour)
            Changements d'année :
                1er janvier
            Cas spéciaux :
                Année bissextile

Méthode compareTo

            Cas normaux :
                Même date
                Date antérieure
                Date postérieure
            Cas spéciaux :
                Comparaison avec null (doit lancer une exception)


2: Évaluation de la couverture des instructions

L'évaluation de la couverture des instructions vise à s'assurer que tous les chemins du code sont couverts. Les tests suivants ont été ajoutés pour augmenter la couverture :

            Tests supplémentaires pour les années bissextiles et non bissextiles dans isValidDate.
            Tests pour les dates limites (début et fin de mois, fin d'année) dans nextDate et previousDate.
            Tests pour les exceptions (compareTo avec null).

3: Couverture des choix de base

Pour les prédicats complexes utilisant plusieurs opérateurs logiques, les tests ont été ajustés pour couvrir toutes les combinaisons possibles. Par exemple, la méthode isValidDate inclut plusieurs vérifications logiques pour la validité du jour et du mois. Les cas suivants ont été ajoutés pour garantir la couverture des choix de base :

            isValidDate avec différentes combinaisons de mois (février bissextile/non bissextile) et de jours limites.
            Tests de logique pour les années dans isLeapYear.

4: Évaluation avec PIT

L'utilisation de PIT permet d'évaluer le score de mutation de la suite de tests. Voici les résultats et les actions entreprises :

            Score de mutation : 66 mutations Killed 61 (92%)
            Mutants vivants : 5
            Actions entreprises : analyse des mutants vivant (resultats considérés comme normal) + score supérieur à 90% -> OK

