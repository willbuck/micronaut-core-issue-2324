Steps to reproduce issue-2324 of micronaut-core:

1. Run both the `issue-2324-repro` and `validate-helper` apps contained here in debug mode with `./gradlew run`
2. Set a breakpoint on line 24 of the `FormSubmissionController`
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

4. Observe the `exception` and `exception.response.body()`. While the message ("Sorry, you did this wrong") returned from the `validate-helper` app the controller calls out to is mapped to the `exception.response.convertedBodies` array, it is not mapped to the body.