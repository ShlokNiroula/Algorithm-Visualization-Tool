/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Asus
 */
public class QuickSort {
    public List<int[]> sortWithSteps(int[] array) {
        List<int[]> steps = new ArrayList<>();
        int[] arr = array.clone();
        steps.add(arr.clone());
        
        quickSort(arr, 0, arr.length - 1, steps);
        steps.add(arr.clone()); // Final sorted
        
        return steps;
    }
    
    private void quickSort(int[] arr, int low, int high, List<int[]> steps) {
        if (low < high) {
            int pi = partition(arr, low, high, steps);
            quickSort(arr, low, pi - 1, steps);
            quickSort(arr, pi + 1, high, steps);
        }
    }
    
    private int partition(int[] arr, int low, int high, List<int[]> steps) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
                steps.add(arr.clone());
            }
        }
        
        swap(arr, i + 1, high);
        steps.add(arr.clone());
        return i + 1;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
