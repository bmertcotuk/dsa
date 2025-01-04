package com.bmcotuk.dsaa.algorithms;

import com.bmcotuk.dsaa.common.Node;
import com.bmcotuk.dsaa.datastructures.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class OtherAlgorithmsTest {

    // to test console output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private static Stream<Arguments> fibonacciData() {
        return Stream.of(
                Arguments.arguments(0, 0),
                Arguments.arguments(1, 1),
                Arguments.arguments(2, 1),
                Arguments.arguments(3, 2),
                Arguments.arguments(4, 3),
                Arguments.arguments(5, 5),
                Arguments.arguments(6, 8)
        );
    }

    private static Stream<Arguments> primeData() {
        return Stream.of(
                Arguments.arguments(0, false),
                Arguments.arguments(1, false),
                Arguments.arguments(2, true),
                Arguments.arguments(3, true),
                Arguments.arguments(4, false),
                Arguments.arguments(5, true),
                Arguments.arguments(6, false),
                Arguments.arguments(7, true),
                Arguments.arguments(8, false),
                Arguments.arguments(9, false),
                Arguments.arguments(10, false),
                Arguments.arguments(11, true),
                Arguments.arguments(12, false),
                Arguments.arguments(13, true),
                Arguments.arguments(14, false),
                Arguments.arguments(15, false),
                Arguments.arguments(16, false),
                Arguments.arguments(17, true)
        );
    }

    @InjectMocks
    private OtherAlgorithms otherAlgorithms;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void test_calculateFibonacciNumbersRecursively(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciRecursive(input));
    }

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void test_calculateFibonacciNumbersTopDownDP(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciTopDownDP(input));
    }

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void test_calculateFibonacciNumbersBottomUpDP(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciBottomUpDP(input));
    }

    @Test
    void test_printPermutationsOfString() {
        String expected = "ABC\n" +
                "ACB\n" +
                "BAC\n" +
                "BCA\n" +
                "CAB\n" +
                "CBA\n";
        otherAlgorithms.printPermutationsOfString("ABC");
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("primeData")
    void test_checkPrimeNumbersIteratively(int input, boolean output) {
        assertEquals(output, otherAlgorithms.isPrimeIterative(input));
    }

    @ParameterizedTest
    @MethodSource("primeData")
    void test_checkPrimeNumbersRecursively(int input, boolean output) {
        assertEquals(output, otherAlgorithms.isPrimeRecursive(input));
    }

    @Test
    void test_sumDigitsIteratively() {
        assertEquals(15, otherAlgorithms.sumDigitsIterative(54321));
        assertEquals(44, otherAlgorithms.sumDigitsIterative(99998));
        assertEquals(0, otherAlgorithms.sumDigitsIterative(0));
        assertEquals(7, otherAlgorithms.sumDigitsIterative(7));
    }

    @Test
    void test_sumDigitsRecursively() {
        assertEquals(15, otherAlgorithms.sumDigitsRecursive(54321));
        assertEquals(44, otherAlgorithms.sumDigitsRecursive(99998));
        assertEquals(0, otherAlgorithms.sumDigitsRecursive(0));
        assertEquals(7, otherAlgorithms.sumDigitsRecursive(7));
    }

    @Test
    void test_printPowersOf2UntilN() {
        String expected = "1\n" +
                "2\n" +
                "4\n" +
                "8\n" +
                "16\n" +
                "32\n" +
                "64\n";

        otherAlgorithms.printPowersOf2UntilN(67);
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    void test_printPowersOf2UntilN_error() {
        otherAlgorithms.printPowersOf2UntilN(0);
        String actual = outContent.toString();
        assertTrue(actual.isEmpty());
    }

    @Test
    void test_printAllSortedPermutationsInTheAlphabetOfLength() {
        otherAlgorithms.printAllSortedPermutationsInTheAlphabetOfLength(3, "");
        String expected = "abc\n" +
                "abd\n" +
                "abe\n" +
                "acd\n" +
                "ace\n" +
                "ade\n" +
                "bcd\n" +
                "bce\n" +
                "bde\n" +
                "cde\n";
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    void test_printAllPositiveIntegerSolutionsToCubicEquation() {
        otherAlgorithms.printAllPositiveIntegerSolutionsToCubicEquation(5);
        String expected = "Pair1(4,4) - Pair2(4,4)\n" +
                "Pair1(1,4) - Pair2(1,4)\n" +
                "Pair1(1,4) - Pair2(4,1)\n" +
                "Pair1(4,1) - Pair2(1,4)\n" +
                "Pair1(4,1) - Pair2(4,1)\n" +
                "Pair1(1,1) - Pair2(1,1)\n" +
                "Pair1(2,3) - Pair2(2,3)\n" +
                "Pair1(2,3) - Pair2(3,2)\n" +
                "Pair1(3,2) - Pair2(2,3)\n" +
                "Pair1(3,2) - Pair2(3,2)\n" +
                "Pair1(2,5) - Pair2(2,5)\n" +
                "Pair1(2,5) - Pair2(5,2)\n" +
                "Pair1(5,2) - Pair2(2,5)\n" +
                "Pair1(5,2) - Pair2(5,2)\n" +
                "Pair1(2,4) - Pair2(2,4)\n" +
                "Pair1(2,4) - Pair2(4,2)\n" +
                "Pair1(4,2) - Pair2(2,4)\n" +
                "Pair1(4,2) - Pair2(4,2)\n" +
                "Pair1(1,2) - Pair2(1,2)\n" +
                "Pair1(1,2) - Pair2(2,1)\n" +
                "Pair1(2,1) - Pair2(1,2)\n" +
                "Pair1(2,1) - Pair2(2,1)\n" +
                "Pair1(2,2) - Pair2(2,2)\n" +
                "Pair1(3,3) - Pair2(3,3)\n" +
                "Pair1(3,5) - Pair2(3,5)\n" +
                "Pair1(3,5) - Pair2(5,3)\n" +
                "Pair1(5,3) - Pair2(3,5)\n" +
                "Pair1(5,3) - Pair2(5,3)\n" +
                "Pair1(5,5) - Pair2(5,5)\n" +
                "Pair1(3,4) - Pair2(3,4)\n" +
                "Pair1(3,4) - Pair2(4,3)\n" +
                "Pair1(4,3) - Pair2(3,4)\n" +
                "Pair1(4,3) - Pair2(4,3)\n" +
                "Pair1(1,3) - Pair2(1,3)\n" +
                "Pair1(1,3) - Pair2(3,1)\n" +
                "Pair1(3,1) - Pair2(1,3)\n" +
                "Pair1(3,1) - Pair2(3,1)\n" +
                "Pair1(4,5) - Pair2(4,5)\n" +
                "Pair1(4,5) - Pair2(5,4)\n" +
                "Pair1(5,4) - Pair2(4,5)\n" +
                "Pair1(5,4) - Pair2(5,4)\n" +
                "Pair1(1,5) - Pair2(1,5)\n" +
                "Pair1(1,5) - Pair2(5,1)\n" +
                "Pair1(5,1) - Pair2(1,5)\n" +
                "Pair1(5,1) - Pair2(5,1)\n";
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    void test_findTheNumberOfElementsInCommon() {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        assertEquals(3, otherAlgorithms.findTheNumberOfElementsInCommon(array1, array2));
        array1 = new int[]{};
        array2 = new int[]{};
        assertEquals(0, otherAlgorithms.findTheNumberOfElementsInCommon(array1, array2));
        array1 = new int[]{1, 2, 3};
        array2 = new int[]{1, 2, 4};
        assertEquals(2, otherAlgorithms.findTheNumberOfElementsInCommon(array1, array2));
        array1 = new int[]{1, 2, 3};
        array2 = new int[]{4, 5, 6};
        assertEquals(0, otherAlgorithms.findTheNumberOfElementsInCommon(array1, array2));
        array1 = new int[]{11, 22, 33};
        array2 = new int[]{4, 5, 6};
        assertEquals(0, otherAlgorithms.findTheNumberOfElementsInCommon(array1, array2));
        array1 = new int[]{1, 1, 3};
        array2 = new int[]{1, 1, 4};
        assertEquals(2, otherAlgorithms.findTheNumberOfElementsInCommon(array1, array2));
        array1 = new int[]{1, 1};
        array2 = new int[]{1, 1};
        assertEquals(2, otherAlgorithms.findTheNumberOfElementsInCommon(array1, array2));
    }

    @Test
    void test_rearrangeArrayWithRunnerTechnique() {
        int[] input = new int[]{1, 2, 3, 4, 5, 6};
        int[] expected = new int[]{1, 4, 2, 5, 3, 6};
        assertArrayEquals(expected, otherAlgorithms.rearrangeArrayWithRunnerTechnique(input));
    }

    @Test
    void test_rearrangeArrayWithRunnerTechnique_null() {
        assertThrows(
                IllegalArgumentException.class,
                () -> otherAlgorithms.rearrangeArrayWithRunnerTechnique(null));
    }

    @Test
    void test_notRearrangeSmallOrEmptyArrayWithRunnerTechnique() {
        int[] input1 = new int[]{1, 2};
        assertArrayEquals(input1, otherAlgorithms.rearrangeArrayWithRunnerTechnique(input1));
        int[] input2 = new int[]{};
        assertArrayEquals(input2, otherAlgorithms.rearrangeArrayWithRunnerTechnique(input2));
    }

    @Test
    void test_throwExceptionIfArraySizeIsNotEven() {
        int[] input = new int[]{1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> otherAlgorithms.rearrangeArrayWithRunnerTechnique(input));
        int[] input2 = new int[]{1};
        assertThrows(IllegalArgumentException.class, () -> otherAlgorithms.rearrangeArrayWithRunnerTechnique(input2));
    }

    @Test
    void test_rearrangeLinkedListWithRunnerTechnique() {
        LinkedList<Integer> list = new LinkedList<>();
        list.appendToTail(0);
        list.appendToTail(1);
        list.appendToTail(2);
        list.appendToTail(3);
        list.appendToTail(4);
        list.appendToTail(5);
        list.appendToTail(6);
        list.appendToTail(7);
        otherAlgorithms.rearrangeLinkedListWithRunnerTechnique(list);

        Node<Integer> currentNode = list.getHead();
        assertEquals(0, currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(4, currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(1, currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(5, currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(2, currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(6, currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(3, currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(7, currentNode.getData());
    }

    @Test
    void test_rearrangeLinkedListWithRunnerTechnique_null() {
        assertThrows(
                IllegalArgumentException.class,
                () -> otherAlgorithms.rearrangeLinkedListWithRunnerTechnique(null));
    }


    @Test
    void test_notRearrangeSmallOrEmptyListWithRunnerTechnique() {
        LinkedList<Integer> list = new LinkedList<>();
        assertEquals(list, otherAlgorithms.rearrangeLinkedListWithRunnerTechnique(list));
    }

    @Test
    void test_throwExceptionIfListSizeIsNotEven() {
        LinkedList<Integer> list = new LinkedList<>();
        list.appendToTail(0);
        assertThrows(IllegalArgumentException.class,
                () -> otherAlgorithms.rearrangeLinkedListWithRunnerTechnique(list));
        list.appendToTail(1);
        list.appendToTail(2);
        assertThrows(IllegalArgumentException.class,
                () -> otherAlgorithms.rearrangeLinkedListWithRunnerTechnique(list));
    }

    @Test
    void test_generateAllPrimesUntilMax() {
        assertArrayEquals(
                new boolean[]{false, false, true, true, false, true, false, true, false, false, false, true, false, true, false}, // 2, 3, 5, 7, 11, 13
                otherAlgorithms.sieveOfEratosthenes(14));
    }
}
