/**
 * @providesModule FileMD5Android
 */

'use strict';

var React = require('react-native');
var {Platform, NativeModules} = React;
var RNFileMD5Android = NativeModules.FileMD5Android;

var FileMD5Android = {
    getFileMD5(filePath) {
        return RNFileMD5Android.getFileMD5(filePath);
    }
};

module.exports = FileMD5Android;
