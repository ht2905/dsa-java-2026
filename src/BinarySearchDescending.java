public class BinarySearchDescending {
    public static int binarySearchDescending(int[] array, int target) {
    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
        int middle = (low + high) / 2;

        if (array[middle] == target) {
            return middle;
        }

        if (array[middle] > target) {
            // Target is to the right
            low = middle + 1;
        } else {
            // Target is to the left
            high = middle - 1;
        }
    }

    return -1;
    }
}