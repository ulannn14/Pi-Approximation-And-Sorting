import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SortingAlgorithms2 {
    static long recurComparisons = 0; // for recursive sorts

    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static ArrayList<Integer> populateArrayFromFile(String filename) {
        ArrayList<Integer> tempList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempList.add(Integer.parseInt(line.trim())); // Just parse the single number per line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempList; 
    }

    public static void printArray(int[] numbers, PrintWriter writer) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
            writer.print(numbers[i] + " ");
        }
        System.out.println();
    }

    public static int getDistanceFromSorted(int[] numbers, int sorted[]) {
        int mismatchCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != sorted[i]) {
                mismatchCount++;
            }
        }
        return mismatchCount;
    }


    public static void bubbleSortDistanceFromSorted(int numbers[], int sorted[], PrintWriter writer, int N) {
        long comparisons = 0;
        int totalComparisons = N * (N - 1) / 2;
        
        for (int i = N - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                comparisons++; // will compare in below code
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                }
            }
            // for every nth number, ilan na yung comparison and then number of mismatches
            if (i % 10000 == 0) {
                int getDistanceFromSorted = getDistanceFromSorted(numbers, sorted);
                System.out.println("Index " + i + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
                writer.println("Index " + i + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
            }
        }
        int getDistanceFromSorted = getDistanceFromSorted(numbers, sorted);
        System.out.println("Index " + 0 + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
        writer.println("Index " + 0 + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
    }

    public static void selectionSortDistanceFromSorted(int numbers[], int sorted[], PrintWriter writer, int N) {
        long comparisons = 0; // <-- use long instead of int
        for (int i = 0; i <= N-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                comparisons++;
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            swap(numbers, i, minIndex);
            if (i % 10000 == 0) {
                int getDistanceFromSorted = getDistanceFromSorted(numbers, sorted);
                System.out.println("Index " + i + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
                writer.println("Index " + i + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
            }
        }
        int getDistanceFromSorted = getDistanceFromSorted(numbers, sorted);
        System.out.println("Index " + (N-1) + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
        writer.println("Index " + (N-1) + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
    }

    public static void insertionSortDistanceFromSorted(int numbers[], int sorted[], PrintWriter writer, int N) {
        int i;
        long comparisons = 0; // <-- use long instead of int
        for (int j = 1; j < N; j++) {
            int key = numbers[j];
            comparisons++;
            for (i = j-1; i >= 0 && numbers[i] > key; i--)
                numbers[i + 1] = numbers[i];
        
            numbers[i+1] = key;
            if (j % 10000 == 0) {
                int getDistanceFromSorted = getDistanceFromSorted(numbers, sorted);
                System.out.println("Index " + j + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
                writer.println("Index " + j + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
            }
        }
        int getDistanceFromSorted = getDistanceFromSorted(numbers, sorted);
        System.out.println("Index " + (N-1) + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
        writer.println("Index " + (N-1) + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
    }

    public static void shellSortDistanceFromSorted(int numbers[], int sorted[], PrintWriter writer, int N) {
        long comparisons = 0; // Track the number of key comparisons

        for (int gap = N / 2; gap > 0; gap /= 2) { // Start with a big gap, then reduce the gap
            for (int i = gap; i < N; i++) { // Do a gapped insertion sort for this gap size
                int temp = numbers[i];
                int j = i;

                while (j >= gap) { // Perform the gap-based insertion sort
                    comparisons++; 
                    if (numbers[j - gap] > temp) {
                        numbers[j] = numbers[j - gap];
                        j -= gap;
                    } else {
                        break;
                    }
                }
                numbers[j] = temp;
                if (i % 10000 == 0) { // Optional progress print (similar to your insertion sort)
                    int distance = getDistanceFromSorted(numbers, sorted);
                    System.out.println("Index  " + i + " Comparisons: " + comparisons + " Distance from sorted: " + distance);
                    writer.println("Index  " + i + " Comparisons: " + comparisons + " Distance from sorted: " + distance);
                }
            }
        }
        int getDistanceFromSorted = getDistanceFromSorted(numbers, sorted);
        System.out.println("Index " + (N-1) + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
        writer.println("Index " + (N-1) + " Comparisons: " + comparisons + " Distance from sorted: " + getDistanceFromSorted);
    }





    //=======================
    public static void quickSort(int[] arr, PrintWriter writer, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, writer, low, pi - 1);
            quickSort(arr, writer, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
            
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void mergeSortDistanceFromSorted(int[] arr, int left, int right, int sorted[], PrintWriter writer) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortDistanceFromSorted(arr, left, mid, sorted, writer);
            mergeSortDistanceFromSorted(arr, mid + 1, right, sorted, writer);

            merge(arr, left, mid, right, sorted, writer);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int sorted[], PrintWriter writer) {
     /*   int i = left;
        int j = mid + 1;
        int k = 0;
        int[] U = new int[right - left + 1];

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j])
                U[k++] = arr[i++];
            else
                U[k++] = arr[j++];
        }

        if (i > mid) {
            for (int kk = j; kk <= right; kk++)
                U[k++] = arr[kk];
        } else {
            for (int kk = i; kk <= mid; kk++)
                U[k++] = arr[kk];
        }

        for (int kk = 0; kk < U.length; kk++)
            arr[left + kk] = U[kk];
            */

        int i = left;
        int j = mid + 1;
        int k = 0;
        int[] U = new int[right - left + 1];

        while (i <= mid && j <= right) {
            recurComparisons++;
            if (arr[i] <= arr[j])
                U[k++] = arr[i++];
            else
                U[k++] = arr[j++];

            if (recurComparisons % 10000 == 0) {
                int dist = getDistanceFromSorted(arr, sorted);
                System.out.println("Comparisons: " + recurComparisons + " Distance from sorted: " + dist);
                writer.println("Comparisons: " + recurComparisons + " Distance from sorted: " + dist);
            }
        }

        while (i <= mid) U[k++] = arr[i++];
        while (j <= right) U[k++] = arr[j++];

        for (int kk = 0; kk < U.length; kk++)
            arr[left + kk] = U[kk];
}

public static void main(String[] args) throws IOException {
    
    try {
        System.out.println("Starting the sort. \n\n");
        
        //String[] inputFiles = {"evenDescOddAsc.csv", "mostly_sorted_numbers.csv", "shuffled_numbers.csv", "Descending.csv"};

        String[] inputFiles = {"Descending.csv"};
        
        for (String inputFile : inputFiles) {
            ArrayList<Integer> tempList = populateArrayFromFile(inputFile);
            int[] numbers = tempList.stream().mapToInt(i->i).toArray();
            int N = numbers.length;
            int[] originalNumbers = numbers.clone(); // Keep original for sorting
            int[] sorted = numbers.clone();
            Arrays.sort(sorted);

            String outputFile = inputFile.replace(".csv", "_result.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
            
            System.out.print("\nBubble Sort Distance from Sorted" + "\n");
            writer.print("\nBubble Sort Distance from Sorted" + "\n");
            bubbleSortDistanceFromSorted(numbers, sorted, writer, N); 

            numbers = originalNumbers.clone(); // Reset to original for next sort
            System.out.print("\nSelection Sort Distance from Sorted" + "\n");
            writer.print("\nSelection Sort Distance from Sorted" + "\n");
            selectionSortDistanceFromSorted(numbers, sorted, writer, N);

            numbers = originalNumbers.clone(); // Reset to original for next sort
            System.out.print("\nInsertion Sort Distance from Sorted" + "\n");
            writer.print("\nInsertion Sort Distance from Sorted" + "\n");
            insertionSortDistanceFromSorted(numbers, sorted, writer, N);

            /* 
            numbers = originalNumbers.clone(); // Reset to original for next sort
            System.out.print("\nShell Sort Distance from Sorted" + "\n");
            writer.print("\nShell Sort Distance from Sorted" + "\n");
            shellSortDistanceFromSorted(numbers, sorted, writer, N);    
            */

            numbers = originalNumbers.clone(); // Reset to original for next sort
            System.out.print("\nMerge Sort Distance from Sorted" + "\n");
            writer.print("\nMerge Sort Distance from Sorted" + "\n");
            mergeSortDistanceFromSorted(numbers, 0, N - 1, sorted, writer);

            System.out.print("\nDone sorting");
            System.out.print("\nDone sorting");
            writer.flush();
            writer.close(); 
        }

    } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
    }


    }
}
