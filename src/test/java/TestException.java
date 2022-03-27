public class TestException {


    public static void test()  {
        int a = 1;
        int b = 1;

        int c = a / b;
        try {
            if (c == 1) {
                throw new Exception("1 value");
            }

        }
        catch (Exception e)
        {
            System.out.println("Error"+ e.getMessage());
        }
    }

    public static void main(String[] args) {
        test();
    }
}
