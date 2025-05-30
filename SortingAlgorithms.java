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

    public static void main(String[] args) {
        int N = 100;
        int MAX_VALUE = 100;

        /*  // N[0] based
        int[] numbers = new int[N]; for (int i = 0; i < N; i++) { numbers[i] = (int) (Math.random() * 10000); System.out.print(numbers[i] + " "); } */

          // N[1] based
        int[] numbers = new int[N + 1]; for (int i = 1; i <= N; i++) { numbers[i] = (int) (Math.random() * MAX_VALUE); System.out.print(numbers[i] + " ");}

        // sortings
        selectionSort(numbers, N);

        // N[0] based
        // for (int i = 0; i < N; i++) { System.out.printf("%d ", numbers[i]); }

        // N[1] based
        System.out.println("\n\nSorted numbers:");
        for (int i = 1; i <= N; i++) { System.out.printf("%d ", numbers[i]); }

    }
}
