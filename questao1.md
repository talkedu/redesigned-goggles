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
* Inserir/Atualizar campanha
    * POST/PUT http://localhost:8080/campanha
    * A ausência do id determinará que é uma inserção
        ```
        {	
        	"idTimeDoCoracao": 1,
        	"dataInicial": "2017-06-08", 
        	"dataFinal": "2017-06-15"
        }
        ```
    * Após inserir/atualizar (e se for o caso atualizar registros que cuja a data final era a igual ao último registro), retornar uma lista de registros alterados no caminho
    
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

License
----

MIT

   [Java 8]: <https://www.java.com/>
   [Maven]: <https://maven.apache.org/>
   [Spring Boot]: <https://projects.spring.io/spring-boot/>
   [Jersey]: <https://jersey.github.io/>
   [Hibernate]: <http://hibernate.org/>
   [HSQLDB]: <http://hsqldb.org/>
   
