package br.com.zup.cadastraAutor

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse

@Client("http://localhost:8081/cep/busca")
interface EnderecoCliente {

    @Get()
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    fun consulta(@QueryValue cep: String): HttpResponse<EnderecoResponse>

}
