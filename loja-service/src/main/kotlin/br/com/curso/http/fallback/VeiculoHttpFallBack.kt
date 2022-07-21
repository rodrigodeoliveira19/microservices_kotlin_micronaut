package br.com.curso.http.fallback

import br.com.curso.dto.output.Veiculo
import br.com.curso.http.VeiculoHttp
import io.micronaut.retry.annotation.Fallback

@Fallback
class VeiculoHttpFallBack: VeiculoHttp {
    override fun findById(id: Long): Veiculo {
        println("Classe: VeiculoHttpFallBack")
        return Veiculo(1,"ARGO","FIAT","PPP1111")
    }
}