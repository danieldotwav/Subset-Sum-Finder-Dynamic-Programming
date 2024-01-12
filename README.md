## Introduction

This Java project includes the implementation of the `howSum` function. The function aims to find any combination of elements within an array that sum up to a specified target. It's designed to explore different algorithmic approaches to solve this common problem in computer science, specifically in the context of dynamic programming.

## Algorithms

### **1. Simple Recursive Approach**

#### Logic

- This approach uses simple recursion to find a combination of numbers that sum up to the target. It checks each element and recursively tries to find a sum combination for the remaining amount.

#### Complexity Analysis

- **Time Complexity:** For each call to howSum, it iterates over all elements in the numbers array and makes a recursive call for each element. If n is the length of the numbers array and m is the targetSum, in the worst case, the function will explore all combinations of elements from the numbers array. This results in a time complexity of O(n^m), as each element could be used up to m/n times (in the worst case) and there are n choices at each step.

- **Space Complexity:** The space complexity is primarily determined by the depth of the recursion stack. In the worst case, the recursion can go as deep as m (the target sum), with each level adding a frame to the stack. Therefore, the space complexity is O(m).

### **2. Recursive Approach with Memoization**

#### Logic

- This enhanced version uses a HashMap for memoization, reducing the number of calculations by storing previously computed results.

#### Complexity Analysis

- **Time Complexity:** The improvedHowSum function still explores each element in the numbers array, but it saves the result of each unique target sum it calculates. With memoization, each target sum from m down to 0 is solved only once, and the results for these subproblems are stored. The time complexity is thus reduced to O(n*m), where n is the length of the numbers array and m is the targetSum.

- **Space Complexity:** The space complexity is influenced by two factors: the depth of the recursion stack and the size of the memoization hashmap. The recursion depth remains at O(m), as in the simple recursive approach. The memoization hashmap will store at most m key-value pairs (since the target sum can range from m to 0)

### Code Snippet

    ```java
    static Integer[] improvedHowSum(int targetSum, Integer[] numbers, HashMap<Integer, Integer[]> memo) {
       if (memo.containsKey(targetSum)) return memo.get(targetSum);
       if (targetSum == 0) return new Integer[0];
       if (targetSum < 0) return null;

       for (int element : numbers) {
           int updatedSum = targetSum - element;
           Integer[] result = improvedHowSum(updatedSum, numbers, memo);
           if (result != null) {
               result = Arrays.copyOf(result, result.length + 1);
               result[result.length - 1] = element;
               memo.put(targetSum, result);
               return result;
           }
       }
       memo.put(targetSum, null);
       return null;
   }
    ```