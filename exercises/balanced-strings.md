# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this.

## Answer

1.
Empty string Balanced: "".
    aucun symbole repéré
Single Pair of Balanced Symbols: "()", "[]", "{}".
    une seule paire de symbole
Single Pair of Unbalanced Symbols: "(", ")", "[}", "{]".
    une fausse paire de symbole
Multiple Balanced Symbols: "([])", "{()[]}", "{[()]}".
    plusieurs paires de symbole bien imbriqué
Multiple Unbalanced Symbols: "([)]", "([)", "([{}])]", "}{".
    plusieurs symboles mal imbriqués
Symbols with Mixed Content: "abc{[()]def}".
    plusieurs symboles bien imbriqué avec d'autre caractères
Symbols with Mixed Content Unbalanced: "a{}b]c".
    plusieurs symboles mal imbriqué avec d'autre caractères

2.
Test coverage 100%
Ajout de test pour parcourir tout le code, chaque conditions est ainsi testée.

4.
Generated 19 mutations Killed 19 (100%).

Pit Test Coverage Report:


Number of Classes	Line Coverage	Mutation Coverage
1	92% 12/13	100% 19/19
Breakdown by Class
Name	Line Coverage	Mutation Coverage
StringUtils.java	92% 12/13	100% 19/19

Le test de mutation est passé.