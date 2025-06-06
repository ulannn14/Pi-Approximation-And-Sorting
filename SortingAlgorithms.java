public class SortingAlgorithms {
    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void bubbleSort(int numbers[], int N) {
        for (int i = N; i >= 1; i--) {
            for (int j = 2; j <= i; j++) {
                if (numbers[j - 1] > numbers[j]) {
                    swap(numbers, j-1, j);
                }
            }
        }
    }

    public static void selectionSort(int numbers[], int N) {
        for (int i = 1; i <= N; i++) {
            int minIndex = i;
            for (int j = i+1; j <= N; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            swap(numbers, i, minIndex);
        }
    }

    public static void insertionSort(int numbers[], int N) {
        int i;
        for (int j = 2; j <= N; j++) {
            int key = numbers[j];
            for (i = j-1; i >= 1 && numbers[i] > key; i--) {
                numbers[i + 1] = numbers[i];
            }
            numbers[i+1] = key;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
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
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 1; i <= n1; i++)
            L[i] = arr[left + i - 1];
        for (int j = 1; j <= n2; j++)
            R[j] = arr[mid + j];

        L[n1 + 1] = Integer.MAX_VALUE; // Sentinel
        R[n2 + 1] = Integer.MAX_VALUE; // Sentinel

        int i = 1, j = 1;

        for (int k = left; k <= right; k++) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            } else {
                arr[k] = R[j++];
            }
        }
    }
    
    public static void main(String[] args) {
        int N = 100;
        int MAX_VALUE = 100;

        /*  // N[0] based
        int[] numbers = new int[N]; for (int i = 0; i < N; i++) { numbers[i] = (int) (Math.random() * 10000); System.out.print(numbers[i] + " "); } */

          // N[1] based
        int[] numbers = new int[N + 1]; for (int i = 1; i <= N; i++) { numbers[i] = (int) (Math.random() * MAX_VALUE); System.out.print(numbers[i] + " ");}

        // sortings
        mergeSort(numbers, 1, N);

        // N[0] based
        // for (int i = 0; i < N; i++) { System.out.printf("%d ", numbers[i]); }

        // N[1] based
        System.out.println("\n\nSorted numbers:");
        for (int i = 1; i <= N; i++) { System.out.printf("%d ", numbers[i]); }

    }
}
