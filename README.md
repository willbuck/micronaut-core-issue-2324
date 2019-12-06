Steps to correct the desired behavior raised by issue-2324 of micronaut-core:

1. Run the `issue-2324-repro` app contained here with `./gradlew run`
3. Issue a POST request to `localhost:8080/forms/validate` with a json body including "name" and "age" (see curl example below)

    ```
    curl -X POST \
    http://localhost:8080/forms/validate \
    -H 'Content-Type: application/json' \
    -H 'Postman-Token: ced7ec68-410f-45fa-86bb-7aef9572f6f2' \
    -H 'cache-control: no-cache' \
    -d '{
        "name": "Tony Stark",
        "age": 35
    }'
    ```

4. Observe a success, utilizing `exception.response.getBody(JsonError::class.java)