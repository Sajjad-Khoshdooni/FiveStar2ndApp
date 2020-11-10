package com.example.fivestarnewedition.MQTT;


import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class MQTT {
    AppCompatActivity appCompatActivity;

    // 单例
    private static MQTT mInstance = null;

    // 回调
    private MqttCallback mCallback;

    // Private instance variables
    private MqttClient client;
    private MqttConnectOptions conOpt;
    private boolean clean = true;

    private MQTT(AppCompatActivity activity) {
        appCompatActivity = activity;
        mCallback = new MqttCallbackBus(activity);
    }

    public static MQTT getInstance(AppCompatActivity activity) {
        if (null == mInstance) {
            mInstance = new MQTT(activity);
        }
        return mInstance;
    }

    /**
     * 释放单例, 及其所引用的资源
     */
    public static void release() {
        try {
            if (mInstance != null) {
                mInstance.disConnect();
                mInstance = null;
            }
        } catch (Exception e) {

        }
    }

    /**
     * 创建Mqtt 连接
     *
     * @param brokerUrl Mqtt服务器地址(tcp://xxxx:1863)
     * @param clientId  clientId
     * @return
     */
    public boolean connect(String brokerUrl, String clientId) {
        boolean flag = false;
        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        try {
            // Construct the connection options object that contains connection parameters
            // such as cleanSession and LWT
            conOpt = new MqttConnectOptions();
            conOpt.setCleanSession(clean);

            // Construct an com.example.fivestar.MQTT.MQTT blocking mode client
            client = new MqttClient(brokerUrl, clientId, dataStore);

            // Set this wrapper as the callback handler
            client.setCallback(mCallback);
            flag = doConnect();
        } catch (Exception e) {
//            Logger.e(e.getMessage());
        }

        return flag;
    }

    /**
     * 建立连接
     *
     * @return
     */
    private boolean doConnect() {
        boolean flag = false;
        if (client != null) {
            try {
                client.connect(conOpt);
//                Logger.d("Connected to " + client.getServerURI() + " with client ID " + client.getClientId());
                flag = true;
            } catch (Exception e) {
            }
        }
        return flag;
    }

    /**
     * Send data with publish
     * @param device
     */

    public void publish(Device device){
        publish(device.getSendCode());
    }

    public boolean publish(String code) {
        String topicName = Constant.getMainAccount(appCompatActivity).getIMEI()+"/topic";
        int qos = 2;
        boolean flag = false;

        if (client != null && client.isConnected()) {

//            Logger.d("Publishing to topic \"" + topicName + "\" qos " + qos);

            // Create and configure a message
            MqttMessage message = new MqttMessage(code.getBytes());
            message.setQos(qos);

            // Send the message to the server, control is not returned until
            // it has been delivered to the server meeting the specified
            // quality of service.
            try {
                client.publish(topicName, message);
                flag = true;
            } catch (MqttException e) {

            }

        }
        return flag;
    }

    /**
     * Recieve data with subscribe
     * @param device
     */
    public void subscribe(Device device){
        subscribe(device.getSendCode());
    }

    public boolean subscribe(String code) {
        int qos = 2;
        boolean flag = false;

        if (client != null && client.isConnected()) {
            // Subscribe to the requested topic
            // The QoS specified is the maximum level that messages will be sent to the client at.
            // For instance if QoS 1 is specified, any messages originally published at QoS 2 will
            // be downgraded to 1 when delivering to the client but messages published at 1 and 0
            // will be received at the same level they were published at.
//            Logger.d("Subscribing to topic \"" + topicName + "\" qos " + qos);
            try {
                client.subscribe(code, qos);
                flag = true;
            } catch (MqttException e) {

            }
        }

        return flag;
    }

    public void disConnect() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
        }
    }
}