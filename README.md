# Documentação projeto 

Tecnologias utilzadas: 
* Java  21.0.6 
* Node 18.0.5 
* React 19.1.1 
* Postgres 17  

## Demonstração do funcionamento do projeto 
  Para ver o vídeo da demonstração do funciomanento do projeto [clique aqui](https://youtu.be/-LthTbAEF54)
## Rodando o backend com o docker 
    Abra a pasta /Backend/projetct_marktplace no terminal e digite o seguinte comando: 
        * docker-compose up --build
        * Abra o container do postgres e rode os seguintes comnados:  
        
            * Opção 1 do Windows:  Get-Content "C:\Users\SEU_USUARIO_COMPUTADOR\Documents\Projeto_Zeine\Backend\projetct_marktplace\src\main\resources\schema.sql" | docker exec -i postgres_db psql -U euler -d marketplace
            * No Linux e opção 2 do Windows:  
                    * docker exec -it postgres_db bash 
                    * psql -U euler -d marketplace -f /caminho/schema.sql 
         Agora a abra a pasta /Front-end/app e digite o comando no terminal: **npm start**
 ## Rodando Backend sem o docker
  Antes de rodar o projeto na sua máquina, observe se essas tecnologias existem na sua máquina. Quando todas as tecnologias estiverem baixadas, crie o banco de dados **Marktplace**  
  no Postgres da sua máquina. 

  Agora para executar o backend, abra a pasta /Backend/projetct_marktplace no terminal e digite o seguinte comando: 

          No Widows:  **.\gradlew bootRun** 

          No Linux:  **.\gradlew bootRun**
  
  Agora a abra a pasta /Front-end/app e digite o comando no terminal: **npm start**
