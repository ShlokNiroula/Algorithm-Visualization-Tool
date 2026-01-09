package Model;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    
    public List<int[]> sortWithSteps(int[] array) {
        List<int[]> steps = new ArrayList<>();
        int[] arr = array.clone();
        
        steps.add(arr.clone()); // Step 0
        
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    
                    // Save step
                    steps.add(arr.clone());
                }
            }
        }
        
        steps.add(arr.clone()); // Final sorted
        return steps;
    }
}