# Workshop Spring Boot MongoDB DevSuperior

* MongoDB 7.0.9 | Docker 24.0 | Mongosh 2.2.5 | MongoDB Compass 1.43.0


### Modelo conceitual desenvolvido no workhsop DevSuperior MongoDB.

https://docs.google.com/document/d/1CCWaBMSpMtQtJSLDAukO5GhBwZgm2MY3pify79Gl_MU/edit?usp=sharing

projetos devsuperior - https://github.com/devsuperior/nosql

### Container Docker do MongoDB para desenvolvimento
```
docker run -d -p 27017:27017 -v /data/db --name mongo1 mongo:4.4.3-bionic
```
```
docker exec -it mongo1 bash
```

### Container DockerCompose do MongoDB.

#### docker-compose.yml
```java
version: '3.9'

services:
  mongo:
    image: mongo:latest
    container_name: mongodb
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: rootpassword
    volumes:
      - mongo-data:/data/db

volumes:
  mongo-data:

```

### Acesso o shell do mongoDB

Interact with the database through the bash shell client
```java
docker exec -it containername bash
```
Launch the MongoDB shell client
```java
mongosh #now it is mongosh to access shell
```


### Postman collection

(baixe o arquivo DSPosts.postman_collection.json que está aqui na pasta do projeto, e importe-o no seu Postman)


### Arquivo de configuração no Spring boot 3.2.1 - application-test.properties

```js
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.username=root
spring.data.mongodb.password=rootpassword
spring.data.mongodb.database=workshop_mongodb
```

### Trechos de código

StandardError

```java
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
```
ResourceExceptionHandler
```java
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError();
		error.setError("Not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		error.setStatus(status.value());
		error.setTimestamp(Instant.now());
	
		return ResponseEntity.status(status).body(error);
	}
}
```

Seed

```java
userRepository.deleteAll();
postRepository.deleteAll();

User maria = new User(null, "Maria Brown", "maria@gmail.com");
User alex = new User(null, "Alex Green", "alex@gmail.com");
User bob = new User(null, "Bob Grey", "bob@gmail.com");

userRepository.saveAll(Arrays.asList(maria, alex, bob));

Post post1 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(maria));
Post post2 = new Post(null, Instant.parse("2021-02-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(maria));

Comment c1 = new Comment("Boa viagem mano!", Instant.parse("2021-02-13T14:30:01Z"), new Author(alex));
Comment c2 = new Comment("Aproveite", Instant.parse("2021-02-13T15:38:05Z"), new Author(bob));
Comment c3 = new Comment("Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(alex));

post1.getComments().addAll(Arrays.asList(c1, c2));
post2.getComments().addAll(Arrays.asList(c3));

postRepository.saveAll(Arrays.asList(post1, post2));

maria.getPosts().addAll(Arrays.asList(post1, post2));
userRepository.save(maria);		
```

Consulta detalhada

```json
{ 
	$and: [ 
		{ 
			moment: {
				$gte: ?1
			} 
		}, 
		{ 	
			moment: { 
				$lte: ?2} 
		} , 
		{ 	
			$or: [ 
				{ 
					'title': { 
						$regex: ?0, 
						$options: 'i' 
					} 
				}, 
				{ 
					'body': { 
						$regex: ?0, 
						$options: 'i' 
					} 
				}, 
				{ 
					'comments.text': { 
						$regex: ?0, $options: 'i' 
					} 
				} 
			] 
		} 
	] 
}
```

## Recursos adicionais

### Documentação MongoDB: query operators

https://docs.mongodb.com/manual/reference/operator/query

### Documentação Spring Data MongoDB

https://docs.spring.io/spring-data/mongodb/docs/current/reference/html

### Localização dos volumes Docker no Windows

https://stackoverflow.com/questions/43181654/locating-data-volumes-in-docker-desktop-windows

### 

```js 
@Document(collection = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String email;

    @DBRef(lazy = true)  
    public List<Post> posts = new ArrayList<>();

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

```
#### 
A anotação @Document é usada no Spring Data MongoDB para indicar que uma classe é um documento MongoDB.

A anotação @DBRef no Spring Data MongoDB é usada para criar referências entre documentos em diferentes coleções. O atributo lazy dessa anotação especifica se os documentos referenciados devem ser buscados de forma imediata (eager) ou preguiçosa (lazy).

* Carregamento Imediato (lazy = false): Quando lazy é definido como false (que é o padrão), os documentos referenciados são carregados imediatamente com o documento principal. Isso significa que sempre que você carrega um documento que contém um campo @DBRef, os documentos referenciados também serão carregados ao mesmo tempo.

* Carregamento Preguiçoso (lazy = true): Quando lazy é definido como true, os documentos referenciados são carregados apenas quando são acessados pela primeira vez. Isso pode melhorar o desempenho ao reduzir a quantidade de dados buscados no banco de dados quando não são necessários imediatamente.

### Introdução NoSQL

[![Image](https://img.youtube.com/vi/c6h5eR0TvfU/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/c6h5eR0TvfU)

### Vídeo sobre tratamento de exceções

[![Image](https://img.youtube.com/vi/MAv7xgnSD-s/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/MAv7xgnSD-s)
