package example.micronaut

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import io.reactivex.Single

@Validated
interface FormSubmissionOperations {

    @Post(value = "/validate", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun validate(@Body submission: Single<FormSubmission>): Single<HttpResponse<ValidationResponseValid>>
}
