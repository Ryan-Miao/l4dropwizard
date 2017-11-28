## Learning for dropwizard

The system building could be found @ [My Blog](http://blog.rmiao.top/categories/Dropwiard/)

### 1. Running in command
You could
```ssh
java -jar target\l4dropwizard-1.0-SNAPSHOT.jar server target/classes/config/dev.yml

```
Or
```cmd
mvn package
mvn exec:java
```

### 2. Running in IntelliJ

Setup a new Application configuration with the following settings:
 
VM options:

```
-Dapplication.name=l4dropwizard -Dapplication.home=. -Dapplication.environment=dev
```

Program arguments:

```
server src/main/resources/config/dev.yml
```
 
Main class:

```
com.test.HelloWorldApplication
```


### 2. Useful link
```ssh
http://localhost:8081/healthcheck
http://localhost:8081/metrics
http://localhost:8080/hello-world?name=Successful+Dropwizard+User
```


### From
- [dropwizard](http://www.dropwizard.io/1.0.6/docs/getting-started.html#gs-healthcheck)
- [github](https://github.com/dropwizard/dropwizard)