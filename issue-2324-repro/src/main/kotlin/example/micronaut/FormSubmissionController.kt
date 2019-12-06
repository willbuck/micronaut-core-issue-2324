package example.micronaut

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.hateoas.JsonError
import io.reactivex.Single

@Controller("/forms")
open class FormSubmissionController(private val formValidatorServiceClient: FormValidatorServiceClient) : FormSubmissionOperations {

    override fun validate(submission: Single<FormSubmission>): Single<HttpResponse<ValidationResponseValid>> {
        return formValidatorServiceClient.validate(submission).map {
            HttpResponse.ok(it.body()!!)
        }
    }

    @Error
    fun formSubmissionException(request: HttpRequest<*>, exception: HttpClientResponseException): Single<HttpResponse<JsonError>> {
        println(exception.response.body())
        return Single.just(
                HttpResponse.status<JsonError>(HttpStatus.BAD_REQUEST, "Form Validation Failure")
                        .body(JsonError(exception.response.body().toString()))
        )
    }
}