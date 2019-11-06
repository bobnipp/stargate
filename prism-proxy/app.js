const express = require('express');
const proxy = require('http-proxy-middleware');
const https = require('https');
const fs = require('fs');

if (!(process.argv.length === 3 && (process.argv[2] === '--http' || process.argv[2] === '--https'))) {
    console.log('You must provide a flag: --http or --https');
    process.exit(-1);
}

const isSecure = process.argv[2] === '--https';

const handleResponse = (response) => {
    if (!isSecure) {
        if (response.headers['set-cookie']) {
            response.headers['set-cookie'] = response.headers['set-cookie'].map(cookie => {
                return cookie.replace(' Secure', '');
            });
        }
    }
};

const options = {
    target: 'https://ec2-96-127-47-237.us-gov-west-1.compute.amazonaws.com/',
    changeOrigin: true,
    secure: false,
    logLevel: 'debug',
    autoRewrite: true,
    onProxyRes: handleResponse
};

if (!isSecure) {
    options.protocolRewrite = 'http'
}

console.log('Starting proxy with options:');
console.log(JSON.stringify(options, undefined, 2), '\n');

const app = express();
app.use('/', proxy(options));

if (isSecure) {
    const selfSignedCert = {
        key: fs.readFileSync('server.key'),
        cert: fs.readFileSync('server.cert')
    };

    https
        .createServer(selfSignedCert, app)
        .listen(5050, () => console.log('\nListening on port 5050 using https...'));
} else {
    app.listen(5050);
    console.log('\nListening on port 5050...');
}
