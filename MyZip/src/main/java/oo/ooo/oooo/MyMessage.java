package oo.ooo.oooo;

public class MyMessage  {
    private long id;

    private int dataLength;
    private int bodyLength;
    private int nom;
    private int size;
    private byte[] body;

    public MyMessage() {

    }

    public MyMessage(long id, int dataLength, int bodyLength, int nom, int size, byte[] body) {
        this.id = id;
        this.dataLength = dataLength;
        this.bodyLength = bodyLength;
        this.nom = nom;
        this.size = size;
        this.body = body;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public int getDataLength() {
        return dataLength;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public int getNom() {
        return nom;
    }

    public int getSize() {
        return size;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
