var formidable = require('formidable');
var fs = require('fs');

var express = require('express');
var http = require('http');
var app = express();
var server = http.createServer(app);

var io = require('socket.io')(server);

server.listen(process.env.PORT || 9000, function() {
	console.log('listening on port 9000');
});

app.get('/downloadall', function(req, res){
  var file = __dirname + '/files/all.zip';
  res.download(file);
});

app.get('/ad', function(req, res){
  var file = __dirname + '/files/all.zip';
  res.download(file);
});

app.get('/q1', function(req, res){
  var file = __dirname + '/files/q1.zip';
  res.download(file);
});

app.get('/q2', function(req, res){
  var file = __dirname + '/files/q2.zip';
  res.download(file);
});

app.get('/q4', function(req, res){
  var file = __dirname + '/files/q4.zip';
  res.download(file);
});

app.get('/q5', function(req, res){
  var file = __dirname + '/files/q5.zip';
  res.download(file);
});

app.get('/q6', function(req, res){
  var file = __dirname + '/files/q6.zip';
  res.download(file);
});

app.get('/q7', function(req, res){
  var file = __dirname + '/files/q7.zip';
  res.download(file);
});

app.get('/q8', function(req, res){
  var file = __dirname + '/files/q8.zip';
  res.download(file);
});


app.get('/q3', function(req, res){
  var file = __dirname + '/files/q3.zip';
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
