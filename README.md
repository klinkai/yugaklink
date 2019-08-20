#YugaKlink
 
Aplicação Exemplo para o Meetup https://www.meetup.com/pt-BR/SouJavaCampinas/events/263802442/

##SpringBoot + Yugabyte's Relational and NoSQL Exemple Application

Exemplo de Aplicação utilizando SpringBoot + Kotlin acessando tanto o Yugabyte Relacional como o Não Relactional (Cassandra).

### Para subir o Yugabyte Localmente em Distros baseadas em Debian

1 - Instale e crie o cluster:
``` 
wget https://downloads.yugabyte.com/yugabyte-1.3.1.0-linux.tar.gz
tar xvfz yugabyte-1.3.1.0-linux.tar.gz && cd yugabyte-1.3.1.0/
./bin/post_install.sh
./bin/yb-ctl create
``` 

2 - Acesse o WebUI em http://127.0.0.1:7000/ para verificar e checar o nó rodando, detalhes, logs, etc.

3 - Conecte no banco relacional usando o ./bin/psql e crie a base de dados yugaklink:

```
CREATE DATABASE yugaklink;
```

4 - Conecte no banco não relacional usando o ./bin/cqlsh e crie o KeySpace yugaklink:

```
CREATE KEYSPACE yugaklink;
```

5 - Crie a tabela AppUser com Transaction habilitado pois utilizamos um indice secundário que necessitada de transações no Yugabyte e o SpringBoot ainda não tem suporte ao dialeto especifico do Yugabyte. (Nas próximas versões o Yugabyte habilitará a transsação automaticamente ao criar indices secundários).

```
CREATE TABLE yugaklink.appuser (
    tenant text,
    username text,
    attribute int,
    email text,
    lastlogin timestamp,
    name text,
    password text,
    randonsalt text,
    recoverpasswordtoken text,
    recoverpasswordtokendue timestamp,
    roles list<text>,
    status text,
    PRIMARY KEY (tenant, username)
) WITH CLUSTERING ORDER BY (username ASC)
    AND default_time_to_live = 0
    AND transactions = {'enabled': 'true'};
```

6 - Rode a aplicação, que criará as Tabelas na base relacional caso não existam.

```
mvn spring-boot:run
```

Slides: https://s3.wasabisys.com/klinkpublic/palestra_yugabyte_yugaklink.pdf


OBS: É um exemplo simples que não contempla funcionalidades interessantes como suporte a transações e JSON no CQL.

Sobre a Klink: http://klink.ai/files/apresentacao_klink_v2.pdf