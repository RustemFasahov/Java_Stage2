public class Test {
    public static void main(String[] args) {

        final int size = 100000000;
        final int h = size/2;
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr,0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        new SecondThread(a2).start();

        for (int i = 0; i < a1.length; i++) {
            a1[i] = (float)(a1[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
        }

        System.arraycopy(a1,0, arr, 0, h);
        System.arraycopy(a2,0,arr, h, h);

        System.out.println("Время работы программы: " + (System.currentTimeMillis()-a)/1000 + " секунд(ы).");
    }
}
