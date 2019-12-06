package example.micronaut

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single

@Controller("/")
open class ValidateHelperController {

    @Post("/validate-helper")
    fun validationHelper(example: Map<String, String>): Single<HttpResponse<ValidationResponseValid>> {
        // Bug reports bad behavior with 400 response, so have this always return 400
        return Single.just(HttpResponse.badRequest(
                ValidationResponseValid(
                        code = HttpStatus.BAD_REQUEST,
                        message = "Sorry you did this wrong"
                )
        ))
    }
}