import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SortingAlgorithms {
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

    public static int countInversions(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }


    // sorting

    public static void bubbleSort(int numbers[], PrintWriter writer, int N) {
        int comparisons = 0;
        ArrayList<Integer> xInversions = new ArrayList<>();
        ArrayList<Integer> yComparisons = new ArrayList<>();

        for (int i = N - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                comparisons++;
                if (numbers[j] > numbers[j + 1]) {
                swap(numbers, j, j + 1);
                }
            }
            xInversions.add(countInversions(numbers));
            yComparisons.add(comparisons);
        }

        System.out.println("\nInversions vs Comparisons:");
        for (int k = 0; k < xInversions.size(); k++) {
            System.out.printf("%d, %d\n", xInversions.get(k), yComparisons.get(k));
        }
    }

    public static void selectionSort(int numbers[], PrintWriter writer, int N) {
        for (int i = 0; i <= N-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            swap(numbers, i, minIndex);
        }
    }

    public static void insertionSort(int numbers[], PrintWriter writer, int N) {
        int i;
        for (int j = 1; j < N; j++) {
            int key = numbers[j];
            for (i = j-1; i >= 0 && numbers[i] > key; i--) {
                numbers[i + 1] = numbers[i];
            }
            numbers[i+1] = key;
        }
    }

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

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        int[] L = new int[n1]; int[] R = new int[n2];

        for (int i = 0; i < n1; i++) 
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) 
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else 
                arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }


    public static void main(String[] args) {
        int N = 0;
        String filename = "descending.csv";
        try {
            PrintWriter writer = new PrintWriter("output.txt");

            ArrayList<Integer> tempList = populateArrayFromFile(filename);
            N = tempList.size();

            // create an array from the ArrayList, N[0] based
            int[] numbers = new int[N]; 
            numbers = tempList.stream().mapToInt(i->i).toArray();
            for (int i = 0; i < N; i++) {
                System.out.print(numbers[i] + " ");
                writer.print(numbers[i] + " ");
            }

            // sortings
            //selectionSort(numbers, writer, N);  
             bubbleSort(numbers, writer, N); 
            // insertionSort(numbers, writer, N); 
            // quickSort(numbers, writer, 0, N - 1); 
            // mergeSort(numbers, writer, 0, N - 1); 

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
