package com.example.protobufexamplegradle;

import com.example.proto.Simple;
import com.google.protobuf.Timestamp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class ProtobufExampleGradleApplication {

  public static void main(String[] args) {
    Simple.SimpleMessage.Builder builder = Simple.SimpleMessage.newBuilder();

    com.google.protobuf.Timestamp ts = Timestamp.newBuilder().setSeconds(System.currentTimeMillis() / 1000).setNanos(999).build();
    Simple.SimpleMessage daniel = builder.setId(99).setName("Daniel")
        .addList(1).addList(2).addList(3).setMyField(ts).build();

    try (OutputStream os = new FileOutputStream("test.bin")) {
      daniel.writeTo(os);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try (InputStream is = new FileInputStream("test.bin")) {
      Simple.SimpleMessage message = Simple.SimpleMessage.parseFrom(is);
      System.out.println(message);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    SpringApplication.run(ProtobufExampleGradleApplication.class, args);
  }
}
