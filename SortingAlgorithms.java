public class SortingAlgorithms {
    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void bubbleSort(int numbers[], int N) {
        for (int i = N - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                swap(numbers, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int numbers[], int N) {
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

    public static void insertionSort(int numbers[], int N) {
        int i;
        for (int j = 1; j < N; j++) {
            int key = numbers[j];
            for (i = j-1; i >= 0 && numbers[i] > key; i--) {
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

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    
    public static void main(String[] args) {
        int N = 100;
        int MAX_VALUE = 100;

        // N[0] based
        int[] numbers = new int[N]; for (int i = 0; i < N; i++) { numbers[i] = (int) (Math.random() * 10000); System.out.print(numbers[i] + " "); }

        // N[1] based
        //int[] numbers = new int[N + 1]; for (int i = 1; i <= N; i++) { numbers[i] = (int) (Math.random() * MAX_VALUE); System.out.print(numbers[i] + " ");}

        // sortings
        // selectionSort(numbers, N);  
        // bubbleSort(numbers, N); 
        //insertionSort(numbers, N); 
        // quickSort(numbers, 0, N - 1); 
        // mergeSort(numbers, 0, N - 1); 

        // N[0] based
        System.out.println("\n\nSorted numbers:");
        for (int i = 0; i < N; i++) { System.out.printf("%d ", numbers[i]); System.out.println();}

        // N[1] based
        //System.out.println("\n\nSorted numbers:");
        //for (int i = 1; i <= N; i++) { System.out.printf("%d ", numbers[i]); }

    }
}
