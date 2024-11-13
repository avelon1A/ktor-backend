package service

import data.Category
import model.data.Example
import model.data.Problem
import model.data.ProblemDetails

class MainService {
    fun getCategories(): List<Category> {
        return listOf(
            Category("Arrays", 4, 26, "22 Remaining"),
            Category("Strings", 0, 22, "Not Started Yet"),
            Category("2D Arrays", 0, 10, "Not Started Yet"),
            Category("Searching & Sorting", 0, 23, "Not Started Yet"),
            Category("Binary Trees", 0, 38, "Not Started Yet"),
            Category("Backtracking", 0, 21, "Not Started Yet"),
            Category("Linked List", 0, 26, "Not Started Yet"),
            Category("Stacks & Queues", 0, 27, "Not Started Yet"),
            Category("Greedy", 0, 22, "Not Started Yet"),
            Category("Binary Search Trees", 0, 21, "Not Started Yet"),
            Category("Heaps & Hashing", 0, 0, "Not Started Yet"),
            Category("Graphs", 0, 40, "Not Started Yet"),
            Category("Tries", 0, 6, "Not Started Yet"),
            Category("Dynamic Programming", 0, 54, "Not Started Yet"),
            Category("Bit Manipulation", 0, 10, "Not Started Yet"),
            Category("Segment Trees", 0, 6, "Not Started Yet")
        )
    }

    fun getArrayProblems(): List<Problem> {
        return listOf(
            Problem(1, "Maximum And Minimum Element In An Array", true, false, false, true),
            Problem(2, "Reverse The Array", true, false, false, true),
            Problem(3, "Maximum-Subarray", true, false, false, true),
            Problem(4, "Contains Duplicate", true, false, false, false),
            Problem(5, "Chocolate Distribution Problem", true, false, false, false),
            Problem(6, "Best Time To Buy And Sell Stock", true, false, false, false),
            Problem(7, "Repeat And Missing Number Array", true, false, false, false),
            Problem(8, "Kth-Largest Element In An Array", true, true, false, false),
            Problem(9, "Trapping Rain Water", true, true, false, false),
//            Problem("Product Of Array Except Self", true, true, false, false),
//            Problem("Search In Rotated Sorted Array", true, true, false, false),
//            Problem("3 Sum", true, true, false, false),
//            Problem("Container With Most Water", true, true, false, false),
//            Problem("Given Sum Pair", true, true, false, false),
//            Problem("Kth - Smallest Element", true, true, false, false),
//            Problem("Merge Overlapping Intervals", true, true, false, false),
//            Problem("Find Minimum In Rotated Sorted Array", true, true, false, false),
//            Problem("Find Minimum Number Of Merge Operations To Make An Array Palindrome", true, true, false, false),
//            Problem("Space Optimization Using Bit Manipulations", false, false, false, true),
//            Problem("Subarray Sum Divisible K", true, true, false, true),
//            Problem("Print All Possible Combinations Of R Elements In A Given Array Of Size N", false, false, false, false),
//            Problem("Mo's Algorithm", false, false, false, true)
        )
    }

    fun getProblem(id: Int): ProblemDetails {
        return ProblemDetails(
            id = id,
            description = "Given an unsorted array `arr` containing only non-negative integers, your task is to find a continuous subarray (a contiguous sequence of elements) whose sum equals a specified value `target`. You need to return the 1-based indices of the leftmost and rightmost elements of this subarray.",
            examples = listOf(
                Example(
                    input = "arr[] = [1, 2, 3, 7, 5], target = 12",
                    output = "[2, 4]",
                    explanation = "The sum of elements from 2nd to 4th position is 12."
                ),
                Example(
                    input = "arr[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], target = 15",
                    output = "[1, 5]",
                    explanation = "The sum of elements from 1st to 5th position is 15."
                ),
                Example(
                    input = "arr[] = [7, 2, 1], target = 2",
                    output = "[2, 2]",
                    explanation = "The sum of elements from 2nd to 2nd position is 2."
                ),
                Example(
                    input = "arr[] = [5, 3, 4], target = 2",
                    output = "[-1]",
                    explanation = "There is no subarray with sum 2."
                )
            )
        )
    }
}
