/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Arrays;
        
/**
 *
 * @author Asus
 */

public class BinarySearch {
    // Binary search implementation
    public int search(int[] array, int target) {
        Arrays.sort(array); // Binary search requires sorted array
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                return mid; // Found
            }
            
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Not found
    }
    
    // Method to demonstrate with steps (for visualization)
    public int[] searchWithSteps(int[] array, int target) {
        Arrays.sort(array);
        int[] steps = new int[array.length];
        Arrays.fill(steps, -1);
        
        int left = 0;
        int right = array.length - 1;
        int step = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            steps[step++] = mid; // Record which index we're checking
            
            if (array[mid] == target) {
                steps[step] = mid; // Found
                break;
            }
            
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return steps;
    }
}
