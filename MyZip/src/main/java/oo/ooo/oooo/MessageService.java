package oo.ooo.oooo;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MessageService {

    private final int headers = Long.BYTES + Integer.BYTES * 4;

    public List<byte[]> createPackages(byte[] data, int sizePack, long time) {
        List<byte[]> messages = new ArrayList<>();
        int lengthData = data.length;
        int lengthBody = sizePack - headers;
        int size = Double.valueOf(Math.floor(lengthData / lengthBody)).intValue();
        int ost = lengthData % lengthBody;
        for (int i = 0; i < size; i++) {
            byte[] message = new byte[lengthBody + headers];
            int count = 0;
            for (byte id : longToByte(time)) {
                message[count] = id;
                count++;
            }
            for (byte dataLength : intToByte(lengthData)) {
                message[count] = dataLength;
                count++;
            }
            for (byte length : intToByte(lengthBody)) {
                message[count] = length;
                count++;
            }
            for (byte nom : intToByte(i)) {
                message[count] = nom;
                count++;
            }
            for (byte nom : intToByte(ost == 0 ? size : size + 1)) {
                message[count] = nom;
                count++;
            }
            for (int j = 0; j < lengthBody; j++) {
                message[count] = data[i * lengthBody + j];
                count++;
            }
            messages.add(message);
        }
        if (ost != 0) {//объединить бы , где data идет массивом маленьких сообщений
            byte[] message = new byte[ost + headers];
            int count = 0;
            for (byte id : longToByte(time)) {
                message[count] = id;
                count++;
            }
            for (byte dataLength : intToByte(lengthData)) {
                message[count] = dataLength;
                count++;
            }
            for (byte length : intToByte(ost)) {
                message[count] = length;
                count++;
            }
            for (byte nom : intToByte(size)) {
                message[count] = nom;
                count++;
            }
            for (byte nom : intToByte(size + 1)) {
                message[count] = nom;
                count++;
            }
            for (int j = 0; j < ost; j++) {
                message[count] = data[size * lengthBody + j];
                count++;
            }
            messages.add(message);
        }
        return messages;

    }

    public void convert(List<MyMessage> messages, byte[] message) {//TODO сообщения могут поступать и одинаковые и еще кривые
        List<Byte> data = new ArrayList<>();
        for (byte b : message)
            data.add(b);
        MyMessage myMessage = new MyMessage();

        int i = Long.BYTES;
        myMessage.setId(byteToLong(listToArray(data.subList(0, i))));
        myMessage.setDataLength(byteToInt(listToArray(data.subList(i, i + Integer.BYTES))));
        i = i + Integer.BYTES;
        myMessage.setBodyLength(byteToInt(listToArray(data.subList(i, i + Integer.BYTES))));
        i = i + Integer.BYTES;
        myMessage.setNom(byteToInt(listToArray(data.subList(i, i + Integer.BYTES))));
        i = i + Integer.BYTES;
        myMessage.setSize(byteToInt(listToArray(data.subList(i, i + Integer.BYTES))));
        i = i + Integer.BYTES;
        myMessage.setBody(listToArray(data.subList(i, data.size())));
        messages.add(myMessage);
/*        messages.add(new MyMessage(
                byteToLong(listToArray(data.subList(0, Long.BYTES))),
                byteToInt(listToArray(data.subList(Long.BYTES, Integer.BYTES))),
                byteToInt(listToArray(data.subList(Long.BYTES + Integer.BYTES, Integer.BYTES))),
                byteToInt(listToArray(data.subList(Long.BYTES + 2 * Integer.BYTES, Integer.BYTES))),
                byteToInt(listToArray(data.subList(Long.BYTES + 3 * Integer.BYTES, Integer.BYTES))),
                listToArray(data.subList(Long.BYTES + 3 * Integer.BYTES, data.size()))
        ));*/

    }

    public byte[] getMessage(List<MyMessage> messages) {
        byte[] body = new byte[messages.get(0).getDataLength()];
        int i = 0;
        messages.sort(new Sort());
        for (MyMessage message : messages) {
            for (byte b : message.getBody()) {
                body[i] = b;
                i++;
            }
        }
        return body;
    }

    private byte[] longToByte(long data) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.putLong(data);
        return byteBuffer.array();
    }

    private byte[] listToArray(List<Byte> data) {
        byte[] array = new byte[data.size()];
        for (int i = 0; i < data.size(); i++)
            array[i] = data.get(i);
        return array;
    }

    private long byteToLong(byte[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.put(data);
        byteBuffer.flip();
        return byteBuffer.getLong();
    }

    private byte[] intToByte(int data) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
        byteBuffer.putInt(data);
        return byteBuffer.array();
    }

    private int byteToInt(byte[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
        byteBuffer.put(data);
        byteBuffer.flip();
        return byteBuffer.getInt();
    }

    private class Sort implements Comparator<MyMessage> {

        @Override
        public int compare(MyMessage o1, MyMessage o2) {
            return o1.getNom() - o2.getNom();
        }
    }
}
