public class q2 {

    public static void main(String[] args) {

        try {
            int[] arr = {10, 20, 30, 40, 50};

            int index = 5;

            System.out.println("Element = " + arr[index]);
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index is out of bounds.");
        }

        finally {
            System.out.println("Finally block executed.");
        }
    }
}
