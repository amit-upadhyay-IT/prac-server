# Node.js Async File Uploader
Upload files using Node.js with upload percentages using AJAX.

## Installing
Clone the repo and run the following:
```
npm install
```

## Running
You can either use node to run this, I suggest something like supervisor:
```
npm install -g supervisor
```

Once supervisor is installed:
```
supervisor server.js
```
Navigate to ```http://localhost:3000``` to view the demo

## How does it work?
- Once you have a server running, upload a file and a preview will be generated for you.
- Click on upload and a progress bar will track the upload progress.
- In the meantime, you can play with the random numbers generator which demonstrates the async nature of the upload and nothing hangs on the page.

---
PR's welcome.
