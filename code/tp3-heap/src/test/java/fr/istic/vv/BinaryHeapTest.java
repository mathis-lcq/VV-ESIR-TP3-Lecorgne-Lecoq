package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    private BinaryHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
    }

    @Test
    void testPushAndPeek() {
        assertEquals(0, heap.count(), "Le tas devrait être vide au début");

        heap.push(10);
        assertEquals(1, heap.count(), "Le tas devrait contenir un élément après un ajout");
        assertEquals(10, heap.peek(), "L'élément en haut du tas devrait être 10");

        heap.push(5);
        assertEquals(2, heap.count(), "Le tas devrait contenir deux éléments après deux ajouts");
        assertEquals(5, heap.peek(), "L'élément en haut du tas devrait être le plus petit, c'est-à-dire 5");

        heap.push(20);
        assertEquals(3, heap.count(), "Le tas devrait contenir trois éléments après trois ajouts");
        assertEquals(5, heap.peek(), "L'élément en haut du tas devrait toujours être le plus petit, c'est-à-dire 5");
    }

    @Test
    void testPop() {
        assertThrows(NoSuchElementException.class, heap::pop, "Pop sur un tas vide devrait lancer une exception");

        heap.push(10);
        heap.push(5);
        heap.push(20);

        assertEquals(5, heap.pop(), "L'élément retiré devrait être le plus petit : 5");
        assertEquals(2, heap.count(), "Le tas devrait contenir deux éléments après un pop");
        assertEquals(10, heap.pop(), "L'élément retiré devrait être le plus petit restant : 10");
        assertEquals(1, heap.count(), "Le tas devrait contenir un élément après deux pops");
        assertEquals(20, heap.pop(), "L'élément retiré devrait être le dernier : 20");
        assertEquals(0, heap.count(), "Le tas devrait être vide après avoir retiré tous les éléments");

        assertThrows(NoSuchElementException.class, heap::pop, "Pop sur un tas vide après avoir retiré tous les éléments devrait lancer une exception");
    }

    @Test
    void testPeek() {
        assertThrows(NoSuchElementException.class, heap::peek, "Peek sur un tas vide devrait lancer une exception");

        heap.push(15);
        assertEquals(15, heap.peek(), "L'élément en haut du tas devrait être 15");
        assertEquals(1, heap.count(), "Le tas devrait contenir un élément");

        heap.push(10);
        assertEquals(10, heap.peek(), "L'élément en haut du tas devrait être le plus petit : 10");
        assertEquals(2, heap.count(), "Le tas devrait contenir deux éléments");
    }

    @Test
    void testCount() {
        assertEquals(0, heap.count(), "Le tas devrait être vide initialement");

        heap.push(1);
        heap.push(2);
        heap.push(3);

        assertEquals(3, heap.count(), "Le tas devrait contenir trois éléments après trois ajouts");

        heap.pop();
        heap.pop();
        heap.pop();

        assertEquals(0, heap.count(), "Le tas devrait être vide après avoir retiré tous les éléments");
    }

    @Test
    void testPushWithDuplicates() {
        heap.push(10);
        heap.push(10);
        heap.push(10);

        assertEquals(3, heap.count(), "Le tas devrait contenir trois éléments identiques");
        assertEquals(10, heap.pop(), "Le premier élément retiré devrait être 10");
        assertEquals(10, heap.pop(), "Le deuxième élément retiré devrait être 10");
        assertEquals(10, heap.pop(), "Le troisième élément retiré devrait être 10");
    }

    @Test
    void testPushPopAlternating() {
        heap.push(30);
        assertEquals(30, heap.peek(), "L'élément en haut du tas devrait être 30 après un ajout");
        heap.push(20);
        assertEquals(20, heap.peek(), "L'élément en haut du tas devrait être 20 après un ajout plus petit");
        heap.pop();
        assertEquals(30, heap.peek(), "L'élément en haut du tas devrait être 30 après avoir retiré 20");
        heap.push(10);
        assertEquals(10, heap.peek(), "L'élément en haut du tas devrait être 10 après un ajout plus petit");
        heap.pop();
        assertEquals(30, heap.peek(), "L'élément en haut du tas devrait être 30 après avoir retiré 10");
    }

    @Test
    void testLargeHeap() {
        for (int i = 1000; i > 0; i--) {
            heap.push(i);
        }

        for (int i = 1; i <= 1000; i++) {
            assertEquals(i, heap.pop(), "Les éléments devraient être retirés dans l'ordre croissant");
        }

        assertEquals(0, heap.count(), "Le tas devrait être vide après avoir retiré tous les éléments");
        assertThrows(NoSuchElementException.class, heap::pop, "Pop sur un tas vide après un grand nombre de retraits devrait lancer une exception");
    }
}
