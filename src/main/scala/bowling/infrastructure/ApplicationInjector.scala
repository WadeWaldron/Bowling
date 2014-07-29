package bowling.infrastructure

import bowling.api.APIModule
import bowling.domain.DomainModule

class ApplicationInjector extends DomainModule with APIModule with InfrastructureModule
