package br.com.curso.service

import br.com.curso.dto.input.VendaInput
import br.com.curso.dto.output.Parcela
import br.com.curso.dto.output.Venda
import br.com.curso.http.VeiculoHttp
import jakarta.inject.Singleton
import java.time.LocalDate

@Singleton
class VendaService(
    private val veiculoService: VeiculoService
){

    fun realizarVenda(vendaInput: VendaInput) {
        val veiculo = veiculoService.getVeiculo(vendaInput.veiculo)
        var parcelas: List<Parcela> = ArrayList<Parcela>()
        var valorParcela = vendaInput.valor.divide(vendaInput.quantidadeParcelas.toBigDecimal())
        var dataVencimento = LocalDate.now().plusMonths(1)

        for(i in 1..vendaInput.quantidadeParcelas){
            var parcela = Parcela(valorParcela, dataVencimento.toString())
            parcelas = parcelas.plus(parcela)
            dataVencimento = dataVencimento.plusMonths(1)
        }

        var venda = Venda(vendaInput.cliente, veiculo, vendaInput.valor, parcelas)
        println(venda)
    }
}