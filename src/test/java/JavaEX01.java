//<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

import org.testng.annotations.Test;

import java.util.Arrays;

public class JavaEX01 {

    @Test
    public void test00001() {
        System.out.println("test00001 started. \n ~AXVLEDA~");
    }

    @Test
    public void test00002() {
        String message01 = "test00002 started. \n ~AXVLEDA~";
        System.out.println(message01);
    }

    @Test
    public void test00003() {
        int myNumber;
        myNumber = 7;
        System.out.println(myNumber);
    }

    @Test
    public void test00004() {
        int[] Array = new int[100];
        for (int i = 0; i <= Array.length; i++){
            try {
                Array[i] = Array[i - 1] *2;
                System.out.println(Array[i]);

            } catch (Exception e) {
                Array[i] = 1;
            }
        }

    }

    @Test
    public void test00005() {
        boolean b = false;
        if (b == false) {
            System.out.println("b is false.");
        }
    }

    @Test
    public void test00006() {
        boolean b = true;

        // boolean isBTrue = b == true;

        if (b) {
            System.out.println("b is true.");
        }
    }

    @Test
    public void test00007() {
        int value1 = 1;
        int value2 = 1;

        if ((value1 == 1) && (value2 == 0)) {
            System.out.println("Value1 and Value2 are 1");
        }

    }

//    @Test
//    public void test00008() {
//        boolean b;
//
//        boolean toBe = false;
//        toBe = b ==
//
//        if ((value1 == 1) || (value2 == 1)) {
//            System.out.println("Value1 or Value2 are 1");
//        }
//    }

    @Test
    public void test00009() {
        boolean elementIsVisible = true;

        if (elementIsVisible){
                System.out.println("blaa");

        }
        else {
            System.out.println("!blaa");
        }


    }

    @Test
    public void test00010() {
        int[] array = new int[10];

        array[0] = 17;
        array[3] = 13;
        System.out.println(array[0]);

        int[] array2 = new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(array2[4]);

        int[] arr3 = {1,345,67,345,88,976,97689,46,345345345};
        System.out.println(Arrays.toString(arr3));

    }

    @Test
    public void test00011() {
        for (int i = 0; i < 4; i++){
            System.out.println("HELLO");
        }
    }

    @Test
    public void test00012() {
        int[]array = {1,345,67,345,88,976,97689,46,345345345};

        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }

    @Test
    public void test00013() {
        int[]array = {1,345,67,345,88,976,97689,46,345345345};
        
        int valueToSearch = 976;
        

        for (int i = 0; i < array.length; i++){
            if (array[i] == valueToSearch){

                System.out.println("found Value = " +array[i] + " at index " + i);
//                System.out.println("%i is found in the array at index %i",(valueToSearch; i));
            }
        }
    }


    @Test
    public void test00014() {
        int[] longArray = new int[100];
        System.out.format("Test 00014 \nArray length: %d.\n", longArray.length);
        for (int i = 0; i < longArray.length; i++){
            Integer multipleOfI = i * i;
            longArray[i] = multipleOfI;
            System.out.format("At index of %d - %4d was added %n", i, multipleOfI);

        }
        System.out.println(Arrays.toString(longArray));
    }

    /////////////////////////////////////////////



        @Test
        public void test00015() {
            int[] array = new int[10];

            array[0] = 1;
            array[1] = 2;
            array[3] = 4;

            int[] array2 = new int[]{17, 2, 53, 4, 5, 6, 47, 8, 39};

            System.out.println(array2[0]);

            System.out.println(array2[2]);

            int[] arr3 = {1, 2, 3, 4, 5};


        }

        @Test
        public void test00016() {

            for (int i = 2; i < 4; i++) {
                System.out.println("HELLO");
            }
        }

        @Test
        public void test00017() {
            int[] array = {1, 34, 5, 653, 737, 357357, 54};

            for (int i = 0; i < array.length - 2; i++) {
                System.out.println(array[i]);
            }
        }

        //TODO: print out message (ONLY) if no elements found
        @Test
        public void test00018() {
            int[] array = {1, 34, 5, 653, 737, 357357, 54};
            int valueToSearch = 2;

            boolean isElementFound = false;

            for (int i = 0; i < array.length; i++) {
                if (array[i] == valueToSearch) {
                    System.out.println("element value found:" + array[i]);
                    System.out.println("index of the element is:" + i);
                    isElementFound = true;
                }
            }

            if(!isElementFound){
                System.out.println("element is not in the array");
            }
        }

        @Test
        public void test00019() {
            int[] array = {1, 34, 5, 653, 737, 357357, 54};
            searchForElement(array, 737);
        }

        @Test
        public void test00020() {
            int[] array = {1, 34, 5, 653, 737, 357357, 54};
            searchForElement(array, 33434);
        }


        public void searchForElement(int[] inputArray, int valueToSearch) {
            for (int i = 0; i < inputArray.length; i++) {
                if (inputArray[i] == valueToSearch) {
                    System.out.println("element value found:" + inputArray[i]);
                    System.out.println("index of the element is:" + i);
                }
            }
        }


        //1. Loop
        //2. Go though number from 1 to 10
        //3. Filter out only even numbers (???)
        //4. Print them
        @Test
        public void testPrintEvenNumbers() {
            for (int i = 1; i <= 10; i++) {
                if (i%2==0) System.out.println(i);
            }
        }

        //1. Loop
        //2. Go though number from 1 to 10
        //3. Filter out only even numbers (???)
        //4. Print them
        @Test
        public void testPrintEvenNumbers2() {
            int[] input = {1,2,3,4,5,6,7,8,9,10};
            solution(input);
        }

        @Test
        public void testPrintEvenNumbers3() {
            int[] input = {1,2,3,44,55,1000};
            solution(input);
        }

        public void solution(int[] input) {
            for (int i = 0; i < input.length; i++) {
                int currentElement = input[i];
                if(currentElement >= 0 && currentElement <= 10){
                    int remainder = currentElement % 2;
                    if (remainder == 0) {System.out.format("Element %4d is even! %n", currentElement);}
                    else {System.out.format("Element %4d is not even. %n", currentElement);}
                } else {
                    //TODO: decide what to do here
                    System.out.format("Element %4d is out of boundaries. \n" , currentElement);
                }
            }
        }

        @Test
        public void SwapNumbersInArray() throws Exception {
        int[] array = {1,2,3,4,5};
        swap(array,4,3);
        System.out.println(Arrays.toString(array));

        }

    public void swap(int[] array, int indexL, int indexR) throws Exception {
        if (indexL  < array.length &&  indexL > 0 && indexR  < array.length &&  indexR > 0){
            int temp = array[indexL];
            array[indexL] = array[indexR];
            array[indexR] = temp;
        }
        else {
            throw new Exception(("Cannot swap numbers. Index is greater than Array length"));
        }
    }

    public int SumAplusB(int firstValue, int secondValue){
        int result = 0;
        result = firstValue +secondValue;
        return result;
    }

    @Test
    public void testSumMethod() {
        int testValue1 = 207;
        int testValue2 = 213;
        int ResultofMethod = SumAplusB(testValue1, testValue2);
        System.out.println(ResultofMethod);
    }
}
