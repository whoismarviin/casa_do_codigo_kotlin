package br.com.zup.cadastraAutor

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse

@Client("http://localhost:8081/cep/busca")
interface EnderecoCliente {

    @Get
    fun consulta(@QueryValue cep:String):HttpResponse<EnderecoResponse>

}
