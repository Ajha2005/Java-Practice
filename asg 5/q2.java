public class q2
{    public static void main(String[] args) {
        try {
            System.out.println("Outer try starts");

            try {
                System.out.println("Inner try starts");
                int[] arr = {1, 2, 3};
                System.out.println(arr[10]);    // throws ArrayIndexOutOfBoundsException
            } catch (NullPointerException e) {
                // Wrong type on purpose: this will NOT catch the array exception
                System.out.println("Inner catch (NPE): " + e);
            } finally {
                System.out.println("Inner finally runs");
            }

            System.out.println("This line is skipped (exception already propagating)");

        } catch (ArrayIndexOutOfBoundsException e) {
            // The outer catch picks up what the inner block could not handle
            System.out.println("Outer catch handled: " + e.getMessage());
        } finally {
            System.out.println("Outer finally runs");
        }

        System.out.println("Program continues normally after full handling");
    }
}
