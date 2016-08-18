# Reclamation center
Tutorial on how to build a REST CRUD application with Akka-Http.
This Back-end side manages the reclamations sent within big organizations like international hotels
or any kind of institutions which works closely with differents clients.

## How to run the service
Clone the repository:
```
> git clone https://github.com/mohamedamines/ReclamationCenter
```

Get to the `akka-http-crud` folder:
```
> cd akka-http-crud
```

Run the service:
```
> sbt run
```

The service runs on port 5000 by default.

## Usage

reclamation entity:
```
case class Reclamation(id: String, title: String, text: String)
```

### Create a reclamation
Request:
```
curl -v -H "Content-Type: application/json" \
	 -X POST http://localhost:5000/reclamations \
	 -d '{"id": "test", "title": "MyTitle", "text":"content of the reclamation"}'
```
Response if the reclamation has been created:
```
*   Trying ::1...
* Connected to localhost (::1) port 5000 (#0)
> POST /reclamations HTTP/1.1
> Host: localhost:5000
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 68
> 
* upload completely sent off: 68 out of 68 bytes
< HTTP/1.1 201 Created
< Location: http://localhost:5000/reclamations/test
< Server: akka-http/2.3.12
< Date: Sun, 07 Feb 2016 11:16:50 GMT
< Content-Type: application/json
< Content-Length: 0
< 
* Connection #0 to host localhost left intact

```
Response if the reclamation with the specified id already exists:
```
*   Trying ::1...
* Connected to localhost (::1) port 5000 (#0)
> POST /reclamations HTTP/1.1
> Host: localhost:5000
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 68
> 
* upload completely sent off: 68 out of 68 bytes
< HTTP/1.1 409 Conflict
< Server: akka-http/2.3.12
< Date: Sun, 07 Feb 2016 11:17:07 GMT
< Content-Type: application/json
< Content-Length: 0
< 
* Connection #0 to host localhost left intact
```


### Get a reclamation
Request:
```
curl -v http://localhost:5000/reclamations/test
```
Response if the reclamation exists:
```
*   Trying ::1...
* Connected to localhost (::1) port 5000 (#0)
> GET /reclamations/test HTTP/1.1
> Host: localhost:5000
> User-Agent: curl/7.43.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Server: akka-http/2.3.12
< Date: Sun, 07 Feb 2016 11:17:31 GMT
< Content-Type: application/json
< Content-Length: 64
< 
* Connection #0 to host localhost left intact
{"id":"test","title":"MyTitle","text":"content of the reclamation"}
```
Response if the reclamation does not exist:
```
*   Trying ::1...
* Connected to localhost (::1) port 5000 (#0)
> GET /reclamations/non-existing-reclamation HTTP/1.1
> Host: localhost:5000
> User-Agent: curl/7.43.0
> Accept: */*
> 
< HTTP/1.1 404 Not Found
< Server: akka-http/2.3.12
< Date: Sun, 07 Feb 2016 11:18:40 GMT
< Content-Type: application/json
< Content-Length: 0
< 
* Connection #0 to host localhost left intact
```

### Update a reclamation
Request:
```
curl -v -H "Content-Type: application/json" \
	 -X PUT http://localhost:5000/reclamations/test \
	 -d '{"text":"Another text"}'
```
Response if the reclamation has been updated:
```
*   Trying ::1...
* Connected to localhost (::1) port 5000 (#0)
> PUT /reclamations/test HTTP/1.1
> Host: localhost:5000
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 23
> 
* upload completely sent off: 23 out of 23 bytes
< HTTP/1.1 200 OK
< Server: akka-http/2.3.12
< Date: Sun, 07 Feb 2016 11:19:31 GMT
< Content-Type: application/json
< Content-Length: 53
< 
* Connection #0 to host localhost left intact
{"id":"test","title":"MyTitle","text":"Another text"}
```
Response if the reclamation could not be updated:
```
*   Trying ::1...
* Connected to localhost (::1) port 5000 (#0)
> PUT /reclamations/non-existing-reclamation HTTP/1.1
> Host: localhost:5000
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 23
> 
* upload completely sent off: 23 out of 23 bytes
< HTTP/1.1 404 Not Found
< Server: akka-http/2.3.12
< Date: Sun, 07 Feb 2016 11:20:07 GMT
< Content-Type: application/json
< Content-Length: 0
< 
* Connection #0 to host localhost left intact
```

### Delete a reclamation
Request:
```
```
curl -v -X DELETE http://localhost:5000/reclamations/test
Response:
```
*   Trying ::1...
* Connected to localhost (::1) port 5000 (#0)
> DELETE /reclamations/test HTTP/1.1
> Host: localhost:5000
> User-Agent: curl/7.43.0
> Accept: */*
> 
< HTTP/1.1 204 No Content
< Server: akka-http/2.3.12
< Date: Sun, 07 Feb 2016 11:20:30 GMT
< Content-Type: application/json
< 
* Connection #0 to host localhost left intact
```
