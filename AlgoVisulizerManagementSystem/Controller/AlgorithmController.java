package Controller;

import View.VisualizationPanel;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Date; // ADD THIS
import java.util.Queue; // ADD THIS
import java.util.LinkedList; // ADD THIS
import java.util.Stack; // ADD THIS
import java.util.PriorityQueue; // ADD THIS

public class AlgorithmController {
    private int[] array;
    private VisualizationPanel visualizationPanel;
    private Timer timer;
    private List<int[]> sortSteps;
    private List<int[]> comparisonIndices;
    private int currentStep = 0;
    private Map<String, SavedState> savedStates;
    private static final String SAVE_FILE = "saved_states.ser";
    private Queue<String> recentOperations; // For recently viewed
    private Stack<String> undoStack; // For undo feature
    private PriorityQueue<String> priorityAlgorithms; // For sorting by priority
    
    private static class SavedState implements Serializable {
        private String name;
        private int[] array;
        private int currentStep;
        private long timestamp;
        
        public SavedState(String name, int[] array, int currentStep) {
            this.name = name;
            this.array = array.clone();
            this.currentStep = currentStep;
            this.timestamp = System.currentTimeMillis();
        }
        
        public String getName() { return name; }
        public int[] getArray() { return array.clone(); }
        public int getCurrentStep() { return currentStep; }
        public long getTimestamp() { return timestamp; }
    }
    
    public AlgorithmController(VisualizationPanel panel) {
        this.savedStates = new HashMap<>();
        this.visualizationPanel = panel;
        
        // Initialize the new data structures
        this.recentOperations = new LinkedList<>();
        this.undoStack = new Stack<>();
        this.priorityAlgorithms = new PriorityQueue<>();
        
        // Start with VISIBLE values (not 0-9!)
        this.array = new int[]{70, 40, 90, 30, 80, 50, 60, 20, 100, 10};
        this.visualizationPanel.setArray(array);
        loadStatesFromFile(); // Load saved states
        
        // Generate initial sort steps
        generateSortSteps();
        
        // Initialize timer
        this.timer = new Timer(800, e -> {
            if (currentStep < sortSteps.size() - 1) {
                currentStep++;
                
                // Update array
                array = sortSteps.get(currentStep).clone();
                visualizationPanel.setArray(array);
                
                // Show what's being compared
                if (currentStep < comparisonIndices.size()) {
                    int[] indices = comparisonIndices.get(currentStep);
                    visualizationPanel.setComparingIndices(indices[0], indices[1]);
                }
                
                System.out.println("Step " + currentStep + ": " + java.util.Arrays.toString(array));
            } else {
                timer.stop();
                visualizationPanel.setComparingIndices(-1, -1);
                System.out.println("Sorting complete!");
            }
        });
        
        // Add some sample data to new data structures
        priorityAlgorithms.add("Bubble Sort");
        priorityAlgorithms.add("Quick Sort");
        priorityAlgorithms.add("Binary Search");
        
        // Track this creation as recent operation
        recentOperations.add("System initialized - " + new Date());
        if (recentOperations.size() > 5) recentOperations.poll(); // Keep only last 5
    }
    
    // ============ NEW METHODS FOR DATA STRUCTURES ============
    
    public void addRecentOperation(String operation) {
        recentOperations.add(operation + " - " + new Date());
        if (recentOperations.size() > 5) recentOperations.poll();
    }
    
    public void pushToUndo(String state) {
        undoStack.push(state);
    }
    
    public String popFromUndo() {
        return undoStack.isEmpty() ? null : undoStack.pop();
    }
    
    public Queue<String> getRecentOperations() {
        return new LinkedList<>(recentOperations); // Return copy
    }
    
    public Stack<String> getUndoStack() {
        return (Stack<String>) undoStack.clone(); // Return copy
    }
    
    public PriorityQueue<String> getPriorityAlgorithms() {
        return new PriorityQueue<>(priorityAlgorithms); // Return copy
    }
    
    public int getSavedStatesCount() {
        return savedStates.size();
    }
    
    // ============ EXISTING METHODS ============
    
    private void generateSortSteps() {
        sortSteps = new ArrayList<>();
        comparisonIndices = new ArrayList<>();
        
        int[] arr = array.clone();
        sortSteps.add(arr.clone()); // Initial state
        
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Record comparison
                comparisonIndices.add(new int[]{j, j + 1});
                
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                
                // Record state after this step
                sortSteps.add(arr.clone());
            }
        }
        
        System.out.println("Generated " + sortSteps.size() + " steps");
    }
    
    public void randomize() {
        Random rand = new Random();
        array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(80) + 20; // Values 20-100 (VISIBLE!)
        }
        visualizationPanel.setArray(array);
        currentStep = 0;
        generateSortSteps();
        System.out.println("Randomized: " + java.util.Arrays.toString(array));
    }
    
    public void reset() {
        array = new int[]{70, 40, 90, 30, 80, 50, 60, 20, 100, 10};
        visualizationPanel.setArray(array);
        visualizationPanel.setComparingIndices(-1, -1);
        currentStep = 0;
        generateSortSteps();
        if (timer != null) {
            timer.stop();
        }
        System.out.println("Reset to default array");
    }
    
    public void playBubbleSort() {
        if (timer != null) {
            if (!timer.isRunning()) {
                timer.start();
                System.out.println("Starting animation...");
            }
        }
    }
    
    public void pause() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
            System.out.println("Paused");
        }
    }
    
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
        reset();
    }
    
    // For debugging
    public int[] getArray() {
        return array.clone();
    }
    
    public boolean validateStateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
    
        name = name.trim();
    
        // Length validation
        if (name.length() < 3 || name.length() > 20) {
            return false;
        }
    
        // Character validation
        if (!name.matches("^[a-zA-Z0-9_\\-\\s]+$")) {
            return false;
        }
    
        return true;
    }

    public boolean stateExists(String name) {
        return savedStates.containsKey(name);
    }
    
    public String[] getSavedStateNames() {
        if (savedStates == null) {
            System.err.println("❌ savedStates map is null!");
            return new String[0];
        }
    
        String[] names = savedStates.keySet().toArray(new String[0]);
        System.out.println("getSavedStateNames() returning: " + Arrays.toString(names));
        return names;
    }
    
    public void loadSavedState(String name) {
        if (!savedStates.containsKey(name)) {
            System.err.println("State not found: " + name);
            return;
        }
        
        SavedState state = savedStates.get(name);
        this.array = state.getArray();
        this.currentStep = state.getCurrentStep();
        this.visualizationPanel.setArray(array);
        visualizationPanel.setComparingIndices(-1, -1);
        generateSortSteps();
        System.out.println("Loaded state: " + name);
    }
    
    public boolean deleteSavedState(String name) {
        if (!savedStates.containsKey(name)) {
            System.err.println("State not found for deletion: " + name);
            return false;
        }
        
        savedStates.remove(name);
        saveStatesToFile();
        System.out.println("Deleted state: " + name);
        return true;
    }
    
    public boolean updateState(String oldName, String newName) {
        if (!savedStates.containsKey(oldName)) {
            System.err.println("State not found for update: " + oldName);
            return false;
        }
        
        if (!validateStateName(newName)) {
            System.err.println("Invalid new state name: " + newName);
            return false;
        }
        
        if (savedStates.containsKey(newName) && !oldName.equals(newName)) {
            System.err.println("New state name already exists: " + newName);
            return false;
        }
        
        SavedState state = savedStates.remove(oldName);
        SavedState updatedState = new SavedState(newName, state.getArray(), state.getCurrentStep());
        savedStates.put(newName, updatedState);
        saveStatesToFile();
        System.out.println("Updated state: " + oldName + " -> " + newName);
        return true;
    }
    
    @SuppressWarnings("unchecked")
    private void loadStatesFromFile() {
        File file = new File(SAVE_FILE);
    
        System.out.println("\n=== LOADING STATES ===");
        System.out.println("File exists: " + file.exists());
        System.out.println("File path: " + file.getAbsolutePath());
        System.out.println("File size: " + (file.exists() ? file.length() + " bytes" : "N/A"));
    
        if (!file.exists()) {
            System.out.println("INFO: No saved states file found. Creating new map.");
            savedStates = new HashMap<>();
            return;
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        Object obj = ois.readObject();
        if (obj instanceof HashMap) {
            savedStates = (HashMap<String, SavedState>) obj;
            System.out.println("SUCCESS: Loaded " + savedStates.size() + " saved states.");
            System.out.println("State names: " + savedStates.keySet());
            } else {
                System.err.println("ERROR: File does not contain a HashMap");
                savedStates = new HashMap<>();
            }
        } catch (IOException e) {
            System.err.println("ERROR reading file: " + e.getMessage());
            savedStates = new HashMap<>();
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: Class not found - " + e.getMessage());
            savedStates = new HashMap<>();
        } catch (Exception e) {
            System.err.println("UNKNOWN ERROR: " + e.getMessage());
            savedStates = new HashMap<>();
        }
    }
    
    private void saveStatesToFile() {
        System.out.println("\n=== SAVING TO FILE ===");
        System.out.println("Number of states to save: " + savedStates.size());
    
        File file = new File(SAVE_FILE);
        System.out.println("Save location: " + file.getAbsolutePath());
    
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(savedStates);
            System.out.println("SUCCESS: Saved " + savedStates.size() + " states to file.");
            System.out.println("File size: " + file.length() + " bytes");
        } catch (IOException e) {
            System.err.println("ERROR saving file: " + e.getMessage());
            e.printStackTrace();
        }    
    }
    
    public String getStateInfo(String name) {
        if (!savedStates.containsKey(name)) {
            return "State not found: " + name;
        }
        
        SavedState state = savedStates.get(name);
        return String.format("Name: %s\nArray: %s\nStep: %d", 
            name, 
            Arrays.toString(state.getArray()), 
            state.getCurrentStep());
    }
    
    public boolean saveCurrentStateWithName(String name) {
        System.out.println("=== SAVING STATE: " + name + " ===");
    
        // 1. Basic validation
        if (name == null || name.trim().isEmpty()) {
            System.err.println("❌ Name is empty!");
            return false;
        }
    
        if (!validateStateName(name)) {
            System.err.println("❌ Invalid name format: " + name);
            return false;
        }
    
        // 2. Check if we have an array to save
        if (array == null || array.length == 0) {
            System.err.println("❌ No array data to save!");
            return false;
        }
    
        System.out.println("Array to save: " + Arrays.toString(array));
        System.out.println("Current step: " + currentStep);
    
        try {
            // 3. Create SavedState object
            SavedState state = new SavedState(name, array, currentStep);
        
            // 4. Save to HashMap
            savedStates.put(name, state);
        
            // 5. Save to file
            saveStatesToFile();
        
            // 6. Add to recent operations
            addRecentOperation("Saved state: " + name);
        
            // 7. Add to priority algorithms
            priorityAlgorithms.add(name);
        
            System.out.println("✅ Saved successfully!");
            System.out.println("Total saved states: " + savedStates.size());
        
            return true;
        
        } catch (Exception e) {
            System.err.println("❌ Save failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // ============ NEW VALIDATION METHODS ============
    
    public boolean validateArray(int[] array) {
        if (array == null) return false;
        if (array.length == 0) return false;
        if (array.length > 100) return false; // Reasonable limit
        
        for (int value : array) {
            if (value < 0 || value > 1000) return false; // Reasonable range
        }
        
        return true;
    }
    
    public boolean validateStep(int step) {
        if (step < 0) return false;
        if (step >= sortSteps.size()) return false;
        return true;
    }
}