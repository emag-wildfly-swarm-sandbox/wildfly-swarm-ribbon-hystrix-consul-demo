# wildfly-swarm-ribbon-hystrix-consul-demo

## Usage

### Run Consul

``` sh
cd $CONSUL_HOME
./consul agent --ui-dir=/path/to/consul_x.y.z_web_ui/ --data-dir=/tmp/consul -bind 127.0.0.1 -server -bootstrap
```

### Build Apps

``` sh
$ ./mvnw clean package
```

### Run time services

``` sh
$ java -Dswarm.port.offset=100 -jar time/target/time-swarm.jar
```

``` sh
$ java -Dswarm.port.offset=200 -jar time/target/time-swarm.jar
```

### Run events service

``` sh
$ java -jar events/target/events-swarm.jar
```

### Check Consul UI

Access to http://localhost:8500

### Accuess events service

``` sh
$ curl localhost:8080 
```

And try to shutdown two time services and then events service should be fallback.