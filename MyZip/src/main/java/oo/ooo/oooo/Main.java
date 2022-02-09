package oo.ooo.oooo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String data = "Кстати, интерактивные прототипы ограничены исключительно образом мышления. В своём стремлении повысить качество жизни, они забывают, что внедрение современных методик создаёт необходимость включения в производственный план целого ряда внеочередных мероприятий с учётом комплекса как самодостаточных, так и внешне зависимых концептуальных решений. И нет сомнений, что независимые государства набирают популярность среди определенных слоев населения, а значит, должны быть призваны к ответу.";
        int length = 508;
        long id = 555;
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        int size = bytes.length;
        ZipService zipService = new ZipService();
        byte[] zip = zipService.zip(bytes);
        MessageService messageService = new MessageService();
        List<byte[]> list = messageService.createPackages(zip, length, id);

        //TODO send


        List<MyMessage> messages = new ArrayList<>();
        list.forEach(mess -> messageService.convert(messages, mess));
        byte[] body = messageService.getMessage(messages);
        byte[] unZip = zipService.unZip(body, bytes.length);//TODO  LENGTH!!!!!!!!!!!!!!!!!!!!!!!
        String convertDataa = new String(unZip);
        System.out.println(convertDataa);


    }
}
