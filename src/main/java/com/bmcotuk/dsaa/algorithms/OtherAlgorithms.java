package com.bmcotuk.dsaa.algorithms;

import com.bmcotuk.dsaa.common.Node;
import com.bmcotuk.dsaa.datastructures.LinkedList;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mert Cotuk
 */
@Component
public class OtherAlgorithms {

    // TODO: Finalize the ultimate ordering
    /*
     * Rate of increase in order
     * -> O(n!) > O(2^n) > O(n^2) > O(n*logn) > O(n) > O(logn) > O(1)
     * -> O(n^n) > O(n!)
     * -> O((2^n) * n!) > O(n!)
     */

    /**
     * time: O(2^n) - branches^depth
     * space: O(n) - space required is proportional to the maximum depth of the recursion tree
     */
    public int nthFibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return nthFibonacciRecursive(n - 1) + nthFibonacciRecursive(n - 2);
    }

    /**
     * Memoization is top-down dynamic programming approach which makes recursive algorithms efficient without making
     * them iterative.
     * More at: https://dzone.com/articles/memoization-make-recursive-algorithms-efficient
     * <p>
     * time: O(n) - because each call is called once!
     * space: O(n)
     */
    public int nthFibonacciTopDownDP(int n) {
        int[] memo = new int[n + 1]; // because we directly index the array with "n"
        return nthFibonacciTopDownDPRecursion(n, memo);
    }

    private int nthFibonacciTopDownDPRecursion(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] == 0) {
            memo[n] = nthFibonacciTopDownDPRecursion(n - 1, memo) + nthFibonacciTopDownDPRecursion(n - 2, memo);
        }
        return memo[n];
    }

    /**
     * Bottom-up dynamic programming approach is iterative and the most efficient among all.
     * <p>
     * time: O(n)
     * space: O(1)
     */
    public int nthFibonacciBottomUpDP(int n) {
        if (n <= 1) {
            return n;
        }
        int previous = 0;
        int current = 1;

        for (int i = 2; i <= n; i++) { // loop starts from 2 since we gave the minimum requirement
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    /**
     * time: O(logn)
     * space: O(logn)
     */
    public int printPowersOf2UntilN(int n) {
        if (n < 1) { // error case
            return 0;
        } else if (n == 1) { // base case to avoid 1/2 = 0 and 0*2 = 0 failure
            System.out.println(n);
            return n;
        } else { // recursion
            int previous = printPowersOf2UntilN(n / 2);
            int current = previous * 2;
            System.out.println(current);
            return current;
        }
    }

    /**
     * time: O(n^1/2)
     * space: O(1)
     */
    public boolean isPrimeIterative(int n) {

        if (n <= 1) return false;

        int roundedSquareRoot = (int) Math.floor(Math.sqrt(n));
        for (int i = 2; i <= roundedSquareRoot; i++) { // or better check for (int i = 2; i * i <= n; i++), <= correct
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * time: O(n^1/2)
     * space: O(n^1/2)
     */
    public boolean isPrimeRecursive(int n) {

        if (n <= 1) {
            return false;
        }
        if (n == 2) { // important to add
            return true;
        }
        return isPrimeRecursion(n, 2);
    }

    private boolean isPrimeRecursion(int n, int divisor) {

        if (n % divisor == 0) {
            return false;
        }
        if (divisor * divisor > n) { // not equal here, negation of the condition in iterative version
            return true;
        }
        return isPrimeRecursion(n, divisor + 1);
    }

    /**
     * time: O(logn)
     * space: O(1)
     */
    public int sumDigitsIterative(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    /**
     * time: O(logn)
     * space: O(logn)
     */
    public int sumDigitsRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 10 + sumDigitsRecursive(n / 10);
    }

    /**
     * time: O(n!*n + n*n!*n) = O(n^2*n!)
     * space: O(n) - The dominant factor is the recursive stack depth, which is O(n). Since strings are immutable in Java and not reused between calls, these don't contribute to the overall recursive memory footprint. Therefore, not O(n^2)!
     */
    public void printPermutationsOfString(String str) {
        permutationRecursion(str, "");
    }

    private void permutationRecursion(String remainder, String prefix) {
        if (remainder.isEmpty()) {
            // n! calls, n each
            System.out.println(prefix);
        } else {
            // n*n! calls, n each
            for (int i = 0; i < remainder.length(); i++) {
                permutationRecursion(remainder.substring(0, i) + remainder.substring(i + 1),
                        prefix + remainder.charAt(i));
            }
        }
    }

    /**
     * a = alphabet size
     * k = length of strings
     * <p>
     * Formula `branches^depth` is the same since high school. This gives us how many calls will occur. The next step
     * is to multiply by the base case's complexity. This logic applies to all kinds of problems.
     * <p>
     * time: O(k*a^k)
     * space: O(k)
     */
    int alphabetSize = 5;

    public void printAllSortedPermutationsInTheAlphabetOfLength(int remaining, String prefix) {
        if (remaining == 0) {
            if (isInOrder(prefix)) {
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < alphabetSize; i++) {
                printAllSortedPermutationsInTheAlphabetOfLength(remaining - 1, prefix + nthLetterOfTheAlphabet(i));
            }
        }
    }

    private char nthLetterOfTheAlphabet(int n) {
        return (char) ('a' + n);
    }

    private boolean isInOrder(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) >= s.charAt(i + 1)) { // just to avoid same characters, still can be ">"
                return false;
            }
        }
        return true;
    }

    /**
     * Equation: a^3 + b^3 = c^3 + d^3 where a, b, c, d in [1, n]
     * <p>
     * time: O(n^2)
     * space: O(n^2)
     */
    public void printAllPositiveIntegerSolutionsToCubicEquation(int n) {
        // first, tally results
        Map<Integer, List<Pair<Integer, Integer>>> result2SolutionMap = new HashMap<>();
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int result = (int) Math.pow(a, 3) + (int) Math.pow(b, 3);
                // computeIfAbsent is more elegant
                result2SolutionMap.computeIfAbsent(result, k -> new ArrayList<>())
                        .add(Pair.of(a, b));
            }
        }

        // then, just like in SQL iterate twice on the same set to handle all cases
        for (List<Pair<Integer, Integer>> solutions : result2SolutionMap.values()) {
            for (Pair<Integer, Integer> pair1 : solutions) {
                for (Pair<Integer, Integer> pair2 : solutions) {
                    System.out.println("Pair1" + pair1 + " - Pair2" + pair2);
                }
            }
        }
    }


    /**
     * Two sorted integer arrays, of the same size, each with all distinct elements.
     * <p>
     * time: O(n)
     * space: O(1)
     */
    public int findTheNumberOfElementsInCommon(int[] array1, int[] array2) {
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                i++;
            } else if (array1[i] > array2[j]) {
                j++;
            } else {
                count++;
                i++;
                j++;
            }
        }
        return count;
    }

    /**
     * Implementation on a singly linked list is a real challenge!
     * <p>
     * time: O(n)
     * space: O(n)
     */
    public int[] rearrangeArrayWithRunnerTechnique(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("Array's length must be an even number.");
        }

        if (array.length == 0) {
            return array;
        }

        // move pointers
        int slowPointer = 0;
        int fastPointer = 1;
        while (fastPointer != array.length - 1) {
            slowPointer++;
            fastPointer += 2;
        }
        slowPointer++;
        fastPointer = 0;

        // start weaving
        int[] arrangedArray = new int[array.length];
        int i = 0;
        while (i < arrangedArray.length) {
            arrangedArray[i] = array[fastPointer];
            i++;
            fastPointer++;
            arrangedArray[i] = array[slowPointer];
            i++;
            slowPointer++;
        }
        return arrangedArray;
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public LinkedList<Integer> rearrangeLinkedListWithRunnerTechnique(LinkedList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("List should not be null.");
        }
        if (list.size() % 2 != 0) {
            throw new IllegalArgumentException("List's size must be an even number.");
        }

        if (list.isEmpty()) {
            return list;
        }

        // move pointers
        Node<Integer> fastPointer = list.getHead().getNext();
        Node<Integer> slowPointer = list.getHead();
        while (fastPointer.getNext() != null) {
            fastPointer = fastPointer.getNext().getNext();
            slowPointer = slowPointer.getNext();
        }
        // do not touch slow pointer here since we have only singly linked list lacking previous()
        fastPointer = list.getHead();

        // start weaving
        while (fastPointer.getNext().getNext() != null) {
            Node<Integer> temporary = slowPointer.getNext();
            slowPointer.setNext(slowPointer.getNext().getNext());
            temporary.setNext(fastPointer.getNext());
            fastPointer.setNext(temporary);

            // do not touch the slow pointer again as it will indirectly be moved forward by each weaving
            fastPointer = fastPointer.getNext().getNext();
        }
        return list;
    }

    /**
     * Generates list of primes until a number
     * <p>
     * time: O(n*log(logn))
     * space: O(n)
     */
    public boolean[] sieveOfEratosthenes(int n) {
        // assume everything is prime at first
        boolean[] primeFlags = new boolean[n + 1];
        for (int i = 2; i < primeFlags.length; i++) { // start filling from 2!
            primeFlags[i] = true;
        }

        int currentPrime = 2;
        while (currentPrime * currentPrime <= n) { // mark non primes until the square root
            markNonPrimes(primeFlags, currentPrime);
            currentPrime = getNextPrime(primeFlags, currentPrime);
        }

        return primeFlags;
    }

    // n*n, n*(n+1), n*(n+2) - mark all multiples starting from the first prime which is 2
    private void markNonPrimes(boolean[] primeFlags, int currentPrime) {
        for (int i = currentPrime * currentPrime; i < primeFlags.length; i += currentPrime) {
            primeFlags[i] = false;
        }
    }

    private int getNextPrime(boolean[] primeFlags, int currentPrime) {
        int nextPrime = currentPrime + 1;
        while (nextPrime < primeFlags.length && !primeFlags[nextPrime]) {
            nextPrime++;
        }
        return nextPrime;
    }
}
