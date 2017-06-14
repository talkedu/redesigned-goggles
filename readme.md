# Questão 1 - Campanha

Uma API de CRUD de campanhas.

### Stack

Campanha utiliza as seguintes tecnologias:

* [Java 8] - Poderosa plataforma de desenvolvimento
* [Maven] - Ferramenta de automação de construção de projetos
* [Spring Boot] - Suíte faciltadora de configuração de projetos
* [Jersey] - Framework de desenvolvimento de aplicações RESTFul
* [Hibernate] - Framework de persistência Objeto-Relacional
* [HSQLDB] - SGBD simples para soluções compactas

### Instalação

Instala as dependências e roda o servidor embutido do Springboot

```sh
$ cd campanha
$ ./mvnw install
$ ./mvnw springboot:run
```

# Questão 1 - Campanha

Uma API de CRUD de campanhas.

### Stack

Campanha utiliza as seguintes tecnologias:

* [Java 8] - Poderosa plataforma de desenvolvimento
* [Maven] - Ferramenta de automação de construção de projetos
* [Spring Boot] - Suíte faciltadora de configuração de projetos
* [Jersey] - Framework de desenvolvimento de aplicações RESTFul
* [Hibernate] - Framework de persistência Objeto-Relacional
* [HSQLDB] - SGBD simples para soluções compactas

### Instalação

Instala as dependências e roda o servidor embutido do Springboot

```sh
$ cd campanha
$ ./mvnw install
$ ./mvnw springboot:run
```

## RESTful URLs

### Exemplos de uso das URLs

* Lista das campanhas:
    * GET http://localhost:8080/campanha
    
    * Exemplo de resultado:
    *  ```
        [
            {	
                "id": 1,
            	"idTimeDoCoracao": 1,
            	"dataInicial": "2017-06-08", 
            	"dataFinal": "2017-06-15"
            },
            {
                "id": 2,
                "idTimeDoCoracao": 1,
                "dataInicial": "2017-06-08",
                "dataFinal": "2021-02-18"
            }
        ]
        ```
* Campanha específica:
    * GET http://localhost:8080/campanha/15
    
    * Exemplo de resultado:
    *  ```
        {	
            "id": 15,
        	"idTimeDoCoracao": 15,
        	"dataInicial": "2017-06-08", 
        	"dataFinal": "2017-06-15"
        }
        ```
* Deletar Campanha específica
    * DELETE http://localhost:8080/campanha/15
    
    * Ao encontrar o registro ele será deletado e virá na reposta, caso contrário a resposta virá vazia
    
    * Exemplo de resultado:
     *  ```
        {	
            "id": 15,
        	"idTimeDoCoracao": 15,
        	"dataInicial": "2017-06-08", 
        	"dataFinal": "2017-06-15"
        }
        ```  
* Inserir campanha
    * POST http://localhost:8080/campanha
        ```
        {	
        	"idTimeDoCoracao": 1,
        	"dataInicial": "2017-06-08", 
        	"dataFinal": "2017-06-15"
        }
        ```
    * Após inserir (e se for o caso atualizar registros que cuja a data final era a igual ao registro solicitado), retornar uma lista de registros alterados no caminho
    
    * Exemplo de resultado:
        ```
        [
          {
            "id": 100,
            "idTimeDoCoracao": 1,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-16"
          },
          {
            "id": 99,
            "idTimeDoCoracao": 1,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-17"
          },
          {
            "id": 98,
            "idTimeDoCoracao": 1,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-18"
          }
        ]
        ```
* Atualizar Campanha
    * PUT http://localhost:8080/campanha/1
        ```
          {
            "idTimeDoCoracao": 2,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-16"
          }
         ```
    
    * Retornar o registro atualizado
    
    * Exemplo de resultado:
        ```
          {
            "id": 1,
            "idTimeDoCoracao": 2,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-16"
          }
        ```
    
# Questão 2 - Sócio Torcedor

### Instalação

Instala as dependências e roda o servidor embutido do Springboot

```sh
$ cd sociotorcedor
$ ./mvnw install
$ ./mvnw springboot:run
```

## RESTful URLs

### Exemplos de uso das URLs

* Lista das sociosTorcedores:
    * GET http://localhost:8080/socioTorcedor
    
    * Retorna um array de Sócios Torcedores e o status OK
    
    * Se o Serviço de campanha estiver fora, retornará um array com null no atributo campanha
    
    * Exemplos de resultado:
    *  ```
        [
          {
            "id": 1,
            "email": "e@as.cossm",
            "nomeCompleto": "Beltrano" ,
            "dataNascimento": "1988-06-07",
            "campanhasAssociadas": [
              {
                "id": 1,
                "idTimeDoCoracao": 1,
                "dataInicial": "2017-06-08",
                "dataFinal": "2017-06-17"
              }
            ]
          },
          {
            "id": 2,
            "email": "e@as.cosasm",
            "nomeCompleto": "Fulano",
            "dataNascimento": "2000-01-01",
            "campanhasAssociadas": [],
            "idTimeDoCoracao": 1
          }
        ]
        ```
         ```
        [
            {
                "id": 1,
                "email": "e@as.cossm",
                "nomeCompleto": "Beltrano" ,
                "dataNascimento": "1988-06-07",
                "campanhasAssociadas": [null]
            }
        ] 
        
* Incluir Sócio Torcedor
    * POST http://localhost:8080/socioTorcedor
    
    * Caso já exista, não tenha nenhuma campanha associada, retornará um array de campanhas vigentes do seu time do coração e o status OK.
    
    * Caso já exista, já tenha nenhuma campanha associada, retornará um array vazio e o status OK.
    
    * Caso já exista, não tenha campanha associada, e o serviço de campanhas estiver fora, retornará nada e o status OK
    
    * Caso não exista, retornará um array vazio e o status CREATED
    
    * Exemplo de resultado:
        ```
        [
          {
            "id": 1,
            "idTimeDoCoracao": 1,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-18"
          },
          {
            "id": 2,
            "idTimeDoCoracao": 1,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-17"
          },
          {
            "id": 3,
            "idTimeDoCoracao": 1,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-16"
          },
          {
            "id": 4,
            "idTimeDoCoracao": 1,
            "dataInicial": "2017-06-08",
            "dataFinal": "2017-06-15"
          }
        ]
        ```
* Associar Sócio Torcedor a Campanha
    * PUT http://localhost:8080/socioTorcedor/1/1
    
    * Associar Sócio Torcedor id 1 a campanha id 1
    
    * Retornar o Sócio torcedor com a campanha associada e o status CREATED
    
    * Caso o Sócio torcedor não exista, retornar um Sócio Torcedor com o id requisitado e todos os atributos nulos além do status NOT FOUND
    
    * Caso a campanha não exista, retornar uma Campanha com o id requisitado e todos os atributos nulos além do status NOT FOUND
    
    * Caso a campanha já esteja associada a esse sócio torcedor, retornar o sócio torcedor com a campanha associada e o status OK
    
    * Caso o serviço de campanha estiver fora do ar, retornar o sócio torcedor com array de nulos no campo Campanha e o status OK
    
    * Exemplo de resultado:
        ```
        {
          "id": 1,
          "email": "e@as.cosassm",
          "nomeCompleto": "Fulano de Tal",
          "dataNascimento": "2000-00-00",
          "campanhasAssociadas": [
            {
              "id": 1,
              "idTimeDoCoracao": 1,
              "dataInicial": "2017-06-08",
              "dataFinal": "2017-06-19"
            },
            {
              "id": 2,
              "idTimeDoCoracao": 1,
              "dataInicial": "2017-06-08",
              "dataFinal": "2017-06-18"
            },
            {
              "id": 3,
              "idTimeDoCoracao": 1,
              "dataInicial": "2017-06-08",
              "dataFinal": "2017-06-17"
            }
          ],
          "idTimeDoCoracao": 1
        }
        ```
    
Licença
----

MIT

   [Java 8]: <https://www.java.com/>
   [Maven]: <https://maven.apache.org/>
   [Spring Boot]: <https://projects.spring.io/spring-boot/>
   [Jersey]: <https://jersey.github.io/>
   [Hibernate]: <http://hibernate.org/>
   [HSQLDB]: <http://hsqldb.org/>
