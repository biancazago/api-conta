# api-conta

Antes de executar o backend, execute o seguinte comando:
```` Java
    $ mvn clean install
```` 

Configurações docker-compose estão disponíveis na pasta raiz do projeto.

Para iniciar/criar o banco de dados da aplicação em um contêiner docker, execute:

     docker-compose up -d

Para interrompê-lo e remover o contêiner, execute:

     docker-compose down
     

## Usuário 
Nome: Campo obrigatorio
CpfCnpj: Campo obrigatorio, validação de cpf e cpnj válido
Senha: Campo obrigatorio
Email: Campo obrigatorio

No cadastro de usuario é feito uma validação caso já existe um usuário cadastrado com email ou CPF/CNPJ é lançada uma exceção 
Ao salvar usuário é emitido uma evento para criar conta.
Utilização de PasswordEncoder para criptografar senha de usuário.

## Conta
valor: Campo obrigatorio

Usuário poderá adicionar valor na conta. O valor será somado com o saldo já existente na conta do usuário.

## Transferencia
idUsuarioRemente: Campo obrigatorio
idUsuarioDestinatario: Campo obrigatorio
valor: Campo obrigatorio

Para realizar a transferencia entre dois usuários é necessário o usuário remetente e destinatario existir.
Usuário remetente precisa ser um pessoa física.
Usuario remetente precisa ter um saldo igual ou superior, ao valor enviado.  
É necessário autorização vinda de uma api externa para concluir a transação.

### Exceções
Usuário remetente não existe e/ou usuário ser lojista: "Usuario Remetente não encontrado"
Usuário destinatario não existe: "Usuario destinatário não encontrado"
Usuário remetente não possui saldo suficiente: "Usuário não possui valor para transferência"
Api exerterna não autorizar: "Transferencia não autorizada"

 
## Arquitetura
 
 ### Tecnologias usadas
 Apache Maven
 Java 11
 Spring Boot
 Spring Data JPA
 Liquibase
 Banco H2 e MySQL
 Feign
 MapStruct
 Docker

 ### Estrutura do Projeto

  ![alt text](/imagens/estrutura_projeto.png)

 ### Modelagem do banco 

 ![alt text](/imagens/modelagem_banco.png)

 
## Exemplos de requisições

 ![alt text](/imagens/requisicao.png)


- Incluir usuário - /api/usuario  (POST)
    
    - /api/usuario  
```` java 
Envio
     {
        "nome":"Elias Julio da Conceição",
        "senha":"123",
        "cpfCnpj":"037.085.979-07",
        "email":"elias@gmail.com"
     }
       ou
     {
        "nome":"Maria Festas e Decorações",
        "senha":"123",
        "cpfCnpj":"91.547.027/0001-54",
        "email":"mariafestas@gmail.com"
     } 

```` 
- Incluir saldo na conta do Usuário - /api/conta/{id} (PATCH) 
    - /api/conta/1 
```` java 
Envio
    {
        "valor":"200"
    }
```` 
- Visualizar conta do Usuário - /api/conta/{id} (GET) 
    - /api/conta/1 
    
```` java 
Retorno
    {
      "id": 1,
      "idUsuario": 1,
      "nomeUsuario": "Elias Julio da Conceição",
      "valor": 150.0
    }
```` 
- Transferir - api/transferir (PUT)
    - /api/transferir
```` java
Envio 
    {
        "idUsuarioRemetente":"1",
        "idUsuarioDestinatario":"2",
        "valor":"50"
    }
Retorno
    {
        "message": "Enviado"
    }
```` 
   
## Futuras implementações
- Criar um histórico de transferência

## Proposta de melhoria na arquitetura
- Dividir em microserviços, parte de cadastro de usuário em um microserviço separado, para que futuramente possa ser consumidos 
por outras api e/ou microserviços.

