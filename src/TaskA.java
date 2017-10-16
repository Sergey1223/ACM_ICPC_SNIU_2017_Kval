import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TaskA {

    public static void main(String[] args) throws IOException {
        byte[][] array = createArray(new BufferedReader(new FileReader("input.txt")));
        byte count = getCount(array[0]);
        byte start = (byte) (findStart(array[0], (byte) 1) +  1);
        merge(start, (byte) (start - 1), (byte) 0, (byte) (count - 1), array);
        System.out.println(asString(array));
    }

    private static byte[][] createArray(BufferedReader bufferedReader) throws IOException {
        byte count = Byte.parseByte(bufferedReader.readLine());
        byte[][] array = new byte[2][];
        array[0] = new byte[count];
        array[1] = new byte[count];
        String[] temp;
        for (byte i = 0; i < count; i++) {
            temp = bufferedReader.readLine().split(" ");
            array[0][i] = Byte.parseByte(temp[0]);
            array[1][i] = Byte.parseByte(temp[1]);
        }
        return array;
    }
    
    private static byte getCount(byte[] array){
        byte counter = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 0) counter++;
        }
        return counter;
    }

    private static void merge(byte listHead, byte startPosition, byte counter, byte count, byte[][] array){
        if(counter != count){
            byte listEnd = findEnd(array[1], startPosition);
            byte listStart = findStart(array[0], listHead);
            array[1][listEnd - 1] = listStart;
            array[0][listStart - 1] = listEnd;
            merge(listHead, listEnd, ++counter, count, array);
        }
        else return;
    }

    private static byte findEnd(byte[] array, byte position){
        if(array[position - 1] == 0){
            return position;
        }
        else {
            return findEnd(array, array[position - 1]);
        }
    }

    private static byte findStart(byte[] array, byte position){
        for (int i = position - 1; i < array.length; i++) {
            if(array[i] == 0) return (byte) (i + 1);
        }
        return -1;
    }

    private static String asString(byte[][] array){
        StringBuilder stringBuilder = new StringBuilder(array[0].length + "\n");
        for (int i = 0; i < array[0].length; i++) {
            stringBuilder.append(array[0][i] + " " + array[1][i] + "\n");
        }
        return stringBuilder.toString();
    }
}
