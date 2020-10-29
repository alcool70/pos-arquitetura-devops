# UNIPE

<img src="https://raw.githubusercontent.com/rodrigofujioka/poo/master/resources/javaspion.png" align="right" width="150px" />

## Pós-graduação em Engenharia e Qualidade de Software

### Arquitetura de Software e DevOps 

Fork do repositório [rodrigofujioka/posengsoft](https://github.com/rodrigofujioka/posengsoft) para práticas da pós-graduação de Engenharia e Qualidade de Software.

#### Equipe Álcool70
- [Artur Araújo](https://github.com/arturaraujo)
- [Daniel Menezes](https://github.com/dsmenezes)
- [Deam Gaudêncio](https://github.com/deamgaudencioramos)
- [Diego Bandeira](https://github.com/rustnnes)
- [Igor Mendes](https://github.com/igormendes)
- [Victor Demétrio](https://github.com/victordemetrio)

#### Tecnologias usadas:
- Maven
- [JDK 11](https://jdk.java.net/java-se-ri/11)
- [Spring Tools](https://spring.io/tools)  
    - Spring Boot
    - Spring Data
- SonarQube
- JaCoCo
- IDE: [Intellij Idea](https://www.jetbrains.com/idea/) 

#### Entregas  

- *Swagger*: < link > 
- *Mensageria*: < link > 
- *Docker*: < link > 
- *Sonar/Jenkins*: < link >
- *DAS*: Este documento
- *TDD_CLEAN_CODE*: Código corrigido, conforme sugestões do SONAR; cobertura
 de código por testes unitários atingido:   

### Preparação do ambiente

Baseado nas imagens docker disponibilizadas ([rodrigofujioka/jenkins_sonar_postgres](https://hub.docker.com/r/rodrigofujioka/jenkins_sonar_postgres) e [rodrigofujioka/jenkins](https://hub.docker.com/r/rodrigofujioka/jenkins)), foram criadas imagens com as configurações necessárias
 já salvas, de forma a agilizar o ambiente de CI. 

#### SONAR
- Necessário configurar o endereço do Sonar no POM.xml no repo
- Acesso a UI: user e password `admin`

```bash
host$ docker run -it -p9000:9000 rustnnes/sonar:v3

container$ sudo su sonar
container$ service sonar start
```

#### JENKINS

- Acesso a UI: user e password: `fujioka`

```bash
host$ docker run -it -p8080:8080 -p50000:50000 rustnnes/jenkins:v3
container$ service jenkins start   
```
