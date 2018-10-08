package com.hencoder.a26;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public class Io {

    public static void main(String[] args) {
        okio2();
    }

    private static void io1() {
        try (OutputStream outputStream = new FileOutputStream("./26/text.txt")) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write('a');
            bufferedOutputStream.write('b');
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io2() {
        try (InputStream inputStream = new FileInputStream("./26/text.txt");
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io3() {
        try (InputStream inputStream = new FileInputStream("./26/text.txt");
                OutputStream outputStream = new FileOutputStream("./26/new_text.txt")) {
            byte[] data = new byte[1024];
            int read;
            while ((read = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nio1() {
        try (RandomAccessFile file = new RandomAccessFile("./26/new_text.txt", "r");
             FileChannel channel = file.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(Charset.defaultCharset().decode(byteBuffer));
            byteBuffer.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nio2() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void okio1() {
        try (BufferedSource source = Okio.buffer(Okio.source(new File("./26/text.txt")))) {
            System.out.println(source.readUtf8Line());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void okio2() {
        try {
            Buffer buffer = new Buffer();
            DataOutputStream dataOutputStream = new DataOutputStream(buffer.outputStream());
            dataOutputStream.writeUTF("abab");
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChar('b');
            DataInputStream inputStream = new DataInputStream(buffer.inputStream());
            System.out.println(inputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
