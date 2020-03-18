package com.stynet.framework.network.webSocket;

import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;
import com.stynet.framework.Printf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by shuiDianBing on 11:56.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << WebSocket安卓客户端实现详解(一)--连接建立与重连 https://blog.csdn.net/zly921112/article/details/72973054
 */
public class WebSocketManager {
    private static final String TAG = "WebSocketManager";
    private static final int FRAME_QUEUE_SIZE = 4;
    private static final int CONNECT_TIMEOUT = 4000;
    private static final String DEF_TEST_URL = "测试服地址";//测试服默认地址
    private static final String DEF_RELEASE_URL = "正式服地址";//正式服默认地址
    private static final String DEF_URL = "访问地址";//BuildConfig.DEBUG ? DEF_TEST_URL : DEF_RELEASE_URL;
    private static WebSocketManager instance;
    private String url;
    private WebSocket webSocket;
    private WebSocketManager(){}
    public WebSocketManager getInstance(){
        if(null== instance)
            synchronized (WebSocketManager.class){
                if(null== instance)
                    instance = new WebSocketManager();
            }
        return instance;
    }
    /**
     * configUrl其实是缓存在本地的连接地址
     * 这个缓存本地连接地址是app启动的时候通过http请求去服务端获取的,
     * 每次app启动的时候会拿当前时间与缓存时间比较,超过6小时就再次去服务端获取新的连接地址更新本地缓存
     */
    public void init(){
        try {
            String url = "https://www.alipay.com/";
            webSocket = new WebSocketFactory().createSocket(url,CONNECT_TIMEOUT).setFrameQueueSize(4000).//设置帧队列最大值为4
                    setMissingCloseFrameAllowed(false).//设置不允许服务端关闭连接却未发送关闭帧
                    addListener(getWebSocketAdapter()).connectAsynchronously();//异步连接

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private WebSocketListener getWebSocketListener(){
        return new WebSocketListener(){
            @Override
            public void onStateChanged(WebSocket websocket, WebSocketState newState) throws Exception {

            }

            @Override
            public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {

            }

            @Override
            public void onConnectError(WebSocket websocket, WebSocketException cause) throws Exception {

            }

            @Override
            public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {

            }

            @Override
            public void onFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onContinuationFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onTextFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onBinaryFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onCloseFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onTextMessage(WebSocket websocket, String text) throws Exception {

            }

            @Override
            public void onTextMessage(WebSocket websocket, byte[] data) throws Exception {
                
            }

            @Override
            public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {

            }

            @Override
            public void onSendingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onFrameSent(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onFrameUnsent(WebSocket websocket, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onThreadCreated(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

            }

            @Override
            public void onThreadStarted(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

            }

            @Override
            public void onThreadStopping(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

            }

            @Override
            public void onError(WebSocket websocket, WebSocketException cause) throws Exception {

            }

            @Override
            public void onFrameError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onMessageError(WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames) throws Exception {

            }

            @Override
            public void onMessageDecompressionError(WebSocket websocket, WebSocketException cause, byte[] compressed) throws Exception {

            }

            @Override
            public void onTextMessageError(WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {

            }

            @Override
            public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

            }

            @Override
            public void onUnexpectedError(WebSocket websocket, WebSocketException cause) throws Exception {

            }

            @Override
            public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {

            }

            @Override
            public void onSendingHandshake(WebSocket websocket, String requestLine, List<String[]> headers) throws Exception {

            }
        };
    }
    private WebSocketAdapter getWebSocketAdapter(){
        return new WebSocketAdapter(){
            @Override
            public void onStateChanged(WebSocket websocket, WebSocketState newState) throws Exception {
                super.onStateChanged(websocket, newState);
                Printf.outDebug(TAG,"getWebSocketAdapter.onStateChanged:websocket="+websocket+"\nnewState="+newState);
            }

            @Override
            public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                super.onConnected(websocket, headers);
                Printf.outDebug(TAG,"getWebSocketAdapter.onConnected:websocket="+websocket+"\nheaders="+headers);
            }

            @Override
            public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
                super.onConnectError(websocket, exception);
                Printf.outDebug(TAG,"getWebSocketAdapter.onConnectError:websocket="+websocket+"\nexception="+exception);
            }

            @Override
            public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
                super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
                Printf.outDebug(TAG,"getWebSocketAdapter.onDisconnected:websocket="+websocket+"\nserverCloseFrame="
                        +serverCloseFrame+"\nclientCloseFrame"+clientCloseFrame+"\nclosedByServer="+closedByServer);
            }

            @Override
            public void onFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onFrame:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onContinuationFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onContinuationFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onContinuationFrame:websocket="+websocket+"\nframe=" +frame);
            }

            @Override
            public void onTextFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onTextFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onTextFrame:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onBinaryFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onBinaryFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onBinaryFrame:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onCloseFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onCloseFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onCloseFrame:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onPingFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onPingFrame:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onPongFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onPongFrame:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onTextMessage(WebSocket websocket, String text) throws Exception {
                super.onTextMessage(websocket, text);
                Printf.outDebug(TAG,"getWebSocketAdapter.onTextMessage:websocket="+websocket+"\ntext="+text);
            }

            @Override
            public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
                super.onBinaryMessage(websocket, binary);
                Printf.outDebug(TAG,"getWebSocketAdapter.onBinaryMessage:websocket="+websocket+"\nbinary="+binary);
            }

            @Override
            public void onSendingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onSendingFrame(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onSendingFrame:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onFrameSent(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onFrameSent(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onFrameSent:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onFrameUnsent(WebSocket websocket, WebSocketFrame frame) throws Exception {
                super.onFrameUnsent(websocket, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onFrameUnsent:websocket="+websocket+"\nframe="+frame);
            }

            @Override
            public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
                super.onError(websocket, cause);
                Printf.outDebug(TAG,"getWebSocketAdapter.onError:websocket="+websocket+"\ncause="+cause);
            }

            @Override
            public void onFrameError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {
                super.onFrameError(websocket, cause, frame);
                Printf.outDebug(TAG,"getWebSocketAdapter.onFrameError:websocket="+websocket+"\ncause="+cause+
                        "\nframe="+frame);
            }

            @Override
            public void onMessageError(WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames) throws Exception {
                super.onMessageError(websocket, cause, frames);
                Printf.outDebug(TAG,"getWebSocketAdapter.onMessageError:websocket="+websocket+"\ncause="+cause+
                        "\nframes="+frames);
            }

            @Override
            public void onMessageDecompressionError(WebSocket websocket, WebSocketException cause, byte[] compressed) throws Exception {
                super.onMessageDecompressionError(websocket, cause, compressed);
                Printf.outDebug(TAG,"getWebSocketAdapter.onMessageDecompressionError:websocket="+websocket+"\ncause="
                        +cause+"\ncompressed="+compressed);
            }

            @Override
            public void onTextMessageError(WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {
                super.onTextMessageError(websocket, cause, data);
                Printf.outDebug(TAG,"getWebSocketAdapter.onTextMessageError:websocket="+websocket+"\ncause="+cause+
                        "\ndate="+data);
            }

            @Override
            public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {
                super.onSendError(websocket, cause, frame);
                Printf.outDebug(this,"getWebSocketAdapter.onSendError:websocket="+websocket+"\ncause="
                        +cause+"\nframe="+frame);
            }

            @Override
            public void onUnexpectedError(WebSocket websocket, WebSocketException cause) throws Exception {
                super.onUnexpectedError(websocket, cause);
                Printf.outDebug(this,"getWebSocketAdapter.onUnexpectedError:websocket="+websocket+"\ncause="
                        +cause);
            }

            @Override
            public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {
                super.handleCallbackError(websocket, cause);
                Printf.outDebug(TAG,"getWebSocketAdapter.handleCallbackError:websocket="+websocket+"\ncause="
                        +cause);
            }

            @Override
            public void onSendingHandshake(WebSocket websocket, String requestLine, List<String[]> headers) throws Exception {
                super.onSendingHandshake(websocket, requestLine, headers);
                Printf.outDebug(TAG,"getWebSocketAdapter.onSendingHandshake:websocket="+websocket+"\nrequestLine="
                        +requestLine+"\nheaders="+headers);
            }

            @Override
            public void onThreadCreated(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {
                super.onThreadCreated(websocket, threadType, thread);
                Printf.outDebug(TAG,"getWebSocketAdapter.onThreadCreated:websocket="+websocket+"\nthread="+thread);
            }

            @Override
            public void onThreadStarted(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {
                super.onThreadStarted(websocket, threadType, thread);
                Printf.outDebug(TAG,"getWebSocketAdapter.onThreadStarted:websocket="+websocket+"\nthreadType="+threadType+"\nthread="+thread);
            }

            @Override
            public void onThreadStopping(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {
                super.onThreadStopping(websocket, threadType, thread);
                Printf.outDebug(TAG,"getWebSocketAdapter.onThreadStopping:websocket="+websocket+"\nthreadType="+threadType+"\nthread="+thread);
            }
        };
    }
}
