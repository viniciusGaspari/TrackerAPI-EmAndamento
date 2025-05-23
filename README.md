# TrackerAPI 🚀

## Tecnologias Utilizadas
O projeto usa diversas tecnologias modernas para garantir um ambiente eficiente e seguro:

<div>
  <h3>Principais Tecnologias</h3>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" width="100" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/git/git-plain.svg" width="100" />       
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" width="100" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original.svg" width="100" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg" width="100" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original.svg" width="100" />
</div>

---

## Sobre o Projeto
Olá, dev! 🚀 Este é meu primeiro projeto utilizando os conhecimentos adquiridos no curso **Spring Boot Expert** da Udemy, ministrado pelo professor **Dougglas Sousa**.

O **TrackerAPI** foca na **segurança dos dados**, garantindo que apenas usuários autenticados via **Spring Security** possam visualizar, editar, salvar e buscar suas **próprias entidades**.  
Ações sobre entidades de outros usuários não são permitidas, assegurando **controle e privacidade**.

### 🔒 Tratamento de Erros e Segurança
- Em caso de **acesso negado**, a API retorna uma exceção informando que a conta autenticada **não tem permissão** para acessar determinada entidade.
- Para **proteger informações**, a resposta pode indicar que a entidade "não existe no banco", evitando revelar sua existência real e mantendo a privacidade dos dados de outros usuários.

---

## 🎯 Planos de Aprendizado e Evolução

A TrackerAPI está em constante aprimoramento, refletindo minha jornada de aprendizado e evolução como desenvolvedor.  
Atualmente, estou estudando e planejando integrar **novas tecnologias e conceitos** para tornar o projeto mais robusto e escalável.

### 📌 Tecnologias e Conceitos em Estudo:
- **Mensageria**: Implementação de **RabbitMQ** ou **Kafka** para comunicação eficiente entre serviços.
- **Spring Security**: Aprimoramento da autenticação e autorização, adicionando **OAuth 2.0** e técnicas avançadas de segurança.
- **ReactJS**: Desenvolvimento de uma interface moderna e interativa para melhorar a experiência dos usuários da API.
- **Microserviços**: Refatoração do projeto para uma **arquitetura distribuída**, garantindo melhor escalabilidade e organização.
- **Cloud**: Integração com plataformas de cloud como **AWS**, **Azure** ou **Google Cloud** para **deploy automatizado** e alta disponibilidade.

### 🔄 Evolução Contínua
Cada novo aprendizado será incorporado à API, trazendo **mais segurança, desempenho e escalabilidade** para o projeto.  
Fique atento às atualizações! 🚀

---

## 📌 Recomendações
O projeto utiliza **Docker Compose** para facilitar a execução de containers, incluindo a configuração automática do banco de dados.  
Isso **requer o Docker Compose instalado na IDE**.

Se estiver usando o **IntelliJ IDEA**, há um **plugin oficial do Docker** disponível na seção de Plugins.

---

## ⚙ Instalação e Configuração do Docker Compose no IntelliJ IDEA

### ✔ Pré-requisitos
Certifique-se de que:
- O **Docker** está instalado e funcionando corretamente.
- O **IntelliJ IDEA** possui o plugin **Docker** ativado.

### 🛠 Passo 1: Ativar o Plugin do Docker no IntelliJ IDEA
1. Abra o **IntelliJ IDEA**.
2. Vá para **`File` > `Settings`** (Windows/Linux) ou **`IntelliJ IDEA` > `Preferences`** (Mac).
3. Navegue até **`Plugins`**.
4. Pesquise por **"Docker"** e ative o plugin.

### 🐳 Passo 2: Configurar o Docker no IntelliJ IDEA
1. Vá para **`File` > `Settings` > `Build, Execution, Deployment` > `Docker`**.
2. Clique em **`+`** e selecione **Docker**.
3. Escolha o tipo de conexão (**Docker for Windows, Docker for Mac, etc.**).
4. Configure o **caminho do Docker** e clique em **`Apply`**.

### 📦 Passo 3: Configurar Docker Compose no IntelliJ IDEA
1. Vá para **`File` > `Settings` > `Build, Execution, Deployment` > `Docker` > `Docker-Compose`**.
2. Clique em **`+`** e selecione **Docker-Compose**.
3. Adicione o **caminho do arquivo** `docker-compose.yml`.
4. Configure as opções necessárias e clique em **`Apply`**.

### 🚀 Passo 4: Iniciar os Containers
Execute o seguinte comando para iniciar os serviço

1. docker-compose up -d

Não consegui identificar a causa do problema, mas percebi que as tabelas não estão sendo criadas automaticamente ao iniciar o banco de dados.
Para garantir que sejam configuradas corretamente, execute manualmente o seguinte comando no terminal

2. docker exec -it docker-compose-dbtrackerapi-1 psql -U postgres -d trackerapi -f /docker-entrypoint-initdb.d/backup.sql

