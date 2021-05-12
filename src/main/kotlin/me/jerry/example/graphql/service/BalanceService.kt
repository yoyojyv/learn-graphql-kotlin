package me.jerry.example.graphql.service

import me.jerry.example.graphql.domain.bank.BankAccount
import me.jerry.example.graphql.support.Logger
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class BalanceService {

    companion object: Logger()

    fun getBalanceFor(bankAccountIds: Map<*, *>, userId: String): Map<UUID, BigDecimal> {
        logger.info("Requesting batch bank account ids: {} for user Id: {}", bankAccountIds, userId)

//        // Spring security context is propagated to dataloader threads (current executing thread)
//        logger.info("User ID: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal())

        // Original set of ids is available via
        val ids = bankAccountIds.keys
        /**
         * Part 29: VisualVM JVM Profiling
         * Set<BigDecimal> bigCrazy = new HashSet<>();
         * var size = ThreadLocalRandom.current().nextInt(250, 500);
         * var littleCrazy = new LinkedHashSet<BigDecimal>(size);
         * IntStream.range(0, size).forEach(nextInt -> littleCrazy.add(BigDecimal.valueOf(nextInt)));
         * bigCrazy.addAll(littleCrazy);
        </BigDecimal></BigDecimal> */
        return java.util.Map.of(
            UUID.fromString("c6aa269a-812b-49d5-b178-a739a1ed74cc"), BigDecimal.ONE,
            UUID.fromString("48e4a484-af2c-4366-8cd4-25330597473f"), BigDecimal("23431.22")
        )
    }
}
