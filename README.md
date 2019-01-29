
# react-native-abal-scanner-sdk

## Getting started

`$ npm install react-native-abal-scanner-sdk --save`

### Mostly automatic installation

`$ react-native link react-native-abal-scanner-sdk`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.nextg.reactnative.RNAbalScannerSdkPackage;` to the imports at the top of the file
  - Add `new RNAbalScannerSdkPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-abal-scanner-sdk'
  	project(':react-native-abal-scanner-sdk').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-abal-scanner-sdk/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-abal-scanner-sdk')
  	```


## Usage
```javascript
import RNAbalScannerSdk from 'react-native-abal-scanner-sdk';

// TODO: What to do with the module?
RNAbalScannerSdk;
```
  