public class q1{
    public static void main(String[] args) {
//arithmetic
        try {
            int a= 10, b=0;
            int result = a/b;
        }
        catch (ArithmeticException e){
            System.out.println("ArithmeticException: " + e.getMessage());
        }

   //null pointer
        try {
            String str= null;
            System.out.println(str.length());
        }
        catch (NullPointerException e){
            System.out.println("NullPointerException: " + e.getMessage());
        }
// array index
        try {
            int arr[] = {1,2,3};
            System.out.println(arr[5]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsExceptio: " + e.getMessage());
        }
        //number format exception
       try {
         String num = "abc";
         int value = Integer.parseInt(num);
         }
         catch (NumberFormatException e) {
        System.out.println("NumberFormatException: " + e.getMessage()); 
    }
// 5. Catching ALL of the above through the common parent class
try { int[] arr = {1, 2};
 System.out.println(arr[10]);
 }
  catch (RuntimeException e) { // Proves ArrayIndexOutOfBoundsException IS-A RuntimeException
 System.out.println("Caught via parent RuntimeException: " + e); }

    }

}