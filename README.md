consul-api
==========

[![Build Status](https://api.travis-ci.org/Ecwid/consul-api.svg)](http://travis-ci.org/Ecwid/consul-api)

Java client for Consul HTTP API (http://consul.io)

Supports all API endpoints (http://www.consul.io/docs/agent/http.html), all consistency modes and parameters (tags, datacenters etc.)

## How to use
```java
ConsulClient client = new ConsulClient("localhost");

// KV
byte[] binaryData = new byte[] {1,2,3,4,5,6,7};
client.setKVBinaryValue("someKey", binaryData);

//list known datacenters
Response<List<String>> response = client.getCatalogDatacenters();
System.out.println("Datacenters: " + response.getValue());

// register new service
NewService newService = new NewService();
newService.setId("myapp_01");
newService.setName("myapp");
newService.setPort(8080);
client.agentServiceRegister(newService);

// register new service with associated health check
NewService newService = new NewService();
newService.setId("myapp_01");
newService.setName("myapp");
newService.setPort(8080);

NewService.Check serviceCheck = new NewService.Check();
serviceCheck.setScript("/usr/bin/some-check-script");
serviceCheck.setInterval("10s");
newService.setCheck(serviceCheck);

client.agentServiceRegister(newService);

```

## How to add consul-api into your project
### Gradle
```
compile "com.ecwid.consul:consul-api:1.1.11"
```
### Maven
```
<dependency>
  <groupId>com.ecwid.consul</groupId>
  <artifactId>consul-api</artifactId>
  <version>1.1.11</version>
</dependency>
```


## How to build from sources
* Checkout the sources
* ./gradlew build

Gradle will compile sources, package classes (sources and javadocs too) into jars and run all tests. The build results will located in build/libs/ folder
