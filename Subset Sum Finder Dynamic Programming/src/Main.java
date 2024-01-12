// Prompt: Write a function 'howSum(targetSum, numbers)' that takes in a targetSum and an array of numbers as arguments.
//         The function should return an array containing any combination of elements that add up to exactly the targetSum. If there is no combination that adds up to the targetSum, then return null.
//         You may use an element of the array as many times as needed.
//         You may assume that all input numbers are nonnegative.

import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int targetSum;
        Integer[] result;

        // Test Case 1: Regular case
        targetSum = 7;
        result = howSum(targetSum, new Integer[] { 5, 3, 4, 7 });
        printResults(result, targetSum);

        // Test Case 2: Target sum cannot be reached
        targetSum = 7;
        result = howSum(targetSum, new Integer[] { 2, 4 });
        printResults(result, targetSum);

        // Test Case 3: Target sum is 0
        targetSum = 0;
        result = howSum(targetSum, new Integer[] { 1, 2, 3 });
        printResults(result, targetSum);

        // Test Case 4: Empty array of numbers
        targetSum = 7;
        result = howSum(targetSum, new Integer[] {});
        printResults(result, targetSum);

        // Test Case 5: Large numbers and target sum
        targetSum = 300;
        result = improvedHowSum(targetSum, new Integer[] { 7, 14 }, new HashMap<Integer, Integer[]>());
        printResults(result, targetSum);
    }

    // Simple recursive approach
    static Integer[] howSum(int targetSum, Integer[] numbers) {
        // Base cases
        // Return an empty array when the targetSum is 0
        if (targetSum == 0) {
            return new Integer[0];
        }

        // Return null when we reach a negative quantity
        if (targetSum < 0) {
            return null;
        }

        for (int element : numbers) {
            int updatedSum = targetSum - element;

            // Pass the updatedSum recursively and store its result
            Integer[] result = howSum(updatedSum, numbers);

            // Check if we've encountered a valid sum
            if (result != null) {
                // Resize existing array and include current element
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = element;
                return result;
            }
        }
        return null;
    }

    // Recursive method with memoization
    // HashMap <targetSum, the array containing all its addends>
    static Integer[] improvedHowSum(int targetSum, Integer[] numbers, HashMap<Integer, Integer[]> memo) {
        // Base cases
        // First check if targetsum is in the memo
        if (memo.containsKey(targetSum)) {
            return memo.get(targetSum);
        }

        // Return an empty array when the targetSum is 0
        if (targetSum == 0) {
            return new Integer[0];
        }

        // Return null when we reach a negative quantity
        if (targetSum < 0) {
            return null;
        }

        for (int element : numbers) {
            int updatedSum = targetSum - element;

            // Pass the updatedSum recursively and store its result
            Integer[] result = improvedHowSum(updatedSum, numbers, memo);

            // Check if we've encountered a valid sum
            if (result != null) {
                // Resize existing array and include current element
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = element;

                memo.put(targetSum, result);
                return result;
            }
        }
        memo.put(targetSum, null);
        return null;
    }


    static void printResults(Integer[] container, int targetSum) {
        String resultString = (container != null) 
        ? Arrays.stream(container).map(String::valueOf).collect(Collectors.joining(", ", "[ ", " ]")) 
        : "No Sum Found";

        System.out.println("\nTarget Sum: " + targetSum + "\nResult: " + resultString);
    }
}

