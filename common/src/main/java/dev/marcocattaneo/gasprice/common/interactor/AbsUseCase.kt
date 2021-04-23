package dev.marcocattaneo.gasprice.common.interactor

abstract class AbsUseCase<Input, Output> {

    abstract suspend fun execute(input: Input): Output

}
