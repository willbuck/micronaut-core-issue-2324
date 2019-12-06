package example.micronaut

import com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Post
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
        return Single.just(
                HttpResponse.status<JsonError>(HttpStatus.BAD_REQUEST, "Form Validation Failure")
                        .body(exception.response.getBody(JsonError::class.java).orElse(null))
        )
    }
}