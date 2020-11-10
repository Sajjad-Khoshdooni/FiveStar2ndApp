package com.example.fivestarnewedition.Constant;

import android.app.ActionBar;
import android.app.Activity;
import android.app.admin.DnsEvent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.BlueTooth.BluetoothTestActivity;
import com.example.fivestarnewedition.BlueTooth.SerialService;
import com.example.fivestarnewedition.BuildConfig;
import com.example.fivestarnewedition.Log.Log;
import com.example.fivestarnewedition.MQTT.MQTT;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Constant {
    private static ControlEnum controlEnum = ControlEnum.INTERNET;

    ///////Ino vel kon
    private static List<Device> groupDeice = new ArrayList<>();
    private static List<Integer> images = new ArrayList<>();
    private static int imageOnIcon;
    private static int imageOffIcon;
    private static boolean on = false;

    //BlueTooth
    static private SerialService serialService = null;

    //Internet
    private static MQTT mqtt = null;

    //File Address
    private static String ACCOUNTS_PATH = "accounts.txt";
    private static String MAIN_ACCOUNT_PATH = "mainaccount.txt";
    private static String LOGS_PATH = "logs.txt";
    private static String DEVICE_PATH = "device.txt";
    private static String GROUP_PATH = "group.txt";
    private static String SENARIO_PATH = "senario.txt";
    private static String IF_THEN_PATH = "ifthen.txt";
    private static String PASSWORD_PATH = "password.txt";


    /**
     * important
     */

    private static void writeFileInPath(Activity activity, String path, String data){
        try {
            File file = new File(activity.getFilesDir(), path);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.append(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }

    private static String readFileInPath(Activity activity, String path){
        try {
            FileInputStream fileInputStream = activity.openFileInput(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder sb = new StringBuilder();
            String line = "";
            int a = 0;
            while ((line = reader.readLine()) != null){
                if (a!= 0) sb.append("\n");
                sb.append(line);
                a++;
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     */


    public static void vibrate(Context context){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    public static void sendMessage(String code,Context context) {
        Toast.makeText(context,"Message Sent",Toast.LENGTH_SHORT).show();
        vibrate(context);
        if (controlEnum == ControlEnum.BLUETOOTH) sendBluetooth(code , context);
        else publish(code);
    }

    public static void subscribe(Device device){
        try{
            mqtt.subscribe(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void subscribe(String code){
        try{
            mqtt.subscribe(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void publish(Device device){
        try{
            mqtt.publish(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void publish(String code){
        try{
            mqtt.publish(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendBluetooth(String message,Context context){
        if (serialService == null){
            Toast.makeText(context,"Select Bluetooth Device",Toast.LENGTH_SHORT).show();
            Intent my = new Intent(context, BluetoothTestActivity.class);
            context.startActivity(my);
            return;
        }
        try {
            serialService.write(message.getBytes());
            Toast.makeText(context,"Sent",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param
     */

    private static List<Account> readAccounts(AppCompatActivity activity){
        if (new Gson().fromJson(readFileInPath(activity,ACCOUNTS_PATH), new TypeToken<List<Account>>(){}.getType()) != null){
            return new Gson().fromJson(readFileInPath(activity,ACCOUNTS_PATH), new TypeToken<List<Account>>(){}.getType());
        }
        return new ArrayList<>();
    }

    private static void writeAccounts(AppCompatActivity activity,List<Account> accounts){
        writeFileInPath(activity,ACCOUNTS_PATH,new Gson().toJson(accounts));
    }

    private static List<Device> readDevice(AppCompatActivity activity){
        if (new Gson().fromJson(readFileInPath(activity,DEVICE_PATH), new TypeToken<List<Device>>(){}.getType()) != null) {
            return new Gson().fromJson(readFileInPath(activity, DEVICE_PATH), new TypeToken<List<Device>>() {
            }.getType());
        }
        return new ArrayList<>();
    }

    private static void writeDevices(AppCompatActivity activity,List<Device> devices){
        writeFileInPath(activity,DEVICE_PATH,new Gson().toJson(devices));
    }

    private static List<Log> readLogs(AppCompatActivity activity){
        if (new Gson().fromJson(readFileInPath(activity,LOGS_PATH), new TypeToken<List<Log>>(){}.getType()) != null) {
            return new Gson().fromJson(readFileInPath(activity, LOGS_PATH), new TypeToken<List<Log>>() {
            }.getType());
        }
        return new ArrayList<>();
    }

    private static void writeLogs(AppCompatActivity activity,List<Log> logs){
        writeFileInPath(activity,LOGS_PATH,new Gson().toJson(logs));
    }

    private static List<Senario> readSenario(AppCompatActivity activity){
        /**
         * Do something
         */
        return new ArrayList<>();
    }

    private static void writeSenario(AppCompatActivity activity,List<Senario> senarios){
        /**
         * Do something
         */
    }

    private static List<IfThen> readIfThen(AppCompatActivity activity){
        /**
         * Do something
         */
        return new ArrayList<>();
    }

    private static void writeIfThen(AppCompatActivity activity,List<IfThen> ifThens){
        /**
         * Do something
         */
    }

    private static List<Group> readGroup(AppCompatActivity activity){
        /**
         * Do something
         */
        return new ArrayList<>();
    }

    private static void writeGroup(AppCompatActivity activity,List<Group> group){
        /**
         * Do something
         */
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void changeAccount(AppCompatActivity activity ,Account oldAccount ,Account newAccount){
        List<Account> accounts = new ArrayList<>();
        try{
            accounts = readAccounts(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (accounts == null)
            accounts = new ArrayList<>();
        accounts.remove(oldAccount);
        accounts.add(newAccount);
        writeAccounts(activity,accounts);
    }

    public static void removeAccount(AppCompatActivity activity ,Account account){
        List<Account> accounts = new ArrayList<>();
        try{
            accounts = readAccounts(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (accounts == null) {
            accounts = new ArrayList<>();
            return;
        }
        for (Account account1:accounts){
            if (account1.getName().equals(account.getName())&&
            account1.getAddress().equals(account.getAddress())&&
            account1.getID().equals(account.getID())&&
            account1.getIMEI().equals(account.getIMEI())){
                accounts.remove(account1);
            }
        }
        writeAccounts(activity,accounts);
    }

    public static void addAccount(AppCompatActivity activity ,Account account) {
        List<Account> accounts = new ArrayList<>();
        try{
            accounts = readAccounts(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (accounts == null)
            accounts = new ArrayList<>();
        accounts.add(account);
        writeAccounts(activity,accounts);
    }

    public static List<Account> getAccounts(AppCompatActivity activity) {
        return readAccounts(activity);
    }

    public static Account getAccounts(AppCompatActivity activity ,int position) {
        return readAccounts(activity).get(position);
    }


    public static String getPassword(AppCompatActivity activity){
        return readFileInPath(activity,PASSWORD_PATH);
    }

    public static List<Device> getDevices(AppCompatActivity activity){
        return readDevice(activity);
    }

    public static Device getDevices(AppCompatActivity activity,int position){
        return readDevice(activity).get(position);
    }

    public static void changeDevice(AppCompatActivity activity,Device oldDevice, Device newDevice){
        List<Device> devices = new ArrayList<>();
        try{
            devices = readDevice(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (devices == null)
            devices = new ArrayList<>();
        devices.remove(oldDevice);
        devices.add(newDevice);
        writeDevices(activity,devices);
    }

    public static void addDevice(AppCompatActivity activity,Device device){
        List<Device> devices = new ArrayList<>();
        try{
            devices = readDevice(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (devices == null)
            devices = new ArrayList<>();
        devices.add(device);
        writeDevices(activity,devices);
    }

    public static void removeDevice(AppCompatActivity activity, Device device) {
        List<Device> devices = new ArrayList<>();
        try{
            devices = readDevice(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (devices == null)
            devices = new ArrayList<>();
        devices.remove(device);
        writeDevices(activity,devices);
    }

    public static List<Senario> getSenarios(AppCompatActivity activity) {
        return readSenario(activity);
    }

    public static Senario getSenarios(AppCompatActivity activity,int position) {
        return readSenario(activity).get(position);
    }

    public static void removeSenario(AppCompatActivity activity, Senario senario){
        List<Senario> senarios = new ArrayList<>();
        try{
            senarios = readSenario(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (senarios == null)
            senarios = new ArrayList<>();
        senarios.remove(senario);
        writeSenario(activity,senarios);
    }

    public static void addSenario(AppCompatActivity activity, Senario senario){
        List<Senario> senarios = new ArrayList<>();
        try{
            senarios = readSenario(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (senarios == null)
            senarios = new ArrayList<>();
        senarios.add(senario);
        writeSenario(activity,senarios);
    }

    public static List<IfThen> getIfThens(AppCompatActivity activity) {
        return readIfThen(activity);
    }

    public static void addIfThens(AppCompatActivity activity, IfThen ifThen) {
        List<IfThen> ifThenList = new ArrayList<>();
        try{
            ifThenList = readIfThen(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ifThenList == null)
            ifThenList = new ArrayList<>();
        ifThenList.add(ifThen);
        writeIfThen(activity,ifThenList);
    }

    public static void removeIfThens(AppCompatActivity activity, IfThen ifThen) {
        List<IfThen> ifThenList = new ArrayList<>();
        try{
            ifThenList = readIfThen(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ifThenList == null)
            ifThenList = new ArrayList<>();
        ifThenList.remove(ifThen);
        writeIfThen(activity,ifThenList);
    }

    public static List<Log> getLogs(AppCompatActivity activity) {
        return readLogs(activity);
    }

    public static void addLogs(AppCompatActivity activity, Log log) {
        List<Log> logList = new ArrayList<>();
        try{
            logList = readLogs(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (logList == null)
            logList = new ArrayList<>();
        logList.add(log);
        writeLogs(activity,logList);
    }

    public static ControlEnum getControlEnum() {
        return controlEnum;
    }

    public static void setControlEnum(ControlEnum controlEnum) {
        Constant.controlEnum = controlEnum;
    }

    public static void setControlDevice(ControlEnum controlDevice, Context context) {
        Constant.controlEnum = controlDevice;
        Toast.makeText(context,controlDevice.toString() + "-Mode" ,Toast.LENGTH_SHORT).show();
    }

    public static List<Device> getGroupDeice() {
        return groupDeice;
    }

    public static void setGroupDeice(List<Device> groupDeice) {
        Constant.groupDeice = groupDeice;
    }

    public static void addGroupDeice(Device groupDeice) {
        Constant.groupDeice.add(groupDeice);
    }

    public static void removeGroupDeice(Device groupDeice) {
        try {
            Constant.groupDeice.remove(groupDeice);
        } catch (Exception e) {
        }
    }

    public static void backup() {
        /**
         * Do something
         */
    }

    public static void restore() {
        /**
         * Do something
         */
    }

    public static void clearLog(AppCompatActivity activity) {
        List<Log> logs = new ArrayList<>();
        writeLogs(activity,logs);
    }

    public static void changePassword(AppCompatActivity activity, String password) {
        writeFileInPath(activity,PASSWORD_PATH,password);
    }

    public static SerialService getSerialService() {
        return serialService;
    }

    public static void setSerialService(SerialService serialService) {
        Constant.serialService = serialService;
    }

    public static List<Group> getGroups(AppCompatActivity activity) {
        return readGroup(activity);
    }

    public static void setGroups(AppCompatActivity activity,List<Group> groups) {
        writeGroup(activity,groups);
    }

    public static void addGroup(AppCompatActivity activity, Group group) {
        List<Group> groups = new ArrayList<>();
        try{
            groups = readGroup(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (groups == null)
            groups = new ArrayList<>();
        groups.add(group);
        writeGroup(activity,groups);
    }

    public static void clearGroupDevice(){
        groupDeice.clear();
    }

    public static Group getGroup(AppCompatActivity activity,Integer position) {
        return readGroup(activity).get(position);
    }

    public static void changeGroup(AppCompatActivity activity,Group oldGroup, Group newGroup) {
        List<Group> groups = new ArrayList<>();
        try{
            groups = readGroup(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (groups == null)
            groups = new ArrayList<>();
        groups.remove(oldGroup);
        groups.add(newGroup);
        writeGroup(activity,groups);
    }

    public static Account getMainAccount(AppCompatActivity activity){
        return new Gson().fromJson(readFileInPath(activity,MAIN_ACCOUNT_PATH), Account.class);
    }

    public static void setMainAccount(AppCompatActivity activity ,Account mainAccount) {
        writeFileInPath(activity,MAIN_ACCOUNT_PATH,new Gson().toJson(mainAccount));
    }

    /////////////////////////////////////////////////
    // values have to be globally unique
    public static final String INTENT_ACTION_DISCONNECT = BuildConfig.APPLICATION_ID + ".Disconnect";
    public static final String NOTIFICATION_CHANNEL = BuildConfig.APPLICATION_ID + ".Channel";
    public static final String INTENT_CLASS_MAIN_ACTIVITY = BuildConfig.APPLICATION_ID + ".MainActivity";

    // values have to be unique within each app
    public static final int NOTIFY_MANAGER_START_FOREGROUND_SERVICE = 1001;

    /**
     * check is device on ot not from Mqtt Broker
     * @param device
     * @return
     */
    public static boolean isDeviceOn(Device device) {
        /**
         * Do something
         */
        return false;
    }

    public static List<Integer> getImages() {
        return images;
    }

    public static void setImages(List<Integer> images) {
        Constant.images = images;
    }

    public static void addImage(int image){
        images.add(image);
    }

    public static int getImageOnIcon() {
        return imageOnIcon;
    }

    public static void setImageOnIcon(int imageOnIcon) {
        Constant.imageOnIcon = imageOnIcon;
    }

    public static int getImageOffIcon() {
        return imageOffIcon;
    }

    public static void setImageOffIcon(int imageOffIcon) {
        Constant.imageOffIcon = imageOffIcon;
    }

    public static Boolean isOn() {
        return on;
    }

    public static void setOn(boolean onn){
        on = onn;
    }

    public static MQTT getMqtt() {
        return mqtt;
    }

    public static void setMqtt(MQTT mqtt) {
        Constant.mqtt = mqtt;
    }
}
