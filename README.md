# User-API (Spring boot example template project) ![CI](https://github.com/mariocandela/user-api/actions/workflows/CI.yml/badge.svg) ![Docker](https://github.com/mariocandela/user-api/actions/workflows/docker-image.yml/badge.svg)

[![Schermata-2022-07-10-alle-13-37-23.png](https://i.postimg.cc/zf80v4HX/Schermata-2022-07-10-alle-13-37-23.png)](https://i.postimg.cc/zf80v4HX/Schermata-2022-07-10-alle-13-37-23.png)

Example Template: Java Spring, OpenAPI, MongoDB, Docker, Full local dev environment 🚀

## Quick Start

Using [`docker-compose`](https://docs.docker.com/compose/)

```bash
$ docker-compose build
$ docker-compose up -d
 ```

## Services

User API: http://localhost:8080/

Mongo Express UI: http://localhost:8888/

Mongodb: localhost:27017

Unit Test:

```bash
$ mvn test
 ```

## How to invoke the Rest APIs?

Import the file ```swagger/openapi.yaml``` in Postman

## Features

- OpenApi CodeGen integration Spec 3.0
- Docker compose for full local dev environment(Java Spring, Mongo DB, Mongo Express)
- Java Spring Container Docker with multistage build
- Example BDD Unit Test
- Continuous Integration Pipeline (Build Docker Image/Build/Unit Test on new Pull Request) on Github Actions

### Example Open API User

###### swagger/openapi.yaml

```yaml
openapi: 3.0.0
info:
  version: 1.0.0
  title: User API
  description: Example Java Spring Microservice with OpenAPI and MongoDB

servers:
  - url: http://localhost:8080/

paths:
  /users:
    get:
      description: Returns a list of users
      operationId: findUser
      parameters:
        - name: name
          in: query
          schema:
            type: string
        - name: surname
          in: query
          schema:
            type: string
      responses:
        '200':
          description: Successfully returned a list of users
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/User'
        '400':
          description: Invalid request
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'

    post:
      description: Create a new User
      operationId: createUser
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/User'
      responses:
        '201':
          description: Successfully created a new user
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/User'
        '422':
          description: Invalid request
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'

  /users/{email}:
    get:
      description: Get User by key (email)
      operationId: getUser
      parameters:
        - name: email
          in: path
          required: true
          schema:
            type: string

      responses:
        '200':
          description: Successfully returned an user
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/User'

        '400':
          description: Invalid request
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'
        '404':
          description: Not found User
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'
    put:
      description: Update User
      operationId: updateUser
      parameters:
        - name: email
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/User'

      responses:
        '200':
          description: Successfully updated an user
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/User'

        '400':
          description: Invalid request
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'
        '404':
          description: Not found User
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'
    delete:
      description: Delete User by Key
      operationId: deleteUser
      parameters:
        - name: email
          in: path
          required: true
          schema:
            type: string

      responses:
        '200':
          description: Successfully deleted an user

        '400':
          description: Invalid request
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'
        '404':
          description: Not found User
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'

  /etl/import-csv:
    post:
      description: ETL Import Users by CSV file, example CSV line name#surname#email#address
      operationId: importCSVUsers
      requestBody:
        content:
          application/csv:
            schema:
              type: string
              format: base64
      responses:
        '201':
          description: Successfully imported CSV Users
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/User'

        '400':
          description: Invalid request
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserError'
components:
  schemas:
    User:
      type: object
      required:
        - name
        - surname
        - email
        - address
      properties:
        name:
          type: string
          minLength: 1
        surname:
          type: string
          minLength: 1
        email:
          type: string
          format: email
          minLength: 1
        address:
          type: string
          minLength: 1
    UserError:
      type: object
      required:
        - message
      properties:
        message:
          type: string
 ```

## Documentation

- [API Docs](https://) #TODO

## Contributing

Welcomes contributions and project participation! There's a bunch of things you can do if you want to contribute! The [Contributor Guide](CONTRIBUTING.md) has all the information you need for everything from reporting bugs to contributing entire new features. Please don't hesitate to jump in if you'd like to, or even ask us questions if something isn't clear.

All participants and maintainers in this project are expected to follow [Code of Conduct](CODE_OF_CONDUCT.md), and just generally be excellent to each other.

Happy hacking!

## License

This project is licensed under [GNU GPL 3 License](LICENSE).
