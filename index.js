
import { NativeModules, DeviceEventEmitter } from 'react-native';

const { RNAbalScannerSdk } = NativeModules;

const allowedEvents=[
    RNAbalScannerSdk.SCAN_ACTION
];

RNAbalScannerSdk.on = (eventName, handler)=>{
    if(!allowedEvents.includes(eventName))
    {
        throw new Error(`Event name $(eventName) is not Supported Event`);
    }
    DeviceEventEmitter.addListener(eventName, handler);
};

RNAbalScannerSdk.off = (eventName, handler)=>{
    if(!allowedEvents.includes(eventName))
    {
        throw new Error(`Event name $(eventName) is not Supported Event`);
    }
    DeviceEventEmitter.removeListener(eventName, handler);
};


export default RNAbalScannerSdk;
