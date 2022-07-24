package br.com.curso.http.fallback

import br.com.curso.dto.output.Veiculo
import br.com.curso.http.VeiculoHttp
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class VeiculoHttpFallBack(private val objectMapper: ObjectMapper): VeiculoHttp {
    override fun findById(id: Long): Veiculo {
        val jedisPool = JedisPool(JedisPoolConfig(), "127.0.0.1", 6379)
        val jedis = jedisPool.resource
        val veiculoJson = jedis.get(id.toString())
        val veiculo = objectMapper.readValue(veiculoJson,Veiculo::class.java)
        return veiculo
    }
}