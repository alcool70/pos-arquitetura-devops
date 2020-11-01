# Imagens Sonar e Jenkins

## SONAR

[https://hub.docker.com/r/rustnnes/sonar](https://hub.docker.com/r/rustnnes/sonar)

- Necessário configurar o endereço do Sonar no POM.xml no repo
- Acesso a UI: user e password `admin`

```bash
host$ docker run -it -p9000:9000 rustnnes/sonar:v3

container$ sudo su sonar
container$ service sonar start
```

## JENKINS

[https://hub.docker.com/r/rustnnes/jenkins](https://hub.docker.com/r/rustnnes/jenkins)

- Acesso a UI: user e password: `fujioka`

```bash
host$ docker run -it -p8080:8080 -p50000:50000 rustnnes/jenkins:v3
container$ service jenkins start   
```
