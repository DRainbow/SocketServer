package com.sugar.android.socketserver.socket;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mwee.android.drivenbus.DrivenBusManager;
import com.sugar.android.socket.model.Request;
import com.sugar.android.socketserver.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SocketServer
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 下午1:45
 */
public class SocketServer {

    private static final String TAG = SocketServer.class.getSimpleName();

    private static final String SOCKET_ADDRESS = "com.mwee.localsocket";
    private static final int SOCKET_PORT = 10010;

//    private LocalServerSocket mServer;
//    private LocalSocket mConnect;

    private ServerSocket mServer;
    private Socket mConnect;

    private BufferedReader mReader;
    private PrintWriter mWriter;

    private Thread mServerThread;

    private Runnable mServerRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                connect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }

        }
    };

    private static Object sLockObj = new Object();

    private static SocketServer _instance = new SocketServer();

    public static SocketServer getInstance() {
        return _instance;
    }

    private SocketServer() {
    }

    public void init() {
        synchronized (sLockObj) {
            if (mServerThread == null || !mServerThread.isAlive()) {
                mServerThread = new Thread(mServerRunnable);
                mServerThread.setName("Local Socket Server");
                mServerThread.start();
            }
        }
    }

    /**
     * 连接
     */
    public void connect() throws IOException, ClassNotFoundException {
        mServer = new ServerSocket(SOCKET_PORT);
        while (true) {
            mConnect = mServer.accept();
            Log.i(TAG, "有客户端消息");
            ClientHandler client = new ClientHandler(mConnect);
            client.start();
//            ObjectOutputStream oos = new ObjectOutputStream(mConnect.getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(mConnect.getInputStream());
////            Request request;
////            while ((request = (Request) ois.readObject()) != null) {
////                Log.d(TAG, "Current Request:\n" + JSON.toJSONString(request));
////
////                List<Object> paramsArr = request.getParams();
////                if (paramsArr == null){
////                    paramsArr = new ArrayList<>();
////                }
////                paramsArr.add(0, App.getInstance());
////                Object[] params = paramsArr.toArray(new Object[paramsArr.size()]);
////
////                DrivenBusManager.getInstance().call(request.getMethod(), params);
////            }
//
//            Request request = (Request) ois.readObject();
//            Log.d(TAG, "Current Request:\n" + JSON.toJSONString(request));
//
//            List<Object> paramsArr = request.getParams();
//            if (paramsArr == null){
//                paramsArr = new ArrayList<>();
//            }
//            paramsArr.add(0, App.getInstance());
//            Object[] params = paramsArr.toArray(new Object[paramsArr.size()]);
//
//            Object obj = DrivenBusManager.getInstance().call(request.getMethod(), params);
//
////            Response obj = new Response();
////            obj.code = 200;
////            obj.message = "success";
//
//            if (obj instanceof Response){
//                Response res = (Response) obj;
//                oos.writeObject(res);
//                oos.flush();
////                oos.writeObject(null);
//            } else {
//                oos.writeObject(null);
//                oos.flush();
//            }
//
//            ois.close();
//            oos.close();
//            mConnect.close();
        }
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        try {
            if (mReader != null) {
                mReader.close();
                mReader = null;
            }
            if (mWriter != null) {
                mWriter.close();
                mWriter = null;
            }
            if (mConnect != null) {
                mConnect.close();
                mConnect = null;
            }
            if (mServer != null) {
                mServer.close();
                mServer = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientHandler extends Thread {
        private Socket socket;

        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            Log.d(TAG, "开始监听客户端:" + socket.getLocalAddress());
            while (true) {
                try {
                    Request request = (Request) ois.readObject();
                    Log.d(TAG, "Current Request:\n" + JSON.toJSONString(request));

                    List<Object> paramsArr = request.getParams();
                    if (paramsArr == null){
                        paramsArr = new ArrayList<>();
                    }
                    paramsArr.add(0, App.getInstance());
                    Object[] params = paramsArr.toArray(new Object[paramsArr.size()]);

                    Object obj = DrivenBusManager.getInstance().call(request.getMethod(), params);

//                    if (obj instanceof Response){
//                        Response res = (Response) obj;
//                        oos.writeObject(res);
//                        oos.flush();
//                    } else {
//                        oos.writeObject(null);
//                        oos.flush();
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
