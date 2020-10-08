package alnero;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReplyServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean isRunning = true;
            while (isRunning) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String req = in.readLine();
                    String repl = "";
                    while (!req.isEmpty()) {
                        if (req.contains("msg=Hello")) {
                            repl = "Hello";
                        } else if (req.contains("msg=Exit")) {
                            isRunning = false;
                        } else if (req.contains("msg=")) {
                            // get substring between 'msg=' and 'HTTP/1.1', then split if there are more than one parameter
                            String paramString = req.substring(req.indexOf("msg=") + 4, req.indexOf("HTTP") - 1);
                            repl = paramString.split("&")[0];
                        }
                        req = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(repl.getBytes());
                }
            }
        }
    }
}
