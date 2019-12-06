package example.micronaut

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single

@Controller("/")
open class FormSubmissionHelperController {

    @Post("/validate-helper")
    fun validateHelper(@Body validationRequest: Single<FormSubmission>): Single<HttpResponse<ValidationResponseValid>> {
        return Single.just(HttpResponse.badRequest(
                ValidationResponseValid(
                        code = HttpStatus.BAD_REQUEST,
                        message = "Sorry you did this wrong"
                )
        ))
    }
}