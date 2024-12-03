
1. unzip file

2. go the root folder of project

3. run (ensure you have java 17 installed)
    mvn generate-source
    mvn install

4. run to build (ensure you have internet access and docker installed on the same directory as Dockerfile)
    docker build -t KCB_TEST:1.0

5. run to deploy with docker compose file
    docker compose up -d

6. send request to GET http://localhost:8080/projects