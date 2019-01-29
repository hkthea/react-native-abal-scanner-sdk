
package com.nextg.reactnative;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import android.device.ScanDevice;

public class RNAbalScannerSdkModule extends ReactContextBaseJavaModule {

    ScanDevice sm;
    private final ReactApplicationContext reactContext;
    private final static String SCAN_ACTION = "scan.rcv.message";
    private final static boolean D = true;
    private String barcodeStr;

    public RNAbalScannerSdkModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        sm = new ScanDevice();
        IntentFilter filter = new IntentFilter();
		filter.addAction(SCAN_ACTION);
		reactContext.registerReceiver(mScanReceiver, filter);
    }

    @Override
    public String getName() {
        return "RNAbalScannerSdk";
    }

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            // byte[] barocode = intent.getByteArrayExtra("barocode");
            // int barocodelen = intent.getIntExtra("length", 0);
            // byte temp = intent.getByteExtra("barcodeType", (byte) 0);
            // byte[] aimid = intent.getByteArrayExtra("aimid");
            WritableMap params = Arguments.createMap();
            barcodeStr = sm.getScanCodeValue();//new String(barocode, 0, barocodelen);
            int scannerType= sm.getScannerType();
            int barcodeType= sm.getOutScanMode();
            params.putString("data", barcodeStr);
            params.putInt("scannerType", scannerType);
            params.putInt("barcodeType", barcodeType);

            // showScanResult.append("Broadcast：");
            // showScanResult.append(barcodeStr);
            // showScanResult.append("\n");
            sm.stopScan();
            sendEvent(SCAN_ACTION, params);
        }

    };

    private void sendEvent(String eventName, @Nullable WritableMap params)
    {
        if(this.reactContext.hasActiveCatalystInstance())
        {
            if(D) Log.d("ABAL BARCODE SCANNER", "Sending Event: "+eventName);
            this.reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
        }
    }

    // @override
    // public ReactContextBaseJavaModule(ReactApplicationContext reactContext) 
    // {
    //     super.ReactContextBaseJavaModule(reactContext);
    //     sm = new ScanDevice();
    // }
    

    @ReactMethod
    public void closeScanner()
    {
        sm.closeScan();
    }

    @ReactMethod
    public void openScanner()
    {
        sm.openScan();
    }

    @ReactMethod
    public void startScanner()
    {
        sm.startScan();
    }

    @ReactMethod
    public void stopScanner()
    {
        sm.stopScan();
    }

    @Override
    public Map<String, Object> getConstants()
    {
        final Map<String, Object> constants=new HashMap<>();
        constants.put("SCAN_ACTION",SCAN_ACTION);
        return constants;
    }

}