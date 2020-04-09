consul-api
==========

[![Build Status](https://api.travis-ci.org/Ecwid/consul-api.svg)](http://travis-ci.org/Ecwid/consul-api)

Java client for Consul HTTP API (http://consul.io)

Supports all API endpoints (http://www.consul.io/docs/agent/http.html), all consistency modes and parameters (tags, datacenters etc.)

## How to use
```java
ConsulClient client = new ConsulClient("localhost");

// set KV
byte[] binaryData = new byte[] {1,2,3,4,5,6,7};
client.setKVBinaryValue("someKey", binaryData);

client.setKVValue("com.my.app.foo", "foo");
client.setKVValue("com.my.app.bar", "bar");
client.setKVValue("com.your.app.foo", "hello");
client.setKVValue("com.your.app.bar", "world");

// get single KV for key
Response<GetValue> keyValueResponse = client.getKVValue("com.my.app.foo");
System.out.println(keyValueResponse.getValue().getKey() + ": " + keyValueResponse.getValue().getDecodedValue()); // prints "com.my.app.foo: foo"

// get list of KVs for key prefix (recursive)
Response<List<GetValue>> keyValuesResponse = client.getKVValues("com.my");
keyValuesResponse.getValue().forEach(value -> System.out.println(value.getKey() + ": " + value.getDecodedValue())); // prints "com.my.app.foo: foo" and "com.my.app.bar: bar"

//list known datacenters
Response<List<String>> response = client.getCatalogDatacenters();
System.out.println("Datacenters: " + response.getValue());

// register new service
NewService newService = new NewService();
newService.setId("myapp_01");
newService.setName("myapp");
newService.setTags(Arrays.asList("EU-West", "EU-East"));
newService.setPort(8080);
client.agentServiceRegister(newService);

// register new service with associated health check
NewService newService = new NewService();
newService.setId("myapp_02");
newService.setTags(Collections.singletonList("EU-East"));
newService.setName("myapp");
newService.setPort(8080);

NewService.Check serviceCheck = new NewService.Check();
serviceCheck.setScript("/usr/bin/some-check-script");
serviceCheck.setInterval("10s");
newService.setCheck(serviceCheck);

client.agentServiceRegister(newService);

// query for healthy services based on name (returns myapp_01 and myapp_02 if healthy)
HealthServicesRequest request = HealthServicesRequest.newBuilder()
					.setPassing(true)
					.setQueryParams(QueryParams.DEFAULT)
					.build();
Response<List<HealthService>> healthyServices = client.getHealthServices("myapp", request);

// query for healthy services based on name and tag (returns myapp_01 if healthy)
HealthServicesRequest request = HealthServicesRequest.newBuilder()
					.setTag("EU-West")
					.setPassing(true)
					.setQueryParams(QueryParams.DEFAULT)
					.build();
Response<List<HealthService>> healthyServices = client.getHealthServices("myapp", request);
```

## How to add consul-api into your project
### Gradle
```
compile "com.ecwid.consul:consul-api:1.4.5"
```
### Maven
```
<dependency>
  <groupId>com.ecwid.consul</groupId>
  <artifactId>consul-api</artifactId>
  <version>1.4.5</version>
</dependency>
```

## How to build from sources
* Checkout the sources
* ./gradlew build

Gradle will compile sources, package classes (sources and javadocs too) into jars and run all tests. The build results will located in build/libs/ folder
