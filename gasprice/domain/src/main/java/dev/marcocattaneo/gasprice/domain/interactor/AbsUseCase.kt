package dev.marcocattaneo.gasprice.domain.interactor

abstract class AbsUseCase<Input, Output> {

    abstract suspend fun execute(input: Input): Output

}