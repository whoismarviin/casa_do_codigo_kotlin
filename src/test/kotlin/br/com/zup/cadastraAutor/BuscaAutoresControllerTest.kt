package br.com.zup.cadastraAutor

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest{
    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client
    lateinit var client: HttpClient

    lateinit var autor:Autor


    @BeforeEach
    internal fun setup(){

        val enderecoResponse = EnderecoResponse("rua das laranjeiras","Rio de janeiro","Rj")
        val endereco= Endereco(enderecoResponse, "1928", "192293-888")
        autor= Autor(nome = "Vinicius",email = "vini.rosa.roo@gmail.com",endereco = endereco,
            descricao = "um conto fantubuloso sobre o amor e a duvida")
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown(){
        autorRepository.deleteAll()
    }


    @Test
    internal fun `deve buscar um autor quando o email estiver valido`(){
        val response = client.toBlocking().exchange("/autores?email=${autor.email}",DetalhesAutorResponse::class.java)
        assertEquals(HttpStatus.OK,response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome,response.body().autor!!.nome)
        assertEquals(autor.descricao,response.body().autor!!.descricao)
        assertEquals(autor.email,response.body().autor!!.email)
        assertEquals(autor.endereco,response.body().autor!!.endereco)
    }

}