package example.micronaut

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("\${formValidatorService.host}")
interface FormValidatorServiceClient {

    @Post("/validate-helper")
    fun validate(@Body validationRequest: Single<FormSubmission>): Single<HttpResponse<ValidationResponseValid>>

}