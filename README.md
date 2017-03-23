Moip integration SDK for spring-boot applications
---
Module to integrate [Moip](https://moip.com.br/) SDK on spring boot apps

Adding Project Lombok Agent
---

This project uses [Project Lombok](http://projectlombok.org/features/index.html)
to generate getters and setters etc. Compiling from the command line this
shouldn't cause any problems, but in an IDE you need to add an agent
to the JVM. Full instructions can be found in the Lombok website. The
sign that you need to do this is a lot of compiler errors to do with
missing methods and fields.

Usage
----
This project is available on maven central

Add the dependency on pom.xml
```
<dependency>
    <groupId>com.marcosbarbero.boot</groupId>
    <artifactId>spring-boot-moip-integration</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```

Sample configuration for Basic Authentication

```
moip:
  health-indicator-enabled: true # Default false
  security:
    basic:
      token: # Basic auth token
      key: # Basic auth key
  environment: production #default value sandbox
```

Sample configuration for OAuth Authentication

```
moip:
  health-indicator-enabled: true # Default false
  security:
    oauth:
      access-token: # AccessToken provided by moip
  environment: production #default value sandbox
```

>**Note 1**: All the security keys are provided by  [Moip](https://moip.com.br/).  
>**Note 2**: The available values for `moip.environment` property are `production` or `sandbox` having the last as default value.  

Health Check testing
---
In order to enabled heal check for Moip services will be needed to turn this flag on:

``` 
moip.health-indicator-enabled: true #default false
```

Also there's a need to add the actuator dependency on the project:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

###### In the code
```
@Autowired
private br.com.moip.API api;
```

Contributing
---

Spring Boot Moip Integration is released under the non-restrictive Apache 2.0 license,
and follows a very standard Github development process, using Github tracker for issues
and merging pull requests into master. If you want to contribute even something trivial
please do not hesitate, but follow the guidelines below.

### Code of Conduct

This project adheres to the Contributor Covenant [code of conduct](https://github.com/marcosbarbero/spring-boot-moip-integration/blob/master/docs/code-of-conduct.adoc).
By participating, you are expected to uphold this code. Please report unacceptable behavior to marcos.hgb@gmail.com.

Footnote
---
Any doubt open an [issue](https://github.com/marcosbarbero/spring-boot-moip-integration/issues).
Any fix send me a [Pull Request](https://github.com/marcosbarbero/spring-boot-moip-integration/pulls).
