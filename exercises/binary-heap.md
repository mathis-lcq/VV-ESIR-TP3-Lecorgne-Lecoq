# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer

1: Conception des cas de test à l'aide du partitionnement de l'espace d'entrée

Pour concevoir les tests, nous allons identifier les caractéristiques pertinentes de chaque méthode de la classe BinaryHeap et les diviser en blocs significatifs. Voici les caractéristiques et les blocs identifiés pour chaque méthode :
Méthode push(T element)

            Caractéristique : Valeur de l'élément
                Bloc 1 : Élément unique (valeur normale, ex : 10)
                Bloc 2 : Élément dupliqué (même valeur plusieurs fois, ex : 10, 10, 10)
                Bloc 3 : Élément plus grand que l'élément existant
                Bloc 4 : Élément plus petit que l'élément existant
                Bloc 5 : Élément nul (ne s'applique pas ici car on suppose que le tas ne gère pas les valeurs nulles)

Méthode pop()

            Caractéristique : État du tas avant l'appel
                Bloc 1 : Tas vide
                Bloc 2 : Tas avec un seul élément
                Bloc 3 : Tas avec plusieurs éléments

Méthode peek()

            Caractéristique : État du tas avant l'appel
                Bloc 1 : Tas vide
                Bloc 2 : Tas avec un seul élément
                Bloc 3 : Tas avec plusieurs éléments

Méthode count()

            Caractéristique : Nombre d'éléments dans le tas
                Bloc 1 : 0 éléments
                Bloc 2 : 1 élément
                Bloc 3 : Plusieurs éléments

Caractéristiques communes:
Les méthodes pop(), peek(), et count() partagent la caractéristique "État du tas avant l'appel", car leur comportement dépend du nombre d'éléments présents dans le tas.


2: Évaluation de la couverture des instructions

Nous avons déjà conçu les tests de la suite précédente pour couvrir différents scénarios, mais nous devons vérifier la couverture des instructions pour s'assurer qu'ils atteignent toutes les parties du code.
Si la couverture n'atteint pas 100%, il faudra ajouter de nouveaux cas de test. Par exemple, vérifier les cas extrêmes (tas très grands), ajouter des tests avec différentes séquences d'ajout et de suppression d'éléments.


3: Couverture de choix de base (Base Choice Coverage)

La couverture de choix de base vérifie que chaque prédicat est évalué à la fois à vrai et à faux au moins une fois.

Dans notre classe BinaryHeap, les conditions les plus complexes impliquent la comparaison entre les éléments lors de l'ajout dans le tas. Nous devons nous assurer que :

La comparaison des éléments lors de l'ajout est testée avec des éléments plus petits, égaux, et plus grands que l'élément de tête actuel.
Les conditions d'exception (ex: pop sur un tas vide) sont également testées.

Si nous avons des prédicats impliquant plusieurs opérateurs booléens (comme && ou ||), nous devrions tester toutes les combinaisons possibles. Cependant, pour le tas binaire, il n'y a pas de telles conditions multi-étapes dans la logique actuelle.


4: Évaluation de la suite de tests avec PIT (mutation testing)

L'étape finale consiste à utiliser PIT (un outil de mutation testing) pour évaluer la robustesse de la suite de tests. Voici les étapes à suivre :

Exécuter PIT : 
Cela génère des mutants (versions modifiées du code avec de petits changements).
Obtenir le score de mutation : Le score est le pourcentage de mutants "tués" par la suite de tests. Plus le score est élevé, plus la suite de tests est efficace.
Analyser les mutants vivants : Les mutants qui survivent indiquent des parties du code qui ne sont pas correctement testées.
Ajouter ou modifier les tests pour tuer les mutants restants. 
Par exemple :
Ajouter des tests pour des cas limites ou des scénarios inhabituels.
Vérifier des comportements inhabituels comme la suppression d'un élément immédiatement après son ajout.
