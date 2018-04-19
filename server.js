var formidable = require('formidable');
var fs = require('fs');

var express = require('express');
var http = require('http');
var app = express();
var server = http.createServer(app);

var io = require('socket.io')(server);

server.listen(process.env.PORT || 3000, function() {
	console.log('listening on port 3000');	
});

app.get('/downloadall', function(req, res){
  var file = __dirname + '/files/all.zip';
  res.download(file);
});

app.get('/', function(req, res) {
	res.sendFile(__dirname + '/index.html');
})

app.post('/', function(req, res) {
	var form = new formidable.IncomingForm();
	form.keepExtensions = true;
	form.parse(req);
	var nsp = io.of('/per');
	io.on('connection', function (socket) {
	  	socket.join('sessionId');
	});
	form.on('progress', function(br, be) {
		var per = (br * 100) / be;
		io.to('sessionId').emit('uploadProgress', { percent: per });
	})
	form.on('file', function(name, file) {
		var rstream = fs.createReadStream(file.path);
		var wstream = fs.createWriteStream(__dirname + '/uploads/' + file.name);
		rstream.pipe(wstream);
		wstream.on('finish', function() {
			res.end('done');
		})
	})
})
