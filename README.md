consul-api
==========

[![Build Status](https://api.travis-ci.org/Ecwid/consul-api.svg)](http://travis-ci.org/Ecwid/consul-api)

Java client for Consul HTTP API (http://consul.io)

Supports all API endpoints (http://www.consul.io/docs/agent/http.html), all consistency modes and parameters (tags, datacenters etc.)

## How to use
```java
ConsulClient client = new ConsulClient("localhost");

// register new service
NewService newService = new NewService();
newService.setId("myapp_01");
newService.setName("myapp");
newService.setPort(8080);
client.agentServiceRegister(newService);

// KV
byte[] binaryData = new byte[] {1,2,3,4,5,6,7};
client.setKVBinaryValue("someKey", binaryData);

//list known datacenters
Response<List<String>> response = client.getCatalogDatacenters();
System.out.println("Datacenters: " + response.getValue());

```

## How to build
* Checkout the sources
* ./gradlew build

Gradle will compile sources, package classes (sources and javadocs too) into jars and run all tests. The build results will located in build/libs/ folder

## Jars
Guys, right now I don't have enough time to upload jars into [Maven Central] (http://maven.apache.org/guides/mini/guide-central-repository-upload.html), but I update jars in our Ecwid Nexus repository every notable commit. You can add our repository in your Gradle build very easy
```
repositories {
	mavenCental()
	maven {
		url "http://nexus.ecwid.com/content/groups/public"
	}
}
```
and use consul-api
```
compile "com.ecwid.consul:consul-api:1.1.0"
```
